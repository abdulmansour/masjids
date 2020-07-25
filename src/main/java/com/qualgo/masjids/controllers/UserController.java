package com.qualgo.masjids.controllers;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.entities.Timing;
import com.qualgo.masjids.entities.User;
import com.qualgo.masjids.exceptions.InvalidTimingRangeException;
import com.qualgo.masjids.services.MasjidService;
import com.qualgo.masjids.services.TimingService;
import com.qualgo.masjids.services.UserService;
import com.qualgo.masjids.services.impl.DefaultUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private DefaultUserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> refisterUser(@Valid @RequestBody final User user) {
        userService.signUpUser(user);
        return  ResponseEntity.ok(user);
    }
    @PostMapping("/login")
    public ResponseEntity<User> logIn(@Valid @RequestBody final User user) {

        return ResponseEntity.ok(user);
    }
}