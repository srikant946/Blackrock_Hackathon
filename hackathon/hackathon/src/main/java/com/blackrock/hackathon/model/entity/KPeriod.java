package com.blackrock.hackathon.model.entity;

import java.time.LocalDateTime;

public class KPeriod {
    private LocalDateTime start;

    private LocalDateTime end;

    public LocalDateTime getStart() { return start; }
    public void setStart(LocalDateTime start) { this.start = start; }
    public LocalDateTime getEnd() { return end; }
    public void setEnd(LocalDateTime end) { this.end = end; }
}
