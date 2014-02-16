package util.tasks;

import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;

import task.ITask;

public class PortAvailableTask<T> implements ITask<T> {

	private int port;
	private boolean complete;

	public PortAvailableTask(int i) {
		port = i;
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return complete;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T call() {
		// TODO Auto-generated method stub
		System.out.println("Checking port " + port);

		try {
			Socket socket = new Socket("localhost", port);
			System.out.println("Port " + port + " is available");
			complete = true;

		} catch (UnknownHostException e) {
			System.out.println("Exception occured" + e);
		} catch (IOException e) {
		}

		return (T) Boolean.valueOf(complete);
	}
}
