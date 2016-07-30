package tournix.util;

import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import tournix.Tournix;

public class Button implements Observer {
	
	public final Vector location, size;
	public String text;
	public Runnable onClick = () -> {};
	public boolean active = true, visible = true;
	private boolean clicked = false;
	
	public Button(Vector location, Vector size, String text) {
		this.location = location;
		this.size = size;
		this.text = text;
		Tournix.mouse.addObserver(this);
	}
	
	public void draw() {
		if (visible || clicked) {
			if (clicked)
				Tournix.app.fill(200);
			if (clicked || active && mouseIn())
				Tournix.app.rect(location.x, location.y, size.x, size.y);
			if (clicked) {
				Tournix.app.noFill();
				clicked = false;
			}
			Tournix.app.textSize(50);
			Tournix.app.textAlign(PApplet.CENTER, PApplet.CENTER);
			Tournix.app.text(text, location.x+size.x/2, location.y+size.y*3/7);
		}
	}
	
	public void update(Observable o, Object arg) {
		if (active && visible && arg == Mouse.mouseClicked && mouseIn()) {
			onClick.run();
			clicked = true;
		}
	}
	
	private boolean mouseIn() {
		return Tournix.app.mouseX >= location.x && Tournix.app.mouseX <= location.x+size.x
				&& Tournix.app.mouseY >= location.y && Tournix.app.mouseY <= location.y+size.y;
	}
}
