package by.zti.lesson15p2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import by.zti.lesson15.Server;

/**
 * Стартовый класс программы
 * 
 * <p>
 * Сделанно в образовательных целях, специально для канала Zhirni Toni Inc.
 * </p>
 * 
 * <p>
 * Вторая программа для туториала, является клиентом для ранее созданного локального сервера.
 * </p>
 * 
 * @author Cvazer
 * @version 1.0
 * @since 1.0
 */
public class Main {
	static JLabel l1;
	public static void setL1(String s) {
		l1.setText(s);
	}

	static Server s = new Server();
	static Thread th = new Thread (s);
	static JButton b1 = new JButton("Send");
	static JTextField t1 = new JTextField(10);
	static JFrame frame = new JFrame("Message");
	static Connector cn = new Connector();
	
	/**
	 * @param args Массив аргументов для программы.
	 */
	public static void main(String[] args) {	
		th.start();
		l1 = new JLabel("");
		Thread th1 = new Thread(cn);
		th1.start();
		l1.setText("Соединения устанавливаются...");
		createGUI();	
		
		
		//System.out.println("Соединения устанавливаются...");
		
		
	}

	/**
	 * Метод для создания интерфейса
	 */
	private static void createGUI() {
		frame.setLayout(new FlowLayout());
		frame.add(l1);
		frame.add(t1);
		frame.add(b1);
		frame.pack();
		frame.setSize(340, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		b1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==b1){
					cn.sendData(t1.getText());
				}
			}
		});
	}
}
