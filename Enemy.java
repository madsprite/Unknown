package Unknown;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy{

	public int e_x = 0;
	public int e_y = 0;
	public int es_x = 20;
	public int es_y = 20;
	protected MainLevel game;
	public int shiftx = 0;
	public int shifty = 0;
	BufferedImage bimg = null;

	int player_direction = 1;
	int player_counter = 0;
	int player_f=0;
	BufferedImage enemyspritesheet;
	public Boolean inScreen = true, falling = false, cleft = true,
			cright = true;
	public int row, col;
	private int movx, movy, stx, sty;
	private int moving = 0;
	int collision = 0, floorbl;
	/** The rectangle used for this entity during collisions resolution */
	private Rectangle me = new Rectangle();
	/** The rectangle used for other entities during collision resolution */
	private Rectangle him = new Rectangle();

	/**
	 * 
	 */

	public Enemy(MainLevel gme, int x, int y, int sx, int sy, int r, int c) {
		this.game = gme;
		this.e_x = x;
		this.e_y = y;
		this.es_x = sx;
		this.es_y = sy;
		this.movx = x;
		this.movy = y;
		this.row = r;
		this.col = c;
		this.init();
	}

	public void update(Player other) {
		shiftx = e_x - MainLevel.mx;
		shifty = e_y - MainLevel.my;
		if ((shiftx < (Game.WIDTH - 50) && shiftx > (0 + 50))
				&& ((shifty < (Game.HEIGHT -50)) && (shifty > (0 +100)))) {
			inScreen = true;
		} else {
			inScreen = false;
		}
		stx = e_x;
		moving = 0;
		// AI Thinking starts here
		// When Enemy gets on screen Enable thinking
		if (inScreen = true) {
			// Start reacting to player
			if (detectPlayer(other)) {
				if (shiftx + (es_x / 2) > other.shiftx + (other.s_x / 2)
						&& cleft == true) {
					movx -= 3;
					moving = -1;
					e_x = (int) (stx + (0.2 * (movx - stx)));
				} else if (shiftx + (es_x / 2) < other.shiftx + (other.s_x / 2)
						&& cright == true) {
					movx += 3;
					moving = 1;
					e_x = (int) (stx + (0.2 * (movx - stx)));
				} else {
					moving = 0;
				}
			}

			// Detect player collision
			if(collidesWith(other)){
				//MainLevel.enemies.remove(this);
			}
			
			// Enemy Collision with blocks
			// detect collisions with player
			collision = 0;
			falling=true;
			cright = true;
			cleft = true;
			for (int p = 0; p < MainLevel.blocks.size(); p++) {
				Block me = (Block) MainLevel.blocks.get(p);
				if (me.ecollidesWith(this)) {
					//System.out.println("collision detected between enemy and " + me
						//	+ " E" + p);
					fall(me);
				}
			}
			if (falling == true) {
				movy += 15;
			}

			sty = e_y;
			e_y = (int) (sty + (0.5 * (movy - sty)));
		}else{moving=0;}
		// Enemy Animation goes here
		if (moving == 1) {
			player_counter++;
			player_direction = 1;
			player_f = 0;
			bimg = player_crop(player_counter / 5, 0);
		} else if (moving == -1) {
			player_counter++;
			player_direction = -1;
			player_f = 1;
			bimg = player_crop(player_counter / 5, 0);
		} else if (moving == 0 && falling == false) {
			bimg = player_crop(0, 0);
			player_counter = 0;
		}
		if (falling == true) {
			bimg = player_crop(2, 2);
			player_counter = 0;
		}
		if (player_counter > 40) {
			player_counter = 0;
		}
		if(e_y>(MainLevel.map.length+2)*MainLevel.map_ySize){
			//MainLevel.enemies.remove(this);
		}
		
	}

	public void draw(Graphics g) {
		g.drawImage(bimg, shiftx + (es_x * player_f), shifty+6,
				(player_direction) * es_x, es_y, game);
	}

	public boolean detectPlayer(Player other) {
		me.setBounds(shiftx - 150, shifty - 150, es_x + 300, es_y + 300);
		him.setBounds(other.shiftx, other.shifty, other.s_x, other.s_y);
		return me.intersects(him);

	}

	public boolean collidesWith(Player other) {
		me.setBounds(shiftx - 6, shifty - 4, es_x + 12, es_y + 8);
		him.setBounds(other.shiftx + 4, other.shifty, other.s_x - 8, other.s_y);
		if(me.intersects(him)){
		//	System.out.print("Detect collision "+this+" with pl E"+row+"+"+col);
		}
		return me.intersects(him);
	}

	protected void init() {
		try {
			enemyspritesheet = ImageIO.read(new File("pic/enee.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("can't load enee.png");
		}
	}
	public BufferedImage player_crop(int col, int row) {
		return enemyspritesheet.getSubimage(col * 79, row * 79, 79, 79);
	}

	private void fall(Block me) {
		if ((me.type == 40) || (me.type == 41) || (me.type == 42)
				|| (me.type == 43) || (me.type == 20) || (me.type == 21)
				|| (me.type == 22) || (me.type == 23) || (me.type == 24)
				|| (me.type == 31) || (me.type == 32) || (me.type == 33)
				|| (me.type == 34) || (me.type == 103) || (me.type == 104)
				|| (me.type == 105) || (me.type == 106)) {
			collision++;
			if (me.ecollidefloor(this)) {
				floorbl++;
				falling = false;
				movy = me.b_y+(es_y*2)+2;// - es_y + 2;
				sty = me.b_y+(es_y*2)+2; //- es_y + 2;
				e_y = me.b_y+(es_y*2)+2;// - es_y + 2;
			} else if (me.ecollideblock(this)) {
				if (me.epinpoint(this) == 1) {
					cleft = false;
				} else if (me.epinpoint(this) == 0) {
					cright = false;
				}
			}else{floorbl = 0;}
		}
	}
}
