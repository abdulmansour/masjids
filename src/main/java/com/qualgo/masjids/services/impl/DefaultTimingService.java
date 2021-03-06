package com.qualgo.masjids.services.impl;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.entities.Timing;
import com.qualgo.masjids.exceptions.TimingNotFoundException;
import com.qualgo.masjids.repositories.TimingRepository;
import com.qualgo.masjids.services.TimingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultTimingService implements TimingService {

    @Autowired
    private TimingRepository timingRepository;

    @Override
    public List<Timing> getTimings() {
        return timingRepository.findAll();
    }

    @Override
    public Timing getTimingById(Long id) {
        return timingRepository.findById(id).orElseThrow(() -> new TimingNotFoundException("Timing not found for id: " + id));
    }

    @Override
    public Timing persistTiming(Timing timing) {
        return timingRepository.save(timing);
    }

    @Override
    public void deleteTiming(Timing timing) {
        timingRepository.delete(timing);
    }

    @Override
    public Timing findTimingByIdAndMasjid(Long timingId, Masjid masjid) {
        return timingRepository.findTimingByIdAndMasjid(timingId, masjid);
    }

    @Override
    @Transactional
    public void deleteTimingByIdAndMasjid(Long timingId, Masjid masjid) {
        timingRepository.deleteTimingByIdAndMasjid(timingId, masjid);
    }


}
