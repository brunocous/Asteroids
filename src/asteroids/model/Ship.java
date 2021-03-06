package asteroids.model;

import asteroids.Asteroids;
import asteroids.IShip;
import asteroids.Util;
import asteroids.Error.*;
import asteroids.model.Util.*;
import be.kuleuven.cs.som.annotate.*;

public class Ship implements IShip {

	// the position vector of a ship.
	private Position pos;
	// the velocity vector of a ship.
	private Velocity vel;
	// the direction a ship is orientated in.
	private double direction;
	// the radius of a ship.
	private double radius;

	/**
	 * Initialize this new ship with given pos, vel, direction and radius.
	 * 
	 * @pre   the given direction must be a valid direction.
	 * 		  | isValidDirection(direction)
	 * @param pos
	 *        the position for this new ship.
	 * @param vel
	 *        the velocity for this new ship.
	 * @param direction
	 *        the direction for this new ship.
	 * @param radius
	 *        the radius for this new ship
	 * @post the new pos for this new ship is equal to the given pos
	 *       | new.getPos()== pos 
	 * @post the new vel for this new ship is equal to the given vel
	 *       | new.getVel()== vel
	 * @post the new direction for this new ship is equal to the given direction
	 *       | new.getDirection()== direction
	 * @post the new radius for this new ship is equal to the given radius
	 *       | new.getRadius()== radius 
	 */
	
	public Ship(Position pos,Velocity vel, double direction, double radius){

		this.setPos(pos);
		this.setVel(vel);
		this.setDirection(direction);
		this.setRadius(radius);
	}
	
	/**
	 * Initialize this new ship as a default ship. 
	 * 
	 * @post the new pos for this new ship is a position object with x-component 0 and 
	 *       y-component 0.
	 *       | new.getPos()== new Position(0,0)
	 * @post the new vel for this new ship is a velocity object with x-component 0 and
	 *       y-component 0.
	 *       | new.getVel()== new Velocity(0,0)
	 * @post the new direction for this new ship is 0.
	 *       | new.getDirection()== 0
	 * @post the new radius for this new ship is 15.
	 *       | new.getRadius()== 15  
	 */
	public Ship(){
		
		Position pos = new Position(0,0);
		this.setPos(pos);
		Velocity vel = new Velocity(0,0);
		this.setVel(vel);
		this.direction =0;
		this.radius=15;
		
	}
	
	
	/**
	 * Return the position for this bank account.
	 */
	//TODO clone doorsturen
	@Basic
	public Position getPos() {
		return pos;
	}

	/**
	 * Set the pos for this ship to the given pos. 
	 * 
	 * @param pos
	 *        the new pos for this ship.
	 * @post The new pos for this ship is equal to the given pos.
	 *       |new.getPos()= pos
	 */
	
	@Basic
	public void setPos(Position pos) {
	
	    this.pos = pos;
	
	}
	
	
	/**
	 * Return the velocity of this ship.
	 */
	@Basic
	public Velocity getVel() {
		
		return vel;
		
	}

	/**
	 * Set the vel of this ship to the given vel.
	 * 
	 * @param vel
	 *        the new velocity for this ship.
	 * @post if the given vel is a valid velocity, in other words if its norm is less than or 
	 *       equal to the speed of light, the new vel for this ship is equal to the given
	 *       vel. Else the new vel of this ship has the direction of the given vel but a norm
	 *       equal to the speed of light.
	 *       |if (isValidVelocity(vel))
	 *       |then new.getVel()==vel
	 *       |else new.getVel() == correctSpeed(vel)
	 */
	@Basic
	public void setVel(Velocity vel) {
		if (isValidVelocity(vel)){
			
			this.vel = vel;
			
		}
		else{
			
			Velocity result = correctSpeed(vel);
			this.vel = result;
			
		}
	}

	/** 
	 * Return the direction of this ship.
	 */
	@Basic
	public double getDirection() {
		
		return direction;
		
	}

	/**
	 * Set the direction of this ship to the given direction.
	 * 
	 * @pre	 the value of the given direction must be greater than -Pi and less than or equal to Pi.
	 * 		 |	isValidDirection(direction)
	 * @param direction
	 *        the new direction for this ship in radians.
	 * @post the new direction for this ship is the given direction
	 *       |	new.getDirection() = direction
	 */
	@Raw
	@Basic
	public void setDirection(double direction) {
		
		assert(isValidDirection(direction));
		this.direction = direction;
		
	}
	
	/** 
	 * Check whether the given direction is a valid direction.
	 * 
	 * @param direction
	 *        the direction to be checked in radians.
	 * @return true if and only if the given direction is greater than -Pi and less than or equal to
	 * 		   Pi.
	 *         | result == (direction > -Math.PI) && (direction <= Math.PI)
	 *        
	 */
	private boolean isValidDirection(double direction){
		
		return (direction > -Math.PI) && (direction <= Math.PI);
		
	}

