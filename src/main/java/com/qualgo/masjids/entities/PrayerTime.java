package com.qualgo.masjids.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qualgo.masjids.enums.Prayer;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "masjid_prayer_times")
public class PrayerTime {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "prayer")
    @Enumerated(EnumType.STRING)
    private Prayer prayer;

    @Column(name = "time")
    private LocalTime time;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="masjid_id", nullable = false)
    private Masjid masjid;

    public PrayerTime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Prayer getPrayer() {
        return prayer;
    }

    public void setPrayer(Prayer prayer) {
        this.prayer = prayer;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Masjid getMasjid() {
        return masjid;
    }

    public void setMasjid(Masjid masjid) {
        this.masjid = masjid;
    }
}
