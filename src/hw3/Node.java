package hw3;

public class Node{
	private Bead bead;
	public boolean nil=false;
	
	public Node(){
		this.nil=true;
	}
	
	public Node(Bead bead){
		this.bead=bead;
	}
	
	public void setBead(Bead bead){
		this.nil=false;
		this.bead=bead;
	}
	
	public Bead getBead(){
		return this.bead;
	}
	
}
