package com.qualgo.masjids.repositories;

import com.qualgo.masjids.entities.Timing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class TimingRepositoryCustomImpl implements TimingRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

}
