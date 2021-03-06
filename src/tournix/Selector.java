package tournix;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import tournix.deviator.Circle;
import tournix.deviator.Deviator;
import tournix.deviator.Gravitor;
import tournix.util.Etat;
import tournix.util.Master;
import tournix.util.Mouse;

public class Selector extends Observable implements Observer {
	
	public static final String allDeviatorPlacedMsg = "allDeviatorPlacedMsg";
	public final float boxSize = 60;
	public final float marginHolder = boxSize;
	public final float marginShape = boxSize/5;
	private final Etat transitionBoxes = new Etat(0.7f).start();
	private float boxLeft = marginHolder;
	private final int nbBoxMax = 3;
	
	public List<Deviator> toPlace = new ArrayList<Deviator>();
	private Deviator toPlaceCurrent = null;
	private float etatToPlaceError = 1; //0:err, 1: Ok
	private final float toPlaceErrorTransitionTime = 0.4f;
	
	public Selector() {
		Tournix.mouse.addObserver(this);
	}
	
	public void draw() {
		int nbBox = Master.min(nbBoxMax, toPlace.size());
		
		final float etatBoxes = transitionBoxes.etat();
		
		for (int i=0; i<nbBox; i++) {
			final int b=nbBoxMax-i-1;
			Tournix.app.noFill();
			Tournix.app.stroke(255, 255f*(b+1+etatBoxes)/(nbBoxMax+1));
			
			float boxLeft = marginHolder+(b-1+etatBoxes)*boxSize;
			if (i==0)
				this.boxLeft = boxLeft;
			Tournix.app.rect(boxLeft, marginHolder, boxSize, boxSize, 7);
			
			Deviator d = toPlace.get(i);
			if (d instanceof Circle) {
				final float tangent = boxSize - marginShape*2;
				Tournix.app.ellipse(boxLeft+boxSize/2, marginHolder+boxSize/2, 
						tangent, tangent);
			}
			
			Tournix.app.textSize(16);
			Tournix.app.textAlign(PApplet.CENTER, PApplet.CENTER);
			String text = "";
			if (d instanceof Gravitor) {
				text = "G";
			}
			Tournix.app.text(text, boxLeft+boxSize/2, marginHolder+boxSize/2);
		}
		
		if (toPlaceCurrent != null) {
			if (etatToPlaceError < 1) {
				etatToPlaceError += 1f/(Tournix.fps*toPlaceErrorTransitionTime);
				if (etatToPlaceError > 1) {
					etatToPlaceError = 1;
				}
				//stroke from white to red
				float s = 255*etatToPlaceError;
				Tournix.app.stroke(255, s, s); 
			} else
				Tournix.app.stroke(255);
			
			toPlaceCurrent.location.set(Tournix.app.mouseX, Tournix.app.mouseY);
			toPlaceCurrent.draw();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == Mouse.mouseClicked) {
			// click on a box
			if (toPlace.size() > 0 &&
					Tournix.app.mouseX >= boxLeft && Tournix.app.mouseX <= boxLeft+boxSize
					&& Tournix.app.mouseY >= marginHolder && Tournix.app.mouseY <= marginHolder+boxSize) {
				if (toPlaceCurrent != null) {
					etatToPlaceError = 0;
				} else {
					toPlaceCurrent = toPlace.get(0);
					toPlace.remove(0);
					transitionBoxes.start();
				}
			//click with a selected deviator
			} else if (toPlaceCurrent != null) {
				Tournix.game.scene.deviators.add(toPlaceCurrent);
				toPlaceCurrent = null;
				if (toPlace.size()==0) {
					setChanged();
					notifyObservers(allDeviatorPlacedMsg);
				}
			}
		}
		
	}
}
