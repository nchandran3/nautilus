package com.nautilus.logging;
import java.util.concurrent.BlockingQueue;

/**
 * Logger that inserts log message type objects into a blocking queue
 */
public class Logger<T extends ILogMessage> implements ILogger {
    private BlockingQueue<T> queue;
    private ILogMessageFactory<T> msgFactory;

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

    /**
     * Creates a log message by providing chaining functionality for consumer readability
     * @return The builder that will handle operations
     */
    public LogMessageBuilder buildNewLogMessage() {
        return getBuilderInstance();
    }

    /**
     * Gets an instance of the builder class; should be overriden by subclasses that modify building or logging functionality.
     * @return The LogMessageBuilder instance
     */
    protected LogMessageBuilder getBuilderInstance() {
        return new LogMessageBuilder(this);
    }



    public class LogMessageBuilder {
		protected Logger<T> logger;
        protected StringBuilder message;

        public LogMessageBuilder(Logger<T> logger) {
            this.logger = logger;
            message = new StringBuilder();
        }

        public LogMessageBuilder append(String s) {
            message.append(s);
            return this;
        }

        public void logMessage(PriorityLevel level) {
            this.logger.log(message.toString(), level);
        }
    }
}
