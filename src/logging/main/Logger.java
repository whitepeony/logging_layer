package logging.main;

import java.util.logging.LogRecord;

public class Logger extends DefaultLogger{

	private final java.util.logging.Logger delegate;
	private final String name;

	public Logger(final String name)
	{
		this.name = name;
		this.delegate = java.util.logging.Logger.getLogger(name);
	}
	
	@Override
	protected void log(final Level level, final String msg, final Throwable t)
	{
		LogRecord r = new LogRecord(getLogLevel(level), msg);
		r.setSourceClassName(name);
		r.setSourceMethodName(null);
		r.setThrown(t);
		delegate.log(r);
	}

	@Override
	protected boolean isEnabled(final Level level)
	{
		return delegate.isLoggable(getLogLevel(level));
	}

	/**
	 * Translates the log level to JDK {@link java.util.logging.Level} class.
	 */
	protected final java.util.logging.Level getLogLevel(final Level level)
	{
		switch (level)
		{
		case TRACE:
			return java.util.logging.Level.FINER;
		case DEBUG:
			return java.util.logging.Level.FINE;
		case INFO:
			return java.util.logging.Level.INFO;
		case WARN:
			return java.util.logging.Level.WARNING;
		case ERROR:
			return java.util.logging.Level.SEVERE;
		}
		throw new IllegalArgumentException("Unsupported log level: " + level);
	}
}
