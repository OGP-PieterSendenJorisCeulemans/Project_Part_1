package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a circular space ship dealing with
 * position, velocity, orientation and radius.
 * 
 * @invar  The position of each ship must be a valid position for any ship.
 *       | isValidPosition(getPosition())
 * @invar  The orientation of each ship must be a valid orientation for any ship.
 *       | isValidOrientation(getOrientation())
 * @invar  Each ship can have its radius as radius.
 *       | canHaveAsRadius(this.getRadius())
 * @invar  The minimal radius of each ship must be a valid minimal radius for any ship.
 *       | isValidMinimalRadius(getMinimalRadius())
 * 
 * @author Joris & Pieter
 * @version 1.0
 *
 */
public class Ship {
	
	/**
	 * Initialize this new ship with given radius.
	 * 
	 * @param  radius
	 *         The radius for this new ship.
	 * @post   The radius of this new ship is equal to the given radius.
	 *       | new.getRadius() == radius
	 * @throws IllegalRadiusException
	 *         This new ship cannot have the given radius as its radius.
	 *       | ! canHaveAsRadius(this.getRadius())
	 */
	public Ship(double radius) throws IllegalRadiusException {
		if (! isValidRadius(radius))
			throw new IllegalRadiusException();
		this.radius = radius;
	}
	
	/**
	 * Initialize this new ship with given position.
	 *
	 * @param  position
	 *         The position for this new ship.
	 * @param  radius
	 * 		   The radius for this new ship
	 * @effect This new ship is initialized with the given radius as its radius
	 * 			| this(radius)
	 * @effect The position of this new ship is set to the given position.
	 *         	| this.setPosition(position)
	 * @throws IllegalRadiusException
	 * 			The given radius is not a valid radius.
	 * 			| !isValidRadius(radius)
	 * @throws IllegalPositionException
	 * 			The given position is not a valid position.
	 * 			| !isValidPosition(position)
	 */
	public Ship(double[] position, double radius) throws IllegalPositionException,IllegalRadiusException {
		this(radius);
		this.setPosition(position);
	}
	
	/**
	 * Initialize this new ship with given orientation.
	 * 
	 * @param  orientation
	 *         The orientation for this new ship.
	 * @pre    The given orientation must be a valid orientation for any ship.
	 *       	| isValidOrientation(orientation)
	 * @effect This new ship is initialized with the given radius as its radius
	 * 			| this(radius)
	 * @post   The orientation of this new ship is equal to the given orientation.
	 *       	| new.getOrientation() == orientation
	 * @throws IllegalRadiusException
	 * 			The given radius is not a valid radius.
	 * 			| !isValidRadius(radius)
	 */
	public Ship(double orientation, double radius) throws IllegalRadiusException {
		this(radius);
		this.setOrientation(orientation);
	}
	
	
	/**
	 * Return the position of this ship.
	 */
	@Basic @Raw
	public double[] getPosition() {
		return this.position.clone();
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any ship.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return true if position has length 2 and both coordinates are finite numbers.
	 * 			false if postition.length is not 2 or at least one coordinate is Nan or Double.POSITIVE_INFINTY or Double.NEGATIVE_INFINITY.
	 *       	| result == (position.length == 2) && Double.isFinite(position[0]) && Double.isFinite(position[1])
	*/
	public static boolean isValidPosition(double[] position) {
		return (position.length == 2) && Double.isFinite(position[0]) && Double.isFinite(position[1]);
	}
	
	/**
	 * Set the position of this ship to the given position.
	 * 
	 * @param  position
	 *         The new position for this ship.
	 * @post   The position of this new ship is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws IllegalPositionException
	 *         The given position is not a valid position for any ship.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	private void setPosition(double[] position) throws IllegalPositionException {
		if (! isValidPosition(position))
			throw new IllegalPositionException();
		this.position = position.clone();
	}
	
	/**
	 * Variable registering the position of this ship.
	 */
	private double[] position;
	
	
	/**
	 * Return the orientation of this ship.
	 */
	@Basic @Raw
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Check whether the given orientation is a valid orientation for any ship.
	 * @param  orientation
	 *         The orientation to check.
	 * @return true iff the value of orientation is contained in the interval [0, 2*Pi]
	 *       | result == (0 <= orientation) && (orientation <= 2*Math.PI)
	*/
	public static boolean isValidOrientation(double orientation) {
		return (0 <= orientation) && (orientation <= 2*Math.PI);
	}
	
	/**
	 * Set the orientation of this ship to the given orientation.
	 * 
	 * @param  orientation
	 *         The new orientation for this ship.
	 * @pre    The given orientation must be a valid orientation for any
	 *         ship.
	 *       | isValidOrientation(orientation)
	 * @post   The orientation of this ship is equal to the given
	 *         orientation.
	 *       | new.getOrientation() == orientation
	 */
	@Raw
	private void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	/**
	 * Variable registering the orientation of this ship.
	 */
	private double orientation;
	
	
	/**
	 * Return the radius of this ship.
	 */
	@Basic @Raw @Immutable
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Check whether this ship can have the given radius as its radius.
	 *  
	 * @param  radius
	 *         The radius to check.
	 * @return true iff the given radius is larger than the minimal radius for any ship.
	 *       | result == (radius >= getMinimalRadius())
	*/
	@Raw
	public static boolean isValidRadius(double radius) {
		return radius >= getMinimalRadius();
	}
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
	
	
	/**
	 * Return the minimal radius of any ship.
	 */
	@Basic @Raw
	public static double getMinimalRadius() {
		return minimalRadius;
	}
	
	/**
	 * Check whether the given minimal radius is a valid minimal radius for any ship.
	 *  
	 * @param  minimal radius
	 *         The minimal radius to check.
	 * @return true iff the given minimalRadius is positive
	 *       | result == minimalRadius > 0
	*/
	public static boolean isValidMinimalRadius(double minimalRadius) {
		return minimalRadius > 0;
	}
	
	/**
	 * Set the minimal radius of any ship to the given minimal radius.
	 * 
	 * @param  minimalRadius
	 *         The new minimal radius for a ship.
	 * @post   The minimal radius of any ship is equal to the given minimal radius.
	 *       | Ship.getMinimalRadius() == minimalRadius
	 * @throws IllegalMinimalRadiusException
	 *         The given minimal radius is not a valid minimal radius for any ship.
	 *       | ! isValidMinimalRadius(getMinimalRadius())
	 */
	@Raw
	public static void setMinimalRadius(double minimalRadius) throws IllegalMinimalRadiusException {
		if (! isValidMinimalRadius(minimalRadius))
			throw new IllegalMinimalRadiusException();
		Ship.minimalRadius = minimalRadius;
	}
	
	/**
	 * Variable registering the minimal radius of this ship.
	 */
	private static double minimalRadius = 10;
}
