import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
public class File{
	public class WriteToFile {
		  public void main(String[] args) {
		    try {
		      FileWriter myWriter = new FileWriter("background.wav");
		      myWriter.write("Files in Java might be tricky, but it is fun enough!");
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  }
		}
}
