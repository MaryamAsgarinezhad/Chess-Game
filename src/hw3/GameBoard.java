package hw3;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
	private Node[][] board={{new Node(),new Node(),new Node(),new Node(),new Node()},
			{new Node(),new Node(),new Node(),new Node(),new Node()},
			{new Node(),new Node(),new Node(),new Node(),new Node()},
			{new Node(),new Node(),new Node(),new Node(),new Node()},
			{new Node(),new Node(),new Node(),new Node(),new Node()}};
	
	public List<Bead> black=new ArrayList<Bead>();
	public List<Bead> white=new ArrayList<Bead>();
	
	public GameBoard(){
		this.board[0][0].setBead(new Bead("K",false,true));
		this.board[1][0].setBead(new Bead("G",false,true));
		this.board[2][0].setBead(new Bead("S",false,true));
		this.board[3][0].setBead(new Bead("B",false,true));
		this.board[4][0].setBead(new Bead("L",false,true));
		this.board[0][1].setBead(new Bead("P",false,true));
		
		this.board[0][4].setBead(new Bead("l",false,false));
		this.board[1][4].setBead(new Bead("b",false,false));
		this.board[2][4].setBead(new Bead("s",false,false));
		this.board[3][4].setBead(new Bead("g",false,false));
		this.board[4][4].setBead(new Bead("k",false,false));
		this.board[4][3].setBead(new Bead("p",false,false));
	}
	public void setBoard(Node[][] board){
		this.board=board;
	}
	
	public Node[][] getBoard(){
		return this.board;
	}
	
	public boolean KingValid(int s1,int s2,int e1,int e2){
		if(Math.abs(s1-e1)<=1 && Math.abs(s2-e2)<=1){
			if(Math.abs(s1-e1)==1 || Math.abs(s2-e2)==1){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public boolean rokhValid(int s1,int s2,int e1,int e2){
		int a=1;
		if(e1==s1 && e2>s2){
			for(int i=s2+1;i<e2;i++){
				if(this.getBoard()[e1][i].nil==false){
					a=0;
					break;
				}
			}
			if(a==0){
		        return false;
			}
			else{
				return true;

			}
		}
		else{
			if(e1==s1 && e2<s2){
				for(int i=e2+1;i<s2;i++){
					if(this.getBoard()[e1][i].nil==false){
						a=0;
						break;
					}
				}
				if(a==0){
			        return false;
				}
				else{
					return true;

				}
			}
			else{
				if(e1>s1 && e2==s2){
					for(int i=s1+1;i<e1;i++){
						if(this.getBoard()[i][e2].nil==false){
							a=0;
							break;
						}
					}
					if(a==0){
				        return false;
					}
					else{
						return true;

					}
				}
				else{
					if(e1<s1 && e2==s2){
						for(int i=e1+1;i<s1;i++){
							if(this.getBoard()[i][e2].nil==false){
								a=0;
								break;
							}
						}
						if(a==0){
					        return false;
						}
						else{
							return true;

						}
					}
					else{
						return false;
					}
				}
			}
		}
	}
	
	public boolean WhiteLanceValid(int s1,int s2,int e1,int e2){
		int a=1;
		if(e1==s1 && e2>s2){
			for(int i=s2+1;i<e2;i++){
				if(this.getBoard()[e1][i].nil==false){
					a=0;
					break;
				}
			}
			if(a==0){
		        return false;
			}
			else{
				return true;

			}
		}
		else{
			return false;
		}
	}
	
	public boolean BlackLanceValid(int s1,int s2,int e1,int e2){
		int a=1;
		if(e1==s1 && e2<s2){
			for(int i=s2-1;i>e2;i--){
				if(this.getBoard()[e1][i].nil==false){
					a=0;
					break;
				}
			}
			if(a==0){
		        return false;
			}
			else{
				return true;

			}
		}
		else{
			return false;
		}
	}
	
	public boolean WhiteGoldValid(int s1,int s2,int e1,int e2){
		if(Math.abs(s1-e1)==1 || Math.abs(s2-e2)==1){
			if(s1!=e1 && s2>e2){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return false;
		}
	}
	
	public boolean BlackGoldValid(int s1,int s2,int e1,int e2){
		if(Math.abs(s1-e1)==1 || Math.abs(s2-e2)==1){
			if(s1!=e1 && s2<e2){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return false;
		}
	}
	
	public boolean WhiteSilverValid(int s1,int s2,int e1,int e2){
		if((e2-s2==1 && (s1==e1 || s1-e1==1 || s1-e1==-1))){
			return true;
		}
		if(((e1-s1==-1 || e1-s1==1) && s2-e2==1)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean BlackSilverValid(int s1,int s2,int e1,int e2){
		if((e2-s2==-1 && (s1==e1 || s1-e1==1 || s1-e1==-1))){
			//System.out.println("gggggg");
			return true;
		}
		if(((e1-s1==-1 || e1-s1==1) && s2-e2==-1)){
			//System.out.println("gggggg2");
			return true;
		}
		else{
			//System.out.println("ffff");
			return false;
		}
	}
	
	public boolean WhiteSoliderValid(int s1,int s2,int e1,int e2){
		if(e1==s1 && e2-s2==1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean BlackSoliderValid(int s1,int s2,int e1,int e2){
		if(e1==s1 && e2-s2==-1){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean philValid(int s1,int s2,int e1,int e2){
		if(WhiteElephantValid(s1,s2,e1,e2)){
			return true;
		}
		else{
			if(BlackElephantValid(s1,s2,e1,e2)){
				return true;
			}
			else{
				if(KingValid(s1,s2,e1,e2)){
					return true;
				}
				else{
					return false;
				}
			}
		}
	}
	public boolean WhiteElephantValid(int s1,int s2,int e1,int e2){
		int a=1;
		if(e1>s1 && e2>s2){
			if(e1-s1==e2-s2){
				int j=e1-1;
				for(int i=e2-1;i>s2;i--){
					if(j==s1){
						break;
					}
					if(this.board[j][i].nil==false){
						a=0;
					}
					j--;
				}
				if(a==0){
					return false;
				}
				else{
					return true;
				}
			}
			else{
				return false;
			}
		}
		else{
			if(e1<s1 && e2>s2){
				if(s1-e1==e2-s2){
					int j=e1+1;
					for(int i=e2-1;i>s2;i--){
						if(j==s1){
							break;
						}
						if(this.board[j][i].nil==false){
							a=0;
						}
						j++;
					}
					if(a==0){
						return false;
					}
					else{
						return true;
					}
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
	}
	public boolean silValid(int s1,int s2,int e1,int e2){
		if(Math.abs(s1-e1)<=2 && Math.abs(s2-e2)<=2){
			if(this.KingValid(s1, s2, e1, e2)){
//				System.out.println("1111");
				return true;
			}
			else{
				if(Math.abs(s1-e1)==2 || Math.abs(s2-e2)==2){
					if(s1==e1){
						if(this.board[s1][(s2+e2)/2].nil==false){
//							System.out.println("2222");
							return false;
						}
						else{
//							System.out.println("3333");
							return true;
						}
					}
					
					if(s2==e2){
//						System.out.println("kharrrr");
						if(this.board[(s1+e1)/2][e2].nil==false){
//							System.out.println("4444");
							return false;
						}
						else{
//							System.out.println("5555");
							return true;
						}
					}
//					System.out.println(s1 + " " + s2 + " " +e1 + " " + e2 );
					return true;
				}
				else{
//					System.out.println("7");
					return false;
				}
			}
		}
		
		else{
			return false;
		}
	}
	
	public boolean BlackElephantValid(int s1,int s2,int e1,int e2){
		int a=1;
		if(e1<s1 && e2<s2){
			if(e1-s1==e2-s2){
				int j=e1+1;
				for(int i=e2+1;i<s2;i++){
					if(j==s1){
						break;
					}
					if(this.board[j][i].nil==false){
						a=0;
					}
					j++;
				}
				if(a==0){
					return false;
				}
				else{
					return true;
				}
			}
			else{
				return false;
			}
		}
		else{
			if(e1>s1 && e2<s2){
				if(s1-e1==e2-s2){
					int j=e1-1;
					for(int i=e2+1;i<s2;i++){
						if(j==s1){
							break;
						}
						if(this.board[j][i].nil==false){
							a=0;
						}
						j--;
					}
					if(a==0){
						return false;
					}
					else{
						return true;
					}
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
	}
}
