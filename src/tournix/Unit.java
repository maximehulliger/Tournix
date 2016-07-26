package tournix;

import processing.core.PVector;
import tournix.util.Vector;

public class Unit {
	
	public Vector location = new Vector(),
			velocity = new Vector(); 
	private Vector direction = new Vector(1, 0);
	
	
	public static final float lenght = 15, width = 5;
	
	public void update() {
		location.add(velocity);
	}
	
	public void draw() {
		Tournix.app.pushMatrix();
		Tournix.app.translate(location.x, location.y);
		Vector direction = velocity.equals(Vector.zero) ? 
				this.direction : velocity.normalized();
		PVector front = direction.multBy(lenght);
		PVector right = direction.cross(Vector.zn).multBy(width);
		Tournix.app.triangle(-right.x, -right.y, front.x, front.y, right.x, right.y);
		Tournix.app.popMatrix();
	}
	
	public void accelerate(Vector a) {
		velocity.add(a);
	}
}
