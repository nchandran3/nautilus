package logging;

import static org.junit.Assert.*;

import java.util.PriorityQueue;

import com.nautilus.logging.LogMessageComparator;
import com.nautilus.logging.TimestampedLogMessage;

import org.junit.Before;
import org.junit.Test;

public class LogMessageComparatorTest {
    private PriorityQueue<TimestampedLogMessage> queue;

    @Before
    public void init() {
        queue = new PriorityQueue<TimestampedLogMessage>(10, new LogMessageComparator());
    }

    @Test
    public void shouldOrderLogMessagesByPriority() {
        TimestampedLogMessage one = new TimestampedLogMessage("", 3);
        TimestampedLogMessage two = new TimestampedLogMessage("", 2);
        TimestampedLogMessage three = new TimestampedLogMessage("", 1);
        queue.add(three);
        queue.add(two);
        queue.add(one);

        assertEquals(queue.poll(), one);
        assertEquals(queue.poll(), two);
        assertEquals(queue.poll(), three);
    }

    @Test
    public void shouldOrderByCreationDateOfEquivalentPriorityMessages() {
        TimestampedLogMessage one = new TimestampedLogMessage("", 1);
        try {Thread.sleep(1);} catch (InterruptedException e) {}
        TimestampedLogMessage two = new TimestampedLogMessage("", 1);

        queue.add(two);
        queue.add(one);

        assertEquals(queue.poll(), one);
        assertEquals(queue.poll(), two);
    }
}
