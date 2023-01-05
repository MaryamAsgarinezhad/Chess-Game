package util;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class ImageLoader {
	public ImageIcon background=new ImageIcon(new ImageIcon("bin/background.png").getImage().getScaledInstance(512, 512, Image.SCALE_DEFAULT));
	public ImageIcon blackElephant=new ImageIcon(new ImageIcon("bin/blackElephant.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
	public ImageIcon whiteElephant=new ImageIcon(new ImageIcon("bin/whiteElephant.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
	public ImageIcon blackLence=new ImageIcon(new ImageIcon("bin/blackLence.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
	public ImageIcon whiteLence=new ImageIcon(new ImageIcon("bin/whiteLence.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
	public ImageIcon blackSoldier=new ImageIcon(new ImageIcon("bin/blackSoldier.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
	public ImageIcon whiteSoldier=new ImageIcon(new ImageIcon("bin/whiteSoldier.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
	public ImageIcon blackGold=new ImageIcon(new ImageIcon("bin/blackGold.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
	public ImageIcon whiteGold=new ImageIcon(new ImageIcon("bin/whiteGold.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
	public ImageIcon blackSilver=new ImageIcon(new ImageIcon("bin/blackSilver.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
	public ImageIcon whiteSilver=new ImageIcon(new ImageIcon("bin/whiteSilver.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
	public ImageIcon whiteKing=new ImageIcon(new ImageIcon("bin/whiteKing.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
	public ImageIcon blackKing=new ImageIcon(new ImageIcon("bin/blackKing.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));

	
	public ImageIcon getImage(String s){
		switch(s){
		case "t":
    		return this.background;
    	case "l":
    		return this.blackLence;
    	case "L":
    		return this.whiteLence;
    	case "b":
    		return this.blackElephant;
    	case "B":
    		return this.whiteElephant;
    	
    	case "s":
    		return this.blackSilver;
    	case "S":
    		return this.whiteSilver;
    		
    	case "g":
    		return this.blackGold;
    	case "G":
    		return this.whiteGold;
    		
    	case "k":
    		return this.blackKing;
    	case "K":
    		return this.whiteKing;
    		
    	case "p":
    		return this.blackSoldier;
    	case "P":
    		return this.whiteSoldier;
    	}
		return null;
	}
}
