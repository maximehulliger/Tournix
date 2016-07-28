package tournix;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import processing.core.PApplet;
import processing.core.PVector;
import tournix.util.Mouse;

public class Tournix extends PApplet {
	/** Main window size. */
	public static final int width = 1080, height = 720;
	public static final float fps = 30;
	
	public static Game game;
	public static Mouse mouse = new Mouse();
	public static PApplet app;
	
	public static void main(String args[]) {
		new Tournix().launch();
	}
	
	public Tournix() {
		app = this;
		game = new Game();
	}
	
	/** Launch the whole software. */
	public void launch() {
		// set window location
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		PVector stageLoc = new PVector((gd.getDisplayMode().getWidth() - width) / 2,
				(gd.getDisplayMode().getHeight() - height) / 3);
		String[] sketchArgs = {"--location="+(int)stageLoc.x+","+(int)stageLoc.y, this.getClass().getName()};
		PApplet.runSketch( sketchArgs, this );
	}
	
	public void settings() {
		//1080, 720
		size(width, height);
	}
	
	public void setup() {
		frameRate(fps);
	}

	public void draw() {
		game.update();
		game.draw();
	}
	
	public void keyPressed() {
		if (key == 'r') {
			game = new Game();
		}
	}
	
	public void mouseClicked() {
		mouse.mouseClicked();
	}
	
	public void mousePressed() {
		mouse.mousePressed();
	}
	
	public void mouseReleased() {
		mouse.mouseReleased();
	}

	public void mouseMoved() {
		mouse.mouseMoved();
	}

	public void mouseDragged() {
		mouse.mouseDragged();
	}
}
