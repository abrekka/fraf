package no.ica.fraf.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.ica.fraf.logging.HibernateAppenderLoggingEvent;
import no.ica.fraf.logging.SaveLogInterface;
import no.ica.fraf.util.ModelUtil;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell LOGG og brukes av logger for å logge til database
 * 
 * @author abr99
 * 
 */
public class Logg extends BaseObject implements HibernateAppenderLoggingEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer logId;

	/** nullable persistent field */
	private String logLevel;

	/** nullable persistent field */
	private String message;

	/** nullable persistent field */
	private String className;

	/** nullable persistent field */
	private String fileName;

	/** nullable persistent field */
	private String lineNumber;

	/** nullable persistent field */
	private String methodName;

	/** nullable persistent field */
	private String loggerName;

	/** nullable persistent field */
	private Date startDate;

	/** nullable persistent field */
	private Date logDate;

	/** nullable persistent field */
	private String threadName;

	/** nullable persistent field */
	private String userName;

	/**
	 * 
	 */
	private List<LoggThrowable> loggingEventThrowableWrapper = new ArrayList<LoggThrowable>();

	/**
	 * @param logId
	 * @param logLevel
	 * @param message
	 * @param className
	 * @param fileName
	 * @param lineNumber
	 * @param methodName
	 * @param loggerName
	 * @param startDate
	 * @param logDate
	 * @param threadName
	 * @param userName
	 */
	public Logg(Integer logId, String logLevel, String message,
			String className, String fileName, String lineNumber,
			String methodName, String loggerName, Date startDate, Date logDate,
			String threadName, String userName) {
		this.logId = logId;
		this.logLevel = logLevel;
		this.message = message;
		this.className = className;
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.methodName = methodName;
		this.loggerName = loggerName;
		this.startDate = startDate;
		this.logDate = logDate;
		this.threadName = threadName;
		this.userName = userName;
	}

	/** default constructor */
	public Logg() {
	}

	/**
	 * @param logId
	 */
	public Logg(Integer logId) {
		this.logId = logId;
	}

	/**
	 * @return id
	 */
	public Integer getLogId() {
		return this.logId;
	}

	/**
	 * @param logId
	 */
	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getLogLevel()
	 */
	public String getLogLevel() {
		return this.logLevel;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#setLogLevel(java.lang.String)
	 */
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getMessage()
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#setMessage(java.lang.String)
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getClassName()
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#setClassName(java.lang.String)
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getFileName()
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#setFileName(java.lang.String)
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getLineNumber()
	 */
	public String getLineNumber() {
		return this.lineNumber;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#setLineNumber(java.lang.String)
	 */
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getMethodName()
	 */
	public String getMethodName() {
		return this.methodName;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#setMethodName(java.lang.String)
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getLoggerName()
	 */
	public String getLoggerName() {
		return this.loggerName;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#setLoggerName(java.lang.String)
	 */
	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getStartDate()
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#setStartDate(java.util.Date)
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getLogDate()
	 */
	public Date getLogDate() {
		return this.logDate;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#setLogDate(java.util.Date)
	 */
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getThreadName()
	 */
	public String getThreadName() {
		return this.threadName;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#setThreadName(java.lang.String)
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getUserName()
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#setUserName(java.lang.String)
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return wrapper for throwable logging
	 */
	public List getLoggingEventThrowableWrapper() {
		return loggingEventThrowableWrapper;
	}

	/**
	 * @param loggingEventThrowableWrapper
	 *            The loggingEventThrowableWrapper to set.
	 */
	public void setLoggingEventThrowableWrapper(
			List<LoggThrowable> loggingEventThrowableWrapper) {
		this.loggingEventThrowableWrapper = loggingEventThrowableWrapper;
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#addThrowableMessage(int,
	 *      java.lang.String)
	 */
	public void addThrowableMessage(int position, String throwableMessage) {
		LoggThrowable letw = new LoggThrowable();
		letw.setPosition(new Integer(position));
		letw.setMessage(throwableMessage);
		loggingEventThrowableWrapper.add(letw);
	}

	/**
	 * @see no.ica.fraf.logging.HibernateAppenderLoggingEvent#getSaveClass()
	 */
	public SaveLogInterface getSaveClass() {
		return (SaveLogInterface) ModelUtil.getBean("loggDAO");
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"logId", logId).append("logLevel", logLevel).append("message",
				message).append("className", className).append("fileName",
				fileName).append("lineNumber", lineNumber).append("methodName",
				methodName).append("loggerName", loggerName).append(
				"startDate", startDate).append("logDate", logDate).append(
				"threadName", threadName).append("userName", userName).append(
				"loggingEventThrowableWrapper", loggingEventThrowableWrapper)
				.toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Logg))
			return false;
		Logg castOther = (Logg) other;
		return new EqualsBuilder().append(logId, castOther.logId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(logId).toHashCode();
	}

}
