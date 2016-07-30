package tournix;

import tournix.util.Master;
import tournix.util.Vector;

public class Spawner {
	
	private final float lenght = 60, width = 20;
	private final Vector location, direction;
	private final Vector front, right;
	private final Vector frontIn, rightIn;
	private final float spawnDuration = 5;
	private final int unitSpawn = 200;
	private int unitLeft;
	private float unitAcc = 0;
	private final float initialVelocity;
	private boolean started = false;
	
	public Spawner(Vector location, Vector direction, float initialVelocity) {
		this.location = location;
		this.direction = direction;
		this.front = direction.multBy(lenght+Unit.lenght);
		this.right = direction.cross(Vector.zn).multBy(width+Unit.width/2);
		this.frontIn = direction.multBy(lenght);
		this.rightIn = direction.cross(Vector.zn).multBy(width);
		this.initialVelocity = initialVelocity;
	}
	
	public void start() {
		started = true;
		unitLeft = unitSpawn;
	}
	
	public void update() {
		if (started && unitLeft > 0) {
			final float rateContrF = unitSpawn/(spawnDuration*Tournix.fps);
			final int rateContr = (int)rateContrF;
			unitAcc += rateContrF-rateContr;
			final int accContr = (int)unitAcc;
			final int n = Master.min(rateContr+accContr, unitLeft);
			spawnUnit(n);
			unitLeft -= n;
			unitAcc -= accContr;
		}
	}
	
	private void spawnUnit(int n) {
		for (int i=0; i<n; i++) {
			final float xloc = Master.randomBi();
			final float yloc = Master.random(0, 1-Master.abs(xloc));
			Unit u = new Unit();
			u.location = location.plus(rightIn.multBy(xloc), frontIn.multBy(yloc));
			u.velocity = direction.multBy(initialVelocity);
			Tournix.game.scene.units.add(u);
		}
	}
	
	public void draw() {
		Tournix.app.pushMatrix();
		Tournix.app.translate(location.x, location.y);
		Tournix.app.triangle(-right.x, -right.y, front.x, front.y, right.x, right.y);
		Tournix.app.popMatrix();
	}
	
}
