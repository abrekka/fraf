package no.ica.fraf.logging;

import java.util.Date;
import java.util.Vector;

import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;
import org.hibernate.HibernateException;

/**
 * Log4J appender that uses Hibernate to store log events in a database. To use
 * this appender, you must provide two properties in the Log4J properties file:
 * 
 * <UL>
 * sessionServiceClass
 * </UL>
 * <UL>
 * loggingEventClass
 * </UL>
 * 
 * <P>
 * The sessionServiceClass must implement the
 * {@link HibernateAppenderSessionService} interface which provides the appender
 * with an open Hibernate session. Your implementation of this interface can
 * perform any additional activities such as registering audit interceptors if
 * required.
 * </P>
 * 
 * <P>
 * The loggingEventClass must implement the
 * {@link HibernateAppenderLoggingEvent} interface. Using an interface for the
 * logging event leaves your implementation free to extend any existing
 * persistence ancestor that you may already be using.
 * </P>
 * 
 * <P>
 * An example Log4J properties file to configure the HibernateAppender would be:
 * </P>
 * 
 * <code>
 * ### direct messages to Hibernate<BR/>
 * log4j.appender.hibernate=HibernateAppender<BR/>
 * log4j.appender.hibernate.sessionServiceClass=HibernateHelper<BR/>
 * log4j.appender.hibernate.loggingEventClass=LogEvent<BR/>
 * </code>
 * 
 * <P>
 * You can now write a Hibernate mapping file for the class specified as the
 * <code>loggingEventClass</code> to persist whichever parts of the logging
 * event that you require.
 * </P>
 * 
 * @author David Howe
 * 
 * @version 1.1
 * 
 */
public class HibernateAppender extends AppenderSkeleton implements Appender {

    /**
     * 
     */
    private String sessionServiceClass;

    /**
     * 
     */
    private String loggingEventClass;

    /**
     * 
     */
    @SuppressWarnings("unused")
    private HibernateAppenderSessionService hibernateSessionServiceImplementation;

    /**
     * 
     */
    private Class loggingEventWrapperImplementationClass;

    /**
     * 
     */
    private static Vector<Object> buffer = new Vector<Object>();

    /**
     * 
     */
    private static Boolean appending = Boolean.FALSE;

    /**
     * 
     */
    private String userName = null;

