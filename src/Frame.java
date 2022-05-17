import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.FileWriter;   
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//CREATE THE OBJECT (STEP 1) 
	Background bg = new Background(0, 10);
	UFO ufo = new UFO(225, 450);
	private ArrayList<Invader> invader = new ArrayList<Invader>();
	ArrayList lasers = ufo.getLaser();
	ArrayList ilasers = ufo.getAttack();
	ArrayList <Integer> scores = new ArrayList <>();

	
	//sounds
	Music shoot = new Music("shoot.wav", false);
	Music background = new Music("background.wav", false);
	Music gameOver = new Music("gameover.wav", false);
	
	
	public static int score = 0;
	private int max = 10;
	public boolean level = false;
	private int timer = 0;
	private boolean start;
	private boolean loser = false;
	private boolean stall = false;
	public int entcount = 0;
	public boolean bmusic = false;

	public static int maxScore = 0;

	 
	public void spawn() {
		Invader i = new Invader();
		invader.add(i);
	}
	
	public void shoot() {
		Laser i = new Laser();
		lasers.add(i);
	}
	
	public void Ishoot() {
		Attack a = new Attack();
		ilasers.add(a);
	}
	
	int time = 0;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//tracking highscore
			for(int i = 0; i < scores.size(); i++) {
				if(scores.get(i) > maxScore) {
					maxScore = scores.get(i); 
					
				}
			}
			
		//paint objects
		bg.paint(g);
		ufo.paint(g);
				
		//start screen
		if(stall == false) {
		Font f1 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		g.setFont(f1);
		if(UFO.stop) {
			g.setColor(Color.WHITE);   
			g.drawString("Press 'Enter' to start", 160 , 250);
			start = false;
		}
		
		Font f2 = new Font(Font.SANS_SERIF, Font.ITALIC, 15);
		g.setFont(f2);
		if(UFO.stop) {
			g.setColor(Color.WHITE);   
			g.drawString("Use 'space bar' to shoot", 180 , 280);
		}
		}
		
		//score
		Font gg = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		g.setFont(gg);
		if(UFO.stop == false) {
			g.setColor(Color.WHITE);   
			g.drawString("Score : " + score + "", 20 , 30);
			g.drawString("High Score : " + maxScore + "", 20 , 60);
		}
		
		//level up
		Font f3 = new Font(Font.SANS_SERIF, Font.ITALIC, 40);
		g.setFont(f3);
		
		if(score == max) {
			 invader.clear();
			 level = true;
		 }
		if(level == true) {
			g.setColor(Color.WHITE);   
			g.drawString("NEXT LEVEL", 125 , 250);
			invader.clear();
			ilasers.clear();
			lasers.clear();
			time += 16;
			if(time>1500) {
				level = false;
				time = 0;
				max += 5;
			}
		}
		
		//game over
		Font f4 = new Font(Font.SANS_SERIF, Font.ITALIC, 40);
		g.setFont(f4);
		
		if(loser == true) {
			stall = true;
			g.setColor(Color.WHITE);   
			g.drawString("GAME OVER", 125 , 250);
			invader.clear();
			ilasers.clear();
			lasers.clear();
			time += 16;
			if(time>1500) {
				loser = false;
				time = 0;
				score = 0;
				stall = false;
			}
		}
		
		
		
		
		//paint lasers
		for(int i = 0; i < lasers.size(); i++) {
			Laser l = (Laser) lasers.get(i);
			l.paint(g);	
			//laser hit box
			//g.drawRect(l.getX(), l.getY(), 12, 12);
		}
		
		//paint invader lasers
		for(int i = 0; i < ilasers.size(); i++) {
			Attack il = (Attack) ilasers.get(i);
			il.paint(g);
			
			//invader laser hit box
			//g.drawRect(il.getX(), il.getY(), 10, 10);
			
			//collision between ufo and invader laser	
			if(ufo.getX() < il.getX() + 15 && ufo.getX() + 80 > il.getX()){
				if(ufo.getY() + 25 > il.getY() && ufo.getY() < il.getY() + 20){
					ufo.reset(); //set ship back to center ad set score to 0
					gameOver.play();
					start = true;
					loser = true;
				}
			}
			

			
		}
		
		//UFO hit box
		//g.drawRect(ufo.getX(), ufo.getY(), 80, 45);
		//g.drawRect(ufo.getX(), ufo.getY(), 80, 45);

		
		//create invader
			for(int i = 0; i < invader.size(); i ++) {
				Invader a = (Invader) invader.get(i);
				a.paint(g);
				
				//invader hit box
		//		g.drawRect(a.x +5, a.y, 40, 43);
					
					//collision between ufo and Invader	
					if(ufo.getX() < a.getX() + 70 && ufo.getX() + 45 > a.getX()){
						if(ufo.getY() + 45 > a.getY() && ufo.getY() < a.getY() + 35){
							invader.remove(i);
							ufo.reset(); //set ship back to center ad set score to 0
							gameOver.play();
							scores.add(score);
							start= true;
							loser = true;
						}
					}
				
			
		}	
			
		
		//collision between laser and invader	
		 if(! (invader.size() == 0) && !(lasers.size() == 0)) {
			 for(int i = 0; i < lasers.size();i++) {
				 for(int p = 0; p < invader.size() && i < lasers.size(); p++) {
					 Laser tempL = (Laser) lasers.get(i);
					 Invader tempA = (Invader) invader.get(p);
					 	if(tempL.getX() < tempA.getX() + 50 && tempL.getX() + 12 > tempA.getX()){
					 		if(tempL.getY() < tempA.getY() + 50 && tempL.getY() + 12 > tempA.getY()) {
								lasers.remove(i);
								invader.remove(p);
							 	score++;
					 		}
						 }
				 }
			 }
		}
	}

	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Space Invaders");
		f.setSize(new Dimension(500, 600));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
			System.out.println(arg0.getKeyCode());
			
			//invader laser
			if(arg0.getKeyCode()==17) { //ctrl
				Ishoot();
			}
			
			//move left
			if(arg0.getKeyCode()==37) { //left arrow
				ufo.moveL();
			}
			if(arg0.getKeyCode()==65) { // right arrow
				ufo.moveL();
			}
			
			//move right
			if(arg0.getKeyCode()==39) { // a key
				ufo.moveR();
			}
			if(arg0.getKeyCode()==68) { //d key
				ufo.moveR();
			}
			
			//shoot laser
			if(arg0.getKeyCode()== 32) { //space bar
				shoot();
				shoot.play();
			}
			
			if(arg0.getKeyCode() == 10 && start == false) { //enter key	
				//start screen	
				start = true;
				UFO.stop = false;
				entcount++;
				bmusic = true;
		      if(entcount <= 1 && bmusic == true) {
		    		background.play();
		        }
				//control invader to disappear when game ends
				new Thread() {
			        public void run(){
						try {
							while (true) {
								if(UFO.stop==true) { //if start screen is on
								//clear all invader before breaking
									invader.clear();
									ilasers.clear();
									break;
								}					
								Thread.sleep(SleepTime.getSleepTime(100));
								spawn();
								Ishoot();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
			        }
		
				}.start();
			}
	
	}
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
