package tournix;

import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import tournix.util.Master;
import tournix.util.Vector;

public class Score {
	
	private static final Vector scorePos = new Vector(900, 50);
	private int score = 0;
	private float scoreSize = 32;
	private List<ScoreChange> scoreChanges = new LinkedList<ScoreChange>();
	
	public void change(int diff) {
		score += diff;
		popScoreChange(diff);
	}
	
	public void draw() {
		Tournix.app.textSize(scoreSize);
		Tournix.app.textAlign(PApplet.CENTER, PApplet.CENTER);
		Tournix.app.text(score, scorePos.x, scorePos.y);
		List<ScoreChange> deadSC = new LinkedList<ScoreChange>();
		for (ScoreChange sc : scoreChanges) {
			sc.draw();
			if (sc.dead())
				deadSC.add(sc);
		}
		scoreChanges.removeAll(deadSC);
	}
	
	private void popScoreChange(int change) {
		float angle = Master.random(0, PApplet.TWO_PI);
		float x = PApplet.cos(angle)*width(score)*scoreSize;
		float y = PApplet.sin(angle)*scoreSize;
		Vector toStart = new Vector(x,y);
		Vector run = toStart.withMag(-30);
		scoreChanges.add(new ScoreChange(change, scorePos.plus(toStart), run));
	}
	
	private static final float logE10 = PApplet.log(10);
	private int width(int x) {
		boolean neg = x<0;
		if (neg)
			x = -x;
		float log10 = PApplet.log(x) / logE10;
		return (int)log10 + (neg ? 1 : 0);
	}
	
	private class ScoreChange {

		private static final float duration = 0.7f;
		private final int change, startTime;
		private final Vector startPos, run;
		
		public ScoreChange(int change, Vector startPos, Vector run) {
			this.change = change;
			this.startPos = startPos;
			this.run = run;
			this.startTime = Tournix.app.millis();
		}
		
		public void draw() {
			final float etat = (startTime - Tournix.app.millis())/(duration*1000);
			final Vector pos = startPos.plus(run.multBy(etat));
			Tournix.app.textSize(scoreSize/2);
			Tournix.app.textAlign(PApplet.CENTER, PApplet.CENTER);
			Tournix.app.text((change>0?"+":"")+change, pos.x, pos.y);
		}
		
		public boolean dead() {
			return Tournix.app.millis() > startTime + duration * 1000;
		}
	}
}
