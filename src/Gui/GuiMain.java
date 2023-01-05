package Gui;

import util.ImageLoader;
import javax.swing.*;

import java.awt.*;

public class GuiMain {
	public static final Dimension frameSize=new Dimension(530,1000);
	
	public static void main(String[] args){
//		JFrame frame=new JFrame("game");
//		frame.setSize(frameSize);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		Container pane=new JPanel();
//		frame.add(pane);
//		ImageIcon image =new ImageLoader().getImage("P");
//		pane.add(new JLabel(image));
//		frame.repaint();
//		frame.revalidate();
		GameFrame frame = new GameFrame();
	}
	
}
