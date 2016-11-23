package de.neuenberger.game.snake.view;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import de.neuenberger.game.snake.model.Vector2D;
import de.neuenberger.game.snake.view.core.GameImageProducer;
import de.neuenberger.game.snake.view.core.ImagePanel;

public class MessageScreen extends ImagePanel {
	
	public static final Image imageApple = getImage("apple.png");
	
	public static final String GAME_OVER =
			"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
			"----***---***--*-----*-*****\n"+
			"---*---*-*---*-**---**-*----\n"+
			"--*------*---*-*-*-*-*-*----\n"+
			"--*--***-*****-*--*--*-****-\n"+
			"--*----*-*---*-*-----*-*----\n"+
			"---*---*-*---*-*-----*-*----\n"+
			"----****-*---*-*-----*-*****\n"+
			"--\n\n\n"+
			"----****--*---*-*****-****-\n"+
			"---*----*-*---*-*-----*---*\n"+
			"---*----*-*---*-*-----*---*\n"+
			"---*----*-*---*-****--****-\n"+
			"---*----*-*---*-*-----*-*--\n"+
			"---*----*--*-*--*-----*--*-\n"+
			"----****----*---*****-*---*\n";
	
	public static final String SNAKE = 
			"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
			"--***--------------------------\n"+
			"-*---*-------------------------\n"+
			"*-----*------------------------\n"+
			"*-------------------*----*-----\n"+
			"*-------------------*---*------\n"+
			"-*------------------*--*-------\n"+
			"--**----------------*--*-------\n"+
			"----*---------------*-*--------\n"+
			"-----*---***---***--**-----***-\n"+
			"------*-*---*-*---*-**----*---*\n"+
			"-*----*-*---*-----*-*-*---*---*\n"+
			"*-----*-*---*--****-*--*--****-\n"+
			"*-----*-*---*-*---*-*--*--*----\n"+
			"-*----*-*---*-*---*-*---*-*---*\n"+
			"--****--*---*--****-*----*-***-\n";

	private GameImageProducer gameImageProducer;
	
	public List<Vector2D> fromString(String str) {
		String[] split = str.split("\\\n");
		List<Vector2D> result = new ArrayList<Vector2D>();
		for(int y=0; y<split.length; y++) {
			String currentString = split[y];
			for (int x=0; x<currentString.length(); x++) {
				if (currentString.charAt(x)=='*') {
					result.add(new Vector2D(x,y));
				}
			}
		}
		return result;
	}

	public void setMessage(String text) {
		GameImageProducer gameImageProducer = getGameImageProducer();
		gameImageProducer.clear(Color.BLACK);
		List<Vector2D> list = fromString(text);
		gameImageProducer.drawListWithImage(list, 0, imageApple);
		gameImageProducer.repaint();
	}

	private GameImageProducer getGameImageProducer() {
		if (gameImageProducer==null) {
			gameImageProducer = new GameImageProducer(10, this, 31,45);
		}
		return gameImageProducer;
	}
	
	private static Image getImage(String string) {
		ImageIcon imageIcon = new ImageIcon(MessageScreen.class.getResource("res/"+string));
		return imageIcon.getImage();
	}
	
	
}