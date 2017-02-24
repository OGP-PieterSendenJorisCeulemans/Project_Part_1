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
 * 
 * @author Joris & Pieter
 * @version 1.0
 *
 */
public class Ship {
	
	/**
	 * Initialize this new ship with given position.
	 *
	 * @param  position
	 *         The position for this new ship.
	 * @effect The position of this new ship is set to
	 *         the given position.
	 *         	| this.setPosition(position)
	 * @throws IllegalPositionException
	 * 			The given position is not a valid position.
	 * 			| !isValidPosition(position)
	 */
	public Ship(double[] position) throws IllegalPositionException {
		if (Ship.isValidPosition(position))
			throw new IllegalPositionException();
		this.setPosition(position);
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
	 * Initialize this new ship with given orientation.
	 * 
	 * @param  orientation
	 *         The orientation for this new ship.
	 * @pre    The given orientation must be a valid orientation for any ship.
	 *       | isValidOrientation(orientation)
	 * @post   The orientation of this new ship is equal to the given orientation.
	 *       | new.getOrientation() == orientation
	 */
	public Ship(double orientation) {
		this.setOrientation(orientation);
	}
	
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
	 * @return 
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
	public void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	/**
	 * Variable registering the orientation of this ship.
	 */
	private double orientation;
	
	
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
//	public Ship(double radius) throws IllegalRadiusException {
//		if (! canHaveAsRadius(radius))
//			throw new IllegalRadiusException();
//		this.radius = radius;
//	}
	
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
	 * @return 
	 *       | result == 
	*/
	@Raw
	public boolean canHaveAsRadius(double radius) {
		return false;
	}
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
}
