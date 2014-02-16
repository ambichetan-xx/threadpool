package client;

import java.util.concurrent.Future;

import task.ITask;
import task.TaskRunner;
import util.tasks.FileCheckerTask;
import util.tasks.PortAvailableTask;

/**
 * Main program for creating and submitting tasks to the Task Runner.
 * 
 * @author ranj4711
 * 
 */
public class TaskSubmitter {

	/**
	 * Creates two tasks and submits them to the TaskRunner.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * TODO: Instantiate and submit an instance of the FileCheckerTask to
		 * the TaskRunner
		 */
		ITask<Boolean> fileCheckerTask = new FileCheckerTask<Boolean>(
				"e:/test.txt");
		TaskRunner taskRunner = new TaskRunner();
		Future<Boolean> result = taskRunner.<Boolean> runTaskAsync(
				fileCheckerTask, 10, 5000);

		/*
		 * TODO: Instantiate and submit an instance of the PortAvailableTask to
		 * the TaskRunner
		 */
		ITask<String> portCheckerTask = new PortAvailableTask<String>(49153);
		taskRunner.runTaskAsync(portCheckerTask, 10, 5000);
		System.out.println(result.get());

		// exit all threads
		taskRunner.getExecutor().shutdown();
		while (!taskRunner.getExecutor().isTerminated()) {
		}
		System.out.println("Exit : Finished all threads");

	}
}
