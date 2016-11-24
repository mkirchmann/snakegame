package de.neuenberger.game.snake.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import org.junit.runners.model.FrameworkMethod;

import de.neuenberger.game.snake.model.Vector2D;
import de.neuenberger.game.snake.view.MessageScreen.Font;
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
			"--***-------------------------\n"+
			"-*---*------------------------\n"+
			"*-----*-----------------------\n"+
			"*-------------------*---------\n"+
			"*-------------------*---------\n"+
			"-*------------------*---------\n"+
			"--**----------------*---------\n"+
			"----*---------------*---*-----\n"+
			"-----*---***---***--*--*--***-\n"+
			"------*-*---*-*---*-*-*--*---*\n"+
			"-*----*-*---*-----*-**---*---*\n"+
			"*-----*-*---*--****-**---****-\n"+
			"*-----*-*---*-*---*-*-*--*----\n"+
			"-*---*--*---*-*---*-*--*-*---*\n"+
			"--***---*---*--****-*---*-***-\n";
	
	public static final String PAUSE = 
			"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
			"---***------------------------\n"+
			"--*---*-----------------------\n"+
			"-*-----*----------------------\n"+
			"-*-----*----------------------\n"+
			"-*-----*----------------------\n"+
			"-*-----*----------------------\n"+
			"-**---*-----------------------\n"+
			"-*-***------------------------\n"+
			"-*------***--*---*--***---***-\n"+
			"-*-----*---*-*---*-*---*-*---*\n"+
			"-*---------*-*---*-*-----*---*\n"+
			"-*------****-*---*--***--****-\n"+
			"-*-----*---*-*---*-----*-*----\n"+
			"-*-----*---*-*---*-*---*-*---*\n"+
			"-*------****--***---***---***-\n";
	
	public static final String LEVEL = 
			"\n\n\n\n"+
			"-*-------------------------\n"+
			"-*-------------------------\n"+
			"-*-----------------------*-\n"+
			"-*-----------------------*-\n"+
			"-*-----------------------*-\n"+
			"-*-----------------------*-\n"+
			"-*-----------------------*-\n"+
			"-*-----------------------*-\n"+
			"-*------***--*---*--***--*-\n"+
			"-*-----*---*-*---*-*---*-*-\n"+
			"-*-----*---*-*---*-*---*-*-\n"+
			"-*-----****--*---*-****--*-\n"+
			"-*-----*------*-*--*-----*-\n"+
			"-*-----*---*--*-*--*---*-*-\n"+
			"-*****--****---*----***---*\n";
	
	public static final String STR_NUM_0 = 
			"-***-\n"+
			"*---*\n"+
			"*---*\n"+
			"*---*\n"+
			"*---*\n"+
			"-***-\n";
	public static final String STR_NUM_1 = 
			"*\n"+
			"*\n"+
			"*\n"+
			"*\n"+
			"*\n"+
			"*\n";
	public static final String STR_NUM_2 = 
			"-***-\n"+
			"*---*\n"+
			"---*-\n"+
			"--*--\n"+
			"-*--*\n"+
			"*****\n";
	
	public static final String STR_NUM_3 = 
			"-***-\n"+
			"*---*\n"+
			"--**-\n"+
			"----*\n"+
			"*---*\n"+
			"-***-\n";
	
	public static final String STR_NUM_4 = 
			"---*-\n"+
			"--**-\n"+
			"-*-*-\n"+
			"*****\n"+
			"---*-\n"+
			"---*-\n";
	
	public static final String STR_NUM_5 = 
			"*****\n"+
			"*----\n"+
			"****-\n"+
			"----*\n"+
			"----*\n"+
			"****-\n";
	
	public static final String STR_NUM_6 = 
			"-****\n"+
			"*----\n"+
			"****-\n"+
			"*---*\n"+
			"*---*\n"+
			"-***-\n";
	
	public static final String STR_NUM_7 = 
			"*****\n"+
			"----*\n"+
			"---*-\n"+
			"---*-\n"+
			"--*--\n"+
			"--*--\n";
	
	public static final String STR_NUM_8 = 
			"-***-\n"+
			"*---*\n"+
			"-***-\n"+
			"*---*\n"+
			"*---*\n"+
			"-***-\n";
	
	public static final String STR_NUM_9 = 
			"-***-\n"+
			"*---*\n"+
			"-****\n"+
			"----*\n"+
			"*---*\n"+
			"-***-\n";
	
	enum Font {
		NUM_0('0',6,STR_NUM_0),
		NUM_1('1',2,STR_NUM_1),
		NUM_2('2',6,STR_NUM_2),
		NUM_3('3',6,STR_NUM_3),
		NUM_4('4',6,STR_NUM_4),
		NUM_5('5',6,STR_NUM_5),
		NUM_6('6',6,STR_NUM_6),
		NUM_7('7',6,STR_NUM_7),
		NUM_8('8',6,STR_NUM_8),
		NUM_9('9',6,STR_NUM_9),
		;
		private final char c;
		private final int width;
		private final List<Vector2D> list;
		Font(char c, int width, String s) {
			this(c,width,fromString(s));
		}
		Font(char c, int width, List<Vector2D> list) {
			this.c = c;
			this.width = width;
			this.list = Collections.unmodifiableList(list);
		}
		
		public static Optional<Font> forCharacter(char x) {
			Font[] values = values();
			for (Font font : values) {
				if (font.c==x) {
					return Optional.of(font);
				}
			}
			return Optional.empty();
		}
		
		public static List<Vector2D> forNumber(int x) {
			String numberAsString = ""+x;
			char[] charArray = numberAsString.toCharArray();
			int currentWidth=0;
			List<Vector2D> resultingVectors = new ArrayList<Vector2D>();
			for (char c : charArray) {
				Optional<Font> forCharacter = forCharacter(c);
				if (forCharacter.isPresent()) {
					Font font = forCharacter.get();
					List<Vector2D> movedVector = Vector2D.move(font.list,new Vector2D(currentWidth,0));
					resultingVectors.addAll(movedVector);
					currentWidth+=font.width;
				}
			}
			return resultingVectors;
		}
	}

	private GameImageProducer gameImageProducer;
	
	private Timer gameOverTimer = new Timer(2000,new GameOverActionListener());
	NextLevelActionListener nextLevelActionListener = new NextLevelActionListener();
	
	private Timer nextLevelTimer = new Timer(200,nextLevelActionListener);
	
	public static List<Vector2D> fromString(String str) {
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

	private void setMessage(String text) {
		List<Vector2D> list = fromString(text);
		setMessage(list);
	}

	private void setMessage(List<Vector2D> list) {
		GameImageProducer gameImageProducer = getGameImageProducer();
		gameImageProducer.clear(Color.BLACK);
		gameImageProducer.drawListWithImage(list, 0, imageApple);
		gameImageProducer.repaint();
	}
	
	public void setPause() {
		setMessage(PAUSE);
		gameOverTimer.stop();
		nextLevelTimer.stop();
	}
	
	public void setSnake() {
		setMessage(SNAKE);
		gameOverTimer.stop();
		nextLevelTimer.stop();
	}
	
	public void setGameOver() {
		setMessage(GAME_OVER);
		gameOverTimer.start();
		nextLevelTimer.stop();
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

	
	class GameOverActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setSnake();
		}
	}
	
	
	class NextLevelActionListener implements ActionListener {
		int currentCount;
		private int countTil;
		private ActionListener callback;
		private int ticksToStop;
		public void countUp(int countTil, ActionListener callback) {
			this.countTil = countTil;
			this.callback = callback;
			currentCount=0;
			ticksToStop=0;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentCount<countTil) {
				setLevel(currentCount++);
			} else if (ticksToStop<10) {
				if (ticksToStop==0) {
					setLevel(currentCount);
				}
				ticksToStop++;
			} else {
				nextLevelTimer.stop();
				callback.actionPerformed(e);
			}
			
		}
	}

	public void setLevel(int currentCount) {
		List<Vector2D> list = Font.forNumber(currentCount);
		int m=5*(""+currentCount).length()/2;
		List<Vector2D> movedLevelNumber = Vector2D.move(list, new Vector2D(15-m,25));
		List<Vector2D> all=new ArrayList<Vector2D>(fromString(LEVEL));
		all.addAll(movedLevelNumber);
		setMessage(all);
	}
	
	public void countTilLevel(int countTil, ActionListener callback) {
		nextLevelActionListener.countUp(countTil, callback);
		nextLevelTimer.start();
		gameOverTimer.stop();
	}
	
}
