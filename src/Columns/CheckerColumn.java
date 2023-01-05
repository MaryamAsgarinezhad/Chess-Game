package Columns;

import javax.swing.*;

import java.awt.*;
import java.util.List;
import util.ImageLoader;
import hw3.Bead;

public class CheckerColumn extends JButton {
	
	private ImageIcon checkerImage;
    private Dimension dimension;
    private final int checkerHeight=102;
    private final int checkerWidth=102;
    private int middleY;
    boolean player;
    
//    public CheckerColumn(Dimension dimension,boolean player){
//        setLayout(null);
//        setOpaque(false);
//        setFocusable(false);
//        setBorderPainted(false);
//        this.player=player;
//        this.dimension = dimension;
//        this.setPreferredSize(dimension);
//        this.middleY = (int)Math.floor(dimension.getHeight() / 2);
//    }
    
    
    public void setCheckers(List<Bead> Bunch){
        this.removeAll();
        int base = 0;
        
        for(Bead s:Bunch){
    		this.setCheckerImage(s.getName());
        	JLabel checker = new JLabel(checkerImage);
            checker.setBounds(base,middleY - checkerHeight / 2, checkerWidth ,checkerHeight);
            this.add(checker);
            base +=checkerWidth;
        }
    }
    
    
    public void setCheckerImage(String s) {
        this.checkerImage =  new ImageLoader().getImage(s);
    }

}
