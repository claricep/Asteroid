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

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//CREATE THE OBJECT (STEP 1) 
	Background bg = new Background(0, 10);
	UFO ufo = new UFO(225, 450);
	private ArrayList<Invader> invader = new ArrayList<Invader>();
	ArrayList lasers = ufo.getLaser();

	public static int score = 0;
	
	public void spawn() {
		Invader i = new Invader();
		invader.add(i);
	}
	
	public void shoot() {
		Laser i = new Laser();
		lasers.add(i);
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//paint objects
				bg.paint(g);
				ufo.paint(g);
				
		//start screen
		Font f1 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		g.setFont(f1);
		if(UFO.stop) {
			g.setColor(Color.WHITE);   
			g.drawString("Press 'Enter' to start", 130 , 250);
		}
		
		Font f2 = new Font(Font.SANS_SERIF, Font.ITALIC, 15);
		g.setFont(f2);
		if(UFO.stop) {
			g.setColor(Color.WHITE);   
			g.drawString("Use 'space bar' to shoot", 140 , 280);
		}
		
		//score
		Font gg = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		g.setFont(gg);
		if(UFO.stop == false) {
			g.setColor(Color.WHITE);   
			g.drawString("Score : " + score + "", 20 , 30);
		}
		
		
		//paint lasers
		for(int i = 0; i < lasers.size(); i ++) {
			Laser l = (Laser) lasers.get(i);
			l.paint(g);	
			//laser hit box
			//g.drawRect(l.getX(), l.getY(), 12, 12);
		}
		
		//UFO hit box
		g.drawRect(ufo.getX(), ufo.getY(), 80, 45);
		
		//create invader
			for(int i = 0; i < invader.size(); i ++) {
				Invader a = (Invader) invader.get(i);
				a.paint(g);
				
				//bounce off walls
				if(a.getX() > 400 || (a.getX() < 100)) {
		    	  //  invader.getVx = -1;
		    	}
				
				//invader hit box
				g.drawRect(a.x +5, a.y, 40, 43);
					
					//collision between ufo and Invader	
					if(ufo.getX() + 30 < a.getX() + 80 && ufo.getX() + 70 > a.getX()){
						if(ufo.getY() + 45 > a.getY() && ufo.getY() < a.getY() + 35){
							invader.remove(i);
							ufo.reset(); //set ship back to center ad set score to 0
						}
					}
			
		}	
			
		//collision between laser and invader	
		 if(! (invader.size() == 0) && !(lasers.size() == 0)) {
			 for(int i = 0; i < lasers.size();i++) {
				 for(int p = 0; p < invader.size() && i < lasers.size(); p++) {
					 Laser tempL = (Laser) lasers.get(i);
					 Invader tempA = (Invader) invader.get(p);
					 	if(tempL.getX() < tempA.getX() + 80 && tempL.getX() + 12 > tempA.getX()){
					 		if(tempL.getY() < tempA.getY() + 80 && tempL.getY() + 12 > tempA.getY()) {
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
			}
			
			if(arg0.getKeyCode() == 10) { //enter key	
				//start screen
				UFO.stop = false;
				//control invader to disappear when game ends
				new Thread() {
			        public void run(){
						try {
							while (true) {
								if(UFO.stop==true) { //if start screen is on
								//clear all invader before breaking
									invader.clear();
									break;
								}					
								Thread.sleep(SleepTime.getSleepTime(200,  2200));
								spawn();
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