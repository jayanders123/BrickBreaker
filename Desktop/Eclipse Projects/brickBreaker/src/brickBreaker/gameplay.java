package brickBreaker;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;

/**
 * 
 * @author Joshua Anderson
 * 
 * This class is the blueprint for any initialised "gameplay" objects. 
 * This class inherits the features of a JPanel.
 * This class implements the methods from the "KeyListener" class - This is for the use of apps recognising key presses.
 * This class implements the methods from the "ActionListener" class - This is for the use of apps recognising the mouse clicks.
 *
 */

public class gameplay extends JPanel implements KeyListener, ActionListener
{
	private boolean play = false;//This specifies that when the app is started the game does not play by itself - it requires a user.
	private int score = 0;//This ensures that the starting score is 0.
	private int totalBricks = 21;//This specifies the amount of bricks when game begins.
	//----------------------
	private int delay = 7;
	//----------------------
	private int playerX = 310;//This specifies the position of the paddle at the beginning of the game.  
	//----------------------
	private int ballPositionX = 120;//This specifies the position of the ball when the game begins.
	private int ballPositionY = 350;
	private int ballDirectionX = -1;//This specifies the direction the ball travels when the game has been started.
	private int ballDirectionY = -2;
	//----------------------
	private MapGenerator map; //This variable will be used to facilitate the object of the MapGenerator class. Used on line "45"
	
	
	public gameplay() 
	{
		map = new MapGenerator(3,7);//This is an instanciation of the MapGenerator. Its created so methods within that class can be called in this gameplay class.
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		Timer timer = new Timer(delay, this);
		timer.start();
	}
	
	/**
	 * This method will be used to paint the objects used in the application such as the background, ball, paddle and bricks.
	 */
	public void paint (Graphics g)
	{
		//background
		g.setColor(Color.black);// This specifies the colour of the background.
		g.fillRect(1, 1, 692, 592);
		
		//drawing map(Bricks)
		map.draw((Graphics2D)g);//This draws the bricks within the game
		
		//borders
		g.setColor(Color.yellow);//This specifies the colour of the borders.
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		//paddle
		g.setColor(Color.green );//This specifies the size and colour of the paddle.
		g.fillRect( playerX, 550, 100, 8);
		//ball
		g.setColor(Color.yellow);//This specifies the colour of the ball.
		g.fillOval( ballPositionX, ballPositionY, 20, 20);//This specifies the size of the ball.
		
		g.dispose();
		
	}
	
	@Override
	/**
	 * This method computes all the movements and interactions that happen between objects in the application.
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(play)
		{
			if(new Rectangle (ballPositionX, ballPositionY,20 , 20).intersects(new Rectangle(playerX, 550 ,100, 8)))//This if statement specifies the interaction between the paddle and the ball.
			{
				ballDirectionY =- ballDirectionY; 
				
			}
			
			
			//This for loops iterates through the 2D array called "map" in the MapGenerator class.
			A: for(int i = 0; i<map.map.length;i++)//This first loop accesses the array through the object (of the MapGenerator class) called "map". As seen on line "40".

			{
				for(int j=0; j<map.map[0].length;j++) //This loop then calls the array "map" from the MapGenerator class so we can create interactions for each individual brick.
				{
					if(map.map[i][j] > 0) // This line states that if the bricks !!!!!!!!! FINISH HERE !!!!!!!!!!
					{
						int brickX = j* map.brickWidth + 80;
						int brickY = i* map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
						Rectangle ballRect = new Rectangle(ballPositionX,ballPositionY,20,20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect))
						{
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;
							
							if(ballPositionX + 19 <= brickRect.x || ballPositionX + 1 >= brickRect.x + brickRect.width) 
							{
								ballDirectionX =- ballDirectionX; 
							}else 
							{
								ballDirectionY =- ballDirectionY;
							}
							
							break A;
						}
					}
				}
			}
		
			ballPositionX += ballDirectionX;
			ballPositionY += ballDirectionY;
			if(ballPositionX < 0) //ball position X
			{
				ballDirectionX =- ballDirectionX;
			}
			if(ballPositionY < 0) //ball position Y
			{
				ballDirectionY =- ballDirectionY;
			}
			if(ballPositionX > 670) 
			{
				ballDirectionX =- ballDirectionX;
			}
		}
		
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) //if the key is pressed
		{
			if(playerX >= 600) //if paddle position is more-than or equal to 600 pixels.
			{
				playerX = 600;
			}else 
			 {
				moveRight();
			 } 
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			if(playerX < 10) //If paddle position is less than 10
			{
				playerX = 10;
			}else 
			 {
				moveLeft();
			 }
		}
		
	}
	
	public void moveRight() 
	{
		play = true;
		playerX += 20;
	}
	
	public void moveLeft() 
	{
		play = true;
		playerX -= 20;
	}

}
