package util.tasks;

import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;

import task.ITask;

public class PortAvailableTask<T> implements ITask<T> {

	// holds the port value
	private int port;

	// holds the status of the task
	private boolean complete;

	public PortAvailableTask(int i) {
		port = i;
	}

	@Override
	public boolean isComplete() {
		return complete;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T call() {
		System.out.println("Checking port " + port);

		try {
			/*
			 * trying to create a socket with the port mentioned. If the socket
			 * is created successfully, the port is available, else not. The
			 * local address can be replaced with server address if we need to
			 * check a port availability in a server
			 */
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
