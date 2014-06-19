package Unknown;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;

	static String play = "PLAY";
	String setting = "SETTING";
	String exit = "EXIT";

	static int selectcounter = 0;
	static int selectcounter_y = (Game.HEIGHT / 2) + 50;
	static int selectcounter_x = Game.WIDTH / 2;
	static int selecttemp = 0;

	static Color select1 = new Color(0, 0, 0);
	static Color select2 = new Color(255, 255, 255);
	static Color select3 = new Color(255, 255, 255);

	// AudioStream as;
	// InputStream in;

	public Menu() {

		/*
		 * try { in = new FileInputStream(
		 * "C:/Users/potatohunter96/Music/indie game the movie soundtrack/mp3/Track 01 - Maybe You'll Get Some, Maybe You Won't - Indie Game_ The Movie OST Main Theme Soundtrack.mp3"
		 * ); } catch (FileNotFoundException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } try { as = new AudioStream(in); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		/*
		 * try { Clip clip = AudioSystem.getClip(); AudioInputStream inputStream
		 * = AudioSystem .getAudioInputStream(Main.class
		 * .getResourceAsStream("/path/to/sounds/" +
		 * "C:/Users/potatohunter96/Desktop/1.wav")); clip.open(inputStream);
		 * clip.start(); } catch (Exception e) {
		 * System.err.println(e.getMessage()); }
		 */

	}

	//
	//
	// ////////////////////////////////////////////////////////////////////////////

	public void draw(Graphics2D g) {
		super.paint(g);

		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(selectcounter_x - 150, selectcounter_y - 40 + selecttemp,
				300, 50);

		g.setFont(GamePanel.menu_font);
		g.setColor(select1);
		g.drawString(play, Game.WIDTH / 2 - 50, (Game.HEIGHT / 2) + 50);
		g.setColor(select2);
		g.drawString(setting, Game.WIDTH / 2 - 50, (Game.HEIGHT / 2) + 100);
		g.setColor(select3);
		g.drawString(exit, Game.WIDTH / 2 - 50, (Game.HEIGHT / 2) + 150);

		g.setFont(Loading.menu_font2);
		g.setColor(Color.WHITE);
		g.drawString("UNKNOWN", Game.WIDTH / 2 - 150, 300);

	}

	//
	//
	// ////////////////////////////////////////////////////////////////////////////

	public void update() {

		selectcounter_y = (Game.HEIGHT / 2) + 50;
		selectcounter_x = Game.WIDTH / 2;

		if (selectcounter == 2) {
			if (selecttemp < 100) {
				selecttemp += 10;
			}
		}
		if (selectcounter == 1) {
			if (selecttemp < 50) {
				selecttemp += 10;
			} else if (selecttemp > 50) {
				selecttemp -= 10;
			}
		}
		if (selectcounter == 0) {
			if (selecttemp > 0) {
				selecttemp -= 10;
			}
		}
	}

}
