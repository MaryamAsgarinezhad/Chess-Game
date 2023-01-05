package hw3;

public class Choice {
	
	public int index1;
	public int index2;	
	public int columnNumber;
	
	public Choice(int index1, int index2) {
        this.index1 = index1;
        this.index2 = index2;
        this.columnNumber=0;
    }
	
	public Choice(int index1,int c,boolean p){
		this.index1 = index1;
		this.columnNumber=c;
	}
	
	@Override
    public String toString() {
        return "Choice{" +
                "index1=" + index1 +
                ", index2=" + index2 +
                '}';
    }
}
