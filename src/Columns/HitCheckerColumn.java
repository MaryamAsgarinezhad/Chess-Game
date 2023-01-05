package Columns;

import java.awt.*;
import java.util.List;
import hw3.Bead;

public class HitCheckerColumn extends CheckerColumn implements UpdatableComponent{
	
//	public HitCheckerColumn(Dimension dimension,boolean p) {
//        super(dimension, p);
//	}
	
	@Override
    public void update(List<Bead> Bunch,String s,int i) {
		setCheckers(Bunch);
    }
}
