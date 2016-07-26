package tournix;

import tournix.deviator.Deviator;
import tournix.util.Vector;

/** Class responsible to place and move the deviators. */
public class Mover {
	public boolean active = true;
	public Deviator focused = null;
	
	public void mousePressed() {
		Vector mousePos = new Vector(Tournix.app.mouseX, Tournix.app.mouseY);
		for (Deviator d : Tournix.game.scene.deviators) {
			if (d.in(mousePos)) {
				focused = d;
				return;
			}
		}
	}
	
	public void mouseReleased() {
		focused = null;
	}
	
	public void mouseDragged() {
		if (active && focused != null) {
			Vector mouseDiff = new Vector(Tournix.app.mouseX-Tournix.app.pmouseX, Tournix.app.mouseY-Tournix.app.pmouseY);
			focused.location.add(mouseDiff);
		}
	}
	
	
}
