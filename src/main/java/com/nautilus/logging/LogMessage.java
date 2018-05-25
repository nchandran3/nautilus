package com.nautilus.logging;

public class LogMessage implements ILogMessage {
    String msg;
    int level;

    public LogMessage(String msg, int level) {
        this.msg = msg;
        this.level = level;
    }

    public String getMessage() { return msg; }
    public int getPriority() { return level; }
}
