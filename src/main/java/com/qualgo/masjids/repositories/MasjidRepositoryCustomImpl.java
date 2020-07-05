package com.qualgo.masjids.repositories;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.entities.Timing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class MasjidRepositoryCustomImpl implements MasjidRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Timing addTimingToMasjid(Masjid masjid) {
        return null;
    }
}
