package com.nautilus.logging;
import java.util.Comparator;

public class LogMessageComparator implements Comparator<TimestampedLogMessage> {
	@Override
	public int compare(TimestampedLogMessage o1, TimestampedLogMessage o2) {
        if (o1.getPriority() == o2.getPriority()) {
            long o1CreationTime = o1.getCreationDate().getTime();
            long o2CreationTime = o2.getCreationDate().getTime();
            return o1CreationTime < o2CreationTime ? -1 : 1;
        }

        return o2.getPriority() -  o1.getPriority();
	}
}

