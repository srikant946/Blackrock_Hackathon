package com.blackrock.hackathon.model.entity;

import java.time.LocalDateTime;

public class PPeriod {
    private double extra;

    private LocalDateTime start;

    private LocalDateTime end;

    public double getExtra() { return extra; }
    public void setExtra(double extra) { this.extra = extra; }
    public LocalDateTime getStart() { return start; }
    public void setStart(LocalDateTime start) { this.start = start; }
    public LocalDateTime getEnd() { return end; }
    public void setEnd(LocalDateTime end) { this.end = end; }
}
