package com.nautilus.logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.PriorityBlockingQueue;

public class LogQueue<E extends Serializable> extends PriorityBlockingQueue<E> {

    private static final long serialVersionUID = -4956582686690552151L;
    protected static final String CONTENTS_DUMP_FILE = "./dump/queue_contents.txt";

    @Override
    public boolean add(E ele) {
        try { return super.add(ele); }
        catch (OutOfMemoryError e) {
            synchronized(this) {
                this.executeOutOfMemoryDataSalvageStrategy();
                System.exit(1);
            }
        }
        return false;
    }

    /**
     * Strategy for running out of memory with this log queue.
     * In a real production application should just use JVM Option -XX:HeapDumpOnOutOfMemoryError in order to salvage data if possible
     * since there is a real possibility our manual error handling will throw another OutOfMemoryError
     */
    protected void executeOutOfMemoryDataSalvageStrategy() {
        File file = new File(CONTENTS_DUMP_FILE);
        try {
            file.createNewFile();
        } catch (IOException e) { return; }

        this.forEach(ele -> {
            try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file, true))){
                o.writeObject(ele);
            } catch(IOException e) { return; }
        });
    }
}
