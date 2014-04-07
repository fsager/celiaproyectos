package ar.com.celia.core.mbean;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "bean:name=Log4jLevelChangerEpagos", description = "MBean para manupular el log4j")
public class Log4jLevelChanger {

	@ManagedOperation(description = "Set Level to log")
	@ManagedOperationParameters({
			@ManagedOperationParameter(name = "loggerName", description = "Nombre del log"),
			@ManagedOperationParameter(name = "level", description = "Nivel") })
	public void setLogLevel(String loggerName, String level) {

		if ("root".equalsIgnoreCase(loggerName)) {
			LogManager.getRootLogger().setLevel(getLevel(level));
		} else
			LogManager.getLogger(loggerName).setLevel(getLevel(level));
	}

	@ManagedOperation(description = "Set threshold to appender")
	@ManagedOperationParameters({
			@ManagedOperationParameter(name = "appenderName", description = "Nombre del appender"),
			@ManagedOperationParameter(name = "level", description = "Nivel") })
	public void changeAppenderLevel(String appenderName, String level) {
		AppenderSkeleton appender = (AppenderSkeleton) LogManager
				.getRootLogger().getAppender(appenderName);
		appender.setThreshold(getLevel(level));
	}

	@ManagedOperation(description = "changeLogFileDebugLevel")
	public void changeLogFileDebugLevel() {
		AppenderSkeleton appender = (AppenderSkeleton) LogManager
				.getRootLogger().getAppender("LOGFILE");
		appender.setThreshold(getLevel("DEBUG"));
	}

	@ManagedOperation(description = "changeLogFileErrorLevel")
	public void changeLogFileErrorLevel() {
		AppenderSkeleton appender = (AppenderSkeleton) LogManager
				.getRootLogger().getAppender("LOGFILE");
		appender.setThreshold(getLevel("ERROR"));
	}

	@ManagedOperation(description = "changeConsoleDebugLevel")
	public void changeConsoleDebugLevel() {
		AppenderSkeleton appender = (AppenderSkeleton) LogManager
				.getRootLogger().getAppender("CONSOLE");
		appender.setThreshold(getLevel("DEBUG"));
	}

	@ManagedOperation(description = "setConsoleErrorLevel")
	public void setConsoleErrorLevel() {
		AppenderSkeleton appender = (AppenderSkeleton) LogManager
				.getRootLogger().getAppender("CONSOLE");
		appender.setThreshold(getLevel("ERROR"));
	}

	@ManagedOperation(description = "enableHibernateDebug")
	public void enableHibernateDebug() {
		LogManager.getLogger("org.hibernate").setLevel(Level.DEBUG);
	}

	@ManagedOperation(description = "disableHibernateDebug")
	public void disableHibernateDebug() {
		LogManager.getLogger("org.hibernate").setLevel(Level.ERROR);
	}

	@ManagedOperation(description = "enableHibernateShowSql")
	public void enableHibernateShowSql() {
		LogManager.getLogger("org.hibernate.SQL").setLevel(Level.DEBUG);
		LogManager.getLogger("org.hibernate.type").setLevel(Level.DEBUG);
		
	/*
log4j.logger.org.hibernate.SQL=DEBUG
log4j.logger.org.hibernate.type=TRACE
	 * */
	}

	@ManagedOperation(description = "disableHibernateShowSql")
	public void disableHibernateShowSql() {
		LogManager.getLogger("org.hibernate.SQL").setLevel(Level.ERROR);
		LogManager.getLogger("org.hibernate.type").setLevel(Level.ERROR);
	}

	private Level getLevel(String level) {
		if ("debug".equalsIgnoreCase(level)) {
			return Level.DEBUG;
		} else if ("info".equalsIgnoreCase(level)) {
			return Level.INFO;
		} else if ("trace".equalsIgnoreCase(level)) {
			return Level.TRACE;
		} else if ("warn".equalsIgnoreCase(level)) {
			return Level.WARN;
		} else if ("error".equalsIgnoreCase(level)) {
			return Level.ERROR;
		} else if ("fatal".equalsIgnoreCase(level)) {
			return Level.FATAL;
		} else if ("off".equalsIgnoreCase(level)) {
			return Level.OFF;
		} else
			throw new RuntimeException("El nivel indicado no existe");
	}
}
