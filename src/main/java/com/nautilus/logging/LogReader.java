package com.nautilus.logging;

import java.util.concurrent.BlockingQueue;

public class LogReader<T> {

    private BlockingQueue<T> queue;
    public LogReader(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    /**
     * Gets the next message from the queue, or returns null if none exists
     * @return The next ILogMessage object that is available in the queue, according to its ordering.
     */
    public T get() {
        return queue.poll();
    }
}
