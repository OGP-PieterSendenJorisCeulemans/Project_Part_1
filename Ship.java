package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a circular space ship dealing with
 * position, velocity, orientation and radius.
 * 
 * @invar  The position of each ship must be a valid position for any ship.
 *       | isValidPosition(getPosition())
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
	 * @return true if both coordinates are finite numbers.
	 * 			false if at least one coordinate is Nan or Double.POSITIVE_INFINTY or Double.NEGATIVE_INFINITY.
	 *       	| result == (! Double.isFinite(position[0])) || (! Double.isFinite(position[1]))
	*/
	public static boolean isValidPosition(double[] position) {
		return (! Double.isFinite(position[0])) || (! Double.isFinite(position[1]));
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
	private double[] position = new double[2];
}
