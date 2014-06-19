package Unknown;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block {
	public int b_x = 0;
	public int b_y = 0;
	public int s_x = 20;
	public int s_y = 20;
	public int type = 0;
	private MainLevel game;
	public int shiftx = 0;
	public int shifty = 0;
	public Boolean inScreen = true;
	public int row, col;
	BufferedImage bimg = null;
	/** The rectangle used for this entity during collisions resolution */
	private Rectangle me = new Rectangle();
	/** The rectangle used for other entities during collision resolution */
	private Rectangle him = new Rectangle();
	private Rectangle floor = new Rectangle();
	private Rectangle noCollide = new Rectangle();

	public Block(MainLevel gme, int x, int y, int sx, int sy, int t, int r,
			int c) {
		this.game = gme;
		this.b_x = x;
		this.b_y = y;
		this.s_x = sx;
		this.s_y = sy;
		this.type = t;
		this.row = r;
		this.col = c;
	}

	public void update() {
		shiftx = b_x - MainLevel.mx+(Game.WIDTH/2);
		shifty = b_y - MainLevel.my+((Game.HEIGHT/8)*3);
		if ((shiftx < (Game.WIDTH + 200) && shiftx > (0 - 200))
				&& ((shifty < (Game.HEIGHT + 120)) && (shifty > (0 - 120)))) {
			inScreen = true;
		} else {
			inScreen = false;
		}
		for(int ti = 1; ti<=115; ti++){
			if(type==ti){
			this.bimg=(crop((ti-1)%19,(ti-1)/19,63,63));
			}
		}
	}

	public void draw(Graphics g) {
		if (!(type == 0)) {
			if (inScreen = true) {
				g.drawImage(bimg, shiftx, shifty, s_x, s_y, game);
			}
		}
	}

	public BufferedImage crop(int col, int row, int w, int h) {
		return MainLevel.mapspritesheet.getSubimage(col * 63, row * 63, w, h);
	}

	/**
	 * Check if this entity collised with another.
	 * 
	 * @param other
	 *            The other entity to check collision against
	 * @return True if the entities collide with each other
	 */
	public boolean collidesWith(Player other) {
		me.setBounds(shiftx-4, shifty-4, s_x+8, s_y+8);
		him.setBounds(other.shiftx+4, other.shifty, other.s_x-8, other.s_y);
		//System.out.print(other.b_x+" - "+other.b_y+"{}");

		return me.intersects(him);
	}
	public boolean collidefloor(Player other) {
		floor.setBounds(shiftx+6,shifty+1,s_x-4,1);
		him.setBounds(other.shiftx+4, other.shifty, other.s_x-8, other.s_y);
		boolean r = false;
		if(floor.intersects(him)){
			if(other.b_y+other.s_y < b_y+(s_y/3) ){
				r=true;
			}
		}
		return r;
	}
	public boolean collideblock(Player other) {
		noCollide.setBounds(shiftx-4,shifty+2,s_x+8,s_y-2);
		him.setBounds(other.shiftx+4, other.shifty, other.s_x-8, other.s_y);
		return noCollide.intersects(him);
	}
	public int pinpoint(Player other){
		int r = 1;
		if(shiftx+(s_x/2)>(other.shiftx+(other.s_x/2))){
			r=0;
		}
		return r;
	}
	public boolean nojump(Player other){
		noCollide.setBounds(shiftx+6,shifty+s_y-4,s_x-12,2);
		him.setBounds(other.shiftx+4, other.shifty, other.s_x-8, other.s_y);
		return noCollide.intersects(him);
	}
	
	//Collision check with AI
	public boolean ecollidesWith(Enemy other) {
		me.setBounds(shiftx-6, shifty-4, s_x+12, s_y+8);
		him.setBounds(other.shiftx+4, other.shifty, other.es_x-8, other.es_y);
		//System.out.print(other.b_x+" - "+other.b_y+"{}");

		return me.intersects(him);
	}
	public boolean ecollidefloor(Enemy other) {
		floor.setBounds(shiftx+6,shifty+1,s_x-4,1);
		him.setBounds(other.shiftx+4, other.shifty, other.es_x-8, other.es_y);
		boolean r = false;
		if(floor.intersects(him)){
			if(other.e_y+other.es_y < b_y+(s_y/3) ){
				r=true;
				
			}
		}
		return floor.intersects(him);
	}
	public boolean ecollideblock(Enemy other) {
		noCollide.setBounds(shiftx-4,shifty+2,s_x+8,s_y-2);
		him.setBounds(other.shiftx+4, other.shifty, other.es_x-8, other.es_y);
		return noCollide.intersects(him);
	}
	public int epinpoint(Enemy other){
		int r = 1;
		if(shiftx+(s_x/2)>(other.shiftx+(other.es_x/2))){
			r=0;
		}
		return r;
	}
}
