package com.qualgo.masjids.services;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.entities.Timing;

import java.time.LocalDate;
import java.util.List;

public interface MasjidService {
    List<Masjid> getMasjids();
    Masjid getMasjidById(Long id);
    Masjid persistMasjid(Masjid masjid);
    void deleteMasjid(Masjid masjid);
}
