package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a circular space ship dealing with
 * position, velocity, orientation and radius.
 * @invar  The position of each ship must be a valid position for any ship.
 *       | isValidPosition(getPosition())
 * @invar  The velocity of each ship must be a valid velocity for any
 *         ship.
 *       | isValidVelocity(getVelocity())
 * @invar  Each ship can have its speed limit as speed limit .
 *       | canHaveAsSpeedLimit(this.getSpeedLimit())
 * 
 * @author Joris & Pieter
 * @version 1.0
 *
 */
public class Ship {
	
	/**
	 * Initialize this new ship with given position and velocity.
	 *
	 * @param	position
	 *        	The position for this new ship.
	 * @param	velocity
	 * 			The velocity for this new ship
	 * @effect 	The position of this new ship is set to
	 *         	the given position.
	 *         	| this.setPosition(position)
	 * @effect	The velocity of this new ship is set to
	 * 			the given velocity
	 * 			| this.setVelocity(velocity)
	 * @post	The speed limit of this ship is set to the speed of light
	 * 			| this.getSpeedLimit() == SPEED_OF_LIGHT
	 * @throws IllegalPositionException
	 * 			The given position is not a valid position.
	 * 			| !isValidPosition(position)
	 */
	public Ship(double[] position, double[] velocity) throws IllegalPositionException {
		if (Ship.isValidPosition(position))
			throw new IllegalPositionException();
		this.setPosition(position);
		this.setVelocity(velocity);
		this.speedLimit = SPEED_OF_LIGHT;
	}
	
	/**
	 * Initialize this new ship with given position and zero velocity.
	 * 
	 * @param position
	 * 			The position for this new ship
	 * @effect  The new ship is initialized with its given position and zero velocity.
	 * 			|this(position, new double[] {0, 0})
	 * @throws 	IllegalPositionException
	 * 			The given position is not a valid position.
	 * 			| !isValidPosition(position)
	 */
	public Ship(double[] position) throws IllegalPositionException {
		this(position, new double[] {0, 0});
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
	 * 			false if postition.length is not 2 or at least one coordinate is NaN or Double.POSITIVE_INFINTY or Double.NEGATIVE_INFINITY.
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
	@Raw @Model
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
	 * Initialize this new ship with given velocity.
	 * 
	 * @param  velocity
	 *         The velocity for this new ship.
	 * @effect 
	 * 			| this.setVelocity(velocity)
	 */
		
	/**
	 * Return the velocity of this ship.
	 */
	@Basic @Raw
	public double[] getVelocity() {
		return this.velocity.clone();
	}
	
	/**
	 * Check whether the given velocity is a valid velocity for
	 * this ship.
	 *  
	 * @param  velocity
	 *         The velocity to check.
	 * @return True if and only if speed associated to the given velocity is less than or equal to the speed limit of this ship
	 *       | result == Math.hypot(velocity[0], velocity[1]) <= getSpeedLimit()
	*/
	public boolean isValidVelocity(double[] velocity) {
		return Math.hypot(velocity[0], velocity[1]) <= getSpeedLimit();
	}
	
	/**
	 * Set the velocity of this ship to the given velocity.
	 * 
	 * @param  velocity
	 *         The new velocity for this ship.
	 * @post   If the given velocity is a valid velocity for this ship,
	 *         the velocity of this new ship is equal to the given
	 *         velocity.
	 *       	| if (isValidVelocity(velocity))
	 *       	|   then new.getVelocity() == velocity
	 * @post	If the given velocity is not a valid velocity for this ship,
	 * 			the velocity is reduced such that the speed equals the speed limit of this ship,
	 * 			but the direction of the velocity is left unchanged.
	 * 			| if (!isValidVelocity(velocity))
	 * 			|	then new.getVelocity() == velocity * getSpeedLimit() / Math.hypot(velocity[0], velocity[1]);
	 */
	@Raw @Model
	private void setVelocity(double[] velocity) {
		if (isValidVelocity(velocity))
			this.velocity = velocity.clone();
		else{
			double speed = Math.hypot(velocity[0], velocity[1]);
			velocity[0] = velocity[0] * getSpeedLimit() / speed;
			velocity[1] = velocity[1] * getSpeedLimit() / speed;
			setVelocity(velocity);
		}
	}
	
	/**
	 * Variable registering the velocity of this ship.
	 */
	private double[] velocity;
		
	/**
	 * Initialize this new ship with given speed limit.
	 * 
	 * @param  speedLimit
	 *         The speed limit for this new ship.
	 * @post   If the given speed limit is a valid speed limit for any ship,
	 *         the speed limit of this new ship is equal to the given
	 *         speed limit. Otherwise, the speed limit of this new ship is equal
	 *         to SPEED_OF_LIGHT.
	 *       	| if (isValidSpeedLimit(speedLimit))
	 *       	|   then new.getSpeedLimit() == speedLimit
	 *       	|   else new.getSpeedLimit() == SPEED_OF_LIGHT
	 */
//	public Ship(double speedLimit) {
//		if (! canHaveAsSpeedLimit(speedLimit))
//			speedLimit = SPEED_OF_LIGHT;
//		this.speedLimit = speedLimit;
//	}
	
	/**
	 * Return the speed limit of this ship.
	 */
	@Basic @Raw @Immutable
	private double getSpeedLimit() {
		return this.speedLimit;
	}
	
	/**
	 * Check whether this ship can have the given speed limit as its speed limit.
	 *  
	 * @param  speedLimit
	 *         The speed limit to check.
	 * @return True if and only if the given speed limit is strictly positive and not greater than the speed of light
	 *       | result == (0 < speedLimit) && (speedLimit <= SPEED_OF_LIGHT)
	*/
//	@Raw
//	public boolean canHaveAsSpeedLimit(double speedLimit) {
//		return (0 < speedLimit) && (speedLimit <= SPEED_OF_LIGHT);
//	}
	
	
	/**
	 * Variable registering the speed limit of this ship.
	 */
	private final double speedLimit;
	
	/**
	 * Constant representing the speed of light (i.e. 300000 km/s)
	 */
	
	private static final double SPEED_OF_LIGHT = 300000;
	
		
}
