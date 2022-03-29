import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Asteroid {

	public  int x;
	public int y;
	private double vy;
	public int w;
	public int h;
	private Image img; 	
	private AffineTransform tx;
	private int min = 10;
	private int max = 420;
	
	public Asteroid() {
		x = (int)Math.floor(Math.random()*(max-min+1)+min); //randomize location of asteroid
		y = 0;
		img = getImage("/imgs/invader.gif");//load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
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
    
    public void shot() {
    	x = (int)Math.floor(Math.random()*(max-min+1)+min);
        Frame.score++;
    }
    
    public void update() {
    	//place asteroid back to the top of screen
    	if(Spaceship.stop ) {
			vy = 0;
			y = -120;
    	}
    	
    	vy = 2;
    	y += vy;
    	
    	tx.setToTranslation(x, y);
		tx.scale(.1 , .1);
    }
    
    private void init(double a, double b) {
    	tx.setToTranslation(a, b);
		tx.scale(1.5, 1.5);
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