
public class SleepTime {
    public static void main(String[] args) throws Exception {
    	
    	while(UFO.stop = true) { //if game is playing
    		int sleepTime = SleepTime.getSleepTime(200,  2500); //timer for asteroids to fall
    		System.out.println("sleep time\t" + sleepTime);  //check time intervals
    		Thread.sleep(sleepTime);
    	}
    }
    
    public static int getSleepTime(int min, int max) {
    	return (int) ((Math.random() * (max - min)) + min);
    }
}
