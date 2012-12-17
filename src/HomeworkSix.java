import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;


public class HomeworkSix {
	private static int frameX = 512;
    private static int frameY = 512;
    
	public static void main(String[] args) throws FileNotFoundException{
		//take file as first argument
        FileInputStream fs;
		DataInputStream in = new DataInputStream(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		InputReader.readInput(br);
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        
		frame.setSize(new Dimension(frameX, frameY));
        RayTracer render = new RayTracer(frameX, frameY);
        frame.getContentPane().add(render);
        frame.setVisible(true);
	}
}
