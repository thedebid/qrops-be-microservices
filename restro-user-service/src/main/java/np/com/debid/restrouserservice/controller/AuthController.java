package np.com.debid.restrouserservice.controller;

import jakarta.validation.Valid;
import np.com.debid.restrocommons.util.ResponseUtil;
import np.com.debid.restrocommons.util.ResponseWrapper;
import np.com.debid.restrouserservice.dto.LoginRequest;
import np.com.debid.restrouserservice.dto.LoginResponse;
import np.com.debid.restrouserservice.dto.RegisterRequest;
import np.com.debid.restrouserservice.service.AuthService;
import np.com.debid.restrouserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static np.com.debid.restrouserservice.constant.Constant.Messages.LOGIN_SUCCESS;
import static np.com.debid.restrouserservice.constant.Constant.Messages.USER_CREATED;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<LoginResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseUtil.successResponse(LOGIN_SUCCESS, authService.authenticateLoginRequest(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseUtil.successResponse(USER_CREATED, userService.create(request));
    }
}
