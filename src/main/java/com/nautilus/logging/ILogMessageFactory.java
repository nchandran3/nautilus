package com.nautilus.logging;

public interface ILogMessageFactory<T extends ILogMessage> {
    /**
     * Generates a log message object given a message and priority level
     * @param msg The msg value
     * @param priorityLevel The priorityLevel of the message
     * @return The generated ILogMessage object
     */
    T generateLogMessage(String msg, PriorityLevel priorityLevel);
}
