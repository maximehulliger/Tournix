package tournix;

import processing.core.PApplet;
import tournix.util.Vector;

public class Obstacle {
	private final Vector[] points;
	private final float[] yDiffInv;
	
	public Obstacle(Vector[] points) {
		this.points = points;
		yDiffInv = new float[points.length];
		for (int i = 0, j = points.length-1; i < points.length; j = i++) {
			yDiffInv[i] = 1/(points[j].y-points[i].y);
		}
		
	}
	
	public boolean isIn(Vector point) {
		boolean in = false;
		for (int i = 0, j = points.length-1; i < points.length; j = i++) {
		    if ( ((points[i].y>point.y) != (points[j].y>point.y)) &&
		     (point.x < (points[j].x-points[i].x) * (point.y-points[i].y) * yDiffInv[i] + points[i].x) )
		       in = !in;
		  }
		return in;
	}
	
	public void draw() {
		Tournix.app.beginShape();
		for (Vector v : points)
			Tournix.app.vertex(v.x, v.y);
		Tournix.app.endShape(PApplet.CLOSE);
	}
}
