package com.qualgo.masjids.services;

import com.qualgo.masjids.entities.Masjid;

import java.util.List;

public interface MasjidService {
    List<Masjid> getMasjids();
    Masjid getMasjidById(Long id);
    Masjid persistMasjid(Masjid masjid);
    void deleteMasjid(Masjid masjid);
}
