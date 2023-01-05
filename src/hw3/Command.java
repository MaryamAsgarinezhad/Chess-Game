package hw3;

public class Command {
	
	public final Choice source;
    public final Choice destination;

    public Command(Choice source, Choice destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Command{" +
                "source=" + source +
                ", destination=" + destination +
                '}';
    }
    
}
