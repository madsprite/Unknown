package Unknown;

import java.io.BufferedReader;
import java.io.FileReader;

public class MapLoad {

	private FileReader textfilereader;
	private BufferedReader mapreader;
	public int[][] map = new int[52][52];

	public MapLoad(String r) {

		try {
			textfilereader = new FileReader(r);
			mapreader = new BufferedReader(textfilereader);
			for (int row = 0; row < map.length; row++) {
				String[] taken = mapreader.readLine().split(",");
				for (int col = 0; col < map[row].length; col++) {

					map[row][col] = Integer.parseInt(taken[col]);
					//System.out.println(taken[col] + " ");
				}
				// System.out.println();
			}
		} catch (Exception e) {
			System.out.print("error");
		}
	}
}
