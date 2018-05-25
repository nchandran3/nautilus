# Nautilus Logger

## TODO

* Implement comparator function for TimestampedLogMessages
* Use builder pattern to implement Logging multiple messages at once by creating a private Builder and allowing to chain log calls
* Implement memory checks in queue class
* Provide example of how to extend ILogMessage interface with a tangible class that provides more than message and priority property
    * Ex:
    ```java
    DecoratedMessage implements ILogMessage {
        ....
        public String getDate()
        public String getOtherProperty()
    }
    ```


## Architecture

At a high level, I created a `LogReader` and `Logger` class, along with an orthogonal `BlockingQueue` as the source for the respective read and write operations. Everything feeds off an `ILogMessage` interface, which defines the structure and data contained within a message. This allows for easy extension to add new properties or functionality and was an integral component of how I was going to approach the issue of adding and decorating messages with new properties.

I took care to adhere to SRP as much as I could, which lead to many classes, but very concise implementations. The classes are parametrized where necessary in order to facilitate easier extension and static type checking with other subclasses.