	/**
	 * Return the radius of this ship.
	 */
	@Basic
	public double getRadius() {
		
		return radius;
		
	}

	/**
	 * Set the radius of this ship to the given radius.
	 * 
	 * @param radius
	 *        the new radius for this ship in km.
	 * @post The new radius for this ship is equal to the given radius if the given radius is
	 *       larger than 10. Else the new radius for this ship is 15.
	 *       |if(isValidRadius(radius))
	 *       |then new.getRadius()== radius
	 *       |else new.getRadius()== 10    
	 * @throws illegalRadiusException
	 *         the given radius is not a legal radius for this ship.
	 *         |!isValidRadius(radius)
	 * 
	 */
	@Basic
	public void setRadius(double radius) {
		
		try{ if(!isValidRadius(radius)){
			
			throw new IllegalRadiusException();
			
		}
		
		this.radius = radius;
		}
		
		catch(IllegalRadiusException exc){
			
			this.setRadius(15);
			
		}
	}
	
	/**
	 * Check whether the given radius is a valid radius. In other words, check whether it is
	 * higher than 0 and less than or equal to 10.
	 *
	 * @param radius
	 *        the radius to be checked in km.
	 * @return true if and only if the given radius is higher than 0 and less than or equal to 10.
	 *         |result == (!Util.fuzzyLessThanOrEqualTo(radius, 0)) && Util.fuzzyLessThanOrEqualTo(radius, 10))
	 */
	private boolean isValidRadius(double radius){
		
		return (!Util.fuzzyLessThanOrEqualTo(radius, 10));
	
	}
	/**
	 * Moves the ship during a fixed amount of time.
	 * 
	 * @param elapsedTime
	 * 		  amount of time during which the ship is moving in seconds.
	 * @post The position of the ship has been changed according to the previous position,
	 * 		   the current velocity of the ship and the given duration elapsedTime. 
	 * 		   |(new this).getPos() == this.getPos().add(new Position(vel.getVelX()*elapsedTime, vel.getVelY()*elapsedTime));
	 * @throws NegativeTimeException
	 *         The given elapsedTime is negative and therefore unvalid.
	 *         |!isValidElapsedTime()
	 */
	
	public void move(double elapsedTime) throws NegativeTimeException{
		
		try{if(!isValidElapsedTime(elapsedTime)){
			
			throw new NegativeTimeException() ;
			
		}
			Position displacement = new Position(vel.getVelX()*elapsedTime, vel.getVelY()*elapsedTime);
			pos.add(displacement);
			
		} catch (NegativeTimeException neg){
			
		}
	}
	
	/** 
	 * Check whether the given time is a valid amount of time. 
	 * 
	 * @param time
	 *        the amount of time to be checked in seconds.
	 * @return true if and only if the given time is greater than or equal to zero.
	 *         | result == !(time<0)
	 *        
	 */
	private boolean isValidElapsedTime(double time){
		return !(time < 0);
	}
	
	/**
	 * Check whether the given velocity is a valid velocity. In other words, check whether its 
	 * norm is less than or equal to the speed of light.
	 * 
	 * @param velocity
	 *        The velocity to be checked.
	 * @return true if and only if the given velocity's norm is less than or equal to the speed
	 *         of light.
	 *         |result == (velocity.getNorm()<=Velocity.getSpeedOfLight())
	 */
	private boolean isValidVelocity(Velocity velocity){
		return (velocity.getNorm()<=Velocity.getSpeedOfLight());
	}
	
	/**
	 * Turns the ship over a fixed angle. 
	 * 
	 * @param angle
	 * 		  The angle (in radians) to be added to the current direction of the ship
	 * @effect the new direction of the ship is the previous direction plus
	 *       the given angle.
	 *       |setDirection(this.getDirection()+ angle)
	 * @note this method has a precondition, namely
	 * 			isValidDirection(this.getDirection() + direction)
	 *       the resulting direction must be a valid direction. Other-
	 * 		 wise it will not be excepted.
	 */
	
	public void turn(double angle){
		
		if (isValidDirection(getDirection() ))
		setDirection(getDirection() + angle);
	}

