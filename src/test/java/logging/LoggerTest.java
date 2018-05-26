package logging;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import com.nautilus.logging.ILogMessage;
import com.nautilus.logging.Logger;
import com.nautilus.logging.PriorityLevel;
import com.nautilus.logging.ILogMessageFactory;
import com.nautilus.logging.LogMessageFactory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LoggerTest {
    private BlockingQueue<ILogMessage> queue;
    private ILogMessageFactory<ILogMessage> factory;

    private Logger<ILogMessage> logger;

    @Before
    public void initMocks() {
        queue = new PriorityBlockingQueue<ILogMessage>(10, (a, b) -> b.getPriority() - a.getPriority());
        factory = new LogMessageFactory();
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
