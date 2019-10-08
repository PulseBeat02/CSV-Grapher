package org.brandonli.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetData {

	public static String[] getNames(File file) {

		String[] names = new String[2];

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			names = br.readLine().split(",");
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return names;

	}
	
	public static double[][] getValues(File file) {
		
		double [][] values = {};
		
		try {
			
			values = new double[(int) Files.lines(Paths.get(file.toURI())).count() - 1][2];
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			br.readLine();
			
			for (int i = 0; i < values.length; i++) {
				
				String[] contents = br.readLine().split(",");
				
				values[i][0] = Double.parseDouble(contents[0]);
				values[i][1] = Double.parseDouble(contents[1]);
				
			}
			
			br.close();
			
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return values;
		
	}

}
