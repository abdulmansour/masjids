package com.qualgo.masjids.services;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.entities.Timing;

import java.util.List;

public interface TimingService {
    List<Timing> getTimings();
    Timing getTimingById(Long id);
    Timing persistTiming(Timing timing);
    void deleteTiming(Timing timing);
    Timing findTimingByIdAndMasjid(Long timingId, Masjid masjid);
    void deleteTimingByIdAndMasjid(Long timingId, Masjid masjid);
}
