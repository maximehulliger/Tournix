package tournix.deviator;

import tournix.Tournix;
import tournix.Unit;
import tournix.util.Vector;

public abstract class Deviator {
	
	public Vector location = new Vector();
	
	public abstract void attractTo(Unit unit);
	
	public abstract boolean in(Vector point);
	
	public abstract void draw();
	
	public void onTouch(Unit unit) {
		Tournix.game.scene.remove(unit);
	}
}
