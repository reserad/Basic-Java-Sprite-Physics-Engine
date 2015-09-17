import java.awt.Color;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Gravity extends Main implements Runnable {
	Thread c;
	Ball b;
	BufferedImage img;
	JLabel label;
	private String threadName;
	double time;
	double distance;
	public Gravity(BufferedImage _img, JLabel _label)
	{
		img = _img;
		label = _label;
		Random r = new Random();
		threadName = r.nextInt(1000) + "";
	}
	public void TurnOnGrav(Ball _b)
	{
		b = _b;
		//b.previousY = b.y;
		//b.previousX = b.x;
		c = new Thread (this, threadName);
        c.start ();
	}
	public void run()
	{
		while(true)
		{
	        try {
				Thread.sleep(30);
				time+= 0.03;
				ChangePixels();
				if(b.y + b.diameter >= img.getHeight()) {
					ErasePixels(b.previousX, b.previousY);
					DrawPixels(b.x, img.getHeight()-b.diameter);
					break;
				}
				else {
					//System.out.println(b.y + (int)((double)b.diameter/ (double)2.0));
					ErasePixels(b.previousX, b.previousY);
					DrawPixels(b.x, b.y);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void ErasePixels(int _x, int _y) {
    	int xCount = 0;
    	int yCount = 0;
    	for (int i = 0; i < (b.diameter * b.diameter); i++)
    	{
    		if (i%b.diameter == 0 && i != 0)
    		{
    			yCount++;
    			xCount = 0;
    		}
    		img.setRGB(_x + xCount, _y + yCount, new Color(255, 255, 255).getRGB());
    		xCount++;
    	}
    	label.setIcon(new ImageIcon(img));
	}
	public void DrawPixels(int _x, int _y) {
    	int xCount = 0;
    	int yCount = 0;
    	for (int i = 0; i < (b.diameter * b.diameter); i++)
    	{
    		if (i%b.diameter == 0 && i != 0)
    		{
    			yCount++;
    			xCount = 0;
    		}
    		img.setRGB(_x + xCount, _y + yCount, new Color(255, 0, 0).getRGB());
    		xCount++;
    	}
    	label.setIcon(new ImageIcon(img));
	}
	public void ChangePixels()
	{
		distance = 0.5 * 9.8 * (time * time);
		Ball temp = new Ball();
		temp.x = b.x;
		temp.y = b.y;
		b.previousX = temp.x;
		b.previousY = temp.y;
		b.y += (int) distance;
	}
}
