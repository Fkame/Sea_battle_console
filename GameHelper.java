import java.io.*;
import java.util.*;

// ����� �।�����祭�� ��� ����祭��  ��ࠡ�⪨ ����� ���짮��⥫�
public class GameHelper {
	
	private static final String alphabet = "abcdefg";
	private int gridLength = 7;
	private int gridSize = 49;
	private int [] grid = new int[gridSize];
	private int comCount = 0;
	
	
	public String getUserInput(String prompt) {
		String inputLine = null;
		System.out.println(prompt + " ");
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
			
			if (inputLine.length() == 0) return null;
		
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return inputLine.toLowerCase();
	}
	
	
	// ������� ���न��� ��� ��ࠡ��
	public ArrayList<String> placeDotCom(int comSize) {
		//System.out.println("\nGameHelper.placeDotCom() called");
		
		ArrayList<String> alphaCells = new ArrayList<String>();
		String[] alphacoords = new String[comSize];
		String temp = null;
		int[] coords = new int [comSize];
		int attempt = 0;
		boolean success = false;
		int location = 0;
		
		comCount++;
		int incr = 1;
		if ((comCount % 2) == 1) {
			incr = gridLength;
		}
		
		while (!success & attempt++ < 200) {
			location = (int) (Math.random() * gridSize);
			//System.out.println("\t�஡㥬 " + location);
			int x = 0;
			success = true;
			while (success && x < comSize) {
				if (grid[location] == 0) {
					coords[x++] = location;
					location += incr;
					
					if (location >= gridSize) {
						success = false;
					}
					if (x > 0 && (location % gridLength == 0)) {
						success = false;
					}
				} else {
					//System.out.println("�ᯮ������" + location);
					success = false;
				}
			}
		}
		
		int x = 0;
		int row = 0;
		int column = 0;
		//System.out.println("");
		while (x < comSize) {
			grid[coords[x]] = 1;
			row = (int) (coords[x] / gridLength);
			column = coords[x] % gridLength;
			temp = String.valueOf(alphabet.charAt(column));
			
			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;
			//System.out.print("\t| coord " + x + " = " + alphaCells.get(x - 1) + " | ");
		}
		//System.out.print("\n");
		
		return alphaCells;
	}
}