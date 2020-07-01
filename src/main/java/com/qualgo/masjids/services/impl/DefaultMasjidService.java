package com.qualgo.masjids.services.impl;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.exceptions.MasjidNotFoundException;
import com.qualgo.masjids.repositories.MasjidRepository;
import com.qualgo.masjids.services.MasjidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMasjidService implements MasjidService {

    @Autowired
    private MasjidRepository masjidRepository;

    @Override
    public List<Masjid> getMasjids() {
        return masjidRepository.findAll();
    }

    @Override
    public Masjid getMasjidById(Long masjidId) {
        return masjidRepository.findById(masjidId).orElseThrow(() -> new MasjidNotFoundException("Employee not found for id: " + masjidId));

    }

    @Override
    public Masjid persistMasjid(Masjid masjid) {
        return masjidRepository.save(masjid);
    }

    @Override
    public void deleteMasjid(Masjid masjid) {
        masjidRepository.delete(masjid);
    }
}
