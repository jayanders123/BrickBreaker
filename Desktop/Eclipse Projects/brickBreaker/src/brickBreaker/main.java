package brickBreaker;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) 
	{
		/**
		 * Underneath is the JFrame and its properties.
		 */
	JFrame obj = new JFrame(); //JFrame is the code that creates the application window.
	gameplay theGame = new gameplay();
	obj.setTitle("Breakout Ball");//setTitle() specifies what will be displayed as the title on the app window.
	obj.setBounds(10,10,700,600);// setBounds() specifies the position on the graphical user interface within the application window. 
	obj.setResizable(false);//setResizeable() specifies whether then app window can be resized.
	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//this specifies that when the user closes the window, the application stops running.
	obj.add(theGame);
	obj.setVisible(true);//setVisible() specifies whether the window is physically visible to the user.
	}
}
