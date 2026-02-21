package com.blackrock.hackathon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.*;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/blackrock/challenge/v1/performance")
public class PerformanceController {

    @GetMapping
    public Map<String, Object> getPerformanceMetrics() {

        LinkedHashMap<String, Object> metrics = new LinkedHashMap<>();

        // Time (system uptime in seconds)
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        long uptimeMillis = runtimeBean.getUptime();
        metrics.put("time", uptimeMillis);

        // Memory Usage
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        double usedMemoryMB = heapMemoryUsage.getUsed() / (1024.0 * 1024.0); // Convert bytes to MB
        metrics.put("memory", String.format("%.2f MB", usedMemoryMB));

        // Thread Count
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        int threadCount = threadMXBean.getThreadCount();
        metrics.put("threads", threadCount);

        return metrics;
    }
}