	/**
	 * Changes the ship's velocity by a given amount, does not change the ship's direction. 
	 * 
	 * @param amount
	 * 		  The amount by which the velocity of the ship will be increased
	 * @post If increasing the ship's velocity by the given amount does not result into a 
	 *       velocity that is higher than the speed of light, the ship's new velocity will be 
	 *       increased by the given amount. If it does exceed the speed of light, the ship's new
	 *       velocity will be the speed of light.   
	 *       |if(isValidVelocity((new Velocity(getVel().getVelX(), getVel().getVelY())).add(new Velocity(amount*Math.cos(getDirection()),amount*Math.sin(getDirection()))))
	 *       |     then (new this).getVel()== this.getVel().add(new Velocity(amount*Math.cos(getDirection()),amount*Math.sin(getDirection())))
	 *       |else ((new this).getVel()= new Velocity (speed.getVelX()/(speed.getNorm()/Velocity.getSpeedOfLight()),speed.getVelY()/(speed.getNorm()/Velocity.getSpeedOfLight()))
	 * @post The ship's direction will not be changed. 
	 *       |(new this).getDirection() == this.getDirection()
	 */
	public void thrust(double amount) throws ExceedsSpeedOfLightException{
		
		Velocity gainedSpeed = new Velocity(amount*Math.cos(getDirection()),amount*Math.sin(getDirection()));
		Velocity newSpeed = new Velocity(getVel().getVelX(), getVel().getVelY());
		newSpeed.add(gainedSpeed);
		
		if(!isValidVelocity(newSpeed)){
			
			Velocity result = correctSpeed(newSpeed);
			setVel(result);
		}
		else{
		
		vel.add(gainedSpeed);
		
		}

	}
	
	/**
	 * Correct a given velocity vector speed (which has a norm that is bigger than the speed
	 * of light) to a velocity vector with the same direction and a norm equal to the speed 
	 * of light.
	 * 
	 * @param speed
	 *        The velocity vector that is to be corrected.
	 * @return the corrected velocity vector, which has the same direction as the given speed
	 *         and has a norm equal to the speed of light.
	 *         | result == new Velocity (speed.getVelX()/(speed.getNorm()/Velocity.getSpeedOfLight()),speed.getVelY()/(speed.getNorm()/Velocity.getSpeedOfLight()))
	 *         
	 */
	
	//TODO Mag dit private??
	private Velocity correctSpeed(Velocity speed){
		
		double correctingFactor = speed.getNorm()/Velocity.getSpeedOfLight();
		Velocity correctedSpeed = new Velocity (speed.getVelX()/correctingFactor,speed.getVelY()/correctingFactor);
		return correctedSpeed;
	}
	
	
	/**
	 * Returns the distance between two given ships.  
	 * 
	 * @param ship1
	 * 	      The first ship of which the position will be compared to the given Ship ship2
	 * @param ship2
	 *        The second ship of which the position will be compared to the given Ship ship1
	 * @throws SameShipException
	 * 		   the given ships are the same ship.
	 *         | ship1 == ship2
	 * @return The distance between the outer side of ship1 and ship2 if ship1 and ship2 are different ships, 
	 *         0.0 if ship1 and ship2 are the same ship. 
	 *         The result will be negative if ship1 and ship2 overlap.
	 *         |if(ship1==ship2)
	 *         |  then result == 0.0
	 *         |else result == ship1.getPos().getDistanceTo(ship2.getPos())-(ship1.getRadius()+ship2.getRadius())
	 */
	public static double getDistanceBetween(Ship ship1, Ship ship2){
		
		double result;
		try {if(ship1==ship2){
			throw new SameShipException();
		}
		
		double distanceBetweenCentres = ship1.getPos().getDistanceTo(ship2.getPos());
		double sumOfRadii= ship1.getRadius()+ship2.getRadius();
		double distance = distanceBetweenCentres - sumOfRadii;
		result = distance;
		}
		
		catch(SameShipException exc){
			
			result = 0;
			
		}
		
		return result;
		
	}
	/**
	 * Check if 2 ships overlap.
	 * 
	 * @param ship1
	 * 	      The first ship of which the position will be compared to the position of ship2
	 * @param ship2
	 * 	      The second ship of which the position will be compared to the position of ship1
	 * @throws SameShipException
	 * 		   the given ships are the same ship.
	 * 		   | ship1 == ship2
	 * @return true if and only if ship1 and ship2 are the same ship or the distance between
	 *         ship1 and ship2 is less than zero. 
	 *         | if( ship1==ship2 || (asteroids.Util.fuzzyLessThanOrEqualTo(getDistanceBetween(ship1,ship2),0) && !asteroids.Util.fuzzyEquals(getDistanceBetween(ship1,ship2), 0)))
	 *         | then result == true
	 *         | else result == false
	 */
	public static boolean overlap(Ship ship1, Ship ship2){
		
		boolean result =false;
		try{ if(ship1==ship2){
			
			throw new SameShipException();
			
		} 
				if(asteroids.Util.fuzzyLessThanOrEqualTo(getDistanceBetween(ship1,ship2),0) && !asteroids.Util.fuzzyEquals(getDistanceBetween(ship1,ship2), 0)){
					
					result= true;
					
				} 
		}
		
		catch(SameShipException exc){
			
			result = true;
			
		}
		  
		return result;
		
	}
	
