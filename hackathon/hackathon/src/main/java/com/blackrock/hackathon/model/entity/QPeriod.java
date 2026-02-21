package com.blackrock.hackathon.model.entity;

import java.time.LocalDateTime;

public class QPeriod {
    private double fixed;

    private LocalDateTime start;

    private LocalDateTime end;

    public double getFixed() { return fixed; }
    public void setFixed(double fixed) { this.fixed = fixed; }
    public LocalDateTime getStart() { return start; }
    public void setStart(LocalDateTime start) { this.start = start; }
    public LocalDateTime getEnd() { return end; }
    public void setEnd(LocalDateTime end) { this.end = end; }
}
