package tournix.deviator;

import tournix.Unit;
import tournix.util.Vector;

public abstract class Deviator {
	
	public Vector location = new Vector();
	
	public abstract void attractTo(Unit unit);
	
	public abstract boolean in(Vector point);
	
	public abstract void draw();
	
	public abstract void onTouch(Unit unit);
}
