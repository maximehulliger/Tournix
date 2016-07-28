package tournix.util;

import java.util.Observable;

import tournix.Tournix;

public class Mouse extends Observable {
	public static final String mouseDragged = "MouseDragged", 
			mouseMoved = "mouseMoved",
			mouseClicked = "mouseClicked",
			mousePressed = "mousePressed",
			mouseReleased = "mouseReleased";
	public static final Vector mousePos = new Vector(),
			mouseDiff = new Vector();
	
	public void mouseMoved() {
		mousePos.set(Tournix.app.mouseX, Tournix.app.mouseY);
		mouseDiff.set(Tournix.app.mouseX-Tournix.app.pmouseX, Tournix.app.mouseY-Tournix.app.pmouseY);
		notifyObservers(mouseMoved);
	}
	
	public void mouseDragged() {
		mousePos.set(Tournix.app.mouseX, Tournix.app.mouseY);
		mouseDiff.set(Tournix.app.mouseX-Tournix.app.pmouseX, Tournix.app.mouseY-Tournix.app.pmouseY);
		notifyObservers(mouseDragged);
	}

	public void mousePressed() {
		notifyObservers(mousePressed);
	}

	public void mouseReleased() {
		notifyObservers(mouseReleased);
	}

	public void mouseClicked() {
		notifyObservers(mouseClicked);
	}
	
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}
}
