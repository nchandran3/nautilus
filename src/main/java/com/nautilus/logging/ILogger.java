package com.nautilus.logging;

public interface ILogger {
    /**
     * Logs a message
     */
    public void log(String msg, PriorityLevel level);
}
