package brickBreaker;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author Joshua Anderson
 * This class is for the purpose of mapping all the interactions between the ball and the bricks within the game.
 * The section of the video which begins talking about this class is 39:48.
 *
 */
public class MapGenerator {

	public int map[][];
	public int brickWidth;
	public int brickHeight;
	
	public MapGenerator(int row, int col){
		
		map = new int [row][col];//This line instantiates the 2D array that was created above.
		
		for(int i=0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				map[i][j] = 1; 
			}
		}
		
		brickWidth = 540/col;
		brickHeight = 150/row;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(map[i][j] > 0) {
					g.setColor(Color.white);//This code creates a solid white block which will become smaller individual blocks.
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					//This code sets the strokes in-between the solid white block white to distinguish individual blocks.
					g.setStroke( new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
		
	}
	
	public void setBrickValue(int value, int row, int col){
		map[row][col] = value;
	}
	
	
}
