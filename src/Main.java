import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main {
	static ArrayList<Ball> Balls;
	public static void main(String s[]) {
		Balls = new ArrayList<Ball>();
		JFrame frame = new JFrame("JFrame Example");

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		final BufferedImage img = new BufferedImage(
			    300, 300, BufferedImage.TYPE_INT_RGB);
		int rgb = new Color(255, 255, 255).getRGB();
		for (int x = 0; x < img.getWidth(); x++)
		{
			for (int y = 0; y < img.getHeight(); y++)
			{
				img.setRGB(x, y, rgb);
			}
		}
		final JLabel label = new JLabel(new ImageIcon(img));
		JButton button = new JButton("Spawn ball");
		panel.add(label);
		panel.add(button);

		frame.add(panel);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	Random r = new Random();
		    	int x = r.nextInt(298);
		    	Ball b = new Ball();
		    	b.diameter = 30;
		    	b.x = x;
		    	b.y = (int) Math.ceil((double) b.diameter/ (double)2.0);
		    	Balls.add(b);
		    	Gravity g = new Gravity(img, label);
		    	g.TurnOnGrav(b);
		    }
		});

	}

}
