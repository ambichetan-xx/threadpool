package task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Runs a submitted task <code>times</code> number of times and supports a sleep
 * interval of <code>sleepMillis</code> between each run and returns a Future
 * result.
 * 
 * This method returns immediately with a Future object which can be used to
 * obtain the result of the task when the task completes.
 * 
 * @param task
 * @param times
 * @param sleepMillis
 */

public class TaskRunner {

	ExecutorService executor = Executors.newFixedThreadPool(3);

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	@SuppressWarnings("unchecked")
	public <V> Future<V> runTaskAsync(ITask<V> task, int times, long sleepMillis) {

		/*
		 * I have copied the values to local final variables, since call()
		 * method takes only final parameters. I didn't want to change the
		 * function signature
		 */
		final ITask<V> executiontask = task;
		final int executionTimes = times;
		final long sleepMiliTime = sleepMillis;

		/*
		 * creates the thread and calls call() method of the ITask object to
		 * perform the required operation
		 */
		return (Future<V>) executor.submit(new Callable<V>() {
			@Override
			public V call() throws Exception {
				Boolean result = false;
				for (int i = 0; i < executionTimes; i++) {
					if (executiontask.isComplete()) {
						break;
					} else {
						// printing the called method information
						System.out.println("calling "
								+ executiontask.getClass().getSimpleName()
								+ "."
								+ executiontask.getClass().getMethod("call")
										.getName() + "() " + i + " time");
						result = (Boolean) executiontask.call();
					}
					if (!executiontask.isComplete()) {
						Thread.sleep(sleepMiliTime);
					}
				}
				return (V) result;
			}
		});
	}
}
