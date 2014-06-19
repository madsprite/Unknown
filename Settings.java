package Unknown;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Settings extends JPanel {

	private static final long serialVersionUID = 1L;

	static Color select4 = new Color(0, 0, 0);
	static Color select5 = new Color(255, 255, 255);
	static Color select6 = new Color(255, 255, 255);

	static int generalsettings = 0;
	static int settingscounter = 0;
	static int settingscountery = (Game.HEIGHT / 2) + 50;
	static int settingstemp = 0;
	static int videocounter = 2;
	static int soundcounter = 0;

	static String videostring = "<" + Integer.toString(Game.WIDTH) + " x "
			+ Integer.toString(Game.HEIGHT) + ">";

	static String soundstring = "<ON>";

	public Settings() {
	}

	public void draw(Graphics2D g) {
		super.paint(g);

		g.setFont(Loading.menu_font2);
		g.setColor(Color.WHITE);
		g.drawString("UNKNOWN", Game.WIDTH / 2 - 150, 300);

		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(0, settingscountery - 40 + settingstemp, Game.WIDTH, 50);

		g.setFont(GamePanel.menu_font);
		g.setColor(select4);
		g.drawString("VIDEO", 50, (Game.HEIGHT / 2) + 50);
		g.drawString(videostring, Game.WIDTH - 200, (Game.HEIGHT / 2) + 50);
		g.setColor(select5);
		g.drawString("SOUND", 50, (Game.HEIGHT / 2) + 100);
		g.drawString(soundstring, Game.WIDTH - 200, (Game.HEIGHT / 2) + 100);
		g.setColor(select6);
		g.drawString("BACK", 50, (Game.HEIGHT / 2) + 150);

	}

	public void update() {

		settingscountery = (Game.HEIGHT / 2) + 50;

		if (settingscounter == 2) {
			if (settingstemp < 100) {
				settingstemp += 10;
			}
		}
		if (settingscounter == 1) {
			if (settingstemp < 50) {
				settingstemp += 10;
			} else if (settingstemp > 50) {
				settingstemp -= 10;
			}
		}
		if (settingscounter == 0) {
			if (settingstemp > 0) {
				settingstemp -= 10;
			}
		}

		if (generalsettings == 0) {
			if (videocounter == 0) {
				videostring = "<800 x 600>";
			} else if (videocounter == 1) {
				videostring = "<1000 x 600>";
			} else if (videocounter == 2) {
				videostring = "<" + Integer.toString(Game.WIDTH) + " x "
						+ Integer.toString(Game.HEIGHT) + ">";
			}
		}

		if (generalsettings == 1) {
			if (soundcounter == 0) {
				soundstring = "<ON>";
			} else if (soundcounter == 1) {
				soundstring = "<OFF>";
			}
		}
	}

}
