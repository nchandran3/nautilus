# Nautilus Logger

## Example
run `./gradlew run`

This launches three threads, each configured to log a message with a specified priority at random intervals (between 1 and 4 seconds).
A reader thread is also created, configured to read from the message queue every 3 seconds.
The output shows the current state of the queue, as well as what the log reader picks up with each subsequent read.

The program finishes when there are no messages found.

## Architecture
At a high level, the module consists of a `LogReader` and `Logger` class, along with an orthogonal `BlockingQueue` as the source for the respective read and write operations. Everything feeds off an `ILogMessage` interface, which defines the structure and data contained within a message. This allows for easy extension to add new properties or functionality and was an integral component of how to approach the issue of adding and decorating messages with new properties.

Care was taken to adhere to SRP as much as possible, which lead to many classes, but very concise implementations. The classes are parametrized where necessary in order to facilitate easier extension and static type checking with other subclasses.

