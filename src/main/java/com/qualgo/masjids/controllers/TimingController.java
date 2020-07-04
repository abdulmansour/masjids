package com.qualgo.masjids.controllers;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.entities.Timing;
import com.qualgo.masjids.services.MasjidService;
import com.qualgo.masjids.services.TimingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TimingController {

    @Autowired
    private MasjidService masjidService;

    @Autowired
    private TimingService timingService;
    Logger log = LoggerFactory.getLogger(TimingController.class);

    /**
     * Get timings for a specific masjid
     *
     * @param masjidId
     * @return list of timings
     */
    @GetMapping("/masjids/{masjidId}/timings")
    public ResponseEntity<List<Timing>> getTimingsByMasjid(@PathVariable final Long masjidId) {
        Masjid masjid = masjidService.getMasjidById(masjidId);

        return ResponseEntity.ok(masjid.getTimings());
    }

    /**
     * Save a timing to a masjid
     *
     * @param timingRequest
     * @param masjidId
     * @return newly created timing
     */
    @PostMapping("/masjids/{masjidId}/timings")
    public ResponseEntity<Timing> addTimingToMasjid(@Valid @RequestBody Timing timingRequest, @PathVariable final Long masjidId) {
        Masjid masjid = masjidService.getMasjidById(masjidId);
        timingRequest.setMasjid(masjid);
        Timing newTiming = timingService.persistTiming(timingRequest);
        log.info("New timing added for masjid - {}: {}", masjidId, newTiming);

        return ResponseEntity.ok(newTiming);
    }

    /**
     * Update timing
     *
     * @param timingRequest
     * @param timingId
     * @param masjidId
     * @return updatedTIming
     */
    @PutMapping("masjids/{masjidId}/timings/{timingId}")
    public ResponseEntity<Timing> updateTiming(@Valid @RequestBody final Timing timingRequest, @PathVariable final Long timingId, @PathVariable final Long masjidId) {
        Masjid masjid = masjidService.getMasjidById(masjidId);
        Timing timing = timingService.getTimingById(timingId);

        if (timingRequest.getPrayer() != null)
            timing.setPrayer(timingRequest.getPrayer());
        if (timingRequest.getTime() != null)
            timing.setTime(timingRequest.getTime());
        if (timingRequest.getStart() != null)
            timing.setStart(timingRequest.getStart());
        if (timingRequest.getEnd() != null)
            timing.setEnd(timingRequest.getEnd());
        if (timingRequest.getDelay() != null)
            timing.setDelay(timingRequest.getDelay());
        if (timingRequest.getAtAdhan() != null)
            timing.setAtAdhan(timingRequest.getAtAdhan());

        Timing updatedTiming = timingService.persistTiming(timing);
        log.info("Updated Timing for masjid - {}: {}", masjidId, updatedTiming);

        return ResponseEntity.ok(updatedTiming);
    }

    /**
     * Delete a timing
     *
     * @param timingId
     * @return response of deletion confirmation
     * @throws com.qualgo.masjids.exceptions.TimingNotFoundException
     */
    @DeleteMapping("/masjids/{masjidId}/timings/{timingId}")
    public ResponseEntity deleteTiming(@PathVariable final Long timingId, @PathVariable final Long masjidId) {
        Masjid masjid = masjidService.getMasjidById(masjidId);
        Timing timing = timingService.getTimingById(timingId);
        timingService.delTiming(timing);
        log.info("Deleted Timing info: {}", timing);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted timing with id - " + timingId, true);

        return ResponseEntity.ok(response);
    }

}
