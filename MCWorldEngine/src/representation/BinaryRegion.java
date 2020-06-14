package representation;

public class BinaryRegion implements Region {

	private boolean exists;
	
	public BinaryRegion() {
		exists = false;
	}
	
	public BinaryRegion(boolean e) {
		exists = e;
	}
	
	public boolean isGenerated() {
		return exists;
	}

}
