package com.qualgo.masjids.services;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.entities.Timing;

import java.util.List;

public interface TimingService {
    List<Timing> getTimings();
    Timing getTimingById(Long id);
    Timing persistTiming(Timing timing);
    void delTiming(Timing timing);
}
