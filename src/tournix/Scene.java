package tournix;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import tournix.deviator.Deviator;
import tournix.util.Color;
import tournix.util.Vector;

public class Scene extends Observable {
	public static final String allUnitsRemovedMsg = "allUnitsRemovedMsg";
	public List<Unit> units = new ArrayList<>(), unitsToRemove = new ArrayList<>();
	public List<Explosion> explosions = new ArrayList<>(), explosionsToRemove = new ArrayList<>();
	public List<Deviator> deviators = new ArrayList<>();
	public List<Obstacle> obstacles = new ArrayList<>();
	private final float margin = 30;
	public Spawner spawner = new Spawner();
	
	public Scene() {
		explosions.add(new Explosion(new Vector(500,500), Color.random()));
	}
	
	public void draw() {
		spawner.draw();
		for (Unit u : units)
			u.draw();
		for (Deviator a : deviators)
			a.draw();
		for (Obstacle o : obstacles)
			o.draw();
		for (Explosion e : explosions)
			e.draw();
	}

	public void remove(Unit unit) {
		unitsToRemove.add(unit);
	}

	public void remove(Explosion explosion) {
		explosionsToRemove.add(explosion);
	}
	
	public void destroy(Unit unit) {
		remove(unit);
		explosions.add(new Explosion(unit.location, unit.color));
	}
	
	public void update() {
		// check for removal
		if (units.removeAll(unitsToRemove)) {
			unitsToRemove.clear();
			if (units.size() == 0){
				setChanged();
				notifyObservers(allUnitsRemovedMsg);
			}
		}
		explosions.removeAll(explosionsToRemove);
		explosionsToRemove.clear();
		
		spawner.update();
		
		// update & attract units
		for (Unit u : units) {
			for (Deviator a : deviators)
				a.attractTo(u);
			u.update();
			// remove units too far away
			if (u.location.x < -margin || u.location.x > Tournix.width+margin
					|| u.location.y < -margin || u.location.y > Tournix.height+margin)
				remove(u);
			// remove units in obstacle
			for (Obstacle o : obstacles)
				if (o.isIn(u.location))
					destroy(u);
		}
	}
	
	public void clear() {
		units.clear();
		deviators.clear();
		obstacles.clear();
	}
}
