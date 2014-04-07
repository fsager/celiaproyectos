package ar.com.celia.aop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "bean:name=PerfInterceptor", description = "Monitor de performance")
public class PerfInterceptor implements MethodInterceptor {

	// private Log logger = LogFactory.getLog(PerfInterceptor.class.getName());
	static Logger logger = Logger.getLogger(PerfInterceptor.class);
	private ConcurrentHashMap<String, MethodStats> methodStats = new ConcurrentHashMap<String, MethodStats>();
	private long statLogFrequency = 10;
	private long methodWarningThreshold = 500;

	public void PerfInterceptor() {
	}

	
	@ManagedAttribute
	public long getStatLogFrequency() {
		return statLogFrequency;
	}

	@ManagedAttribute
	public void setStatLogFrequency(long statLogFrequency) {
		this.statLogFrequency = statLogFrequency;
	}

	@ManagedAttribute
	public long getMethodWarningThreshold() {
		return methodWarningThreshold;
	}

	@ManagedAttribute
	public void setMethodWarningThreshold(long methodWarningThreshold) {
		this.methodWarningThreshold = methodWarningThreshold;
	}
	

	public Object invoke(MethodInvocation method) throws Throwable {

		long start = System.currentTimeMillis();
		try {
			return method.proceed();
		} finally {
			updateStats(method.getThis().getClass().getName() + " "
					+ method.getMethod().getName(),
					(System.currentTimeMillis() - start));
		}
	}

	private void updateStats(String classAndMethodName, long elapsedTime) {
		MethodStats stats = methodStats.get(classAndMethodName);
		if (stats == null) {
			stats = new MethodStats(classAndMethodName);
			methodStats.put(classAndMethodName, stats);
		}
		stats.count++;
		stats.totalTime += elapsedTime;
		if (elapsedTime > stats.maxTime) {
			stats.maxTime = elapsedTime;
		}

		if (elapsedTime > methodWarningThreshold) {
			logger.warn("method warning: " + classAndMethodName + "(), cnt = "
					+ stats.count + ", lastTime = " + elapsedTime
					+ ", maxTime = " + stats.maxTime);
		}

		if (stats.count % statLogFrequency == 0) {
			long avgTime = stats.totalTime / stats.count;
			long runningAvg = (stats.totalTime - stats.lastTotalTime)
					/ statLogFrequency;
			logger.debug("method: " + classAndMethodName + "(), cnt = "
					+ stats.count + ", lastTime = " + elapsedTime
					+ ", avgTime = " + avgTime + ", runningAvg = " + runningAvg
					+ ", maxTime = " + stats.maxTime);

			// reset the last total time
			stats.lastTotalTime = stats.totalTime;
		}
	}

	class MethodStats {
		public String methodName;
		public long count;
		public long totalTime;
		public long lastTotalTime;
		public long maxTime;

		public MethodStats(String methodName) {
			this.methodName = methodName;
		}
	}
}
