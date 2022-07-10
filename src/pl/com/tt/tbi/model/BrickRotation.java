package pl.com.tt.tbi.model;

import java.util.Arrays;
import java.util.List;

public class BrickRotation {

	private List<Coordinates> fillinfCoordinates;

	public BrickRotation(Coordinates ... fillinfCoordinates){
		this.fillinfCoordinates = Arrays.asList(fillinfCoordinates);
	}
	
	public List<Coordinates> getAllFilledPoints() {
		return fillinfCoordinates;
	}

	@Override
	public String toString() {
		String brick[] = new String[]{"XXXX","XXXX","XXXX","XXXX"};
		for (Coordinates coords: fillinfCoordinates){
			String row = brick[coords.getY()];
			char[] rowChars = row.toCharArray();
			rowChars[coords.getX()] = 'O';
			brick[coords.getY()] = String.valueOf(rowChars);
		}
		String result = ""; 
		for (String row: brick){
			result += "\n" + row;
		}
		return result;
	}
	
	
	
}
