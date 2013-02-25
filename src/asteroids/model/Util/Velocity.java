package asteroids.model.Util;

public class Velocity {
	private double velX;
	private double velY;
	
	
	public double getVelX() {
		return velX;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public double getVelY() {
		return velY;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public double getNorm(){
		double n = Math.sqrt(Math.pow(getVelX(), 2.0) + Math.pow(getVelY(), 2.0));
		return n;
	}

}
