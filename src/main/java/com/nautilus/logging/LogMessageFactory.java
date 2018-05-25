package com.nautilus.logging;

public class LogMessageFactory implements ILogMessageFactory<ILogMessage> {

    @Override
    public ILogMessage generateLogMessage(String msg, PriorityLevel level) {
        if (msg == null || msg.equals("")) { return null; }
        return new LogMessage(msg, level.getValue());
    }


    private static class LogMessage implements ILogMessage {
        private String msg;
        private int level;

        public LogMessage(String msg, int level) {
            this.msg = msg;
            this.level = level;
        }

        public String getMessage() { return msg; }
        public int getPriority() { return level; }
    }
}
