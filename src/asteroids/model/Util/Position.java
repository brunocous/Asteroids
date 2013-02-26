package asteroids.model.Util;

import be.kuleuven.cs.som.annotate.*;

public class Position {
	
private double posX;
private double posY;

public Position(double posX, double posY) {
	this.posX = posX;
	this.posY = posY;
}
/**
 * Returns the x component of the position.
 * @return
 */
@Basic 
public double getPosX() {
	return posX;
}
/**
 * Set the value for the x component.
 * 
 * @param posX 
 * 			The value for the x component.
 */
@Basic
public void setPosX(double posX) {
	this.posX = posX;
}
/**
 * Returns the y component of the position.
 * @return the y component of the position.
 */
@Basic
public double getPosY() {
	return posY;
}
/**
 *Set the value for y component of the position. 
 *
 * @param posY
 * 			The new y component 
 */
@Basic
public void setPosY(double posY) {
	this.posY = posY;
}

public void add(Position posToAdd){
	setPosX(posToAdd.getPosX() + getPosX());
	setPosY(posToAdd.getPosY() + getPosY());
}

public double getDistanceTo(Position position){
	double distance = Math.sqrt(Math.pow(getPosX()-position.getPosX(),2)+Math.pow(getPosY()-position.getPosY(),2));
	return distance;
}

}
