package np.com.debid.restroauthservice.controller;

import np.com.debid.restroauthservice.service.AuthService;
import np.com.debid.restroauthservice.service.UserService;
import np.com.debid.restrocommons.util.ResponseUtil;
import np.com.debid.restrocommons.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth0")
public class AuthoController {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @GetMapping("/status")
    public ResponseEntity<ResponseWrapper<String>> getAuth() {
        return ResponseUtil.successResponse("Auth Service is up and running");
    }

}
