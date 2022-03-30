import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Laser {

	public static int x;
	public int y;
	private int vy;
	private Image img; 	
	private AffineTransform tx;
	int time = 0;
	
	
	public Laser () {
		x= UFO.x + 35;
		y = 400 + 50;
		img = getImage("/imgs/laser.png");//load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		update();
		g2.drawImage(img, tx, null);
		
	}
	
	
	public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public double getVel() {
    	return vy;
    }
    
    //shoot
    public void shot() {
    	time += 16;
    	if(time > 6000) {
    		x = UFO.x;
    		y = UFO.y;
    		update();
    	}
    }
    
    private void update() {
    	vy = -8;
    	y += vy;
    	
    	//get rid of laser when game resets
    	if(UFO.stop ) {
			vy = 0;
		}
    	
    	tx.setToTranslation(x, y);
		tx.scale(.14, .14);
    }
    
    private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.14, .14);
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