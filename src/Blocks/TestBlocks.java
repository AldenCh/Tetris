package Blocks;

public class TestBlocks {
	public static void main(String[] args) {
		J_Block i = new J_Block(false);
		for (Integer[] coord : i.getTiles()) {
			System.out.println(coord[0]+","+coord[1]);
		}
	}
}
