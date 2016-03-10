package logging.main;

public class LoggerFactoryImpl implements LoggerFactory{
	@Override
	public int getPriority()
	{
		return 10;
	}

	@Override
	public Logger createLogger(final String logger)
	{
		return new Logger(logger);
	}
}
