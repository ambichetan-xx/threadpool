package util.tasks;

import java.io.File;

import task.ITask;

/**
 * 
 * Checks if the passed in file name exists
 * 
 * @param <T>
 */
public class FileCheckerTask<T> implements ITask<T> {

	// holds the filename
	private String fileName;

	// holds the status of the task
	private boolean complete;

	public FileCheckerTask(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public boolean isComplete() {
		return complete;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T call() {
		File f = new File(fileName);
		complete = f.exists();
		return (T) Boolean.valueOf(complete);
	}
}
