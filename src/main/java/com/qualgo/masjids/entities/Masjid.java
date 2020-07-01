package com.qualgo.masjids.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "masjids")
public class Masjid {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "masjid", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PrayerTime> prayerTimes;

    public Masjid() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PrayerTime> getPrayerTimes() {
        return prayerTimes;
    }

    public void setPrayerTimes(List<PrayerTime> prayerTimes) {
        this.prayerTimes = prayerTimes;
    }

    @Override
    public String toString() {
        return "Masjid{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
