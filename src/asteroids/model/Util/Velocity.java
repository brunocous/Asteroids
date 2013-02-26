package asteroids.model.Util;


public class Velocity {
	private double velX;
	private double velY;
	
	private static final double SPEED_OF_LIGHT=300000000.0;
	
	public Velocity(double velX, double velY) {
	 this.velX=velX;
	 this.velY=velY;
	}
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
	
	public static double getSpeedOfLight(){
		return SPEED_OF_LIGHT;
	}
	
	public double getNorm(){
		double n = Math.sqrt(Math.pow(getVelX(), 2.0) + Math.pow(getVelY(), 2.0));
		return n;
	}
	
	public void add(Velocity velToAdd){
		setVelX(velToAdd.getVelX() + getVelX());
		setVelY(velToAdd.getVelY() + getVelY());
	}

}
