package logging.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logging.main.Logger;

public class LoggerTest {

	private TestLog log;

	@Before
	public void setUp()
	{
		log = new TestLog("My Test");
	}

	@After
	public void tearDown()
	{
		log = null;
	}

	@Test
	public void testSimpleStringArgument()
	{
		log.info("Say Hi {}!", "Cindy");
		assertEquals("INFO - Say Hi Cindy!", log.getLogString());
	}

	@Test
	public void testSimpleBooleanArgument()
	{
		log.info("Result: {}", true);
		assertEquals("INFO - Result: true", log.getLogString());
	}

	@Test
	public void testSimpleLongArgument()
	{
		log.info("Result: {}", 1234567890l);
		assertEquals("INFO - Result: 1234567890", log.getLogString());
	}

	@Test
	public void testWarning()
	{
		log.warn("Result: {}", (Object) null);
		assertEquals("WARN - Result: null", log.getLogString());
	}

	@Test
	public void testArgumentWithError()
	{
		log.error("Error", 123);
		assertEquals("ERROR - Error", log.getLogString());
	}

	@Test
	public void testTraceArgument()
	{
		log.trace("Result: {}");
		assertEquals("TRACE - Result: {}", log.getLogString());
	}

	/**
	 * Simple class extending {@link Logger}, storing last message.
	 */
	private static class TestLog extends Logger
	{

		public TestLog(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}

		private String logString;

		@Override
		protected void log(final Level level, final String msg, final Throwable t)
		{
			logString = level.name() + " - " + msg;
		}

		@Override
		protected boolean isEnabled(final Level level)
		{
			return true;
		}

		public String getLogString()
		{
			return logString;
		}

	}

}
