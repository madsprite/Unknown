package Unknown;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Selectlevels extends JPanel {

	private static final long serialVersionUID = 1L;

	int temp1x = Game.WIDTH / 2 - 100;
	int temp1y = Game.HEIGHT * 9 / 10;

	Color level1color = new Color(0, 0, 0);
	Color level2color = new Color(255, 255, 255);
	Color level3color = new Color(255, 255, 255);
	Color level4color = new Color(255, 255, 255);
	Color level5color = new Color(255, 255, 255);
	Color level6color = new Color(255, 255, 255);
	Color level7color = new Color(255, 255, 255);
	Color level8color = new Color(255, 255, 255);
	Color level9color = new Color(255, 255, 255);
	Color level10color = new Color(255, 255, 255);

	static int levelscounter = 0;
	int levelscounterx = Game.WIDTH / 2 - 100 - 3;
	int levelscountery = Game.HEIGHT * 9 / 10 - 3;

	static String[] mapstxt = { "level0.png", "level1.png", "level2.png",
			"level3.png", "level4.png", "level5.png", "level6.png",
			"level7.png", "level8.png", "level9.png" };

	String levelslock = "pic/lock.png";

	static ImageIcon maps_res = new ImageIcon("pic/" + mapstxt[levelscounter]);
	ImageIcon lockres = new ImageIcon(levelslock);

	static Image maps = maps_res.getImage();
	Image lock = lockres.getImage();

	static boolean[] levelsunlock = { true, false, false, false, false, false,
			false, false, false, false };

	public Selectlevels() {
	}

	public void draw(Graphics2D g) {
		super.paint(g);

		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(levelscounterx, levelscountery, 16, 16);
		g.fillRect(0, Game.HEIGHT / 10 - 10, Game.WIDTH, 50);

		g.setColor(Color.BLACK);
		g.setFont(GamePanel.menu_font);
		g.drawString("SELECT A LEVEL", Game.WIDTH / 6, Game.HEIGHT / 10 + 30);

		g.setColor(level1color);
		g.fillRect(temp1x, temp1y, 10, 10);

		g.setColor(level2color);
		g.fillRect(temp1x + 20, temp1y, 10, 10);

		g.setColor(level3color);
		g.fillRect(temp1x + 40, temp1y, 10, 10);

		g.setColor(level4color);
		g.fillRect(temp1x + 60, temp1y, 10, 10);

		g.setColor(level5color);
		g.fillRect(temp1x + 80, temp1y, 10, 10);

		g.setColor(level6color);
		g.fillRect(temp1x + 100, temp1y, 10, 10);

		g.setColor(level7color);
		g.fillRect(temp1x + 120, temp1y, 10, 10);

		g.setColor(level8color);
		g.fillRect(temp1x + 140, temp1y, 10, 10);

		g.setColor(level9color);
		g.fillRect(temp1x + 160, temp1y, 10, 10);

		g.setColor(level10color);
		g.fillRect(temp1x + 180, temp1y, 10, 10);

		g.drawImage(maps, Game.WIDTH / 6, (Game.HEIGHT * 2 / 10) - 10,
				Game.WIDTH * 4 / 6, Game.HEIGHT * 7 / 10, this);

		g.drawImage(lock, Game.WIDTH / 2 - 128, Game.HEIGHT / 2 - 128, this);

		// g.drawImage(maps,0, (Game.HEIGHT * 2 / 10) - 10,
		// Game.WIDTH , Game.HEIGHT * 7 / 10, this);

	}

	public void update() {

		lockres = new ImageIcon(levelslock);
		lock = lockres.getImage();

		maps_res = new ImageIcon("pic/" + mapstxt[levelscounter]);
		maps = maps_res.getImage();

		temp1x = Game.WIDTH / 2 - 100;
		temp1y = Game.HEIGHT * 9 / 10;

		levelscounterx = (Game.WIDTH / 2 - 100 - 3) + 20 * levelscounter;
		levelscountery = Game.HEIGHT * 9 / 10 - 3;

		if (levelscounter == 0) {
			level1color = new Color(0, 0, 0);
			level2color = new Color(255, 255, 255);
			level3color = new Color(255, 255, 255);
			level4color = new Color(255, 255, 255);
			level5color = new Color(255, 255, 255);
			level6color = new Color(255, 255, 255);
			level7color = new Color(255, 255, 255);
			level8color = new Color(255, 255, 255);
			level9color = new Color(255, 255, 255);
			level10color = new Color(255, 255, 255);
			if (levelsunlock[levelscounter] == false) {
				levelslock = "pic/lock.png";
			} else {
				levelslock = "";
			}
		} else if (levelscounter == 1) {
			level1color = new Color(255, 255, 255);
			level2color = new Color(0, 0, 0);
			level3color = new Color(255, 255, 255);
			level4color = new Color(255, 255, 255);
			level5color = new Color(255, 255, 255);
			level6color = new Color(255, 255, 255);
			level7color = new Color(255, 255, 255);
			level8color = new Color(255, 255, 255);
			level9color = new Color(255, 255, 255);
			level10color = new Color(255, 255, 255);
			if (levelsunlock[levelscounter] == false) {
				levelslock = "pic/lock.png";
			} else {
				levelslock = "";
			}
		} else if (levelscounter == 2) {
			level1color = new Color(255, 255, 255);
			level2color = new Color(255, 255, 255);
			level3color = new Color(0, 0, 0);
			level4color = new Color(255, 255, 255);
			level5color = new Color(255, 255, 255);
			level6color = new Color(255, 255, 255);
			level7color = new Color(255, 255, 255);
			level8color = new Color(255, 255, 255);
			level9color = new Color(255, 255, 255);
			level10color = new Color(255, 255, 255);
			if (levelsunlock[levelscounter] == false) {
				levelslock = "pic/lock.png";
			} else {
				levelslock = "";
			}
		} else if (levelscounter == 3) {
			level1color = new Color(255, 255, 255);
			level2color = new Color(255, 255, 255);
			level3color = new Color(255, 255, 255);
			level4color = new Color(0, 0, 0);
			level5color = new Color(255, 255, 255);
			level6color = new Color(255, 255, 255);
			level7color = new Color(255, 255, 255);
			level8color = new Color(255, 255, 255);
			level9color = new Color(255, 255, 255);
			level10color = new Color(255, 255, 255);
			if (levelsunlock[levelscounter] == false) {
				levelslock = "pic/lock.png";
			} else {
				levelslock = "";
			}
		} else if (levelscounter == 4) {
			level1color = new Color(255, 255, 255);
			level2color = new Color(255, 255, 255);
			level3color = new Color(255, 255, 255);
			level4color = new Color(255, 255, 255);
			level5color = new Color(0, 0, 0);
			level6color = new Color(255, 255, 255);
			level7color = new Color(255, 255, 255);
			level8color = new Color(255, 255, 255);
			level9color = new Color(255, 255, 255);
			level10color = new Color(255, 255, 255);
			if (levelsunlock[levelscounter] == false) {
				levelslock = "pic/lock.png";
			} else {
				levelslock = "";
			}
		} else if (levelscounter == 5) {
			level1color = new Color(255, 255, 255);
			level2color = new Color(255, 255, 255);
			level3color = new Color(255, 255, 255);
			level4color = new Color(255, 255, 255);
			level5color = new Color(255, 255, 255);
			level6color = new Color(0, 0, 0);
			level7color = new Color(255, 255, 255);
			level8color = new Color(255, 255, 255);
			level9color = new Color(255, 255, 255);
			level10color = new Color(255, 255, 255);
			if (levelsunlock[levelscounter] == false) {
				levelslock = "pic/lock.png";
			} else {
				levelslock = "";
			}
		} else if (levelscounter == 6) {
			level1color = new Color(255, 255, 255);
			level2color = new Color(255, 255, 255);
			level3color = new Color(255, 255, 255);
			level4color = new Color(255, 255, 255);
			level5color = new Color(255, 255, 255);
			level6color = new Color(255, 255, 255);
			level7color = new Color(0, 0, 0);
			level8color = new Color(255, 255, 255);
			level9color = new Color(255, 255, 255);
			level10color = new Color(255, 255, 255);
			if (levelsunlock[levelscounter] == false) {
				levelslock = "pic/lock.png";
			} else {
				levelslock = "";
			}
		} else if (levelscounter == 7) {
			level1color = new Color(255, 255, 255);
			level2color = new Color(255, 255, 255);
			level3color = new Color(255, 255, 255);
			level4color = new Color(255, 255, 255);
			level5color = new Color(255, 255, 255);
			level6color = new Color(255, 255, 255);
			level7color = new Color(255, 255, 255);
			level8color = new Color(0, 0, 0);
			level9color = new Color(255, 255, 255);
			level10color = new Color(255, 255, 255);
			if (levelsunlock[levelscounter] == false) {
				levelslock = "pic/lock.png";
			} else {
				levelslock = "";
			}
		} else if (levelscounter == 8) {
			level1color = new Color(255, 255, 255);
			level2color = new Color(255, 255, 255);
			level3color = new Color(255, 255, 255);
			level4color = new Color(255, 255, 255);
			level5color = new Color(255, 255, 255);
			level6color = new Color(255, 255, 255);
			level7color = new Color(255, 255, 255);
			level8color = new Color(255, 255, 255);
			level9color = new Color(0, 0, 0);
			level10color = new Color(255, 255, 255);
			if (levelsunlock[levelscounter] == false) {
				levelslock = "pic/lock.png";
			} else {
				levelslock = "";
			}
		} else if (levelscounter == 9) {
			level1color = new Color(255, 255, 255);
			level2color = new Color(255, 255, 255);
			level3color = new Color(255, 255, 255);
			level4color = new Color(255, 255, 255);
			level5color = new Color(255, 255, 255);
			level6color = new Color(255, 255, 255);
			level7color = new Color(255, 255, 255);
			level8color = new Color(255, 255, 255);
			level9color = new Color(255, 255, 255);
			level10color = new Color(0, 0, 0);
			if (levelsunlock[levelscounter] == false) {
				levelslock = "pic/lock.png";
			} else {
				levelslock = "";
			}
		}

	}

}
