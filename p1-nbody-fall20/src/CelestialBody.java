

/**
 * Celestial Body class for NBody
 * @author ola
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
						 double yv, double mass, String filename){
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
	}

	/**
	 * get X
	 * @return value of X position
	 */
	public double getX() {
		return myXPos;
	}

	/**
	 * get Y
	 * @return value of Y position.
	 */
	public double getY() {
		return myYPos;
	}

	/**
	 * get X Velocity
	 * @return value of x-velocity.
	 */

	public double getXVel() {
		return myXVel;
	}
	/**
	 * get y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		return myYVel;
	}

	/**
	 * get mass value of this body
	 * @return the mass value
	 */
	public double getMass() {
		return myMass;
	}

	/**
	 * get name of this body
	 * @return the file name of this body
	 */
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double rsq = Math.pow((myXPos-b.myXPos),2)+Math.pow((myYPos-b.myYPos),2);
		return Math.sqrt(rsq);
	}

	/**
	 * calculates the force exerted by this body on another
	 * @param b the other body to which force is exerted on
	 * @return force exerted by this body onto another
	 */
	public double calcForceExertedBy(CelestialBody b) {
		double force = ((6.67e-11)*myMass*b.myMass)/(Math.pow(calcDistance(b),2));
		return force;
	}

	/**
	 * calculates the force exerted by this body to another in the x direction
	 * @param b the other body to which force is exerted on
	 * @return force exerted by this body onto another in x direction
	 */
	public double calcForceExertedByX(CelestialBody b) { ;
		double force_x = calcForceExertedBy(b)*(b.myXPos-myXPos)/calcDistance(b);
		return force_x;
	}

	/**
	 * calculates the force exerted by this body to another in the y direction
	 * @param b the other body to which force is exerted on
	 * @return force exerted by this body onto another in y direction
	 */
	public double calcForceExertedByY(CelestialBody b) {
		double force_y = calcForceExertedBy(b)*(b.myYPos-myYPos)/calcDistance(b);
		return force_y;
	}

	/**
	 * the net force exerted by the bodies in the x direction
	 * @param bodies the bodies exerting net force on each other in the x direction
	 * @return sum of the forces in the x direction exerted by each body
	 */
	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b : bodies) {
			if(! b.equals(this)) {
				sum += calcForceExertedByX(b);
			}
		}
		return sum;
	}

	/**
	 * the net force exerted by the bodies in the y direction
	 * @param bodies the bodies exerting net force on each other in the y direction
	 * @return sum of the forces in the y direction exerted by each body
	 */
	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b : bodies) {
			if(! b.equals(this)) {
				sum += calcForceExertedByY(b);
			}
		}
		return sum;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
