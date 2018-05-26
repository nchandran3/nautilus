package com.nautilus.logging;
import java.io.Serializable;

public class LogMessage implements ILogMessage, Serializable {
    private static final long serialVersionUID = 7036942293365031403L;
	String msg;
    int level;

    public LogMessage(String msg, int level) {
        this.msg = msg;
        this.level = level;
    }

    public String getMessage() { return msg; }
    public int getPriority() { return level; }
}
