package com.qualgo.masjids.repositories;

import com.qualgo.masjids.entities.Masjid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasjidRepository extends JpaRepository<Masjid, Long> {
}
