package asteroids.model;

import asteroids.Error.*;
import asteroids.model.Util.*;
import be.kuleuven.cs.som.annotate.*;

public class Ship {

	private Position pos;
	private Velocity vel;
	private double direction;
	private double radius;

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public Velocity getVel() {
		return vel;
	}

	public void setVel(Velocity vel) {
		this.vel = vel;
	}

	public double getDirection() {
		return direction;
	}
//TODO simpelste vorm
	public void setDirection(double direction) {
		this.direction = direction;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	/**
	 * Moves the ship during a fixed amount of time.
	 * @param elapsedTime
	 * 
	 * @post 
	 * TODO exceptions checken.
	 */
	//TODO afmaken
	public void Move(double elapsedTime) throws NegativeTimeException{
		try{if(!isValidElapsedTime(elapsedTime)){
			throw NegativeTimeException ;
		}
			Position displacement = new Position(vel.getVelX()*elapsedTime, vel.getVelY()*elapsedTime);
			pos.add(displacement);
		} catch (NegativeTimeException neg){
			
		}
	}
	private boolean isValidElapsedTime(double time){
		return !(time < 0);
	}
	
	public void turn(double angle){
		setDirection(getDirection() + angle);
	}
	// TODO implementeren: een add methode in velocity. + testen
	public void thrust(double direction, double amount){
		
	}
	//TODO implementeren: testen of schip1 en 2 gelijk zijn
	public double getDistanceBetween(Ship ship1, Ship ship2){
		return 0.0;
	}
	//TODO documenteren
	public boolean overlap(Ship ship1, Ship ship2){
		if(getDistanceBetween(ship1,ship2) <=0){
			return true;
		} else {
			return false;
		}
		
	}
}
