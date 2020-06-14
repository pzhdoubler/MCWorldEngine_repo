package representation;

/**
 * Collection of MC region files.
 * @author Zack HDoubler
 *
 */
public interface Terrain {

	WorldInfo getInfo();
	
	Region getRegion(int x, int z);
	
}
