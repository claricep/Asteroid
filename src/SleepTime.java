
public class SleepTime {
    public static void main(String[] args) throws Exception {
    	
    	while(UFO.stop = true) { //if game is playing
    		int sleepTime = SleepTime.getSleepTime((int)Math.floor(Math.random()*(1000-600+1)+600)); //timer for asteroids to fall
    		System.out.println("sleep time\t" + sleepTime);  //check time intervals
    		Thread.sleep(sleepTime);
    	}
    	
    	
    	
    	
    }
    
    public static int getSleepTime(int sec) {
    	return 500;
    }
}