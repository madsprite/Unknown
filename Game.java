package Unknown;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Game {

	public static JFrame frame = new JFrame();

	static Toolkit tk = Toolkit.getDefaultToolkit();
	static int WIDTH = ((int) tk.getScreenSize().getWidth());
	static int HEIGHT = ((int) tk.getScreenSize().getHeight());

	static int staticWIDTH = ((int) tk.getScreenSize().getWidth());
	static int staticHEIGHT = ((int) tk.getScreenSize().getHeight());

	public static void main(String[] arg) {

		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("UNKNOWN");
		frame.setUndecorated(true);
		frame.setContentPane(new GamePanel());
		frame.setVisible(true);

	}

}
