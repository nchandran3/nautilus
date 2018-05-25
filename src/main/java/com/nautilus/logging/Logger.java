package com.nautilus.logging;
import java.util.concurrent.BlockingQueue;

public class Logger<T extends ILogMessage> implements ILogger {
    private BlockingQueue<T> queue;
    private ILogMessageFactory<T> msgFactory;

    /**
     * Creates a logger that inserts objects into a queue
     */
    public Logger(BlockingQueue<T> queue, ILogMessageFactory<T> msgFactory) {
        this.queue = queue;
        this.msgFactory = msgFactory;
    }

    /**
     * Logs the given message to the queue, ordered by the priority level
     * @param msg the message to log
     * @param level the priority level of the message
     */
    public void log(String msg, PriorityLevel level) {
        T message = this.msgFactory.generateLogMessage(msg, level);
        queue.add(message);
    }
}
