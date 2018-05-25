package logging;

import static org.junit.Assert.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import com.nautilus.logging.LogReader;

import org.junit.Before;
import org.junit.Test;

public class LogReaderTest {
    private LogReader<Integer> reader;

    private BlockingQueue<Integer> queue;

    @Before
    public void init() {
        queue = new PriorityBlockingQueue<Integer>();
        reader = new LogReader<Integer>(queue);
    }

    @Test
    public void shouldReturnNullWhenQueueIsEmptyOnGet() {
        assertNull(reader.get());
    }

    @Test
    public void shouldReturnTopItemInQueueWhenGetIsCalled() {
        Integer added = 5;
        queue.add(added);
        assertEquals(reader.get(), added);
    }

    @Test
    public void shouldReturnItemsInCorrectOrderOnMultipleGetCalls() {
        Integer added1 = 1;
        Integer added2 = 2;
        Integer added3 = 3;
        queue.add(3);
        queue.add(2);
        queue.add(1);
        assertEquals(reader.get(), added1);
        assertEquals(reader.get(), added2);
        assertEquals(reader.get(), added3);
    }

}
