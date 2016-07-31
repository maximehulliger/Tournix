package tournix;

import tournix.util.Color;
import tournix.util.Etat;
import tournix.util.Vector;

public class Explosion {
	private static final float explosionRadius = 15, blastRadius = 30,
			explosionDuration = 0.7f, blastDuration = 0.3f;
	private final Vector location;
	private final Color color;
	private final Etat explosion = new Etat.Crete(explosionDuration, 0.7f).start(),
			blast = new Etat(blastDuration).start();
	
	public Explosion(Vector location, Color color) {
		this.location = location;
		this.color = color;
	}
	
	public void draw() {
		// the blast
		final float blastEtat =  blast.etat();
		if (blastEtat < 1) {
			Tournix.app.noFill();
			Tournix.app.stroke(255, 100);
			final float radiusBlast = blastEtat * blastRadius*2;
			Tournix.app.ellipse(location.x, location.y, radiusBlast, radiusBlast);
		}
		// the explosion
		final float explEtat = explosion.etat();
		if (explEtat > 0) {
			color.fill();
			color.stroke();
			final float radiusExpl = explEtat * explosionRadius*2;
			Tournix.app.ellipse(location.x, location.y, radiusExpl, radiusExpl);
			Tournix.app.fill(255);
			Tournix.app.noFill();
			Tournix.app.stroke(255);
		} else if (explosion.over())
			Tournix.game.scene.remove(this);
	}
	
}
