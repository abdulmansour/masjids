package com.qualgo.masjids.services;

import com.qualgo.masjids.entities.Timing;
import com.qualgo.masjids.entities.User;

public interface UserService {
    User getUserByEmail(String email);
    void signUpUser(User user);

}
