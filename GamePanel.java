package Unknown;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class GamePanel extends JPanel implements ActionListener, Runnable {

	private static final long serialVersionUID = 1L;

	BufferedImage cursorImg = new BufferedImage(16, 16,
			BufferedImage.TYPE_INT_ARGB);
	Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
			cursorImg, new Point(0, 0), "blank cursor");

	static Font menu_font = new Font("Agency FB", Font.BOLD, 40);

	static ImageIcon intro_res = new ImageIcon("pic/11.jpg");
	static ImageIcon intro2_res = new ImageIcon("pic/unknown1.png");

	static Image intro = intro_res.getImage();
	static Image intro2 = intro2_res.getImage();

	private Thread thread;
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;

	private int FPS = 60;
	static double averageFPS = 0;

	static int screensizecounter = 0;

	Loading loading = new Loading();
	static boolean loadingb = true;
	Menu menu = new Menu();
	static boolean menub = false;
	Settings settings = new Settings();
	static boolean settingsb = false;
	Selectlevels selectlevels = new Selectlevels();
	static boolean selectlevelsb = false;
	MainLevel mainlevel = new MainLevel();
	static boolean mainlevelb = false;

	static boolean right = false;
	static boolean left = false;
	static boolean up = false;
	static boolean down = false;

	static AudioPlayer potato;
	static AudioStream background;

	int loadinglevels = 0;
	boolean loadinglevelsb = false;

	static File f = new File("pic/level0.png");
	static int adding = Game.WIDTH / (Loading.filesb.length + 1);

	public GamePanel() {
		super();
		addKeyListener(new keylistener());
		setCursor(blankCursor);
		setFocusable(true);
		requestFocus();

		try {
			potato = AudioPlayer.player;
			background = new AudioStream(new FileInputStream(
					"sound/fullsound.wav"));
		} catch (IOException IOE) {
		}
		
	}

	//
	//
	// ////////////////////////////////////////////////////////////////////////////

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	//
	//
	// ////////////////////////////////////////////////////////////////////////////

	public void run() {

		running = true;
		image = new BufferedImage(Game.WIDTH, Game.HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;

		int framecount = 0;
		int maxFramecount = 60;

		long targetTime = 1000 / FPS;

		while (running) {

			startTime = System.nanoTime();

			try {
				gameUpdate();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gameRender();
			gameDraw();

			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;

			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
			}

			totalTime += System.nanoTime() - startTime;
			framecount++;
			if (framecount == maxFramecount) {
				averageFPS = 1000.0 / ((totalTime / framecount) / 1000000);
				framecount = 0;
				totalTime = 0;

			}
		}
	}

	//
	//
	// ////////////////////////////////////////////////////////////////////////////

	private void gameUpdate() throws InterruptedException {
		if (loadingb == false) {
			if (Settings.soundcounter == 0) {
				potato.start(background);
			} else if (Settings.soundcounter == 1) {
				potato.stop(background);
			}
		}

		if (loadingb == true) {
			loading.update();
		} else if (menub == true) {
			menu.update();
		} else if (settingsb == true) {
			settings.update();
		} else if (selectlevelsb == true) {
			selectlevels.update();
		} else if (mainlevelb == true) {
			mainlevel.update(right, left, up, down);
		}

		loadinglevels = Selectlevels.levelscounter;

		if (selectlevelsb == true) {
			if (Selectlevels.levelsunlock[loadinglevels] == true
					&& loadinglevelsb == true) {

				mainlevelb = true;
				selectlevelsb = false;
				mainlevel.MainLevelin(loadinglevels);
			} else {
				mainlevelb = false;
				selectlevelsb = true;
				loadinglevelsb = false;
			}
		}
	}

	//
	//
	// ////////////////////////////////////////////////////////////////////////////

	private void gameRender() {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawString("FPS: " + averageFPS, Game.WIDTH - 70, Game.HEIGHT - 10);

		g.drawImage(intro, 0, 0, Game.WIDTH, Game.HEIGHT, null);

		if (selectlevelsb == false && mainlevelb == false) {
			g.drawImage(intro2, (Game.WIDTH - 267) / 2, 0, null);
		}

		if (loadingb == true) {
			loading.draw(g);
		} else if (menub == true) {
			menu.draw(g);
		} else if (settingsb == true) {
			settings.draw(g);
		} else if (selectlevelsb == true) {
			selectlevels.draw(g);
		} else if (mainlevelb == true) {
			mainlevel.draw(g);
		}
		// System.out.println( averageFPS);

		g.setColor(Color.WHITE);
		g.setFont(menu_font);
		g.drawString("FPS: " + (int) averageFPS, Game.WIDTH - 150,
				Game.HEIGHT - 10);
	}

	//
	//
	// ////////////////////////////////////////////////////////////////////////////

	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

		// System.gc();
	}

	//
	//
	// ////////////////////////////////////////////////////////////////////////////

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	//
	//
	// ////////////////////////////////////////////////////////////////////////////

	private class keylistener extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

			if (e.getKeyCode() == KeyEvent.VK_UP
					|| e.getKeyCode() == KeyEvent.VK_W) {
				// System.out.println("UP || W (Pressed)");
				up = true;
				if (menub == true) {
					if (Menu.selectcounter == 0) {
						Menu.selectcounter = 2;
						Menu.select1 = new Color(255, 255, 255);
						Menu.select2 = new Color(255, 255, 255);
						Menu.select3 = new Color(0, 0, 0);
					} else if (Menu.selectcounter == 2) {
						Menu.selectcounter = 1;
						Menu.select1 = new Color(255, 255, 255);
						Menu.select2 = new Color(0, 0, 0);
						Menu.select3 = new Color(255, 255, 255);
					} else if (Menu.selectcounter == 1) {
						Menu.selectcounter = 0;
						Menu.select1 = new Color(0, 0, 0);
						Menu.select2 = new Color(255, 255, 255);
						Menu.select3 = new Color(255, 255, 255);
					}
				}

				if (settingsb == true) {
					if (Settings.settingscounter == 0) {
						Settings.settingscounter = 2;
						Settings.select4 = new Color(255, 255, 255);
						Settings.select5 = new Color(255, 255, 255);
						Settings.select6 = new Color(0, 0, 0);
					} else if (Settings.settingscounter == 2) {
						Settings.settingscounter = 1;
						Settings.select4 = new Color(255, 255, 255);
						Settings.select5 = new Color(0, 0, 0);
						Settings.select6 = new Color(255, 255, 255);
					} else if (Settings.settingscounter == 1) {
						Settings.settingscounter = 0;
						Settings.select4 = new Color(0, 0, 0);
						Settings.select5 = new Color(255, 255, 255);
						Settings.select6 = new Color(255, 255, 255);
					}

					if (Settings.generalsettings == 0) {
						Settings.generalsettings = 2;
					} else if (Settings.generalsettings == 1) {
						Settings.generalsettings = 0;
					} else if (Settings.generalsettings == 2) {
						Settings.generalsettings = 1;
					}
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN
					|| e.getKeyCode() == KeyEvent.VK_S) {
				// System.out.println("DOWN || S (Pressed)");
				down = true;
				if (menub == true) {
					if (Menu.selectcounter == 0) {
						Menu.selectcounter = 1;
						Menu.select1 = new Color(255, 255, 255);
						Menu.select2 = new Color(0, 0, 0);
						Menu.select3 = new Color(255, 255, 255);
					} else if (Menu.selectcounter == 1) {
						Menu.selectcounter = 2;
						Menu.select1 = new Color(255, 255, 255);
						Menu.select2 = new Color(255, 255, 255);
						Menu.select3 = new Color(0, 0, 0);
					} else if (Menu.selectcounter == 2) {
						Menu.selectcounter = 0;
						Menu.select1 = new Color(0, 0, 0);
						Menu.select2 = new Color(255, 255, 255);
						Menu.select3 = new Color(255, 255, 255);
					}
				}

				if (settingsb == true) {
					if (Settings.settingscounter == 0) {
						Settings.settingscounter = 1;
						Settings.select4 = new Color(255, 255, 255);
						Settings.select5 = new Color(0, 0, 0);
						Settings.select6 = new Color(255, 255, 255);
					} else if (Settings.settingscounter == 1) {
						Settings.settingscounter = 2;
						Settings.select4 = new Color(255, 255, 255);
						Settings.select5 = new Color(255, 255, 255);
						Settings.select6 = new Color(0, 0, 0);
					} else if (Settings.settingscounter == 2) {
						Settings.settingscounter = 0;
						Settings.select4 = new Color(0, 0, 0);
						Settings.select5 = new Color(255, 255, 255);
						Settings.select6 = new Color(255, 255, 255);
					}

					if (Settings.generalsettings == 0) {
						Settings.generalsettings = 1;
					} else if (Settings.generalsettings == 1) {
						Settings.generalsettings = 2;
					} else if (Settings.generalsettings == 2) {
						Settings.generalsettings = 0;
					}
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT
					|| e.getKeyCode() == KeyEvent.VK_A) {
				// System.out.println("LEFT || A (Pressed)");
				left = true;

				if (settingsb == true) {
					if (Settings.generalsettings == 0) {
						if (screensizecounter == 0) {
							screensizecounter = 2;
							Settings.videocounter = 2;
							Game.WIDTH = ((int) Game.tk.getScreenSize()
									.getWidth());
							Game.HEIGHT = ((int) Game.tk.getScreenSize()
									.getHeight());
							Game.frame.setSize(Game.WIDTH, Game.HEIGHT);
							Game.frame.setLocationRelativeTo(null);
						} else if (screensizecounter == 1) {
							screensizecounter = 0;
							Settings.videocounter = 0;
							Game.WIDTH = 800;
							Game.HEIGHT = 600;
							Game.frame.setSize(Game.WIDTH, Game.HEIGHT);
							Game.frame.setLocationRelativeTo(null);
						} else if (screensizecounter == 2) {
							screensizecounter = 1;
							Settings.videocounter = 1;
							Game.WIDTH = 1000;
							Game.HEIGHT = 600;
							Game.frame.setSize(Game.WIDTH, Game.HEIGHT);
							Game.frame.setLocationRelativeTo(null);
						}
					} else if (Settings.generalsettings == 1) {
						if (Settings.soundcounter == 0) {
							Settings.soundcounter = 1;
						} else if (Settings.soundcounter == 1) {
							Settings.soundcounter = 0;
						}
					}
				}

				if (selectlevelsb == true) {
					if (Selectlevels.levelscounter == 0) {
						Selectlevels.levelscounter = 9;
					} else if (Selectlevels.levelscounter == 1) {
						Selectlevels.levelscounter = 0;
					} else if (Selectlevels.levelscounter == 2) {
						Selectlevels.levelscounter = 1;
					} else if (Selectlevels.levelscounter == 3) {
						Selectlevels.levelscounter = 2;
					} else if (Selectlevels.levelscounter == 4) {
						Selectlevels.levelscounter = 3;
					} else if (Selectlevels.levelscounter == 5) {
						Selectlevels.levelscounter = 4;
					} else if (Selectlevels.levelscounter == 6) {
						Selectlevels.levelscounter = 5;
					} else if (Selectlevels.levelscounter == 7) {
						Selectlevels.levelscounter = 6;
					} else if (Selectlevels.levelscounter == 8) {
						Selectlevels.levelscounter = 7;
					} else if (Selectlevels.levelscounter == 9) {
						Selectlevels.levelscounter = 8;
					}
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT
					|| e.getKeyCode() == KeyEvent.VK_D) {
				// System.out.println("RIGHT || D (Pressed)");
				right = true;

				if (settingsb == true) {
					if (Settings.generalsettings == 0) {
						if (screensizecounter == 0) {
							screensizecounter = 1;
							Settings.videocounter = 1;
							Game.WIDTH = 1000;
							Game.HEIGHT = 600;
							Game.frame.setSize(Game.WIDTH, Game.HEIGHT);
							Game.frame.setLocationRelativeTo(null);
						} else if (screensizecounter == 1) {
							screensizecounter = 2;
							Settings.videocounter = 2;
							Game.WIDTH = ((int) Game.tk.getScreenSize()
									.getWidth());
							Game.HEIGHT = ((int) Game.tk.getScreenSize()
									.getHeight());
							Game.frame.setSize(Game.WIDTH, Game.HEIGHT);
							Game.frame.setLocationRelativeTo(null);
						} else if (screensizecounter == 2) {
							screensizecounter = 0;
							Settings.videocounter = 0;
							Game.WIDTH = 800;
							Game.HEIGHT = 600;
							Game.frame.setSize(Game.WIDTH, Game.HEIGHT);
							Game.frame.setLocationRelativeTo(null);
						}
					} else if (Settings.generalsettings == 1) {
						if (Settings.soundcounter == 0) {
							Settings.soundcounter = 1;
						} else if (Settings.soundcounter == 1) {
							Settings.soundcounter = 0;
						}
					}
				}

				if (selectlevelsb == true) {
					if (Selectlevels.levelscounter == 0) {
						Selectlevels.levelscounter = 1;
					} else if (Selectlevels.levelscounter == 1) {
						Selectlevels.levelscounter = 2;
					} else if (Selectlevels.levelscounter == 2) {
						Selectlevels.levelscounter = 3;
					} else if (Selectlevels.levelscounter == 3) {
						Selectlevels.levelscounter = 4;
					} else if (Selectlevels.levelscounter == 4) {
						Selectlevels.levelscounter = 5;
					} else if (Selectlevels.levelscounter == 5) {
						Selectlevels.levelscounter = 6;
					} else if (Selectlevels.levelscounter == 6) {
						Selectlevels.levelscounter = 7;
					} else if (Selectlevels.levelscounter == 7) {
						Selectlevels.levelscounter = 8;
					} else if (Selectlevels.levelscounter == 8) {
						Selectlevels.levelscounter = 9;
					} else if (Selectlevels.levelscounter == 9) {
						Selectlevels.levelscounter = 0;
					}
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_INSERT) {
				// System.out.println("Insert (Pressed)");
			}

			if (e.getKeyCode() == KeyEvent.VK_DELETE) {
				// System.out.println("Delete (Pressed)");
			}

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// System.out.println("Enter (Pressed)");
			}

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				// System.out.println("ESCAPE (Pressed)");
			}

		}

		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

			if (e.getKeyCode() == KeyEvent.VK_UP
					|| e.getKeyCode() == KeyEvent.VK_W) {
				// System.out.println("UP || W (Released)");
				up = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN
					|| e.getKeyCode() == KeyEvent.VK_S) {
				// System.out.println("DOWN || S (Released)");
				down = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT
					|| e.getKeyCode() == KeyEvent.VK_A) {
				// System.out.println("LEFT || A (Released)");
				left = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT
					|| e.getKeyCode() == KeyEvent.VK_D) {
				// System.out.println("RIGHT || D (Released)");
				right = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_DELETE) {
				// System.out.println("Delete (Released)");
				System.exit(0);
			}

			if (e.getKeyCode() == KeyEvent.VK_INSERT) {
				// System.out.println("Insert (Released)");
				if (loadingb == false) {
					if (screensizecounter == 0) {
						screensizecounter = 1;
						Settings.videocounter = 1;
						Game.WIDTH = 1000;
						Game.HEIGHT = 600;
						Game.frame.setSize(Game.WIDTH, Game.HEIGHT);
						Game.frame.setLocationRelativeTo(null);
					} else if (screensizecounter == 1) {
						screensizecounter = 2;
						Settings.videocounter = 2;
						Game.WIDTH = ((int) Game.tk.getScreenSize().getWidth());
						Game.HEIGHT = ((int) Game.tk.getScreenSize()
								.getHeight());
						Game.frame.setSize(Game.WIDTH, Game.HEIGHT);
						Game.frame.setLocationRelativeTo(null);
					} else if (screensizecounter == 2) {
						screensizecounter = 0;
						Settings.videocounter = 0;
						Game.WIDTH = 800;
						Game.HEIGHT = 600;
						Game.frame.setSize(Game.WIDTH, Game.HEIGHT);
						Game.frame.setLocationRelativeTo(null);
					}
				}
				if (mainlevelb == true) {
					MainLevel.changesize();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// System.out.println("Enter (Released)");
				if (menub == true) {
					if (Menu.selectcounter == 0) {
						settingsb = false;
						menub = false;
						selectlevelsb = true;
						Menu.play = "RESUME";
					} else if (Menu.selectcounter == 1) {
						settingsb = true;
						menub = false;
					} else if (Menu.selectcounter == 2) {
						System.exit(0);
					}
				} else if (settingsb == true) {
					if (Settings.settingscounter == 0) {

					} else if (Settings.settingscounter == 1) {

					} else if (Settings.settingscounter == 2) {
						menub = true;
						settingsb = false;
					}
				} else if (selectlevelsb == true && loadinglevelsb == false) {
					loadinglevelsb = true;
				} else if (selectlevelsb == true && loadinglevelsb == true) {
					loadinglevels = Selectlevels.levelscounter;
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				// System.out.println("ESCAPE (Released)");
				if (settingsb == true) {
					menub = true;
					settingsb = false;
				} else if (selectlevelsb == true) {
					menub = true;
					settingsb = false;
					selectlevelsb = false;
				} else if (mainlevelb == true) {
					menub = true;
					settingsb = false;
					selectlevelsb = false;
					mainlevelb = false;
					loadinglevelsb = false;
				}
			}
		}

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}

}
