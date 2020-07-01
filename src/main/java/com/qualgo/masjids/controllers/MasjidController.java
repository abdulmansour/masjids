package com.qualgo.masjids.controllers;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.exceptions.MasjidNotFoundException;
import com.qualgo.masjids.services.MasjidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MasjidController {

    Logger log = LoggerFactory.getLogger(MasjidController.class);

    @Autowired
    private MasjidService masjidService;

    /**
     * Get all masjids
     *
     * @return list of masjids
     */
    @GetMapping("/masjids")
    public ResponseEntity<List<Masjid>> getMasjids() {
        List<Masjid> masjids = masjidService.getMasjids();
        return ResponseEntity.ok(masjids);
    }

    /**
     * Get masjid by masjidId
     *
     * @param masjidId
     * @return requested masjid
     * @throws MasjidNotFoundException extends RuntimeException
     */
    @GetMapping("/masjids/{masjidId}")
    public ResponseEntity<Masjid> getMasjidById(@PathVariable long masjidId) {
        Masjid masjid = masjidService.getMasjidById(masjidId);
        log.info("Masjid info: {}", masjid);
        return ResponseEntity.ok(masjid);
    }
}
