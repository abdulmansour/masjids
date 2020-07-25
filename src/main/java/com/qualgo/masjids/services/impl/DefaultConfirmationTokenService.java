package com.qualgo.masjids.services.impl;

import com.qualgo.masjids.entities.ConfirmationToken;
import com.qualgo.masjids.repositories.ConfirmationTokenRepository;
import com.qualgo.masjids.services.ConfirmationTokenService;
import org.springframework.stereotype.Service;

@Service
public class DefaultConfirmationTokenService implements ConfirmationTokenService {

    //needed final
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
             confirmationTokenRepository.save(confirmationToken);
    }
}
