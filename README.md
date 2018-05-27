# Nautilus Logger

## Architecture

At a high level, I created a `LogReader` and `Logger` class, along with an orthogonal `BlockingQueue` as the source for the respective read and write operations. Everything feeds off an `ILogMessage` interface, which defines the structure and data contained within a message. This allows for easy extension to add new properties or functionality and was an integral component of how I was going to approach the issue of adding and decorating messages with new properties.

I took care to adhere to SRP as much as I could, which lead to many classes, but very concise implementations. The classes are parametrized where necessary in order to facilitate easier extension and static type checking with other subclasses.

