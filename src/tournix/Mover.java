package tournix;

import java.util.Observable;
import java.util.Observer;

import tournix.deviator.Deviator;
import tournix.util.Mouse;

/** Class responsible to place and move the deviators. */
public class Mover implements Observer {
	public boolean active = true;
	public Deviator focused = null;
	
	public Mover() {
		Tournix.mouse.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == Mouse.mousePressed) {
			// get a Deviator under the mouse.
			for (Deviator d : Tournix.game.scene.deviators) {
				if (d.in(Mouse.mousePos)) {
					focused = d;
					return;
				}
			}
		} else if (arg == Mouse.mouseDragged) {
			if (active && focused != null) {
				focused.location.add(Mouse.mouseDiff);
			}
		} else if (arg == Mouse.mouseReleased) {
			focused = null;
		}
		
	}
	
	
}
