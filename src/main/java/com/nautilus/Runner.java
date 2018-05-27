package com.nautilus;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

import com.nautilus.logging.*;

class Runner {
    public static void main(String[] args) {
        final BlockingQueue<TimestampedLogMessage> queue = new LogQueue<TimestampedLogMessage>(100, new LogMessageComparator());
        final ILogMessageFactory<TimestampedLogMessage> msgGenerator = new TimestampedLogMessageFactory();
        final LogReader<TimestampedLogMessage> reader = new LogReader<>(queue);

        List<Thread> threads = createLoggerThreads(queue, msgGenerator);
        threads.add(createReadQueueOnIntervalThread(reader, queue, 3000));
        System.out.println("Running Threads");
        threads.forEach(t -> {
            t.start();
        });
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static <T extends ILogMessage> Thread createReadQueueOnIntervalThread(
        final LogReader<T> reader, final BlockingQueue<T> queue, final long millis
        ) {
        return new Thread(() ->  {
            int numNoMessages = 0;
            while (numNoMessages < 10) {
                System.out.println("=========================================================================");
                printQueueState(queue);
                StringBuilder output = new StringBuilder("\n\n[READER]: ");
                T msg = reader.get();
                if (msg != null) {
                    output.append(
                        String.format("Message read:\n\t[Priority: %d]\n\t[Message: %s]\n", msg.getPriority(), msg.getMessage())
                    );
                } else {
                    numNoMessages++;
                    output.append("No messages present in queue");
                }
                System.out.println(output.toString());
                System.out.println("=========================================================================\n\n");
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private static <T extends ILogMessage> void printQueueState(BlockingQueue<T> queue) {
        ArrayList<T> list = new ArrayList<>(queue);
        final int COLUMNS = 10;
        StringBuilder finalBuilder = new StringBuilder("Number of items: ").append(queue.size()).append("\n\n");
        for (int row = 0; row <= queue.size() / COLUMNS; row++) {
             StringBuilder builder = new StringBuilder().append(row*COLUMNS).append("-").append((row+1)*(COLUMNS)-1).append(": [");
            for (int c = 0; c < COLUMNS; c++) {
                int index = row * COLUMNS + c;
                if (index < list.size()) {
                    builder.append(list.get(index).getPriority()).append(",");
                }
            }
            int lastCommaIndex = builder.lastIndexOf(",");
            if (lastCommaIndex > 0) { builder.deleteCharAt(lastCommaIndex); }
            builder.append("]\n");
            finalBuilder.append(builder.toString());
        }
        System.out.println("==============\nQueue State\n==============");
        System.out.println(finalBuilder.toString());
    }
    private static <T extends ILogMessage> List<Thread> createLoggerThreads(BlockingQueue<T> queue, ILogMessageFactory<T> msgGenerator) {
        List<Thread> threads = new ArrayList<>();
        for (int i =1; i <= 3; i++) {
            PriorityLevel level = getPriorityLevelFromValue(i);
            threads.add(new Thread(() -> {
                Logger<T> logger = new Logger<T>(queue, msgGenerator);
                for (int j = 0; j < 10; j++) {
                    logger.log(
                        String.format("Message #%d at priority level %s from Thread %d", j, level.name(), level.getValue()),
                        level
                    );
                    try {
                        Thread.sleep((long) (Math.random() * 3000) + 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        return threads;
    }

    private static PriorityLevel getPriorityLevelFromValue(final int i) {
        Optional<PriorityLevel> result = Arrays.stream(PriorityLevel.values()).filter(l -> i == l.getValue()).findFirst();
        return result.isPresent() ? result.get() : null;
    }
}
