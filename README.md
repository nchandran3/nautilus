# Nautilus Logger

## TODO

* Use builder pattern to implement Logging multiple messages at once by creating a private builder and allowing to chain log calls
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

