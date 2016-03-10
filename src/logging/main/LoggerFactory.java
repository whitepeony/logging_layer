package logging.main;

public interface LoggerFactory {
   /**
    * Create a new log factory for the given logger name.
    * 
    * @param logger The name of the logger
    * @return A log adapter extending {@link Logger}
    */
   public Logger createLogger(String logger);
   public int getPriority();
}
