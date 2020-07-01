package com.qualgo.masjids.controllers;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.services.MasjidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MasjidController {

    @Autowired
    private MasjidService masjidService;

    @GetMapping("/masjids")
    public ResponseEntity<List<Masjid>> getMasjids() {
        List<Masjid> masjids = masjidService.getMasjids();
        return ResponseEntity.ok(masjids);
    }
}
