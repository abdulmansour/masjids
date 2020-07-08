package com.qualgo.masjids.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qualgo.masjids.enums.Prayer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name = "timings")
public class Timing {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prayer")
    @Enumerated(EnumType.STRING)
    private Prayer prayer;

    @Column(name = "time")
    private LocalTime time;

    //if true, fetch the prayer time using the te adhan api
    @Column(name = "is_at_adhan")
    private Boolean isAtAdhan;

    //to add a delay in minutes to the time (intention to be used if say prayer time is 10mins after adhan);
    @Column(name = "delay")
    private Long delay;

    @Column(name = "start")
    private LocalDate start;

    @Column(name = "end")
    private LocalDate end;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="masjids_timings",
            joinColumns = @JoinColumn(name = "timing_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "masjid_id", referencedColumnName = "id")
    )
    private Masjid masjid;

    public Timing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getAtAdhan() {
        return isAtAdhan;
    }

    public void setAtAdhan(Boolean atAdhan) {
        isAtAdhan = atAdhan;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Masjid getMasjid() {
        return masjid;
    }

    public void setMasjid(Masjid masjid) {
        this.masjid = masjid;
    }

    @Override
    public String toString() {
        return "Timing{" +
                "id=" + id +
                ", prayer=" + prayer +
                ", time=" + time +
                ", isAtAdhan=" + isAtAdhan +
                ", delay=" + delay +
                ", start=" + start +
                ", end=" + end +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
