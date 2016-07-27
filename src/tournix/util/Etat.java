package tournix.util;

import tournix.Tournix;

public class Etat {
	
	private static final float toSeconds = 1f/1000;
	private final float duration, durationInv;
	private int startTime = -1;
	
	public Etat(float duration) {
		this.duration = duration;
		this.durationInv = 1/duration;
	}
	
	public Etat start() {
		startTime = Tournix.app.millis();
		return this;
	}
	
	private float elapsedTime() {
		assert (startTime > 0) : "Etat should be started";
		return (Tournix.app.millis() - startTime)*toSeconds;
	}
	
	/** Retourne l'avancement entre 0 -> 1. */
	public float etat() {
		final float elapsedTime = elapsedTime();
		if (elapsedTime > duration)
			return 1;
		else
			return elapsedTime*durationInv;
	}
	
	public class Crete extends Etat {
		private final float creteTime, creteTimeInv, creteTimeComplInv;
		/** creteTime: ]0,1[ */
		public Crete(float duration, float creteTime) {
			super(duration);
			this.creteTime = creteTime;
			this.creteTimeInv = 1/creteTime;
			this.creteTimeComplInv = 1/(1-creteTime);
		}

		/** 
		 * Retourne l'avancement de l'explosion en crête:
		 * 0 au début, 1 à creteTime, 0 à la fin
		 */
		public float etat() {
			float e = super.etat();
			if (e == 1)
				return 0;
			else if (e > creteTime)
				return e*creteTimeInv;
			else 			
				return (1-e)*creteTimeComplInv;
		}
	}
}
