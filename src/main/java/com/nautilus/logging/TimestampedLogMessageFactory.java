package com.nautilus.logging;

public class TimestampedLogMessageFactory implements ILogMessageFactory<TimestampedLogMessage> {

    @Override
    public TimestampedLogMessage generateLogMessage(String msg, PriorityLevel level) {
        if (msg == null || msg.equals("")) { return null; }
        return new TimestampedLogMessage(msg, level.getValue());
    }
}
