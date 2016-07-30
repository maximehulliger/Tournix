package tournix;

import java.util.Observable;
import java.util.Observer;

import tournix.util.Button;
import tournix.util.Vector;

public class Game implements Observer {
	
	public Scene scene = new Scene();
	public Selector selector = new Selector();
	public Spawner spawner = new Spawner();
	public Score score = new Score();
	public Mover mover = new Mover();
	public Button statusButton = new Button(new Vector(350,50), new Vector(300, 100), "Start !");
	
	private int round = 1, levelIndex = 0;
	
	public Game() {
		Tournix.game = this;
		scene.addObserver(this);
		selector.addObserver(this);
		statusButton.visible = false;
		
		Level.levels[levelIndex].load();
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
		round = 1;
		levelIndex ++;
		if (levelIndex >= Level.levels.length) {
			// load end screen
		} else {
			scene.clear();
			Level.levels[levelIndex].load();
		}
	}
}
