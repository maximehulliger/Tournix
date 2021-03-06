package tournix.util;

import processing.core.PVector;

public class Vector extends PVector {
	private static final long serialVersionUID = 1;
	
	public static final Vector zero = new Vector(0,0,0);
	public static final Vector xn = new Vector(1, 0, 0);
	public static final Vector yn = new Vector(0, 1, 0);
	public static final Vector zn = new Vector(0, 0, 1);
	/** Small value used to consider a value as zero. */
	public static final float epsilon = 1E-5f;
	
	// --- Contructor ---
	
	public Vector() {
		super();
	}

	public Vector(float x, float y, float z) {
		super(x,y,z); 
	}

	public Vector(float x, float y) {
		super(x,y); 
	}

	public Vector(PVector v) {
		super(v.x,v.y,v.z); 
	}
	
	// --- Getters ---

	public Vector copy() {
		return new Vector(this);
	}

	public Vector normalized() {
		return new Vector(copy().normalize());
	}

	/** Return a new vector: this + v... */
	public Vector plus(Vector... v) {
		return copy().add(v);
	}

	/** Return a new vector: this - v. */
	public Vector minus(Vector v) {
		return copy().sub(v);
	}

	/** Return a new vector: this * f. */
	public Vector multBy(float f) {
		return copy().mult(f);
	}

	public Vector cross(PVector v) {
		return new Vector(super.cross(v));
	}

	public Vector limited(float max) {
		return copy().limit(max);
	}

	public Vector withMag(float mag) {
		return copy().setMag(mag);
	}

	/** Multiply this vector element by element. */
	public Vector multElementsBy(Vector other) {
		return copy().multElements(other);
	}

	/** Divide this vector element by element. */
	public Vector divElementsBy(Vector other) {
		return copy().divElements(other);
	}
	
	// --- modifyers ---

	public Vector set(PVector v) {
		super.set(v);
		return this;
	}

	public Vector set(float x, float y, float z) {
		super.set(x,y,z);
		return this;
	}

	/** Add some vectors to this. */
	public Vector add(Vector... vs) {
		for (int i=0; i<vs.length; i++)
			super.add(vs[i]);
		return this;
	}
	
	public Vector sub(Vector v) {
		super.sub(v);
		return this;
	}
	
	public Vector mult(float f) {
		super.mult(f);
		return this;
	}

	public Vector div(float f) {
		super.mult(1/f);
		return this;
	}

	public Vector limit(float max) {
		super.limit(max);
		return this;
	}

	public Vector setMag(float mag) {
		super.setMag(mag);
		return this;
	}

	/** Multiply this vector element by element. */
	public Vector multElements(Vector other) {
		return set(this.x * other.x, this.y * other.y, this.z * other.z);
	}

	/** Divide this vector element by element. */
	public Vector divElements(Vector other) {
		return set(this.x / other.x, this.y / other.y, this.z / other.z);
	}
	
	// --- static ---

	public static Vector sumOf(Vector... vs) {
		Vector ret = vs[0].copy();
		for (int i=1; i<vs.length; i++)
			ret.add(vs[1]);
		return ret;
	}

	/** return new Vector(d,d,d). */
	public static Vector cube(float d) {
		return new Vector(d, d, d);
	}

	/** Deep copy. */
	public static Vector[] copy(Vector[] vectors) {
		Vector[] ret = new Vector[vectors.length];
		for (int i=0; i<vectors.length; i++)
			ret[i] = vectors[i].copy();
		return ret;
	}
	
	public static Vector average(Vector[] vectors) {
		return Vector.sumOf(vectors).div(vectors.length);
	}

	// --- Equals epsilon ---

	/** Return true if p is zero or zero eps (nearly zero). if clean & zero eps, reset p. */
	public boolean isZeroEps(boolean clean) {
		return isZeroEps(this, clean, epsilon);
	}
	
	/** return true if this is close to reference. if clean, set p1 to p2. */
	public boolean equalsEps(Vector reference, boolean clean) {
		return equalsEps(this, reference, clean, epsilon);
	}
	
	/** return true if this is close to reference after epsilon. if clean, set p1 to p2. */
	public boolean equalsEps(Vector reference, boolean clean, float epsilon) {
		return equalsEps(this, reference, clean, epsilon);
	}
	
	/** return true if close to zero. if clean, set p1 to p2. */
	private static boolean equalsEps(Vector v, Vector reference, boolean clean, float epsilon) {
		if (v == reference)
			return true;
		else if (v == null || reference == null)
			return false;
		else {
			final boolean isZero = isZeroEps(v.minus(reference), false, epsilon);
			if (clean && isZero)
				v.set(reference);
			return isZero;
		}
	}

	/** Return true if p is zero or zero eps (nearly zero). if clean & zero eps, reset p. */
	public static boolean isZeroEps(PVector v, boolean clean, float epsilon) {
		if (v.equals(zero))
			return true;
		else {
			if (isZeroEps(v.x, epsilon) && isZeroEps(v.y, epsilon) && isZeroEps(v.z, epsilon)) {
				if (clean)
					v.x = v.y = v.z = 0;
				return true;
			} else
				return false;
		}
	}

	/** Return true if f is nearly zero (after given epsilon). */
	private static boolean isZeroEps(float f, float epsilon) {
		return f == 0 || (f <= epsilon && f >= -epsilon);
	}
}
