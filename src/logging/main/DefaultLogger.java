package logging.main;

/**
 * Class to create log messages.
 * 
 * @author Cindy Zhang
 */
public abstract class DefaultLogger
{
	public enum Level
	{
		TRACE, DEBUG, INFO, WARN, ERROR
	}

	protected abstract void log(Level level, String msg, Throwable t);

	protected abstract boolean isEnabled(Level level);

	public boolean isTraceEnabled()
	{
		return isEnabled(Level.TRACE);
	}

	public void trace(final String msg)
	{
		log(Level.TRACE, msg, null);
	}

	public void trace(final String msg, final Object arg)
	{
		log(Level.TRACE, format(msg, new Object[] { arg }), null);
	}

	public void trace(final String msg, final Object arg1, final Object arg2)
	{
		log(Level.TRACE, format(msg, new Object[] { arg1, arg2 }), null);
	}

	public void trace(final String msg, final Object[] argArray)
	{
		log(Level.TRACE, format(msg, argArray), null);
	}

	public void trace(final String msg, final Throwable t)
	{
		log(Level.TRACE, msg, t);
	}

	public boolean isDebugEnabled()
	{
		return isEnabled(Level.DEBUG);
	}

	public void debug(final String msg)
	{
		log(Level.DEBUG, msg, null);
	}

	public void debug(final String msg, final Object arg)
	{
		log(Level.DEBUG, format(msg, new Object[] { arg }), null);
	}

	public void debug(final String msg, final Object arg1, final Object arg2)
	{
		log(Level.DEBUG, format(msg, new Object[] { arg1, arg2 }), null);
	}

	public void debug(final String msg, final Object[] argArray)
	{
		log(Level.DEBUG, format(msg, argArray), null);
	}

	public void debug(final String msg, final Throwable t)
	{
		log(Level.DEBUG, msg, t);
	}

	public boolean isInfoEnabled()
	{
		return isEnabled(Level.INFO);
	}

	public void info(final String msg)
	{
		log(Level.INFO, msg, null);
	}

	public void info(final String msg, final Object arg)
	{
		log(Level.INFO, format(msg, new Object[] { arg }), null);
	}

	public void info(final String msg, final Object arg1, final Object arg2)
	{
		log(Level.INFO, format(msg, new Object[] { arg1, arg2 }), null);
	}

	public void info(final String msg, final Object[] argArray)
	{
		log(Level.INFO, format(msg, argArray), null);
	}

	public void info(final String msg, final Throwable t)
	{
		log(Level.INFO, msg, t);
	}

	public boolean isWarnEnabled()
	{
		return isEnabled(Level.WARN);
	}

	public void warn(final String msg)
	{
		log(Level.WARN, msg, null);
	}

	public void warn(final String msg, final Object arg)
	{
		log(Level.WARN, format(msg, new Object[] { arg }), null);
	}

	public void warn(final String msg, final Object arg1, final Object arg2)
	{
		log(Level.WARN, format(msg, new Object[] { arg1, arg2 }), null);
	}

	public void warn(final String msg, final Object[] argArray)
	{
		log(Level.WARN, format(msg, argArray), null);
	}

	public void warn(final String msg, final Throwable t)
	{
		log(Level.WARN, msg, t);
	}

	public boolean isErrorEnabled()
	{
		return isEnabled(Level.ERROR);
	}

	public void error(final String msg)
	{
		log(Level.ERROR, msg, null);
	}

	public void error(final String msg, final Object arg)
	{
		log(Level.ERROR, format(msg, new Object[] { arg }), null);
	}

	public void error(final String msg, final Object arg1, final Object arg2)
	{
		log(Level.ERROR, format(msg, new Object[] { arg1, arg2 }), null);
	}

	public void error(final String msg, final Object[] argArray)
	{
		log(Level.ERROR, format(msg, argArray), null);
	}

	public void error(final String msg, final Throwable t)
	{
		log(Level.ERROR, msg, t);
	}

	protected String format(final String msg, final Object[] args)
	{

		StringBuilder builder = new StringBuilder(msg);
		for (Object o : args)
		{
			int i = builder.indexOf("{}");
			if (i == -1)
			{
				break;
			}
			builder.replace(i, i + 2, (o != null ? o.toString() : "null"));

		}
		return builder.toString();
	}

	/**
	 * Create a {@link SimpleLogger} instance
	 * 
	 * @param clazz The class to create the log for
	 * @return The {@link Logger} instance
	 */
	public static Logger getLogger(final Class<?> clazz)
	{
		return getLogger(clazz.getName());
	}

	/**
	 * Create a {@link Logger} instance for a specific logger name
	 * 
	 * @param logger the logger name
	 * @return The {@link Logger} instance
	 */
	public static Logger getLogger(final String logger)
	{
		LoggerFactory loggerFactory = getLoggerFactory();
		return loggerFactory.createLogger(logger);
	}

	private static volatile LoggerFactory _loggerFactory = null;
	/**
	 * This method provides access to the {@link LogAdapterFactory}. It will obtain the {@link LogAdapterFactory} lazily
	 * using {@link #createAdapterFactory()}.
	 */
	private static LoggerFactory getLoggerFactory()
	{
		// double-checked locking
		if (_loggerFactory == null)
		{
			synchronized (Logger.class)
			{
				if (_loggerFactory == null)
				{
					_loggerFactory = new LoggerFactoryImpl();
				}
			}
		}
		return _loggerFactory;
	}
}
