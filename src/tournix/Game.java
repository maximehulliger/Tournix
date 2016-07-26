package tournix;

import tournix.deviator.Deviator;
import tournix.deviator.Final;
import tournix.deviator.Gravitor;
import tournix.util.Vector;

public class Game {
	public enum Mode {
		Placement, Run
	}
	
	public Scene scene = new Scene();
	public Selector selector = new Selector();
	public Spawner spawner = new Spawner(new Vector(100,500), new Vector(1,0), 5);
	public Score score = new Score();
	public Placer placer = new Placer();
	public Mode mode = Mode.Placement;
	
	public Game() {
		Deviator a = new Gravitor();
		Deviator a2 = new Gravitor();
		Final f = new Final();
		
		f.location.set(900, 200);
		
		scene.deviators.add(f);
		selector.toPlace.add(a);
		selector.toPlace.add(a2);
	}
	
	public void update() {
		spawner.update();
		scene.update();
	}

	public void draw() {
		Tournix.app.background(0);
		scene.draw();
		spawner.draw();
		selector.draw();
		score.draw();
	}

	public void mouseClicked() {
		selector.mouseClicked();
	}
}
