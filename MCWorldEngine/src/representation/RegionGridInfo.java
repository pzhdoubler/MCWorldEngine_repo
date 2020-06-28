package representation;

import java.io.File; 
import java.lang.Math;

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
	
	
	public static void main(String[] argv)
	{
		int Xmax = 0;
		int Xmin = 0;
		int Zmax = 0;
		int Zmin = 0;
		int Xsum = 0;
		int Zsum = 0;
		int regionCount = 0;
		
		String world_path = "C:\\Users\\Zack HDoubler\\AppData\\Roaming\\.minecraft\\saves\\boiiiiiiiiiii_test";
		
		File regionFolder = new File(world_path + "\\region");
		
		File[] regionArray = regionFolder.listFiles();
		for(int i = 0; i < regionArray.length; i++) 
		{
			
			if(regionArray[i].isFile())
			{
				String regionName = regionArray[i].getName();
				String[] coordTokens = regionName.split("\\.");
				int Xval = Integer.parseInt(coordTokens[1]);
				int Zval = Integer.parseInt(coordTokens[2]);
				//should be private attributes
				if(Xval > Xmax)
					Xmax = Xval;
				if(Xval < Xmin)
					Xmin = Xval;
				if(Zval > Zmax)
					Zmax = Zval;
				if(Zval < Zmin)
					Zmin = Zval;
				Xsum += Xval;
				Zsum += Zval;
				regionCount++;
			}
			
		}
		
		int outlineArea = (Xmax - Xmin)*(Zmax - Zmin);
		double Xavg = Xsum / regionCount;
		double Zavg = Zsum / regionCount;
		
		double Xvar = 0;
		double Zvar = 0;
		
		for(int i = 0; i < regionArray.length; i++) 
		{
			
			String regionName = regionArray[i].getName();
			String[] coordTokens = regionName.split("\\.");
			int Xval = Integer.parseInt(coordTokens[1]);
			int Zval = Integer.parseInt(coordTokens[2]);
			Xvar += (Xval - Xavg) * (Xval - Xavg);
			Zvar += (Zval - Zavg) * (Zval - Zavg);
			
		}
		
		Xvar /= regionCount;
		Zvar /= regionCount;
		double Xsd = Math.sqrt(Xvar);
		double Zsd = Math.sqrt(Zvar);
		
		System.out.println("--------------------------------------------------");
		System.out.println("Info for World: " + world_path);
		System.out.println("Region Count: " + Integer.toString(regionCount));
		System.out.println("Outline Area: " + Integer.toString(outlineArea));
		System.out.println("X Average: " + Double.toString(Xavg));
		System.out.println("X Standard Dev: " + Double.toString(Xsd));
		System.out.println("X Length: " + Integer.toString(Xmax - Xmin));
		System.out.println("    Max X: " + Integer.toString(Xmax));
		System.out.println("    Min X: " + Integer.toString(Xmin));
		System.out.println("Z Average: " + Double.toString(Zavg));
		System.out.println("Z Standard Dev: " + Double.toString(Zsd));
		System.out.println("Z Length: " + Integer.toString(Zmax - Zmin));
		System.out.println("    Max Z: " + Integer.toString(Zmax));
		System.out.println("    Min Z: " + Integer.toString(Zmin));
		System.out.println("--------------------------------------------------\n");
		
	}
	

}
