package Columns;

import java.awt.Dimension;
import java.util.List;
import hw3.Bead;

public class PointCheckerCell extends CheckerCell implements UpdatableComponent{
	
	public PointCheckerCell(Dimension dimension) {
        super(dimension);
	}
	
	@Override
    public void update(List<Bead> Bunch,String s,int i) {
		if(i==1){
    		this.setCheckerImage(s);
			setCheckers();
		}
		else{
			delCheckers();
		}
    }
}
