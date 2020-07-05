package com.qualgo.masjids.repositories;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.entities.Timing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimingRepository extends JpaRepository<Timing, Long>, TimingRepositoryCustom {
    Timing findTimingByIdAndMasjid(Long timingId, Masjid masjid);
    void deleteTimingByIdAndMasjid(Long timingId, Masjid masjid);
}
