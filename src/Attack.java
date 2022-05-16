import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Attack {

	public  int x;
	public int y;
	private int vy;
	private Image img; 	
	private AffineTransform tx;
	private static boolean stop = true;
	
	
	public Attack () {
		x= (int)Math.floor(Math.random()*(550-50+1)+50);
		y = (int)Math.floor(Math.random()*(100-60+1)+60);
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
    
    public void reset() { //start screen
    	stop = true;
    }
    
    //shoot
    public void shot() {
    	x = (int)Math.floor(Math.random()*(550-50+1)+50);
    }
    
    private void update() {
    	vy = 4;
    	y += vy;
    	
    	//get rid of laser when game resets
    	if(UFO.stop ) {
			vy = 0;
		}
    	
    	tx.setToTranslation(x, y);
		tx.scale(.1, .1);
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