    /**
     * @see org.apache.log4j.AppenderSkeleton#append(org.apache.log4j.spi.LoggingEvent)
     */
    @Override
    protected void append(LoggingEvent loggingEvent) {
        if (userName == null) {
            userName = System.getProperty("user.name");
        }
        /*
         * Ensure exclusive access to the buffer in case another thread is
         * currently writing the buffer.
         */
        synchronized (buffer) {
            // Add the current event into the buffer
            buffer.add(loggingEvent);
            /*
             * Ensure exclusive access to the appending flag to guarantee that
             * it doesn't change in between checking it's value and setting it
             */
            synchronized (appending) {
                if (!appending.booleanValue()) {
                    /*
                     * No other thread is appending to the log, so this thread
                     * can perform the append
                     */
                    appending = Boolean.TRUE;
                } else {
                    /*
                     * Another thread is already appending to the log and it
                     * will take care of emptying the buffer
                     */
                    return;
                }
            }
        }

        try {
            /*
             * Session session = hibernateSessionServiceImplementation
             * .openSession();
             */

            /*
             * Ensure exclusive access to the buffer in case another thread is
             * currently adding to the buffer.
             */
            synchronized (buffer) {
                LoggingEvent bufferLoggingEvent;
                HibernateAppenderLoggingEvent loggingEventWrapper;

                /*
                 * Get the current buffer length. We only want to process events
                 * that are currently in the buffer. If events get added to the
                 * buffer after this point, they must have been caused by this
                 * loop, as we have synchronized on the buffer, so no other
                 * thread could be adding an event. Any events that get added to
                 * the buffer as a result of this loop will be discarded, as to
                 * attempt to process them will result in an infinite loop.
                 */

                int bufferLength = buffer.size();

                for (int i = 0; i < bufferLength; i++) {
                    bufferLoggingEvent = (LoggingEvent) buffer.get(i);

                    try {
                        loggingEventWrapper = (HibernateAppenderLoggingEvent) loggingEventWrapperImplementationClass
                                .newInstance();
                    } catch (IllegalAccessException iae) {
                        this.errorHandler.error("Unable to instantiate class "
                                + loggingEventWrapperImplementationClass
                                        .getName(), iae,
                                ErrorCode.GENERIC_FAILURE);
                        return;
                    } catch (InstantiationException ie) {
                        this.errorHandler.error("Unable to instantiate class "
                                + loggingEventWrapperImplementationClass
                                        .getName(), ie,
                                ErrorCode.GENERIC_FAILURE);
                        return;
                    }

                    loggingEventWrapper.setUserName(userName);

                    loggingEventWrapper.setMessage(bufferLoggingEvent
                            .getRenderedMessage());
                    loggingEventWrapper.setClassName(bufferLoggingEvent
                            .getLocationInformation().getClassName());
                    loggingEventWrapper.setFileName(bufferLoggingEvent
                            .getLocationInformation().getFileName());
                    loggingEventWrapper.setLineNumber(bufferLoggingEvent
                            .getLocationInformation().getLineNumber());
                    Date logDate = new Date();
                    logDate.setTime(bufferLoggingEvent.timeStamp);

                    loggingEventWrapper.setLogDate(logDate);

                    loggingEventWrapper.setLoggerName(bufferLoggingEvent
                            .getLoggerName());
                    loggingEventWrapper.setMethodName(bufferLoggingEvent
                            .getLocationInformation().getMethodName());

                    Date startDate = new Date();
                    startDate.setTime(LoggingEvent.getStartTime());

                    loggingEventWrapper.setStartDate(startDate);
                    loggingEventWrapper.setThreadName(bufferLoggingEvent
                            .getThreadName());

                    if (bufferLoggingEvent.getThrowableStrRep() != null) {
                        for (int j = 0; j < bufferLoggingEvent
                                .getThrowableStrRep().length; j++) {
                            loggingEventWrapper.addThrowableMessage(j,
                                    bufferLoggingEvent.getThrowableStrRep()[j]);
                        }
                    }

                    if (bufferLoggingEvent.equals(Level.ALL)) {
                        loggingEventWrapper.setLogLevel("ALL");
                    } else if (bufferLoggingEvent.getLevel()
                            .equals(Level.DEBUG)) {
                        loggingEventWrapper.setLogLevel("DEBUG");
                    } else if (bufferLoggingEvent.getLevel()
                            .equals(Level.ERROR)) {
                        loggingEventWrapper.setLogLevel("ERROR");
                    } else if (bufferLoggingEvent.getLevel()
                            .equals(Level.FATAL)) {
                        loggingEventWrapper.setLogLevel("FATAL");
                    } else if (bufferLoggingEvent.getLevel().equals(Level.INFO)) {
                        loggingEventWrapper.setLogLevel("INFO");
                    } else if (bufferLoggingEvent.getLevel().equals(Level.OFF)) {
                        loggingEventWrapper.setLogLevel("OFF");
                    } else if (bufferLoggingEvent.getLevel().equals(Level.WARN)) {
                        loggingEventWrapper.setLogLevel("WARN");
                    } else {
                        loggingEventWrapper.setLogLevel("UNKNOWN");
                    }

                    SaveLogInterface saveClass = loggingEventWrapper
                            .getSaveClass();
                    // session.save(loggingEventWrapper);
                    saveClass.save(loggingEventWrapper);

                }
                // HibernateUtil.beginTransaction();
                // session.flush();
                // HibernateUtil.commitTransaction();
                // hibernateSessionServiceImplementation.closeSession();
                // session.close();
                buffer.clear();

                /*
                 * Ensure exclusive access to the appending flag - this really
                 * shouldn't be needed as the only other check on this flag is
                 * also synchronized on the buffer. We don't want to do this in
                 * the finally block as between here and the finally block we
                 * will not be synchronized on the buffer and another process
                 * could add an event to the buffer, but the appending flag will
                 * still be true, so that event would not get written until
                 * another log event triggers the buffer to be emptied.
                 */
                synchronized (appending) {
                    appending = Boolean.FALSE;
                }
            }
        } catch (HibernateException he) {
            this.errorHandler.error("HibernateException", he,
                    ErrorCode.GENERIC_FAILURE);
            // Reset the appending flag
            appending = Boolean.FALSE;
            return;
        }
    }

    /**
     * @see org.apache.log4j.Appender#close()
     */
    @Override
    public void close() {
    }

    /**
     * @see org.apache.log4j.Appender#requiresLayout()
     */
    @Override
    public boolean requiresLayout() {
        return false;
    }

    /**
     * Returns the name of class implementing the
     * {@link HibernateAppenderSessionService} interface.
     * 
     * @return Fully qualified class name
     */
    public String getSessionServiceClass() {
        return sessionServiceClass;
    }

    /**
     * Sets the name of the class implementing the
     * {@link HibernateAppenderSessionService} interface.
     * 
     * @param string
     */
    public void setSessionServiceClass(String string) {
        this.sessionServiceClass = string;
        try {
            Class c = Class.forName(string);
            try {
                hibernateSessionServiceImplementation = (HibernateAppenderSessionService) c
                        .newInstance();
            } catch (InstantiationException ie) {
                this.errorHandler.error("Unable to instantiate class "
                        + c.getName(), ie, ErrorCode.GENERIC_FAILURE);
            } catch (IllegalAccessException iae) {
                this.errorHandler.error("Unable to instantiate class "
                        + c.getName(), iae, ErrorCode.GENERIC_FAILURE);
            }
        } catch (ClassNotFoundException cnfe) {
            this.errorHandler.error(
                    "Invalid HibernateAppenderSessionService class " + string,
                    cnfe, ErrorCode.GENERIC_FAILURE);
        }
    }

    /**
     * Returns the name of the class implementing the
     * {@link HibernateAppenderLoggingEvent} interface.
     * 
     * @return Fully qualified class name
     */
    public String getLoggingEventClass() {
        return loggingEventClass;
    }

    /**
     * Sets the name of class implementing the
     * {@link HibernateAppenderLoggingEvent} interface.
     * 
     * @param string
     */
    public void setLoggingEventClass(String string) {
        loggingEventClass = string;
        try {
            loggingEventWrapperImplementationClass = Class
                    .forName(loggingEventClass);
        } catch (ClassNotFoundException cnfe) {
            this.errorHandler.error("Invalid LoggingEvent class " + string,
                    cnfe, ErrorCode.GENERIC_FAILURE);
        }
    }
}