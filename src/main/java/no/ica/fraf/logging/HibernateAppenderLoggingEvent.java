package no.ica.fraf.logging;

import java.util.Date;

/**
 * Interface for logging events that are to be persisted using the
 * {@link HibernateAppender}.
 * 
 * @author David Howe
 * 
 * @version 1.0
 * 
 */
public interface HibernateAppenderLoggingEvent {
	/**
	 * Returns the log message.
	 * 
	 * @return The log message
	 */
	public String getMessage();

	/**
	 * Returns the name of the class.
	 * 
	 * @return The class name
	 */
	public String getClassName();

	/**
	 * Returns the source file name of the class.
	 * 
	 * @return The source file name
	 */
	public String getFileName();

	/**
	 * Returns the line number in the class that initiated the log event.
	 * 
	 * @return The line number
	 */
	public String getLineNumber();

	/**
	 * Returns the date and time of the log event.
	 * 
	 * @return The date and time of the log event
	 */
	public Date getLogDate();

	/**
	 * Returns the name of the logger.
	 * 
	 * @return The name of the logger
	 */
	public String getLoggerName();

	/**
	 * Returns the name of the method in the class that initiated the log event.
	 * 
	 * @return The name of the method
	 */
	public String getMethodName();

	/**
	 * Returns the date and time that the application started.
	 * 
	 * @return The date and time that the application started
	 */
	public Date getStartDate();

	/**
	 * Returns the name of the thread.
	 * 
	 * @return The name of the thread
	 */
	public String getThreadName();

	/**
	 * Returns the logging level of this log event.
	 * 
	 * @return The log level of this logging event
	 */
	public String getLogLevel();

	/**
	 * @return brukernavn
	 */
	public String getUserName();

	/**
	 * Sets the log message.
	 * 
	 * @param message
	 */
	public void setMessage(String message);

	/**
	 * Sets the name of the class.
	 * 
	 * @param className
	 */
	public void setClassName(String className);

	/**
	 * Sets source file name for the class.
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName);

	/**
	 * Sets the line number in the class that initiated the log event.
	 * 
	 * @param lineNumber
	 */
	public void setLineNumber(String lineNumber);

	/**
	 * Sets the date and time of the log event.
	 * 
	 * @param logDate
	 */
	public void setLogDate(Date logDate);

	/**
	 * Sets the logger name.
	 * 
	 * @param loggerName
	 */
	public void setLoggerName(String loggerName);

	/**
	 * Sets the name of the method that initiated the log event.
	 * 
	 * @param methodName
	 */
	public void setMethodName(String methodName);

	/**
	 * Sets the date and time that the application started.
	 * 
	 * @param startDate
	 */
	public void setStartDate(Date startDate);

	/**
	 * Sets the name of the thread.
	 * 
	 * @param threadName
	 */
	public void setThreadName(String threadName);

	/**
	 * Sets the log level.
	 * 
	 * @param level
	 */
	public void setLogLevel(String level);

	/**
	 * Adds a throwable exception message to the log event.
	 * 
	 * @param position
	 * @param throwableMessage
	 */
	public void addThrowableMessage(int position, String throwableMessage);

	/**
	 * @param userName
	 */
	public void setUserName(String userName);

	/**
	 * @return klasse for lagring
	 */
	public SaveLogInterface getSaveClass();
}