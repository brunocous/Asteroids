package asteroids.model;

import asteroids.Asteroids;
import asteroids.Error.*;
import asteroids.model.Util.*;
import be.kuleuven.cs.som.annotate.*;

public class Ship {

	private Position pos;
	private Velocity vel;
	private double direction;
	private double radius;

	public Ship(Position pos,Velocity vel, double direction, double radius){
		this.pos=pos;
		this.vel=vel;
		this.direction=direction;
		this.radius=radius;
	}
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
		this.direction = direction%(2*Math.PI);
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
			throw new NegativeTimeException() ;
		}
			Position displacement = new Position(vel.getVelX()*elapsedTime, vel.getVelY()*elapsedTime);
			pos.add(displacement);
		} catch (NegativeTimeException neg){
			
		}
	}
	private boolean isValidElapsedTime(double time){
		return !(time < 0);
	}
	
	private boolean isValidVelocity(Velocity velocity){
		return (velocity.getNorm()<=Velocity.getSpeedOfLight());
	}
	
	public void turn(double angle){
		setDirection(getDirection() + angle);
	}
	// TODO implementeren: een add methode in velocity. + testen
	
	public void thrust(double direction, double amount) throws ExceedsSpeedOfLightException{
		
		Velocity gainedSpeed = new Velocity(amount*Math.cos(direction),amount*Math.sin(direction));
		Velocity newSpeed = new Velocity(getVel().getVelX(), getVel().getVelY());
		newSpeed.add(gainedSpeed);
		
		try{if(!isValidVelocity(newSpeed)){
			
			throw new ExceedsSpeedOfLightException();
		}
		
		vel.add(gainedSpeed);
		}
		catch(ExceedsSpeedOfLightException tohigh){
			
			double correctingFactor = newSpeed.getNorm()/Velocity.getSpeedOfLight();
			Velocity correctedSpeed = new Velocity (newSpeed.getVelX()/correctingFactor,newSpeed.getVelY()/correctingFactor);
			setVel(correctedSpeed);
		}
		
	}
	//TODO implementeren: testen of schip1 en 2 gelijk zijn
	public double getDistanceBetween(Ship ship1, Ship ship2){
		
		if(ship1==ship2){
			return 0.0;
		}
		
		else{
			
		double distanceBetweenCentres = ship1.getPos().getDistanceTo(ship2.getPos());
		double sumOfRadii= ship1.getRadius()+ship2.getRadius();
		double distance = distanceBetweenCentres - sumOfRadii;
		return distance;
		
		}
		
	}
	//TODO documenteren
	public boolean overlap(Ship ship1, Ship ship2){
		
		if(ship1==ship2){
			
			return true;
			
		} else if(asteroids.Util.fuzzyLessThanOrEqualTo(getDistanceBetween(ship1,ship2),0)){
			
			return true;
			
		} else {
			
			return false;
		}
		
	}
	
	public boolean collide(Ship ship1, Ship ship2){
		
		return true;
	}
}
