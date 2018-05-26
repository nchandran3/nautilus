package com.nautilus.logging;

import java.util.concurrent.BlockingQueue;

public class DefaultPriorityLogger<T extends ILogMessage> extends Logger<T> {
    private PriorityLevel defaultLevel;
    public DefaultPriorityLogger(BlockingQueue<T> queue, ILogMessageFactory<T> msgFactory, PriorityLevel defaultLevel) {
        super(queue, msgFactory);
        this.defaultLevel = defaultLevel;
    }

    public void log(String message) {
        this.log(message, defaultLevel);
    }

    @Override
    protected LogMessageBuilder getBuilderInstance() {
        return new DefaultPriorityLogMessageBuilder(this);
    }



    public class DefaultPriorityLogMessageBuilder extends LogMessageBuilder {
        protected DefaultPriorityLogger<T> logger;

        public DefaultPriorityLogMessageBuilder(DefaultPriorityLogger<T> logger) {
            super(logger);
        }

        public void logMessage() {
            this.logger.log(message.toString());
        }
    }
}
