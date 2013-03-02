package asteroids.model;

import asteroids.Util;
import asteroids.Error.*;
import asteroids.model.Util.*;
import asteroids.IShip;
import java.lang.Exception;
import be.kuleuven.cs.som.annotate.*;

public class Ship implements IShip{

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
	
	public void move(double elapsedTime) throws NegativeTimeException, OverflowException{
		
		try{if(!isValidElapsedTime(elapsedTime)){
			
			throw new NegativeTimeException() ;
			
		}
			Position displacement = new Position(vel.getVelX()*elapsedTime, vel.getVelY()*elapsedTime);
			pos.add(displacement);
			
		} catch (NegativeTimeException neg){
			
		} catch (OverflowException exc){
			//TODO Hij moet dan de rand krijgen als positie. moeilijkheid: welke rand?
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
	
	public double scalarProduct(double x1, double y1, double x2, double y2){
		
		return x1*y1+x2*y2;
	}
	

	public double getTimeToCollision(Ship ship1, Ship ship2){
		
		double deltavx = ship2.getVel().getVelX()- ship1.getVel().getVelX();
		double deltavy = ship2.getVel().getVelY()- ship1.getVel().getVelY();
		double deltarx = ship2.getPos().getPosX()- ship1.getPos().getPosX();
		double deltary = ship2.getPos().getPosY()- ship1.getPos().getPosY();
		double sigma = ship1.getRadius()+ship2.getRadius();
		double d = Math.pow(scalarProduct(deltavx, deltavy, deltarx, deltary), 2)-scalarProduct(deltavx,deltavy,deltavx,deltavy)*(scalarProduct(deltarx,deltary,deltarx,deltary)-Math.pow(sigma, 2));
		
		if(Util.fuzzyLessThanOrEqualTo(-scalarProduct(deltavx,deltavy,deltarx,deltary),0) || Util.fuzzyLessThanOrEqualTo(d,0) ){
			return Double.POSITIVE_INFINITY;
		}
		else{
			return -(scalarProduct(deltavx, deltavy, deltarx,deltary)+Math.sqrt(d))/scalarProduct(deltavx,deltavy,deltavx,deltavy);
		}
	}
	
	public Position getCollisionPosition(Ship ship1, Ship ship2){
		
		double deltaT= getTimeToCollision(ship1,ship2);
		
		if(deltaT==Double.POSITIVE_INFINITY){
			
			return null;
			
		}
		else{
			
			double xCoordCollision = ship1.getPos().getPosX()+deltaT*ship1.getVel().getVelX();
			double yCoordCollision = ship1.getPos().getPosY()+deltaT*ship1.getVel().getVelY();
			
			Position collisionPoint = new Position(xCoordCollision, yCoordCollision);
			return collisionPoint;
			
		}
		}
		
		
	}

