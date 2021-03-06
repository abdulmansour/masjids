package com.qualgo.masjids.controllers;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.exceptions.MasjidNotFoundException;
import com.qualgo.masjids.services.MasjidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
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
     * Get all masjids for timings that are between a specified date
     *
     * @param date
     * @return list of masjids
     */
    @GetMapping("/masjids/date/{date}")
    public ResponseEntity<List<Masjid>> getMasdjidsByDate(@PathVariable final String date) {
        LocalDate currentDate = LocalDate.parse(date);

        List<Masjid> masjids = masjidService.getMasjids();

        for (Masjid masjid:masjids) {
            masjid.getTimings().removeIf(timing -> currentDate.isBefore(timing.getStart()) || currentDate.isAfter(timing.getEnd()));
        }
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
    public ResponseEntity<Masjid> getMasjidById(@PathVariable final Long masjidId) {
        Masjid masjid = masjidService.getMasjidById(masjidId);
        log.info("Masjid info: {}", masjid);
        return ResponseEntity.ok(masjid);
    }

    /**
     * Get a masjid for timings that are between a specified date
     *
     * @param masjidId
     * @param date
     * @return masjid
     */
    @GetMapping("/masjids/{masjidId}/date/{date}")
    public ResponseEntity<Masjid> getMasdjidByDate(@PathVariable final Long masjidId, @PathVariable final String date) {
        LocalDate currentDate = LocalDate.parse(date);

        Masjid masjid = masjidService.getMasjidById(masjidId);

        masjid.getTimings().removeIf(timing -> currentDate.isBefore(timing.getStart()) || currentDate.isAfter(timing.getEnd()));

        return ResponseEntity.ok(masjid);
    }

    /**
     * Create a new masjid
     *
     * @param masjidRequest
     * @return newly created masjid with URI location attached in header
     */
    @PostMapping("/masjids")
    public ResponseEntity<Masjid> saveMasjid(@Valid @RequestBody final Masjid masjidRequest) {
        Masjid newMasjid = masjidService.persistMasjid(masjidRequest);
        log.info("New Masjid info: {}", newMasjid);

        //* Attach newly created masjid URI to header of response as per HTTP/2.2 */
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newMasjid.getId())
                .toUri();

        return ResponseEntity.created(location).body(newMasjid);
    }

    /**
     * Update a masjid name, address or timings - by id
     *
     * @param masjidRequest
     * @param masjidId
     * @return
     */
    @PutMapping("/masjids/{masjidId}")
    public ResponseEntity<Masjid> updateMasjid(@Valid @RequestBody final Masjid masjidRequest, @PathVariable(value = "masjidId") final Long masjidId) {
        Masjid masjid = masjidService.getMasjidById(masjidId);

        if (masjidRequest.getName() != null)
            masjid.setName(masjidRequest.getName());
        if (masjidRequest.getAddressLine() != null)
            masjid.setAddressLine(masjidRequest.getAddressLine());
        if (masjidRequest.getState() != null)
            masjid.setState(masjidRequest.getState());
        if (masjidRequest.getCity() != null)
            masjid.setCity(masjidRequest.getCity());
        if (masjidRequest.getZip() != null)
            masjid.setZip(masjidRequest.getZip());
        if (masjidRequest.getCountry() != null)
            masjid.setCountry(masjidRequest.getCountry());
        if (masjidRequest.getPhoneNumber() != null)
            masjid.setPhoneNumber(masjidRequest.getPhoneNumber());
        if (masjidRequest.getZoneId() != null)
            masjid.setZoneId(masjidRequest.getZoneId());
        if (masjidRequest.getLatitude() != null)
            masjid.setLatitude(masjidRequest.getLatitude());
        if (masjidRequest.getLongitude() != null)
            masjid.setLongitude(masjidRequest.getLongitude());
        if (masjidRequest.getWebsite() != null)
            masjid.setWebsite(masjidRequest.getWebsite());
        if (masjidRequest.getFacebook() != null)
            masjid.setFacebook(masjidRequest.getFacebook());
        if (masjidRequest.getInstagram() != null)
            masjid.setInstagram(masjidRequest.getInstagram());

        Masjid updatedMasjid = masjidService.persistMasjid(masjid);
        log.info("Updated Masjid info: {}", masjid);

        return ResponseEntity.ok(updatedMasjid);
    }

    /**
     * Delete a masjid by id
     *
     * @param masjidId to be deleted
     * @return custom response to confirm deletion
     */
    @DeleteMapping("/masjids/{masjidId}")
    public ResponseEntity deleteMasjid(@PathVariable(value = "masjidId") Long masjidId) {
        Masjid masjid = masjidService.getMasjidById(masjidId);
        masjidService.deleteMasjid(masjid);
        log.info("Deleted Masjid info: {}", masjid);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted masjid with id - " + masjid.getId(), true);
        return ResponseEntity.ok(response);
    }


}
