package np.com.debid.restrouserservice.service;

import np.com.debid.restrocommons.exception.CustomException;
import np.com.debid.restrocommons.util.ResponseUtil;
import np.com.debid.restrouserservice.dto.RegisterRequest;
import np.com.debid.restrouserservice.dto.UserResponse;
import np.com.debid.restrouserservice.entity.Role;
import np.com.debid.restrouserservice.entity.User;
import np.com.debid.restrouserservice.entity.UserDTO;
import np.com.debid.restrouserservice.repository.RoleRepository;
import np.com.debid.restrouserservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static np.com.debid.restrouserservice.constant.Constant.ErrorCodes.EMAIL_IS_ALREADY_TAKEN_ERROR_CODE;
import static np.com.debid.restrouserservice.constant.Constant.ErrorCodes.ROLE_NOT_FOUND_ERROR_CODE;
import static np.com.debid.restrouserservice.constant.Constant.ErrorCodes.USERNAME_IS_ALREADY_TAKEN_ERROR_CODE;
import static np.com.debid.restrouserservice.constant.Constant.Messages.EMAIL_IS_ALREADY_TAKEN;
import static np.com.debid.restrouserservice.constant.Constant.Messages.ROLE_NOT_FOUND;
import static np.com.debid.restrouserservice.constant.Constant.Messages.USERNAME_IS_ALREADY_TAKEN;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;

    /**
     * Method to create new user
     *
     * @param registerRequest user detail from client
     * @return success message or error message
     */
    public User create(RegisterRequest registerRequest) {
        //Check Username and Email is already present or not
        if (userRepository.existsByUsername(registerRequest.getUsername().trim())) {
            if (userRepository.existsByEmail(registerRequest.getEmail().trim())) {
                throw new CustomException(EMAIL_IS_ALREADY_TAKEN, 400, EMAIL_IS_ALREADY_TAKEN_ERROR_CODE);
            }
            throw new CustomException(USERNAME_IS_ALREADY_TAKEN, 400, USERNAME_IS_ALREADY_TAKEN_ERROR_CODE);
        }
        // Create new user's account
        User user = new User(registerRequest.getUsername(), registerRequest.getEmail(), passwordEncoder.encode(registerRequest.getPassword()));
        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            String roleName = role.trim().toUpperCase();
            Role verifiedRole = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new CustomException(ROLE_NOT_FOUND, 404, ROLE_NOT_FOUND_ERROR_CODE));
            roles.add(verifiedRole);
        });
        user.setRoles(roles);
       return userRepository.save(user);
    }

    public String readAuthenticatedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String user = "";
        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        }
        return user;
    }

    public ResponseEntity<?> readAllUsers() {
        List<UserResponse> users = userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRoles()))
                .collect(Collectors.toList());
        return ResponseUtil.successResponse("Users fetched successfully!", users);
    }

    /**
     * Method to delete existing user
     *
     * @param id from client
     * @return success message or exception
     */
    public ResponseEntity<?> deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("Error: User not found for this id : " + id));
        userRepository.delete(user);
        return ResponseUtil.successResponse("User deleted successfully!");
    }

    public UserDTO getUserById(Long username) {
        User user =  userRepository.findById(username)
                .orElseThrow(() -> new CustomException("Error: User Not Found for the username: " + username));
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
}
