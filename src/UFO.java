import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class UFO {

	public static int x, y;
	private int vx;
	private Image img; 	
	private AffineTransform tx;
	private ArrayList<Laser> laser = new ArrayList<Laser>();
	private ArrayList<Invader> invader = new ArrayList<Invader>();
	public static boolean stop = true;
	
	
	public UFO(int x, int y) {
		this.x = x;
		this.y = y;
		img = getImage("/imgs/ufo.png"); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); //initialize the location of the image
	}
	
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		//call update to update the actual picture location		
		update();
		g2.drawImage(img, tx, null);
	}
	
	
	public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getVel(){
        return vx;
    }
    
    public void moveL() { //left arrow
    	vx = -12;
		x += vx;
    }
       
    public void moveR() { //right arrow
		vx = 12;
		x += vx; 
	}
	
     public void reset() { //start screen
    	stop = true;
    }
    
    private void update() {
    	
	// prevent going up from frame
    	if(x < -40) {
		x = 500; 
	}
	if(x > 500) {
		x = -40;
	}
		
	if(stop ) { //recenter UFO and reset score
		x = 200;
		this.y = 450;
	    	Frame.score = 0;
	}
		
		tx.setToTranslation(x, y);
		tx.scale(.3 , .3);
    }
	
    public ArrayList getLaser() {
    	return laser;
    }
    
    public ArrayList getAsteroids() {
    	return asteroids;
    }
      
    private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.5, .5);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
}