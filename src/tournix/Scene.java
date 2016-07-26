package tournix;

import java.util.ArrayList;
import java.util.List;

import tournix.deviator.Deviator;

public class Scene {
	public List<Unit> units = new ArrayList<Unit>();
	private List<Unit> unitsToRemove = new ArrayList<Unit>();
	public List<Deviator> deviators = new ArrayList<Deviator>();
	private final float margin = 30;
	
	public void draw() {
		units.removeAll(unitsToRemove);
		unitsToRemove.clear();
		Tournix.app.noFill();
		Tournix.app.stroke(255);
		for (Unit u : units)
			u.draw();
		for (Deviator a : deviators)
			a.draw();
	}
	
	public void remove(Unit unit) {
		unitsToRemove.add(unit);
	}
	
	public void update() {
		for (Unit u : units) {
			for (Deviator a : deviators)
				a.attractTo(u);
			u.update();
			// remove units too far away
			if (u.location.x < -margin || u.location.x > Tournix.width+margin
					|| u.location.y < -margin || u.location.y > Tournix.height+margin)
				remove(u);
		}
	}
	
	public void clear() {
		units.clear();
		deviators.clear();
	}
}
