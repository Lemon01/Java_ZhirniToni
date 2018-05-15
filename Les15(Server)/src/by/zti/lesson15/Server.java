package by.zti.lesson15;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private boolean isRunning = true;
	
	/**
	 * Метод для старта сервера
	 * Открывает и устаннавливает соединения и потоки.
	 * Принимает входящий поток и отправляет его с припиской назад.
	 */
	public void ServerStarter(){
		try{
			server = new ServerSocket(1235, 100);
			while (isRunning) {
				connection = server.accept();
				output = new ObjectOutputStream(connection.getOutputStream());
				output.flush();
				input = new ObjectInputStream(connection.getInputStream());
				output.writeObject("Вы прислали: "+input.readObject());
				output.flush();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
	}
	
	/**
	 * Метод для закрытия потоков и соединений.
	 */
	private void close() {
		try{
			output.close();
			input.close();
			connection.close();
			isRunning = false;
		}catch(Exception e){e.printStackTrace();}
	}

	@Override
	public void run() {
		ServerStarter();
	}

}
