package com.qualgo.masjids.services.impl;


import com.qualgo.masjids.entities.User;
import com.qualgo.masjids.entities.ConfirmationToken;


import com.qualgo.masjids.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;


@Service
    public class DefaultUserService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;
        @Autowired
        private DefaultConfirmationTokenService confirmationTokenService;
        @Autowired
        private  BCryptPasswordEncoder bCryptPasswordEncoder;
        //@Autowired
       // private ConfirmationToken confirmationToken;

        public User getUserByEmail(String email) {
            return userRepository.findUserByEmail(email);
        }

        public void signUpUser(User user) {
            final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            user.setEnabled(true);
            final User createdUser = userRepository.save(user);

            //beacause we wont use the confirmation token but if we use it we must make it after we check the token
            //user.setEnabled(true);


           // final ConfirmationToken confirmationToken = new ConfirmationToken(user);

            //confirmationTokenService.saveConfirmationToken(confirmationToken);


        }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByEmail(email));

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }

        /*
        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            final Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByEmail(email));

            if (optionalUser.isPresent()) {
                return (UserDetails) optionalUser.get();
            }
            else {
                throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
            }
        }
        */

    }

