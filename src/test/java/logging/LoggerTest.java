package logging;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import com.nautilus.logging.Logger;
import com.nautilus.logging.PriorityLevel;
import com.nautilus.logging.TimestampedLogMessage;
import com.nautilus.logging.ILogMessageFactory;
import com.nautilus.logging.TimestampedLogMessageFactory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LoggerTest {
    private BlockingQueue<TimestampedLogMessage> queue;
    private ILogMessageFactory<TimestampedLogMessage> factory;

    private Logger<TimestampedLogMessage> logger;

    @Before
    public void initMocks() {
        queue = new PriorityBlockingQueue<TimestampedLogMessage>(10, (a, b) -> b.getPriority() - a.getPriority());
        factory = new TimestampedLogMessageFactory();
        logger = new Logger<>(queue, factory);
    }

    @Test
    public void shouldLogMessageWithLowPriority() {
        logger.log("Test message", PriorityLevel.LOW);
        assertEquals(queue.size(), 1);
    }

    @Test
    public void shouldLogMessageWithMediumPriority() {
        logger.log("Test message", PriorityLevel.MEDIUM);
        assertEquals(queue.size(), 1);
    }

    @Test
    public void shouldLogMessageWithHighPriority() {
        logger.log("Test message", PriorityLevel.HIGH);
        assertEquals(queue.size(), 1);
    }

    @Test
    public void shouldLogMultipleMessagesOfVaryingPriorities() {
        logger.log("Test message 1", PriorityLevel.LOW);
        logger.log("Test message 2", PriorityLevel.MEDIUM);
        logger.log("Test message 3", PriorityLevel.HIGH);
        assertEquals(queue.size(), 3);
    }

    @Test
    public void shouldBuildLogMessages() {
        logger.buildNewLogMessage()
        .append("Hello ")
        .append("World")
        .logMessage(PriorityLevel.HIGH);

        assertEquals(queue.size(), 1);
        assertEquals(queue.poll().getMessage(), "Hello World");
    }
}
