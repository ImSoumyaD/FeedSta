package com.soumya.Social_Media_Project.controller;

import com.soumya.Social_Media_Project.models.User;
import com.soumya.Social_Media_Project.repo.UserRepo;
import com.soumya.Social_Media_Project.request.LoginRequest;
import com.soumya.Social_Media_Project.response.AuthResponse;
import com.soumya.Social_Media_Project.services.CustomUserDetailService;
import com.soumya.Social_Media_Project.services.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
        User isExists = userRepo.findByEmail(user.getEmail());
        if (isExists != null){
            throw new Exception("This email is already used with another account..");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);
        return new ResponseEntity<>("User successfully registered",HttpStatus.ACCEPTED);
    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        return new AuthResponse(token,"Login success");
    }
    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);

        if (userDetails == null){
            throw new BadCredentialsException("invalid email id..");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("wrong password..");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
