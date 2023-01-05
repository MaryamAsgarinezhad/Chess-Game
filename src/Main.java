

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hw3.Bead;
import hw3.GameBoard;


public class Main {
	public static void main(String[] args){
		boolean player=false;
		GameBoard game=new GameBoard();
		
		List<Bead> currentBunch;
		
		if(player==false){
			currentBunch=game.black;
		}
		else{
			currentBunch=game.white;
		}
		Scanner input = new Scanner(System.in);
		while(true){
	        String str1 = input.nextLine();
	        if(str1.equals("0")){
	        	break;
	        }
	        String array1[]=str1.split(" ");
	        String array2[]=array1[1].split("");
	        String array3[]=array1[2].split("");
	        int s1=Integer.parseInt(array2[0]);
	        int s2=Integer.parseInt(array2[1]);
	        int e1=Integer.parseInt(array3[0]);
	        int e2=Integer.parseInt(array3[1]);

	        if((e1>5 || e1<1) || (e2>5 || e2<1) || (s1>5 || s1<0) || (s2>5 || s2<0) || ((s1==0 && s2==1)||(s1==1 && s2==0))){
	        	//System.out.println("out of range");
	        	print(game,game.black,game.white);
	        	continue;
	        }
	        
	        if(!(array1[0].equals("l") || array1[0].equals("s") || array1[0].equals("g") || array1[0].equals("p") ||array1[0].equals("k") ||array1[0].equals("b") ||array1[0].equals("L") ||array1[0].equals("S") ||array1[0].equals("G") ||array1[0].equals("B") ||array1[0].equals("K") ||array1[0].equals("P"))){
	        	//System.out.println("wrong letter");
	        	print(game,game.black,game.white);
	        	continue;
	        }
	        if(!array1[1].equals("00")){
	        	 if(game.getBoard()[s1-1][s2-1].getBead()!=null){
	 	        	if(!(game.getBoard()[s1-1][s2-1].getBead().getName().equals(array1[0]))){
	 		        	//System.out.println("wrong start point");
	 		        	print(game,game.black,game.white);
	 		        	continue;
	 		        }
	 		        if(game.getBoard()[s1-1][s2-1].getBead().getColor()!=player){
	 		        	//System.out.println("wrong player");
	 		        	print(game,game.black,game.white);
	 		        	continue;
	 		        }
	 	        }
	 	        else{
	 	        	//System.out.println("empty start point");
	 	        	print(game,game.black,game.white);
	 	        	continue;
	 	        }
	 	        
	 	        if(game.getBoard()[e1-1][e2-1].getBead()!=null){
	 	        	if(game.getBoard()[e1-1][e2-1].getBead().getColor()==player){
	 		        	//System.out.println("cant hit the same color");
	 		        	print(game,game.black,game.white);
	 		        	continue;
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
            	
            	if(b!=player){
 		        //	System.out.println("wrong player");
 		        	print(game,game.black,game.white);
 		        	continue;
 		        }
	        	if(currentBunch.isEmpty()){
		        	//System.out.println("empty bunch");
		        	print(game,game.black,game.white);
    	        	continue;
        		}
        		else{
        			if(find(currentBunch,array1[0])==null){
        				//System.out.println("not found in bunch");
    		        	print(game,game.black,game.white);
        	        	continue;
        			}
        			else{
        				if(!implant(game,e1-1,e2-1,currentBunch,find(currentBunch,array1[0]))){
            	        	//System.out.println("cant implant in a full point");
        					print(game,game.black,game.white);
            				continue;
            			}
            			player=!player;
            			if(player==false){
            				currentBunch=game.black;
            			}
            			else{
            				currentBunch=game.white;
            			}
            			print(game,game.black,game.white);
        	        	continue;
        			}
        		}
	        }
	        
	        
	        if(array1[0].equals("K") || array1[0].equals("k")){
	        	if(!game.KingValid(s1-1,s2-1,e1-1,e2-1)){
		        	//System.out.println("k valid");
		        	print(game,game.black,game.white);
		        	continue;
	        	}
	        	else{
	        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
	        			prnt(player,game);
	        			break;
	        		}
	        		else{
	        			print(game,game.black,game.white);
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			continue;
	        		}
	        	}
	        }
	        
	        
	        if(array1[0].equals("L")){
	        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
	        		if(!game.rokhValid(s1-1,s2-1,e1-1,e2-1)){
	    	        	//System.out.println("rokh valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        	else{
	        		if(!game.WhiteLanceValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("wl valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        }
	        
	        if(array1[0].equals("l")){
	        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
	        		if(!game.rokhValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("rokh valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        	else{
	        		if(!game.BlackLanceValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("bl valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        }
	        
	        if(array1[0].equals("G")){
	        	if(!game.WhiteGoldValid(s1-1,s2-1,e1-1,e2-1)){
        			//System.out.println("wg valid");
		        	print(game,game.black,game.white);
		        	continue;
	        	}
	        	else{
	        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
	        			prnt(player,game);
	        			break;
	        		}
	        		else{
	        			print(game,game.black,game.white);
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			continue;
	        		}
	        	}
	        }
	        
	        if(array1[0].equals("g")){
	        	if(!game.BlackGoldValid(s1-1,s2-1,e1-1,e2-1)){
        			//System.out.println("bg valid");
		        	print(game,game.black,game.white);
		        	continue;
	        	}
	        	else{
	        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
	        			prnt(player,game);
	        			break;
	        		}
	        		else{
	        			print(game,game.black,game.white);
	        			player=!player;
	        			if(player==false){
	        				currentBunch=game.black;
	        			}
	        			else{
	        				currentBunch=game.white;
	        			}
	        			continue;
	        		}
	        	}
	        }
	        
	        if(array1[0].equals("S")){
	        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
	        		if(!game.silValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("sil valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        	else{
	        		if(!game.WhiteSilverValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("ws valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        }
	        
	        if(array1[0].equals("s")){
	        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
	        		if(!game.silValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("sil valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        	else{
	        		if(!game.BlackSilverValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("bs valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        }
	        
	        if(array1[0].equals("P")){
	        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
	        		if(!game.WhiteGoldValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("wg valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        	else{
	        		if(!game.WhiteSoliderValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("wp valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        }
	        
	        if(array1[0].equals("p")){
	        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
	        		if(!game.BlackGoldValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("bg valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        	else{
	        		if(!game.BlackSoliderValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("bp valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        }
	        
	        if(array1[0].equals("B")){
	        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
	        		if(!game.philValid(s1-1,s2-1,e1-1,e2-1)){
	        		//	System.out.println("fil valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        	else{
	        		if(!game.WhiteElephantValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("wb valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        }
	        
	        if(array1[0].equals("b")){
	        	if(game.getBoard()[s1-1][s2-1].getBead().isUpgraded()){
	        		if(!game.philValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("fil valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        	else{
	        		if(!game.BlackElephantValid(s1-1,s2-1,e1-1,e2-1)){
	        			//System.out.println("bb valid");
	    	        	print(game,game.black,game.white);
			        	continue;
		        	}
		        	else{
		        		if(!move(game,array1[0],s1-1,s2-1,e1-1,e2-1,currentBunch,player)){
		        			prnt(player,game);
		        			break;
		        		}
		        		else{
		        			print(game,game.black,game.white);
		        			player=!player;
		        			if(player==false){
		        				currentBunch=game.black;
		        			}
		        			else{
		        				currentBunch=game.white;
		        			}
		        			continue;
		        		}
		        	}
	        	}
	        }
	        
	        
		}
	}

	public static void prnt(boolean p,GameBoard game){
		if(p==false){
			print(game,game.black,game.white);
			System.out.println("black wins!");
		}
		else{
			print(game,game.black,game.white);
			System.out.println("white wins!");
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
	public static void print(GameBoard g,List<Bead> b,List<Bead> w){
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if(g.getBoard()[j][i].nil==true){
					System.out.print('-');
				}
				else{
					System.out.print(g.getBoard()[j][i].getBead().getName());
				}
			}
		}
		
		System.out.println("");

		for(int i=0;i<b.size();i++){
			System.out.print(b.get(i).getName());
		}
		
		System.out.println("");

		for(int i=0;i<w.size();i++){
			System.out.print(w.get(i).getName());
		}
		System.out.println("");
	}
	
	public static boolean move(GameBoard g,String s,int s1,int s2,int e1,int e2,List<Bead> bunch,boolean p){
		boolean ans=true;
		String shah;
		if(p==false){
			shah="K";
		}
		//l 15 12   L 51 52    l 00 22    L 52 53    l 12 11
		else{
			shah="k";
		}
		if(g.getBoard()[e1][e2].getBead()!=null){
			if(g.getBoard()[e1][e2].getBead().getName().equals(shah)){
				ans=false;
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
}
