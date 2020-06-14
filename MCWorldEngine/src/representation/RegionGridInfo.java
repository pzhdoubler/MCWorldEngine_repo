package representation;

public class RegionGridInfo implements WorldInfo {

	private int maxX;
	private int minX;
	private int maxZ;
	private int minZ;
	
	
	public void setXMax(int val) {
		maxX = val;
	}
	
	public void setXMin(int val) {
		minX = val;
	}
	
	public void setZMax(int val) {
		maxZ = val;
	}
	
	public void setZMin(int val) {
		minZ = val;
	}
	
	
	public int getXMax() {
		return maxX;
	}

	public int getXMin() {
		return minX;
	}

	public int getZMax() {
		return maxZ;
	}

	public int getZMin() {
		return minZ;
	}

}
