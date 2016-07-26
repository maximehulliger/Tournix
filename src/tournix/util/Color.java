package tournix.util;

import tournix.Tournix;

/** Represent a color to apply in processing (color or stroke). */
public class Color {
	
	private final int[] c;

	public final Color white = new Color(255);
	public final Color grey = new Color(150);
	public final Color black = new Color(0);
	public final Color red = new Color(255, 0, 0);
	public final Color green = new Color(255, 0, 0);
	public final Color blue = new Color(0, 0, 255);
	public final Color yellow = new Color(255,255,0);
	public final Color pink = new Color(255, 105, 180);
	
	/**
	 * understand 4 arguments for a color: wether c, ca, rgb or rgba equivalent to:
	 * (c,c,c,255), (c,c,c,a), (r,g,b,255), or (r,g,b,a).
	 */
	public Color(int... rgba) {
		assert(rgba.length > 0 && rgba.length <= 4);
		if (rgba.length == 1)
			this.c = new int[] {rgba[0], rgba[0], rgba[0], 255};
		else if (rgba.length == 2)
			this.c = new int[] {rgba[0], rgba[0], rgba[0], rgba[1]};
		else if (rgba.length == 3)
			this.c = new int[] {rgba[0], rgba[1], rgba[2], 255};
		else // rgba.length = 4
			this.c = rgba;
	}

	public Color random(int a) {
		return new Color(Master.random(0, 256), Master.random(0, 256), Master.random(0, 256), a);
	}

	public Color random() {
		return random(256);
	}

	/** apply the color in fill. */
	public void fill() {
		Tournix.app.fill(c[0], c[1], c[2], c[3]);
	}
	
	/** apply the color in stroke. */
	public void stroke() {
		Tournix.app.stroke(c[0], c[1], c[2], c[3]);
	}
}