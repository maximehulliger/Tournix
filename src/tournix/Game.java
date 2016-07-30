package tournix;

import java.util.Observable;
import java.util.Observer;

import tournix.deviator.Deviator;
import tournix.deviator.Final;
import tournix.deviator.Gravitor;
import tournix.util.Button;
import tournix.util.Vector;

public class Game implements Observer {
	
	public Scene scene = new Scene();
	public Selector selector = new Selector();
	public Spawner spawner = new Spawner(new Vector(0,500), new Vector(1,0), 5);
	public Score score = new Score();
	public Mover mover = new Mover();
	public Button statusButton = new Button(new Vector(350,50), new Vector(300, 100), "Start !");
	
	private int round = 1;
	
	public Game() {
		scene.addObserver(this);
		selector.addObserver(this);
		statusButton.visible = false;
		
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
		statusButton.draw();
	}

	public void update(Observable o, Object arg) {
		if (o == scene) {
			if (arg == Scene.allUnitsRemovedMsg) {
				// end of the round
				mover.active = true;
				round++;
				if (score.score > 1500)
					loadNextLevel();
				else if (round == 2) {
					statusButton.visible = true;
					statusButton.text = "Start Round 2";
				} else if (round == 3) {
					statusButton.visible = true;
					statusButton.text = "Start Last Round";
				} else { //over
					statusButton.visible = true;
					statusButton.text = "Game Over";
					statusButton.onClick = () -> restart();
				}
			}
		} else if (o == selector) {
			if (arg == Selector.allDeviatorPlacedMsg) {
				statusButton.visible = true;
				statusButton.onClick = () -> startRun();
			}
		}
	}
	
	private void startRun() {
		Tournix.game.spawner.start();
		mover.active = false;
		statusButton.visible = false;
		score.score = 0;
	}
	
	private void restart() {
		
	}
	
	private void loadNextLevel() {
		
	}
}
