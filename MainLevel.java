package Unknown;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainLevel extends JPanel {

	private static final long serialVersionUID = 1L;

	boolean mapb = true;
	static int map_xSize = Game.HEIGHT / 8;
	static int map_ySize = Game.HEIGHT / 8;
	double backgroundx = 0;
	double backgroundy = 0;
	int map_x = 0;
	int map_y = Game.HEIGHT - map_ySize;

	int player_x = 0;
	int player_y = 0;
	int plxS = (Game.HEIGHT / 8);
	int plyS = (Game.HEIGHT / 8);
	int maxspeed = 40;
	int speed = 40;
	int stvalue = 0;
	int stx, sty;
	static int movx = (Game.WIDTH / 2) - ((Game.HEIGHT / 8) / 2 / 2);
	static int movy = (Game.HEIGHT / 2) - (Game.HEIGHT / 8 / 2);
	int stx1, sty1, movx1, movy1;
	static int jump = 100;
	boolean jumping = false;
	static boolean cjump = true;
	boolean cup, cdown, cleft, cright;
	int collision = 0;
	static boolean climb;
	int floorbl = 0;

	static boolean falling = false;

	double easing = 0.2;

	FileReader textfilereader;
	BufferedReader mapreader;
	public static BufferedImage mapspritesheet;
	BufferedImage playsprite;
	BufferedImage bg;

	int map_xlimit = 0;
	static int map_ylimit = 0;

	private Block block;
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	public static int mx = 0;
	public static int my = 0;
	public static int px = 0;
	public static int py = 0;

	public Player player;

	static int[][] map;
	
	private static boolean[][] blockm;

	public MainLevel() {
		super();
		init();
	}

	public void draw(Graphics2D g) {
		super.paint(g);

		g.setColor(Color.LIGHT_GRAY);
		g.drawString("MAIN LEVEL", Game.WIDTH / 2, Game.HEIGHT / 2);

		g.drawImage(bg, -(mx / 30), -(my / 60), bg.getWidth(), bg.getHeight(),
				this);
		if (Game.WIDTH >= map[0].length * map_xSize) {
			map_xlimit = map[0].length;
		} else if (Game.WIDTH < map[0].length * map_xSize) {
			map_xlimit = Game.WIDTH / map_xSize;
		}

		if (Game.HEIGHT >= map.length * map_ySize) {
			map_ylimit = 0;
		} else if (Game.HEIGHT < map.length * map_ySize) {
			map_ylimit = (map.length * map_ySize - Game.HEIGHT) / map_ySize;
		}

		if (mapb == true) {
			// cycle round drawing all the entities we have in the game
			for (int i = 0; i < blocks.size(); i++) {
				Block block = blocks.get(i);
				block.draw(g);
			}
			for (int i = 0; i < enemies.size(); i++) {
				Enemy enemy = enemies.get(i);
				enemy.draw(g);
			}
		}
		player.draw(g);

	}

	public void update(boolean right, boolean left, boolean up, boolean down) {
		if (Game.WIDTH >= map[0].length * map_xSize) {
			map_xlimit = map[0].length;
		} else if (Game.WIDTH < map[0].length * map_xSize) {
			map_xlimit = Game.WIDTH / map_xSize;
		}

		if (Game.HEIGHT >= map.length * map_ySize) {
			map_ylimit = 0;
		} else if (Game.HEIGHT < map.length * map_ySize) {
			map_ylimit = (map.length * map_ySize - Game.HEIGHT) / map_ySize;
		}
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {

				int map_reader = map[row][col];

				if (blockm[row][col] == false) {
					if ((((map_x * map_xSize) - mx) < Game.WIDTH + 240)
							&& ((map_x * map_xSize) - mx) > (0 - 805)
							&& (((map_y * map_ySize) - my) < (Game.HEIGHT + 240))
							&& ((map_y * map_ySize) - my) > (0 - 700)) {
						block = new Block(this, map_x * map_xSize, map_ySize
								* map_y, map_xSize, map_ySize, map_reader, row,
								col);
						blocks.add(block);
						blockm[row][col] = true;
					}
				}
				map_x++;
			}
			map_y++;
			map_x = 0;
		}
		map_y = 0;

		// cycle round update all the entities we have in the game
		for (int i = 0; i < blocks.size(); i++) {
			Block block = (Block) blocks.get(i);
			block.update();
			if (block.inScreen == false) { // if the block updates and finds
											// itself out of the user's screen,
											// make itself invalid to print and
											// remove
				blockm[block.row][block.col] = false;
				blocks.remove(block);
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			enemy.update(player);
		}
		// System.out.println(enemy);

		// detect collisions with player
		collision = 0;
		floorbl = 0;
		climb = false;
		for (int p = 0; p < blocks.size(); p++) {
			Block me = (Block) blocks.get(p);
			if (me.collidesWith(player)) {
				// System.out.println("collision detected between player and " +
				// me + " B" + p);
				fall(me);
			}
		}
		if (floorbl < 1 && climb == false) {
			falling = true;
		}

		stx = player.b_x;
		sty = player.b_y;
		if (falling == true) {
			movy += 15;
		}

		if (right == true && cright == true) {
			movx += 8;
			player.b_x = (int) (stx + (0.2 * (movx - stx)));
		}
		if (left == true && cleft == true) {
			movx -= 8;
			player.b_x = (int) (stx + (0.2 * (movx - stx)));
		}
		if (up == true) {
			if (climb == true) {
				movy -= 5;
			} else if (jump > 15 && cjump == true) {
				movy -= jump;
				jumping = true;
				cjump = false;
			} else {
				jumping = false;
			}
			jump = jump / 2;

		}
		player.b_y = (int) (sty + (easing * (movy - sty)));
		mx = (int) (stx1 + (1 * (player.b_x - stx1)));
		my = (int) (sty1 + (1 * (player.b_y - sty1)));
		// System.out.println(player.b_x+"   "+player.b_y+"!");
		if (down == true) {
			if (climb == true) {
				movy += 5;
			}
		}
		if ((right == false) && (left == false)) {
		}
		if (up == false) {
			jump = 325;
			jumping = false;
		}
		if (down == false) {
		}
		player.update();
		cright = true;
		cleft = true;
	}

	private void fall(Block me) {
		if ((me.type == 40) || (me.type == 41) || (me.type == 42)
				|| (me.type == 43) || (me.type == 20) || (me.type == 21)
				|| (me.type == 22) || (me.type == 23) || (me.type == 24)
				|| (me.type == 31) || (me.type == 32) || (me.type == 33)
				|| (me.type == 34) || (me.type == 103) || (me.type == 104)
				|| (me.type == 105) || (me.type == 106) || (me.type == 99)) {
			collision++;
			if (me.collidefloor(player)) {
				floorbl++;
				falling = false;
				cjump = true;
				movy = me.b_y - plyS + 2;
				sty = me.b_y - plyS + 2;
				player.b_y = me.b_y - plyS + 2;
			} else if (me.collideblock(player)) {
				if (me.pinpoint(player) == 1) {
					cleft = false;
				} else if (me.pinpoint(player) == 0) {
					cright = false;
				} else {
					cleft = true;
					cright = true;
				}
				if (me.nojump(player)) {
					cjump = false;
					falling = true;
					movy = me.b_y + me.s_y;
					sty = me.b_y + me.s_y;
					player.b_y = me.b_y + me.s_y;
				}
			}

		} else if ((me.type == 16) || (me.type == 17) || (me.type == 18)
				|| (me.type == 19) || (me.type == 35) || (me.type == 36)
				|| (me.type == 37) || (me.type == 38) || (me.type == 84)
				|| (me.type == 85) || (me.type == 86) || (me.type == 87)) {
			collision++;
			falling = false;
			climb = true;
		}else if(me.type==77){
			movy =Game.HEIGHT / 2;
			sty = Game.HEIGHT / 2;
			player.b_y = Game.HEIGHT / 2;
			sty1=Game.HEIGHT/2;
			my = (int) (sty1 + (1 * (player.b_y - sty1)));
		}
	}

	public static void changesize() {
		map_xSize = Game.HEIGHT / 8;
		map_ySize = Game.HEIGHT / 8;
		for (int i = 0; i < blocks.size(); i++) {
			Block block = (Block) blocks.get(i);
			blockm[block.row][block.col] = false;
			blocks.remove(block);
		}
	}

	public void MainLevelin(int loadinglevels) {

	}

	public BufferedImage crop(int col, int row, int w, int h) {
		return mapspritesheet.getSubimage(col * 63, row * 63, w, h);
	}

	public BufferedImage player(int col, int row, int w, int h, double n) {
		return playsprite.getSubimage(col * 165, row * 282, w, h);
	}

	private void init() {

		try {
			mapspritesheet = ImageIO.read(new File("pic/Image-Tileset2-2.png"));
			playsprite = ImageIO.read(new File("pic/runningGrant.png"));
			bg = ImageIO.read(new File("pic/landskypixelbg.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("can't load Sprites!");
		}

		MapLoad m = new MapLoad("mainlevel1.txt");
		map = m.map;
		blockm = new boolean[map.length][map[0].length];

		// This code handles detecting spots where
		int bst = 0;
		int bstc = 0, bstr = 0;
		player = new Player(this, Game.WIDTH / 2, Game.HEIGHT / 2, plxS, plyS);
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {

				int map_reader = map[row][col];

				if ((map_reader == 40) || (map_reader == 41)
						|| (map_reader == 42) || (map_reader == 43)
						|| (map_reader == 20) || (map_reader == 21)
						|| (map_reader == 22) || (map_reader == 23)
						|| (map_reader == 24) || (map_reader == 31)
						|| (map_reader == 32) || (map_reader == 33)
						|| (map_reader == 34) || (map_reader == 103)
						|| (map_reader == 104) || (map_reader == 105)
						|| (map_reader == 106)) {
					if(!(map[row-1][col]==0)){
						bst=-1;
					}else if (bst == 0) {
						bstr = row;
						bstc = col;
					}
					bst++;
					if (bst > 3) {
						Enemy enemy = new Enemy(this, (bstc + 2) * map_xSize,
								map_ySize * (bstr - 1), plxS, plyS, row, col);
						enemies.add(enemy);
						bst = -4;
					}
				} else {
					bst = 0;
				}
				map_x++;
			}
			map_y++;
			map_x = 0;
		}
		map_y = 0;
	}
}
