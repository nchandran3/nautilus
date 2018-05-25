package com.nautilus.logging;

public class LogMessageFactory implements ILogMessageFactory<ILogMessage> {

    @Override
    public ILogMessage generateLogMessage(String msg, PriorityLevel level) {
        if (msg == null || msg.equals("")) { return null; }
        return new LogMessage(msg, level.getValue());
    }
}
