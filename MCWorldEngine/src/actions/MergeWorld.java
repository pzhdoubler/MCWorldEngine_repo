package actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class MergeWorld {

	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	
	
//Make class to get info about region
//	private static void copyRegionUsingStream(File source, File dest, int xOffset, int zOffset) throws IOException {
//	    InputStream is = null;
//	    OutputStream os = null;
//	    try {
//	        is = new FileInputStream(source);
//	        os = new FileOutputStream(dest);
//	        byte[] chunkBuffer = new byte[4];
//	        byte[] fillerBuffer = new byte[4096];
//	        byte[] editBuffer = new byte[4096];
//	        int length = 1;
//	        int fillerCount = 0;
//	        while (length > 0) {
//	        	if(fillerCount++ < 2) {
//	        		length = is.read(fillerBuffer);
//	        		if(length > 0)
//	        			os.write(fillerBuffer, 0, length);
//	        	}
//	        	else {
//	        		length = is.read(chunkBuffer);
//	        		if(length > 0) {
//	        			int dataLength = java.nio.ByteBuffer.wrap(chunkBuffer).getInt();
//	        			System.out.println(dataLength);
//	        			is.read(fillerBuffer, 0, dataLength);
//	        			os.write(chunkBuffer, 0, length);
//	        			os.write(fillerBuffer, 0, dataLength);
//	        			try {
//	        				offsetRegionEntities(fillerBuffer, editBuffer, dataLength - 1, xOffset, zOffset);
//	        			}
//	        			catch(DataFormatException de) {
//	        				System.out.println("DataFormatException");
//	        			}
//	        		}
//	        	}
//	        }
//	    } finally {
//	        is.close();
//	        os.close();
//	    }
//	}
//	
//	private static void offsetRegionEntities(byte[] input, byte[] workspace, int inputLength, int xOffset, int zOffset) throws DataFormatException 
//	{
//		Inflater decomp = new Inflater();
//		decomp.setInput(input, 1, inputLength); //ignore first byte
//		int resultLength = decomp.inflate(workspace);
//	}
	
	//cuts out part of chosen world
	public static void main(String[] args) {
		int xUpper = -16;
		int xLower = -18;
		int xOffset = 35;
		int zUpper = -2;
		int zLower = -4;
		int zOffset = 42;
		String worldDir = "C:\\Users\\Zack HDoubler\\AppData\\Roaming\\.minecraft\\saves\\worldCalebMatthew";
		
		File regionFile = new File(worldDir + "\\region");
		
        File worldSlice = new File(worldDir + "\\worldSlice");
        if (!worldSlice.exists()) {
            if (worldSlice.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
		
		File[] regionArray = regionFile.listFiles();
		for(int i = 0; i < regionArray.length; i++) 
		{
			
			if(regionArray[i].isFile())
			{
				String regionName = regionArray[i].getName();
				String[] coordTokens = regionName.split("\\.");
				int Xval = Integer.parseInt(coordTokens[1]);
				int Zval = Integer.parseInt(coordTokens[2]);
				if(Xval <= xUpper && Xval >= xLower && Zval <= zUpper && Zval >= zLower)
				{
					int newX = Xval + xOffset;
					int newZ = Zval + zOffset;
					String newRegion = "r." + Integer.toString(newX) + "." + Integer.toString(newZ) + ".mca";
					System.out.println("creating region " + newRegion);
					File newRegionFile = new File(worldDir + "\\worldSlice\\" + newRegion);
					try {
						copyFileUsingStream(regionArray[i], newRegionFile);
					}
					catch(IOException e){
						System.out.println("IOException");
					}
				}
			}
			
		}
	}

}
