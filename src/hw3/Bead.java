package hw3;

public class Bead{
	private String name;
	private boolean upgraded;
	private boolean color;
	
	public Bead(String name, boolean upgraded, boolean color){
		this.name=name;
		this.upgraded=upgraded;
		this.color=color;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isUpgraded(){
		return this.upgraded;
	}
	
	public void setUpgraded(boolean upgraded){
		this.upgraded=upgraded;
	}
	
	public void setColor(boolean color){
		this.color=color;
	}
	
	public boolean getColor(){
		return this.color;
	}
}