	/**
	 * Calculates the scalar product of two 2-dimensional vectors.
	 * 
	 * @param x1
	 *        x-co�rdinate of the first vector.
	 * @param y1
	 *        y-co�rdinate of the first vector
	 * @param x2
	 *        x-co�rdinate of the second vector.
	 * @param y2
	 *        y-co�rdinate of the second vector.
	 * @return the scalar product of vector1(x1,y1) and vector2(x2,y2)
	 *         |result==x1*y1+x2*y2
	 */
	
	private static double scalarProduct(double x1, double y1, double x2, double y2){
		
		return x1*y1+x2*y2;
	}
	
	
	/**
	 * Finds out whether and in how many seconds 2 ships will collide. 
	 * 
	 * @param ship1
	 * 		  The first ship that will or will not collide with ship2 after an amount of time.
	 * @param ship2
	 * 		  The second ship that will or will not collide with ship1 after an amount of time.
	 * @throws SameShipException
	 * 		   the given ships are the same ship
	 * 		   | ship1==ship2
	 * @return The amount of time (in seconds) it will take for ship1 and ship2 to collide or 
	 * 	       POSITIVE_INFINITY if they will never collide (given their current position and 
	 *         velocity). 
	 * 
	 */
	                      
	
	public static double getTimeToCollision(Ship ship1, Ship ship2){
		
		double result;
		
		try{ if(ship1 == ship2) {
			
			throw new SameShipException();
			
		}
		
		double deltavx = ship2.getVel().getVelX()- ship1.getVel().getVelX();
		double deltavy = ship2.getVel().getVelY()- ship1.getVel().getVelY();
		double deltarx = ship2.getPos().getPosX()- ship1.getPos().getPosX();
		double deltary = ship2.getPos().getPosY()- ship1.getPos().getPosY();
		double sigma = ship1.getRadius()+ship2.getRadius();
		double d = Math.pow(scalarProduct(deltavx, deltavy, deltarx, deltary), 2)-scalarProduct(deltavx,deltavy,deltavx,deltavy)*(scalarProduct(deltarx,deltary,deltarx,deltary)-Math.pow(sigma, 2));
		
		if(Util.fuzzyLessThanOrEqualTo(-scalarProduct(deltavx,deltavy,deltarx,deltary),0) || Util.fuzzyLessThanOrEqualTo(d,0) ){
			result = Double.POSITIVE_INFINITY;
		}
		else{
			result = -(scalarProduct(deltavx, deltavy, deltarx,deltary)+Math.sqrt(d))/scalarProduct(deltavx,deltavy,deltavx,deltavy);
			
		}
		}
		
		catch(SameShipException exc){
			
			result = Double.POSITIVE_INFINITY;
			
		}
		
		return result;
	}
	
	/**
	 * Calculates the position where 2 ships will collide, if they will collide within a 
	 * finite amount of time.
	 * 
	 * @param ship1
	 *        The first ship of which we want to know at which position it will collide with
	 *        ship2.
	 * @param ship2
	 *        The second ship of which we want to know at which position it will collide with
	 *        ship1.
	 * @throws SameShipException
	 * 		   the given ships are the same ship.
	 * 		   | ship1==ship2
	 * @return The position where ship1 and ship2 will collide if they will collide eventually 
	 *         or 'null' if ship1 and ship2 will never collide. 
	 *         | if(deltaT==Double.POSITIVE_INFINITY)
	 *	       | then result== null
	 *	       | else result== new Position(ship1.getPos().getPosX()+getTimeToCollision(ship1,ship2)*ship1.getVel().getVelX(),ship1.getPos().getPosY()+getTimeToCollision(ship1,ship2)*ship1.getVel().getVelY())
	 */
	
	public static Position getCollisionPosition(Ship ship1, Ship ship2){
		
		Position result = null;
		
		try{ if(ship1 == ship2){
			throw new SameShipException();
		}
		
		double deltaT= getTimeToCollision(ship1,ship2);
		
		if(deltaT==Double.POSITIVE_INFINITY){
			
			result= null;
			
		}
		else{
			
			double xCoordCollision = ship1.getPos().getPosX()+deltaT*ship1.getVel().getVelX();
			double yCoordCollision = ship1.getPos().getPosY()+deltaT*ship1.getVel().getVelY();
			
			Position collisionPoint = new Position(xCoordCollision, yCoordCollision);
			result= collisionPoint;
			
		}
		}
		catch(SameShipException exc){
			
			result = null;
			
		}
	
		return result;
		}
		
		
	}
