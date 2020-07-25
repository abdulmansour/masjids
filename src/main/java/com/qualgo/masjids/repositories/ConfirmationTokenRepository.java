package com.qualgo.masjids.repositories;


import com.qualgo.masjids.entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
}
