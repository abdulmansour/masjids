package com.qualgo.masjids.repositories;

import com.qualgo.masjids.entities.Timing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimingRepository extends JpaRepository<Timing, Long>, TimingRepositoryCustom {
}
