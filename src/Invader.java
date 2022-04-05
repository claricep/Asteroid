import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Invader {

	public  int x;
	public int y;
	private int vx=5;
	public int w;
	public int h;
	private Image img; 	
	private AffineTransform tx;
	private int min = 10;
	private int max = 420;
	
	public Invader() {
		x = 50;
		y = 50;
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
        Frame.score++;
    }
    
    public void update() {
    	//place invader back to the top of screen
    	x += vx;
	
    	if(UFO.stop ) {
			vx = 0;
    	}
    	
    	if(x > 400 || x < 50) {
    	    vx = -vx;
    	    y+=60;
    	}
    
    	tx.setToTranslation(x, y);
		tx.scale(.15, .15);
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