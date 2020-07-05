package com.qualgo.masjids.controllers;

import com.qualgo.masjids.entities.Masjid;
import com.qualgo.masjids.entities.Timing;
import com.qualgo.masjids.exceptions.InvalidTimingRangeException;
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

@CrossOrigin(origins = "http://localhost:4200")
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
     * @throws InvalidTimingRangeException
     */
    @PostMapping("/masjids/{masjidId}/timings")
    public ResponseEntity<Timing> addTimingToMasjid(@Valid @RequestBody Timing timingRequest, @PathVariable final Long masjidId) {
        Masjid masjid = masjidService.getMasjidById(masjidId);
        for (Timing timing:masjid.getTimings()) {
            if (timing.getPrayer().equals(timingRequest.getPrayer())) {
                if (!timingRequest.getStart().isBefore(timing.getStart()) && !timingRequest.getStart().isAfter(timing.getEnd())) {
                    throw new InvalidTimingRangeException("Timing request start (" + timingRequest.getStart() + ") is conflicting with timing " + timing);
                }
                if (!timingRequest.getEnd().isBefore(timing.getStart()) && !timingRequest.getEnd().isAfter(timing.getEnd())) {
                    throw new InvalidTimingRangeException("Timing request end (" + timingRequest.getEnd() + ") is conflicting with timing " + timing);
                }
            }
        }
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
        if (timingRequest.getStart() != null) {
            for (Timing t : masjid.getTimings()) {
                if (t.getId() == timingId)
                    continue;
                if (t.getPrayer().equals(timingRequest.getPrayer())) {
                    if (!timingRequest.getStart().isBefore(timing.getStart()) && !timingRequest.getStart().isAfter(timing.getEnd())) {
                        throw new InvalidTimingRangeException("Timing request start (" + timingRequest.getStart() + ") is conflicting with timing " + t);
                    }
                }
            }
            timing.setStart(timingRequest.getStart());
        }
        if (timingRequest.getEnd() != null) {
            for (Timing t : masjid.getTimings()) {
                if (t.getId() == timingId)
                    continue;
                if (t.getPrayer().equals(timingRequest.getPrayer())) {
                    if (!timingRequest.getEnd().isBefore(timing.getStart()) && !timingRequest.getEnd().isAfter(timing.getEnd())) {
                        throw new InvalidTimingRangeException("Timing request end (" + timingRequest.getEnd() + ") is conflicting with timing " + t);
                    }
                }
            }
            timing.setEnd(timingRequest.getEnd());
        }
        if (timingRequest.getDelay() != null)
            timing.setDelay(timingRequest.getDelay());
        if (timingRequest.getAtAdhan() != null)
            timing.setAtAdhan(timingRequest.getAtAdhan());

        Timing updatedTiming = timingService.persistTiming(timing);
        log.info("Updated Timing {} - Masjid {}", timingId, masjidId);

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
        Timing timing = timingService.getTimingById(timingId);
        timing.setMasjid(null);
        timingService.deleteTiming(timing);
        log.info("Deleted Timing {} - Masjid {}", timingId, masjidId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted timing " + timingId + " - Masjid " + masjidId, true);

        return ResponseEntity.ok(response);
    }

}
