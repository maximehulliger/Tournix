package tournix.deviator;

import tournix.Tournix;
import tournix.Unit;
import tournix.util.Master;
import tournix.util.Vector;

public class Gravitor extends Circle {

	public float force = 10f;
	
	public void attractTo(Unit unit) {
		Vector toThis = location.minus(unit.location);
		float distSq = toThis.magSq();
		
		if (distSq < Master.sq(radiusMin))
			onTouch(unit);
		else if (distSq <= Master.sq(radiusMax)){
			float gForce = force/(toThis.mag());
			unit.accelerate(toThis.normalized().multBy(gForce));
		}
	}
	
	public void onTouch(Unit unit) {
		Tournix.game.scene.destroy(unit);
		Tournix.game.score.change(-5);
	}
}
