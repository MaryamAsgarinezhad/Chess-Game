package Columns;


import javax.swing.*;

import hw3.Bead;
import hw3.Node;

import java.awt.*;
import java.util.List;
import util.ImageLoader;


public class CheckerCell extends JButton{
	
	private ImageIcon checkerImage;
    private Dimension dimension;
    private final int checkerHeight=102;
    private final int checkerWidth=102;
    private int middleX;
    
    public CheckerCell(Dimension dimension){
        setLayout(null);
        setContentAreaFilled(false);
        setOpaque(false);
        repaint();
        setFocusable(false);
        setBorderPainted(false);
        this.dimension = dimension;
        this.setPreferredSize(dimension);
        this.middleX = (int)Math.floor(dimension.getWidth() / 2);
    }

    
    public void setCheckers(){
        this.removeAll();
        JLabel checker = new JLabel(checkerImage);
        checker.setBounds(middleX - checkerWidth / 2,0,checkerWidth ,checkerHeight);
        checker.setVisible(true);
        this.add(checker);
    }
    
    public void delCheckers(){
        this.removeAll();
    }
    
    public void setCheckerImage(String s) {
        this.checkerImage = new ImageLoader().getImage(s);
    }
}
