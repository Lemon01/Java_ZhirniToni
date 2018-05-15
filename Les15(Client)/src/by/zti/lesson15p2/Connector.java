package by.zti.lesson15p2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *  ласс дл€ св€зи с сервером
 * 
 * <p>
 * ѕодготовленно в образовательных цел€х, специально дл€ канала Zhirni Toni Inc.
 * </p>
 * 
 * <p>
 *  ласс, выполн€емый в отдельном потоке, 
 * предназначенный дл€ установлени€ соединени€ 
 * между клиентом и сервером
 * </p>
 * 
 * @author Cvazer
 * @created 09.07.2013
 * @version 1.0
 * @since 1.0
 */
public class Connector implements Runnable{
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	private static Socket connection;
	private static boolean isRunning = true;
	
	/**
	 * ћетод стартующий при запуске потока.
	 * 
	 * ”станавливает соединени€ и начинает читать input.
	 */
	@Override
	public void run() {
		try {
			while(isRunning){
				connection = new Socket(InetAddress.getByName("192.168.1.40"), 1234);
				output = new ObjectOutputStream(connection.getOutputStream());
				output.flush();
				input = new ObjectInputStream(connection.getInputStream());
				Main.setL1("—оединение установленно");
				JOptionPane.showMessageDialog(null, (String)input.readObject());
			}
			close();
		}catch(Exception e) {e.printStackTrace();}
	}
	
	/**
	 * ѕередает данные на сервер.
	 * 
	 * @param s —трока, которую нужно передать.
	 */
	public void sendData(String s){
		try {
			output.flush();
			output.writeObject(s);
			output.flush();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	/**
	 * «акрывает соединени€
	 * 
	 * ћетод закрывает соединени€, чтобы не возникало 
	 * дальнейших проблем при последующем запуске приложени€
	 */
	public void close() {
		try {
			isRunning = false;
			output.close();
			input.close();
			connection.close();
		} catch (Exception e) {e.printStackTrace();}
	}

}
