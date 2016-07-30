package tournix;

import tournix.deviator.Deviator;
import tournix.deviator.Final;
import tournix.deviator.Gravitor;
import tournix.util.Vector;

public abstract class Level {
	public static final Level[] levels = new Level[] {
			new Level1(), new Level2()
	};
	
	public abstract void load(Game game);

	private static class Level1 extends Level {

		public void load(Game game) {
			game.scene.spawner.set(new Vector(0,500), new Vector(1,0));
			
			Deviator a = new Gravitor();
			Final f = new Final();
			Obstacle upperObst = new Obstacle(new Vector[] {
					new Vector(0,0), new Vector(860,0), 
					new Vector(660,220), new Vector(400,190), 
					new Vector(320,400), new Vector(0,400)
			});
			Obstacle downObst = new Obstacle(new Vector[] {
					new Vector(0,719), new Vector(0,600), 
					new Vector(380,620), new Vector(650,550), 
					new Vector(915,450), new Vector(952,330), 
					new Vector(1079,300), new Vector(1079,719)
			});
			
			f.location.set(900, 200);
			
			game.scene.deviators.add(f);
			game.scene.obstacles.add(upperObst);
			game.scene.obstacles.add(downObst);
			game.selector.toPlace.add(a);
		}
	}

	private static class Level2 extends Level {

		public void load(Game game) {
			game.scene.spawner.set(new Vector(0,200), new Vector(1,0));
			
			Deviator a = new Gravitor();
			Final f = new Final();
			
			f.location.set(900, 500);
			
			game.scene.deviators.add(f);
			game.selector.toPlace.add(a);
		}
	}
}
