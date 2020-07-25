package com.qualgo.masjids.repositories;


import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
        User findUserByEmail(String email);
    }


