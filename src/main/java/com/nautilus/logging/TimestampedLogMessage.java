package com.nautilus.logging;

import java.util.Date;

public class TimestampedLogMessage extends LogMessage {
	private static final long serialVersionUID = -1281988091886602074L;
	protected Date creationDate;
    public TimestampedLogMessage(String msg, int level) {
        super(msg, level);
        creationDate = new Date();
    }

    public Date getCreationDate() { return creationDate; }
}
