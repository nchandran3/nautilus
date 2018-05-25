package com.nautilus.logging;

import java.util.Date;

public class TimestampedLogMessage extends LogMessage {
    protected Date creationDate;
    public TimestampedLogMessage(String msg, int level) {
        super(msg, level);
        creationDate = new Date();
    }

    public Date getCreationDate() { return creationDate; }
}
