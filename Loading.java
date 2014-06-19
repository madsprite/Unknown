package Unknown;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Loading extends JPanel {

	private static final long serialVersionUID = 1L;

	static int loadingrect = 0;
	int temp1 = Game.HEIGHT - (Game.HEIGHT / 6);

	String ang_loading = "0";

	static Font menu_font2 = new Font("Agency FB", Font.BOLD, 90);
	static Font menu_font23 = new Font("Agency FB", Font.BOLD, 30);

	static int tempoo = 0;

	static int numo = 16;
	static boolean filesb[] = new boolean[numo];

	boolean level0_fileb = true;
	int level0_filey = Game.HEIGHT - 100;
	int level0_fileopc = 255;

	BufferedImage loadingspritesheet;

	int loadingrow = 0;
	int loadingcol = 0;

	int tempoooi = 0;
	static String tempoooo;
	static boolean tempoooob = true;

	public Loading() {
		for (int i = 0; i < filesb.length; i++) {
			filesb[i] = false;
		}
		init();
	}

	//
	//
	// ////////////////////////////////////////////////////////////////////////////

	public void draw(Graphics2D g) {
		super.paint(g);

		g.setFont(menu_font2);

		temp1 = Game.HEIGHT - (Game.HEIGHT / 6);

		g.setColor(Color.WHITE);
		g.drawString("UNKNOWN", Game.WIDTH / 2 - 150, 300);

		/*
		 * g.setColor(Color.WHITE); g.fillRect(0, temp1 - 1, Game.WIDTH, 12);
		 * g.setColor(Color.DARK_GRAY); g.fillRect(0, temp1, loadingrect, 10);
		 */

		g.setFont(menu_font23);
		g.setColor(Color.WHITE);

		if (tempoooob == true) {
			if (tempoooi < 10) {
				tempoooo = "+checking: " + GamePanel.f.getName() + ".";
				tempoooi++;
			} else if (tempoooi < 20) {
				tempoooo = "+checking: " + GamePanel.f.getName() + "..";
				tempoooi++;
			} else if (tempoooi < 30) {
				tempoooo = "+checking: " + GamePanel.f.getName() + "...";
				tempoooi++;
			} else
				tempoooi = 0;
		}
		g.drawString(tempoooo, Game.WIDTH / 2 - 100, Game.HEIGHT -20);

		g.drawImage(crop(loadingcol, loadingrow, 188, 188),
				Game.WIDTH / 2 - 94, Game.HEIGHT -200 - 94, this);

		g.setFont(GamePanel.menu_font);
		g.setColor(Color.WHITE);
		String tempooo = "" + ang_loading + " %";
		g.drawString(tempooo, Game.WIDTH / 2 - tempooo.length() * 5,
				Game.HEIGHT -200 + tempooo.length() * 5);

	}

	//
	//
	// ////////////////////////////////////////////////////////////////////////////

	public void update() throws InterruptedException {
		if (Loading.tempoo <= Loading.filesb.length) {
			Loading.tempoo++;
		}
		if (GamePanel.f.exists() && Loading.filesb[0] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/level0.png");
			Loading.filesb[0] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[1] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/level1.png");
			Loading.filesb[1] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[2] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/level2.png");
			Loading.filesb[2] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[3] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/level3.png");
			Loading.filesb[3] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[4] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/level4.png");
			Loading.filesb[4] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[5] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/level5.png");
			Loading.filesb[5] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[6] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/level6.png");
			Loading.filesb[6] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[7] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/level7.png");
			Loading.filesb[7] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[8] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/level8.png");
			Loading.filesb[8] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[9] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/level9.png");
			Loading.filesb[9] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[10] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/Image-Tileset2.png");
			Loading.filesb[10] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[11] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/11.jpg");
			Loading.filesb[11] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[12] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/lock.png");
			Loading.filesb[12] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[13] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/unknown1.png");
			Loading.filesb[13] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[14] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("pic/loading-sprite2.png");
			Loading.filesb[14] = false;
		} else if (GamePanel.f.exists() && Loading.filesb[15] == true) {
			Loading.loadingrect += GamePanel.adding;
			GamePanel.f = new File("sound/fullsound.wav");
			Loading.filesb[15] = false;
		} else if (GamePanel.f.exists() == false
				&& GamePanel.f.getName().equals("") == false) {
			Loading.tempoooo = "CAN'T LOAD: " + GamePanel.f.getName() + "!!!";
			Loading.tempoooob = false;
			System.out.println("CAN'T LOAD: " + GamePanel.f.getName() + "!!!");
			// System.exit(0);
		}

		if (loadingrect * 100 / Game.WIDTH / 2 < 49) {
			loadingcol = (int) loadingrect * 100 / Game.WIDTH / 2;
		} else
			loadingcol = 48;
		//System.out.println(loadingrow + "   " + loadingcol + "   "
			//	+ (int) loadingrect * 100 / Game.WIDTH / 2 + "   "
				//+ loadingrect * 100 / Game.WIDTH);

		if (tempoo < filesb.length) {
			filesb[tempoo] = true;
			ang_loading = Integer.toString(loadingrect * 100 / Game.WIDTH);
		}

		if (tempoooob == true)
			if (loadingrect < Game.WIDTH && tempoo > filesb.length) {
				loadingrect += 1;
				GamePanel.f = new File("");
				ang_loading = Integer.toString(loadingrect * 100 / Game.WIDTH);
			} else if (loadingrect * 100 / Game.WIDTH >= 100) {
				GamePanel.menub = true;
				GamePanel.loadingb = false;
			}
	}

	public BufferedImage crop(int col, int row, int w, int h) {
		return loadingspritesheet.getSubimage(col * 188, row * 188, w, h);
	}

	private void init() {

		try {
			loadingspritesheet = ImageIO.read(new File(
					"pic/loading-sprite2.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("can't load loading-sprite2.png");
		}

	}
}
