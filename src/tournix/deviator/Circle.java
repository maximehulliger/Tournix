package tournix.deviator;

import tournix.Tournix;
import tournix.Unit;
import tournix.util.Master;
import tournix.util.Vector;

public abstract class Circle extends Deviator {
	public float radiusMin = 10, radiusMax = 80;
	
	public Circle() {
		super();
	}
	
	public abstract void attractTo(Unit unit);

	public void draw() {
		Tournix.app.noFill();
		Tournix.app.stroke(255);
		Tournix.app.ellipse(location.x, location.y, radiusMax*2, radiusMax*2);
		Tournix.app.ellipse(location.x, location.y, radiusMin*2, radiusMin*2);
	}
	
	public boolean in(Vector point) {
		return point.minus(location).magSq() < Master.sq(radiusMax);
	}
	
	public static boolean doCollide(Circle c1, Circle c2) {
		final float dSq = c1.location.minus(c2.location).magSq();
		final float d1Sq = Master.sq(c1.radiusMax + c2.radiusMin);
		final float d2Sq = Master.sq(c2.radiusMax + c1.radiusMin);
		return dSq < d1Sq || dSq < d2Sq;
	}
}
