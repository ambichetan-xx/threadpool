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

	private String fileName;
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
		// perform the "File(fileName).isFile()" task again
		File f = new File(fileName);
		complete = f.exists();
		return (T) Boolean.valueOf(complete);
	}
}
