package asteroids.model.Util;

public class Position {
	
private double posX;
private double posY;
/**
 * Returns the x component of the position.
 * @return
 */
@Basic 
public double getPosX() {
	return posX;
}

public void setPosX(double posX) {
	this.posX = posX;
}

public double getPosY() {
	return posY;
}

public void setPosY(double posY) {
	this.posY = posY;
}

}
