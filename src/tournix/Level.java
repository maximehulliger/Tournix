package tournix;

import tournix.deviator.Deviator;
import tournix.deviator.Final;
import tournix.deviator.Gravitor;
import tournix.util.Vector;

public abstract class Level {
	public static final Level[] levels = new Level[] {
			new Level1(), new Level2()
	};
	
	public abstract void load();

	private static class Level1 extends Level {

		public void load() {
			Tournix.game.spawner.set(new Vector(0,500), new Vector(1,0));
			
			Deviator a = new Gravitor();
			Final f = new Final();
			
			f.location.set(900, 200);
			
			Tournix.game.scene.deviators.add(f);
			Tournix.game.selector.toPlace.add(a);
		}
		
	}

	private static class Level2 extends Level {

		public void load() {
			Tournix.game.spawner.set(new Vector(0,200), new Vector(1,0));
			
			Deviator a = new Gravitor();
			Final f = new Final();
			
			f.location.set(900, 500);
			
			Tournix.game.scene.deviators.add(f);
			Tournix.game.selector.toPlace.add(a);
		}
		
	}
}
