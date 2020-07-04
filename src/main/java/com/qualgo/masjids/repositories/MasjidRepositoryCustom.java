package com.qualgo.masjids.repositories;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.entities.Timing;

public interface MasjidRepositoryCustom {
    Timing addTimingToMasjid(Masjid masjid);
}
