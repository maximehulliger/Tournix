package tournix.deviator;

import processing.core.PApplet;
import tournix.Tournix;
import tournix.Unit;

public class Final extends Gravitor {
	
	public Final() {
		radiusMin = 50;
		force = 100;
	}
	
	public void draw() {
		super.draw();
		Tournix.app.fill(255);
		Tournix.app.arc(location.x, location.y, radiusMin*2, radiusMin*2, 0, PApplet.HALF_PI);
		Tournix.app.arc(location.x, location.y, radiusMin*2, radiusMin*2, PApplet.PI, PApplet.PI+PApplet.HALF_PI);
		Tournix.app.noFill();
	}
	
	public void onTouch(Unit unit) {
		Tournix.game.score.change(10);
		Tournix.game.scene.remove(unit);
	}
}
