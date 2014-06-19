package Unknown;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Player extends JPanel {

	private static final long serialVersionUID = 1L;

	public int b_x = 0;
	public int b_y = 0;
	public int s_x = 20;
	public int s_y = 20;
	public int type = 0;
	protected MainLevel game;
	public int shiftx = 0;
	public int shifty = 0;
	public Boolean inScreen = true;
	BufferedImage bimg = null;
	BufferedImage playsprite;

	int player_direction = 1;
	int player_counter = 0;
	int player_f=0;
	BufferedImage playerspritesheet;

	public Player(MainLevel gme, int x, int y, int sx, int sy) {
		this.game = gme;
		this.b_x = x;
		this.b_y = y;
		this.s_x = sx;
		this.s_y = sy;
		init();
	}

	public void update() {
		shiftx = MainLevel.mx - b_x + (Game.WIDTH / 2);
		shifty = MainLevel.my - b_y + ((Game.HEIGHT / 8) * 3);

		if(GamePanel.up==true && MainLevel.climb==true){
			bimg= player_crop(player_counter / 5, 7);
			player_counter++;
		}else if (GamePanel.down==true && MainLevel.climb==true){
			bimg= player_crop(player_counter / 5, 7);
			player_counter--;
			if(player_counter<1){
				player_counter=0;
			}
		}else if (GamePanel.right == true) {
			player_counter++;
			player_direction = 1;
			player_f=0;
			bimg = player_crop(player_counter/ 5, 0);
		} else if (GamePanel.left == true) {
			player_counter++;
			player_direction = -1;
			player_f=1;
			bimg = player_crop(player_counter / 5, 0);
		} else if (GamePanel.right == false && GamePanel.left == false
				&& MainLevel.falling == false) {
			bimg = player_crop(0, 0);
			player_counter = 0;
		}
		if (MainLevel.falling == true && MainLevel.climb==false) {
			bimg = player_crop(2, 2);
			player_counter = 0;
		}
		if (player_counter > 40) {
			player_counter = 0;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(bimg, shiftx + (s_x * player_f), shifty+8,
				(player_direction) * s_x, s_y, game);

	}

	/**
	 * Check if this entity collised with another.
	 * 
	 * @param other
	 *            The other entity to check collision against
	 * @return True if the entities collide with each other
	 * 
	 *         public boolean collidesWith(int x, int y, int width, int height)
	 *         { me.setBounds(shiftx, shifty, s_x, s_y); him.setBounds(x, y,
	 *         width, height);
	 * 
	 *         return me.intersects(him); }
	 */
	public BufferedImage player_crop(int col, int row) {
		return playerspritesheet.getSubimage(col * 80, row * 80, 80, 80);
	}

	protected void init() {

		try {
			playerspritesheet = ImageIO.read(new File("pic/56412.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("can't load 56412.png");
		}

	}
}
