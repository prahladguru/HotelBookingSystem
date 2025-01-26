package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Country;
import com.airbnb.exception.UserExists;
import com.airbnb.payload.JWTToken;
import com.airbnb.payload.LoginDto;
import com.airbnb.repository.AppUserRepository;
import com.airbnb.repository.CountryRepository;
import com.airbnb.service.Impl.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AppUserRepository appUserRepository;
    private final AppUserServiceImpl appUserService;
    @Autowired
    private CountryRepository countryRepository;

    public AuthController(AppUserRepository appUserRepository, AppUserServiceImpl appUserService) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

    @PostMapping("/createuser")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());

        if (opEmail.isPresent()) {
            throw new UserExists("Email Id Exists");
        }

        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());

        if (opUsername.isPresent()) {
            throw new UserExists("Username Exists");
        }
        user.setRole("ROLE_USER");
        AppUser saved = appUserService.createUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/createpropertyowner")
    public ResponseEntity<AppUser> propertyOwner(@RequestBody AppUser user) {
        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());

        if (opEmail.isPresent()) {
            throw new UserExists("Email Id Exists");
        }

        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());

        if (opUsername.isPresent()) {
            throw new UserExists("Username Exists");
        }
        user.setRole("ROLE_OWNER");
        AppUser saved = appUserService.createUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    //crete property
    @PostMapping("/createpropertymanager")
    public ResponseEntity<AppUser> createPropertyManager(@RequestBody AppUser user) {
        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());

        if (opEmail.isPresent()) {
            throw new UserExists("Email Id Exists");
        }

        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());

        if (opUsername.isPresent()) {
            throw new UserExists("Username Exists");
        }
        user.setRole("ROLE_MANAGER");
        AppUser saved = appUserService.createUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @ExceptionHandler(UserExists.class)
    public ResponseEntity<String> handleUserExistsException(UserExists ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginDto loginDto) {
        String token = appUserService.verifyLogin(loginDto);
        JWTToken jwttoken = new JWTToken();
        if (token != null) {
            jwttoken.setTokenType("JWT");
            jwttoken.setToken(token);
            return new ResponseEntity<>(jwttoken, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("invalid username/password", HttpStatus.UNAUTHORIZED);
        }
    }



}
