package asteroids.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.Util;
import asteroids.model.Ship;
import asteroids.model.Util.Position;
import asteroids.model.Util.Velocity;
import asteroids.Error.*;

public class ShipTest {

	private Ship speedOfLightShip, negativeVelocityShip, negativeSpeedOfLightVelocityShip, zeroVelocityShip, positiveVelocityShip, infiniteNegativePositionShip, zeroRadiusShip, negativeRadiusShip,
				positiveRadiusShip, infiniteRadiusShip, negativePositionShip, zeroPositionShip, positivePositionShip,
				infinitePositionShip, infiniteNegativeDirectionShip, largeNegativeDirectionShip, smallNegativeDirectionShip,
				zeroDirectionShip, smallPositiveDirectionShip, largePositiveDirectionShip, infinitePositiveDirectionShip;
	
	private Position zeroPosition, negativePosition, positivePosition, infinitePosition, infiniteNegativePosition;
	private Velocity speedOfLightVelocity, negativeVelocity, negativeSpeedOfLightVelocity, zeroVelocity, positiveVelocity, positiveInfiniteVelocity;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		zeroPosition = new Position(0.0,0.0);
		negativePosition = new Position(-50.0,-50.0);
		positivePosition = new Position(50.0,50.0);
		infinitePosition = new Position(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
		infiniteNegativePosition = new Position(Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY);
		
		speedOfLightVelocity = new Velocity(Velocity.getSpeedOfLight(),Velocity.getSpeedOfLight());
		negativeVelocity = new Velocity(-50.0,-50.0);
		negativeSpeedOfLightVelocity = new Velocity(-Velocity.getSpeedOfLight(),-Velocity.getSpeedOfLight());
		zeroVelocity = new Velocity(0.0,0.0);
		positiveVelocity = new Velocity(50.0,50.0);
		positiveInfiniteVelocity = new Velocity(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		
		speedOfLightShip = new Ship(positivePosition, speedOfLightVelocity, 0.0 , 1.0);
		negativeVelocityShip = new Ship(positivePosition, negativeVelocity, 0.0,1.0);
		negativeSpeedOfLightVelocityShip = new Ship(positivePosition, negativeSpeedOfLightVelocity, 0.0, 1.0);
		zeroVelocityShip = new Ship(positivePosition, zeroVelocity, 0.0,1.0);
		positiveVelocityShip = new Ship(positivePosition, positiveVelocity, 0.0,1.0);
		
		zeroRadiusShip = new Ship(positivePosition, positiveVelocity, 0.0, 0.0);
		negativeRadiusShip = new Ship(positivePosition, positiveVelocity, 0.0, -10.0);
		positiveRadiusShip = new Ship(positivePosition, positiveVelocity, 0.0, 10.0);
		infiniteRadiusShip = new Ship(positivePosition, positiveVelocity, 0.0, Double.POSITIVE_INFINITY);
		
		negativePositionShip = new Ship(negativePosition, positiveVelocity, 0.0,1.0);
		zeroPositionShip = new Ship(zeroPosition, positiveVelocity, 0.0, 1.0);
		positivePositionShip = new Ship(positivePosition, positiveVelocity, 0.0, 1.0);
		infinitePositionShip = new Ship(infinitePosition, positiveVelocity, 0.0, 1.0);
		infiniteNegativePositionShip = new Ship(infiniteNegativePosition, positiveVelocity, 0.0, 1.0);
		
		infiniteNegativeDirectionShip = new Ship(positivePosition, positiveVelocity, Double.NEGATIVE_INFINITY, 1.0);
		largeNegativeDirectionShip = new Ship(positivePosition, positiveVelocity, -2000.0, 1.0);
		smallNegativeDirectionShip = new Ship(positivePosition, positiveVelocity, -1.0, 1.0);
		zeroDirectionShip = new Ship(positivePosition, positiveVelocity, 0.0, 1.0);
		smallPositiveDirectionShip = new Ship(positivePosition, positiveVelocity, 1.0, 1.0);
		largePositiveDirectionShip = new Ship(positivePosition, positiveVelocity, 2000.0, 1.0);
		infinitePositiveDirectionShip = new Ship(positivePosition, positiveVelocity, Double.POSITIVE_INFINITY, 1.0);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void extendedConstructor_LegalCase() throws Exception{
		Ship theShip = new Ship(positivePosition, positiveVelocity, Math.PI, 2.0);
		assertTrue(Util.fuzzyEquals(positivePosition.getPosX(), theShip.getPos().getPosX()) );
		assertTrue(Util.fuzzyEquals(positivePosition.getPosY(), theShip.getPos().getPosY()));
		assertTrue(Util.fuzzyEquals(positiveVelocity.getVelX(), theShip.getVel().getVelX()));
		assertTrue(Util.fuzzyEquals(positiveVelocity.getVelY(), theShip.getVel().getVelY()));
		assertTrue(Util.fuzzyEquals(Math.PI, theShip.getDirection()));
		assertTrue(Util.fuzzyEquals(2.0, theShip.getRadius()));
	}
	//TODO bespreken hoe dit moet afgehandeld worden. Via exceedspeedoflightexception, of toelaten of moet gwn lichtsnelheid worden.
	@Test
	public void extendendConstructor_infiniteSpeedCase() throws Exception{
		Ship theShip = new Ship(positivePosition, positiveInfiniteVelocity, Math.PI, 2.0);
		assertTrue(Util.fuzzyEquals(theShip.getVel().getVelX(), positiveInfiniteVelocity.getVelX()));
	}
	
	@Test
	public void extendenConstructor_infinitePositionCase() throws Exception{
		Ship theShip = new Ship(infinitePosition, positiveVelocity, Math.PI, 2.0);
		assertTrue(Util.fuzzyEquals(theShip.getPos().getPosX(), infinitePosition.getPosX()));
	}
	
	@Test 
	public void move_infiniteBorderCase() throws Exception{
		infinitePositionShip.move(0.0);
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosX(), infinitePosition.getPosX()));
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosY(), infinitePosition.getPosY()));
	}
	
	@Test
	public void move_overInfiniteBorderCase() throws Exception{
		infinitePositionShip.move(10.0);
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosX(), infinitePosition.getPosX()));
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosY(), infinitePosition.getPosY()));
	}
	@Test
	public void move_underInfiniteBorderCase() throws Exception{
		infinitePositionShip.setDirection(4*Math.PI/3);
		infinitePositionShip.move(10.0);
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosX(), infinitePosition.getPosX() + 10.0*infinitePositionShip.getVel().getVelX()));
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosY(), infinitePosition.getPosY() + 10.0*infinitePositionShip.getVel().getVelY()));
	}
	@Test
	public void move_toZeroPosition() throws Exception{
		negativePositionShip.move(1.0);
		assertTrue(Util.fuzzyEquals(negativePositionShip.getPos().getPosX(), zeroPosition.getPosX()));
		assertTrue(Util.fuzzyEquals(negativePositionShip.getPos().getPosY(), zeroPosition.getPosY()));
	}
	@Test
	public void move_noChange() throws Exception{
		positivePositionShip.move(0.0);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getPos().getPosX(), positivePosition.getPosX()));
		assertTrue(Util.fuzzyEquals(positivePositionShip.getPos().getPosY(), positivePosition.getPosY()));
	}
	@Test (expected = NegativeTimeException.class)
	public void move_negativeTime() throws Exception{
		positivePositionShip.move(-10);
		fail("Exception Expected!");
	}
	
	@Test
	public void turn_zeroAngle(){
		positivePositionShip.turn(0.0);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), 0.0));
	}
	@Test
	//TODO check schrijfwijze "than"
	public void turn_lessThan2PiCase(){
		positivePositionShip.turn(Math.PI);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Math.PI));
	}
	@Test 
	public void turn_greaterThan2PiCase(){
		positivePositionShip.turn(9*Math.PI);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Math.PI));
	}
	@Test
	public void turn_infiniteCase(){
		positivePositionShip.turn(Double.POSITIVE_INFINITY);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Double.POSITIVE_INFINITY%(Math.PI*2)));
	}
	@Test
	public void turn_negativeLessThan2PiCase(){
		positivePositionShip.turn(-Math.PI);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), -Math.PI));
	}
	@Test 
	public void turn_negativeGreaterThan2PiCase(){
		positivePositionShip.turn(-9*Math.PI);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), -Math.PI));
	}
	@Test
	public void turn_negativeInfiniteCase(){
		positivePositionShip.turn(Double.NEGATIVE_INFINITY);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Double.NEGATIVE_INFINITY%(Math.PI*2)));
	}
	@Test
	public void thrust_sameDirectionPositiveAmount() throws Exception{
		positivePositionShip.thrust(positivePositionShip.getDirection(), 10.0);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelX(), positiveVelocity.getVelX() + 10.0*Math.cos(positivePositionShip.getDirection())));
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelY(), positiveVelocity.getVelY() + 10.0*Math.sin(positivePositionShip.getDirection())));	
	}
	@Test
	public void thrust_sameDirectionNegativeAmount() throws Exception{
		positivePositionShip.thrust(positivePositionShip.getDirection(), -10.0);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelX(), positiveVelocity.getVelX() + (-10.0)*Math.cos(positivePositionShip.getDirection())));
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelY(), positiveVelocity.getVelY() + (-10.0)*Math.sin(positivePositionShip.getDirection())));	
	}
	@Test
	public void thrust_otherDirectionPositiveAmount() throws Exception{
		positivePositionShip.thrust(Math.PI/2, 10.0);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelX(), positiveVelocity.getVelX() + 10.0*Math.cos(0.0)));
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelY(), positiveVelocity.getVelY() + 10.0*Math.sin(0.0)));	
	}
	@Test
	public void thrust_otherDirectionNegativeAmount() throws Exception{
		positivePositionShip.thrust(Math.PI/2, -10.0);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelX(), positiveVelocity.getVelX() + (-10.0)*Math.cos(0.0)));
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelY(), positiveVelocity.getVelY() + (-10.0)*Math.sin(0.0)));
	}
	@Test
	public void thrust_speedOfLight() throws Exception{
		speedOfLightShip.thrust(speedOfLightShip.getDirection(), 0.0);
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelX(), speedOfLightVelocity.getVelX() + speedOfLightShip.getDirection()*Math.cos(0.0)));
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelY(), speedOfLightVelocity.getVelY() + speedOfLightShip.getDirection()*Math.sin(0.0)));
	}
	@Test
	public void thrust_overSpeedOfLight() throws Exception{
		speedOfLightShip.thrust(speedOfLightShip.getDirection(), 10.0);
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelX(), speedOfLightVelocity.getVelX() + speedOfLightShip.getDirection()*Math.cos(10.0)));
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelY(), speedOfLightVelocity.getVelY() + speedOfLightShip.getDirection()*Math.sin(10.0)));
	}
	@Test
	public void thrust_underSpeedOfLight() throws Exception{
		speedOfLightShip.thrust(speedOfLightShip.getDirection(), -10.0);
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelX(), speedOfLightVelocity.getVelX() + speedOfLightShip.getDirection()*Math.cos(-10.0)));
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelY(), speedOfLightVelocity.getVelY() + speedOfLightShip.getDirection()*Math.sin(-10.0)));
	}
	
}
