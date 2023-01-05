package Gui;

import util.ImageLoader;
import hw3.Choice;
import hw3.Command;
import hw3.Bead;
import hw3.GameBoard;
import hw3.Node;
import javax.swing.*;

//import Main;
import Columns.CheckerColumn;
import Columns.HitCheckerColumn;
import Columns.PointCheckerCell;
import Columns.CheckerCell;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class GameFrame extends JFrame{
	
	public static final int DISPLAY_NUMBER = 0;
    public static final int GAME_FRAME_WIDTH = 1020;
    public static final int GAME_FRAME_HEIGHT = 800;
    public static final Dimension CELL_DIMENSION = new Dimension(102, 102);
//    public static final Dimension COLUMN_DIMENSION = new Dimension(600, 102);    
    
	private GameBoard game=new GameBoard();
	private boolean player=false;
	private List<Bead> currentBunch;

	
	
    private JPanel pane;
    private JPanel board;
    private JPanel board2;
    private JLabel background;
    private PointCheckerCell[][] points=new PointCheckerCell[5][5];
    private PointCheckerCell[] whiteHitCheckers=new PointCheckerCell[10];
    private PointCheckerCell[] blackHitCheckers=new PointCheckerCell[10];
    private Queue<Choice> choices = new LinkedList<>();
    
    
    public GameFrame(){
    	//construct board components
        initComponents();
        alignComponents();
        
    	//settings
        setSize(new Dimension(GAME_FRAME_WIDTH, GAME_FRAME_HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Game of Generals");
        
        //open on the [DISPLAY_NUMBER]-th display
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();

        setLocation(gd[DISPLAY_NUMBER].getDefaultConfiguration().getBounds().x +
                    (gd[DISPLAY_NUMBER].getDefaultConfiguration().getBounds().width - GAME_FRAME_WIDTH) / 2, getY());


        //initBackGround
        initBackGround();

        setVisible(true);

        repaintFrame();
    }
    public void initBackGround(){
    	points[0][0].update(null, "K", 1);
    	points[1][0].update(null, "G", 1);
    	points[2][0].update(null, "S", 1);
    	points[3][0].update(null, "B", 1);
    	points[4][0].update(null, "L", 1);
    	points[0][1].update(null, "P", 1);
    	
    	points[0][4].update(null, "l", 1);
    	points[1][4].update(null, "b", 1);
    	points[2][4].update(null, "s", 1);
    	points[3][4].update(null, "g", 1);
    	points[4][4].update(null, "k", 1);
    	points[4][3].update(null, "p", 1); 	
    }
    public void repaintFrame(){
        this.revalidate();
        this.repaint();
    }
    
    private void initComponents(){
    	pane = new JPanel();
        board = new JPanel();
        board2 = new JPanel();
        board.setOpaque(false);
        board2.setOpaque(false);
        background = new JLabel(new ImageLoader().background);
        setContentPane(pane);
        for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 5; j++){
        		points[i][j]=new PointCheckerCell(CELL_DIMENSION);
        	}
        }
        
        for (int i = 0; i < 10; i++){
        	whiteHitCheckers[i]=new PointCheckerCell(CELL_DIMENSION);
        	blackHitCheckers[i]=new PointCheckerCell(CELL_DIMENSION);
        }
        
        addMouseAdapters();
    }
    
    private void addMouseAdapters(){
    	for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 5; j++){
                points[i][j].addActionListener(new ColumnMouseEventHandler(this, new Choice(i,j)));
        	}
        }
    	for (int i = 0; i < 10; i++){
        	blackHitCheckers[i].addActionListener(new ColumnMouseEventHandler(this, new Choice(i,2,true)));
        	whiteHitCheckers[i].addActionListener(new ColumnMouseEventHandler(this, new Choice(i,1,true)));
    	}
    }
    
    private void removeMouseAdapters(){
    	for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 5; j++){
                points[i][j].removeActionListener(new ColumnMouseEventHandler(this, new Choice(i,j)));
        	}
        }
    	
    	for (int i = 0; i < 10; i++){
        	blackHitCheckers[i].removeActionListener(new ColumnMouseEventHandler(this, new Choice(i,2,true)));
        	whiteHitCheckers[i].removeActionListener(new ColumnMouseEventHandler(this, new Choice(i,1,true)));
    	}
    }
    
    private void alignComponents(){
        pane.setLayout(null);
        pane.add(board);
        pane.add(board2);
        pane.add(background);
        background.setBounds(0, 0, 512, 512);
        board.setBounds(0, 0, 512, 512);
        board2.setBounds(0, 512, 1020, 205);
        board.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        
        for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 5; j++){
                CheckerCell Cell= points[i][j];
                Cell.setOpaque(false);
                
                gbc.gridwidth = 1;
                gbc.gridheight = 1;

                gbc.gridx = i;
                gbc.gridy = 4-j;
                board.add(Cell, gbc);
        	}
        }
        
        board2.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.CENTER;
        
        for (int i = 0; i < 10; i++){
        	CheckerCell Cell=whiteHitCheckers[i] ;
            Cell.setContentAreaFilled(true);

        	gbc2.gridwidth = 1;
            gbc2.gridheight = 1;
            gbc2.gridx = i;
            gbc2.gridy = 0;
            board2.add(Cell, gbc2);
            
            Cell=blackHitCheckers[i] ;
            Cell.setContentAreaFilled(true);

            gbc2.gridwidth = 1;
            gbc2.gridheight = 1;
            gbc2.gridx = i;
            gbc2.gridy = 1;
            board2.add(Cell, gbc2);
        }
    }
    
    
    public void addChoice(Choice choice) {
        System.out.println(choice);
        choices.add(choice);
        if (choices.size() > 1){
            Choice source = choices.poll();
            Choice destination = choices.poll();
            String commandStr="";
            if(source.columnNumber==0){
            	commandStr=game.getBoard()[source.index1][source.index2].getBead().getName()+ " " +(source.index1+1)+(source.index2+1)+" "+(destination.index1+1)+(destination.index2+1);
            }
            else{
            	if(source.columnNumber==1){
                	if(player==false){
                		System.out.println("cant use rivals bunch11");
                	}
                	else{
                    	commandStr=game.white.get(source.index1).getName()+ " " +"00"+" "+(destination.index1+1)+(destination.index2+1);
                	}
            	}
            	else{
            		if(source.columnNumber==2){
            			if(player==true){
                    		System.out.println("cant use rivals bunch22");
                    	}
                    	else{
                        	commandStr=game.black.get(source.index1).getName()+ " " +"00"+" "+(destination.index1+1)+(destination.index2+1);
                    	}
            		}
            	}
            }
            if(!commandStr.equals("")){
            	int a=check(game,commandStr);
                switch(a){
            	case 10:
            		if(player==false){
            			setCheckerColumn(game.black,blackHitCheckers);
            			repaint();
            		}
                	else{
            			setCheckerColumn(game.white,whiteHitCheckers);
            			repaint();
            		}
            		removeMouseAdapters();
            	case 1:
            		points[source.index1][source.index2].update(null, null, 0);
            		repaint();
            		points[destination.index1][destination.index2].update(null, commandStr.substring(0, 1), 1);
            	case 2:
            		points[source.index1][source.index2].update(null, null, 0);
            		repaint();
            		points[destination.index1][destination.index2].update(null, commandStr.substring(0, 1), 1);
            		List<Bead> Bunch;
            		if(player==false){
            			Bunch=game.white;
            			setCheckerColumn(Bunch,whiteHitCheckers);
            			repaint();
            		}
            		else{
            			Bunch=game.black;
            			setCheckerColumn(Bunch,blackHitCheckers);
            			repaint();
            		}
            	case 3:
            		points[destination.index1][destination.index2].update(null, commandStr.substring(0, 1), 1);
            		List<Bead> Bunch2;
            		if(player==false){
            			Bunch2=game.white;
            			setCheckerColumn(Bunch2,whiteHitCheckers);
            			repaint();
            		}
            		else{
            			Bunch2=game.black;
            			setCheckerColumn(Bunch2,blackHitCheckers);
            			repaint();
            		}
                }
            }
        }
    }
    
    public int check(GameBoard game,String str1){
    	
		if(this.player==false){
			currentBunch=game.black;
		}
		else{
			currentBunch=game.white;
		}
		
		String array1[]=str1.split(" ");
        String array2[]=array1[1].split("");
        String array3[]=array1[2].split("");
        int s1=Integer.parseInt(array2[0]);
        int s2=Integer.parseInt(array2[1]);
        int e1=Integer.parseInt(array3[0]);
        int e2=Integer.parseInt(array3[1]);

        if((e1>5 || e1<1) || (e2>5 || e2<1) || (s1>5 || s1<0) || (s2>5 || s2<0) || ((s1==0 && s2==1)||(s1==1 && s2==0))){
        	System.out.println("out of range");
        	return 0;
        }
		
        if(!array1[1].equals("00")){     	
       	    if(game.getBoard()[s1-1][s2-1].getBead()!=null){
	        	if(!(game.getBoard()[s1-1][s2-1].getBead().getName().equals(array1[0]))){
		        	System.out.println("wrong start point");
		        	return 0;
		        }
		        if(game.getBoard()[s1-1][s2-1].getBead().getColor()!=this.player){
		        	System.out.println("wrong player");
		        	return 0;
		        }
	        }
	        else{
	        	System.out.println("empty start point");
	        	return 0;
	        }
	        
	        if(game.getBoard()[e1-1][e2-1].getBead()!=null){
	        	if(game.getBoard()[e1-1][e2-1].getBead().getColor()==this.player){
		        	System.out.println("cant hit the same color");
	        		return 0;
		        }
	        }
        }
        else{
        	boolean b;
           	char cha[]=array1[0].toCharArray();
           	char ch=cha[0];
           	if((int)ch>90){
           		b=false;
           	}
           	else{
           		b=true;
           	}
           	
           	if(b!=this.player){
    	        System.out.println("wrong player");
           		return 0;
    	    }
           	if(currentBunch.isEmpty()){
    	        System.out.println("empty bunch");
    	        return 0;
       		}
       		else{
       			if(find(currentBunch,array1[0])==null){
    				System.out.println("not found in bunch");
       				return 0;
    			}
    			else{
    				if(!implant(game,e1-1,e2-1,currentBunch,find(currentBunch,array1[0]))){
           	        	System.out.println("cant implant in a full point");
           	        	return 0;
           			}
           			this.player=!this.player;
           			if(this.player==false){
           				currentBunch=game.black;
           			}
           			else{
           				currentBunch=game.white;
           			}
        	        return 3;
    			}
       		}
        }
        
        if(array1[0].equals("K") || array1[0].equals("k")){
        	if(!game.KingValid(s1-1,s2-1,e1-1,e2-1)){
	        	System.out.println("k valid");
        		return 0;
        	}
        	else{
        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
        		if(a==0){
        			if(player==false){
	        			System.out.println("black wins!");
        			}
        			else{
	        			System.out.println("white wins!");
        			}
        			return 10;
        		}
        		else{
        			player=!player;
        			if(player==false){
        				currentBunch=game.black;
        			}
        			else{
        				currentBunch=game.white;
        			}
        			return a;
        		}
        	}
        }
        
        
        if(array1[0].equals("L")){
        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
        		if(!game.rokhValid(s1-1,s2-1,e1-1,e2-1)){
    	        	System.out.println("rokh valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        	else{
        		if(!game.WhiteLanceValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("wl valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        }
        
        if(array1[0].equals("l")){
        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
        		if(!game.rokhValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("rokh valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        	else{
        		if(!game.BlackLanceValid(s1-1,s2-1,e1-1,e2-1)){
        			//System.out.println("bl valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        }
        
        if(array1[0].equals("G")){
        	if(!game.WhiteGoldValid(s1-1,s2-1,e1-1,e2-1)){
    			System.out.println("wg valid");
	        	return 0;
        	}
        	else{
        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
        		if(a==0){
        			if(player==false){
	        			System.out.println("black wins!");
        			}
        			else{
	        			System.out.println("white wins!");
        			}
        			return 10;
        		}
        		else{
        			player=!player;
        			if(player==false){
        				currentBunch=game.black;
        			}
        			else{
        				currentBunch=game.white;
        			}
        			return a;
        		}
        	}
        }
        
        if(array1[0].equals("g")){
        	if(!game.BlackGoldValid(s1-1,s2-1,e1-1,e2-1)){
    			System.out.println("bg valid");
	        	return 0;
        	}
        	else{
        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
        		if(a==0){
        			if(player==false){
	        			System.out.println("black wins!");
        			}
        			else{
	        			System.out.println("white wins!");
        			}
        			return 10;
        		}
        		else{
        			player=!player;
        			if(player==false){
        				currentBunch=game.black;
        			}
        			else{
        				currentBunch=game.white;
        			}
        			return a;
        		}
        	}
        }
        
        if(array1[0].equals("S")){
        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
        		if(!game.silValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("sil valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        	else{
        		if(!game.WhiteSilverValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("ws valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        }
        
        if(array1[0].equals("s")){
        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
        		if(!game.silValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("sil valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        	else{
        		if(!game.BlackSilverValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("bs valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        }
        
        if(array1[0].equals("P")){
        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
        		if(!game.WhiteGoldValid(s1-1,s2-1,e1-1,e2-1)){
        			//System.out.println("wg valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        	else{
        		if(!game.WhiteSoliderValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("wp valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        }
        
        if(array1[0].equals("p")){
        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
        		if(!game.BlackGoldValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("bg valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        	else{
        		if(!game.BlackSoliderValid(s1-1,s2-1,e1-1,e2-1)){
        		    System.out.println("bp valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        }
        
        if(array1[0].equals("B")){
        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
        		if(!game.philValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("fil valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        	else{
        		if(!game.WhiteElephantValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("wb valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        }
        
        if(array1[0].equals("b")){
        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
        		if(!game.philValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("fil valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        			return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        	else{
        		if(!game.BlackElephantValid(s1-1,s2-1,e1-1,e2-1)){
        			System.out.println("bb valid");
    	        	return 0;
	        	}
	        	else{
	        		int a=move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player);
	        		if(a==0){
	        			if(player==false){
		        			System.out.println("black wins!");
	        			}
	        			else{
		        			System.out.println("white wins!");
	        			}
	        		    return 10;
	        		}
	        		else{
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			return a;
	        		}
	        	}
        	}
        }
        return 20;
    }
    
    public static int move(GameBoard g,String s,int s1,int s2,int e1,int e2,List<Bead> bunch,boolean p){
		int ans=100;
		String shah;
		if(p==false){
			shah="K";
		}
		
		else{
			shah="k";
		}
		if(g.getBoard()[e1][e2].getBead()!=null){
			if(g.getBoard()[e1][e2].getBead().getName().equals(shah)){
				ans=0;
			}
			g.getBoard()[e1][e2].getBead().setColor(p);
			
			
			char cha[]=g.getBoard()[e1][e2].getBead().getName().toCharArray();
        	char ch=cha[0];
			if(g.getBoard()[e1][e2].getBead().getColor()){
				g.getBoard()[e1][e2].getBead().setName(Character.toString((char)((int)ch-32)));;
			}
			else{
				g.getBoard()[e1][e2].getBead().setName(Character.toString((char)((int)ch+32)));;
			}
			
			
			g.getBoard()[e1][e2].getBead().setUpgraded(false);
			bunch.add(g.getBoard()[e1][e2].getBead());
			
			g.getBoard()[e1][e2].setBead(g.getBoard()[s1][s2].getBead());
			g.getBoard()[s1][s2].setBead(null);
			g.getBoard()[s1][s2].nil=true;
			
			if(p==false){
				if(e2<=1){
					g.getBoard()[e1][e2].getBead().setUpgraded(true);
				}
			}
			else{
				if(e2>=3){
					g.getBoard()[e1][e2].getBead().setUpgraded(true);
				}
			}
			if(ans==100){
				ans=2;
			}
		}
		else{
			g.getBoard()[e1][e2].setBead(g.getBoard()[s1][s2].getBead());
			g.getBoard()[s1][s2].setBead(null);
			g.getBoard()[s1][s2].nil=true;
			if(p==false){
				if(e2<=1){
					g.getBoard()[e1][e2].getBead().setUpgraded(true);
				}
			}
			else{
				if(e2>=3){
					g.getBoard()[e1][e2].getBead().setUpgraded(true);
				}
			}
			ans=1;
		}
		return ans;
	}

	public static boolean implant(GameBoard g,int e1,int e2,List<Bead> bunch,Bead b){
		if(g.getBoard()[e1][e2].nil==false){
			return false;
		}
		else{
			bunch.remove(b);
			g.getBoard()[e1][e2].setBead(b);
			return true;
		}
	}
	
	public static Bead find(List<Bead> bunch,String s){
		for(Bead b:bunch){
			if(b.getName().equals(s)){
				return b;
			}
			else{
				continue;
			}
		}
		return null;
	}
	
	public static void setCheckerColumn(List<Bead> bunch,PointCheckerCell[] a){
		for(int i=0;i<6;i++){
			a[i].removeAll();
		}
        
        for(int i=0;i<bunch.size();i++){
    		a[i].setCheckerImage(bunch.get(i).getName());
    		a[i].setCheckers();
        }
    }
}
