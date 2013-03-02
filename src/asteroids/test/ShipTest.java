package asteroids.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import asteroids.model.Ship;
import asteroids.model.Util.Position;
import asteroids.model.Util.Velocity;

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
		negativeRadiusShip = new Ship(positivePosition, positiveVelocity, 0.0, -20.0);
		positiveRadiusShip = new Ship(positivePosition, positiveVelocity, 0.0, 20.0);
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
	public void extendedConstructor_LegalCase(){
		Ship theShip = new Ship(positivePosition, positiveVelocity, Math.PI, 2.0);
		assertTrue((positivePosition.getPosX() == theShip.getPos().getPosX()));
		assertTrue((positivePosition.getPosY() == theShip.getPos().getPosY()));
		assertTrue((positiveVelocity.getVelX() == theShip.getVel().getVelX()));
		assertTrue((positiveVelocity.getVelY() == theShip.getVel().getVelY()));
		assertTrue((Math.PI == theShip.getDirection()));
		assertTrue((2.0 == theShip.getRadius()));
	}
	//TODO bespreken hoe dit moet afgehandeld worden. Via exceedspeedoflightexception, of toelaten of moet gwn lichtsnelheid worden.
	@Test
	public void extendendConstructor_infiniteSpeedCase(){
		Ship theShip = new Ship(positivePosition, positiveInfiniteVelocity, Math.PI, 2.0);
		assertTrue((theShip.getVel().getVelX() == positiveInfiniteVelocity.getVelX()));
	}
	
	@Test
	public void extendenConstructor_infinitePositionCase(){
		Ship theShip = new Ship(infinitePosition, positiveVelocity, Math.PI, 2.0);
		assertTrue((theShip.getPos().getPosX() == infinitePosition.getPosX()));
	}
	
	@Test
}
