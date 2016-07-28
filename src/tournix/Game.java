package tournix;

import java.util.Observable;
import java.util.Observer;

import tournix.deviator.Deviator;
import tournix.deviator.Final;
import tournix.deviator.Gravitor;
import tournix.util.Vector;

public class Game implements Observer {
	
	public Scene scene = new Scene();
	public Selector selector = new Selector();
	public Spawner spawner = new Spawner(new Vector(0,500), new Vector(1,0), 5);
	public Score score = new Score();
	public Mover mover = new Mover();
	
	private int round = 0;
	
	public Game() {
		scene.addObserver(this);
		selector.addObserver(this);
		
		//scene creation:
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

	public void update(Observable o, Object arg) {
		if (o == scene) {
			if (arg == Scene.allUnitsRemovedMsg) {
				mover.active = true;
			}
		} else if (o == selector) {
			if (arg == Selector.allDeviatorPlacedMsg) {
				Tournix.game.spawner.start();
				mover.active = false;
			}
		}
	}
}
