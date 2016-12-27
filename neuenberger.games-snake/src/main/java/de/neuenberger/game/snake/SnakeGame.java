package de.neuenberger.game.snake;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.Timer;

import de.neuenberger.game.snake.logic.SnakeModelLogic;
import de.neuenberger.game.snake.model.SnakeModel;
import de.neuenberger.game.snake.model.SnakePlayer;
import de.neuenberger.game.snake.model.Vector2D;
import de.neuenberger.game.snake.view.MessageScreen;
import de.neuenberger.game.snake.view.ScoreController;
import de.neuenberger.game.snake.view.SnakeController;

public class SnakeGame extends JFrame {
	private static final String START = "Start";
	private static final int GAME_PANE_WIDTH = 30;
	private static final int GAME_PANE_HEIGHT = 48;
	private static final int INITIAL_LIFES = 3;
	private final SnakeModel model;
	private final SnakeController controller;



	GameState gameState = null;
	enum GameState {
		RUNNING, PAUSED;
	}
	
	class HandlePlayerKey extends KeyAdapter {
		final SnakePlayer player;
		final int rotateLKey;
		final int rotateRKey;
		java.awt.event.KeyEvent blockedKeyEvent = null;

		public HandlePlayerKey(SnakePlayer player, int rotateLKey, int rotateRKey) {
			this.player = player;
			this.rotateLKey = rotateLKey;
			this.rotateRKey = rotateRKey;
		}
		boolean blockKey = false;
		@Override
		public void keyPressed(final java.awt.event.KeyEvent e) {
			if (e.getKeyCode() == rotateLKey) {
				if (blockKey == false) {
					player.getDirection().rotate(1);
					blockKey = true;
				} else {
					blockedKeyEvent = e;
				}
			} else if (e.getKeyCode() == rotateRKey) {
				if (blockKey == false) {
					player.getDirection().rotate(-1);
					blockKey = true;
				} else {
					blockedKeyEvent = e;
				}
			} else {
				System.out.println("unknonw keycode: " + e.getKeyCode());
			}
		};

		public void nextTick() {
			blockKey = false;
			if (blockedKeyEvent != null) {
				keyPressed(blockedKeyEvent);
				blockedKeyEvent = null;
			}
		}
	};

	HandlePlayerKey handlePlayerKey1;


	ActionListener timerHandler = new ActionListener() {

		@Override
		public void actionPerformed(final ActionEvent e) {
			logic.nextTick();
			controller.update();

			final List<Vector2D> bites = model.getBites();
			if (bites != null && bites.size() == 0) {
				setupLevel(model, model.getLevel() + 1);
			}

			handlePlayerKey1.nextTick();
			handlePlayerKey2.nextTick();
		}

	};

	PropertyChangeListener lifeHandler = new PropertyChangeListener() {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			final int lifes = model.getP1().getLifes();
			if (lifes >= 0) {
				setupLevel(model, model.getLevel());
			} else {
				setGameOver();
			}
		}
	};

	Timer gameTickTimer = new Timer(500, timerHandler);
	private final SnakeModelLogic logic;
	private JButton startStopPauseButton;
	private MessageScreen messageScreen;
	private JCheckBox twoPlayerGameButton;
	private HandlePlayerKey handlePlayerKey2;

	SnakeGame() {
		this.setSize(325, 540);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model = new SnakeModel();

		controller = new SnakeController(model);
		messageScreen = new MessageScreen();
		showMessageScreen();
		logic = new SnakeModelLogic(model);

		final JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		this.add(toolbar, BorderLayout.NORTH);
		setupToolBar(toolbar);
		this.requestFocus();
		messageScreen.setSnake();
		handlePlayerKey1 = new HandlePlayerKey(model.getP1(), 37, 39);
		handlePlayerKey2 = new HandlePlayerKey(model.getP2(), 81, 69);
		this.addKeyListener(handlePlayerKey1);
		this.addKeyListener(handlePlayerKey2);
		final ScoreController scoreController = new ScoreController(model.getP1());
		this.add(scoreController.getScorePanel(), BorderLayout.SOUTH);
		model.addPropertyChangeListener(SnakeModel.LIFES, lifeHandler);
		
	}

	private void resetGame() {
		startStopPauseButton.setText(START);
		model.getP1().clearPoints();
		model.setLifes(model.getP1(), INITIAL_LIFES);
		if (model.isTwoPlayerGame()) {
			model.getP2().clearPoints();
			model.setLifes(model.getP2(), INITIAL_LIFES);
		}
		setupLevel(model, 0);
		gameState=GameState.RUNNING;
	}

	private void showGameScreen() {
		this.add(controller.getPanel(), BorderLayout.CENTER);
		controller.getPanel().setVisible(true);
		messageScreen.setVisible(false);
	}
	
	private void showMessageScreen() {
		this.add(messageScreen, BorderLayout.CENTER);
		controller.getPanel().setVisible(false);
		messageScreen.setVisible(true);
	}

	private void setupLevel(final SnakeModel model, final int level) {
		model.setLevel(level);

		
		SnakePlayer player = model.getP1();
		initializePlayer(player, new Vector2D(15, 24));
		if (model.isTwoPlayerGame()) {
			initializePlayer(model.getP2(), new Vector2D(14, 8));
			model.getP2().getDirection().setY(1);
		}
		gameTickTimer.setDelay(Math.max(70, 150 - level * 10));

		final List<Vector2D> bricks = getLevelBricks(level);

		final int maxItems = 10 + level * 5;
		model.setBites(randomBlocks(maxItems, bricks));
		model.setBlocks(bricks);
		showLevelMessage(level);
	}

	private void initializePlayer(SnakePlayer player, Vector2D initialPosition) {
		List<Vector2D> dots = player.getDots();
		dots.clear();
		dots.add(initialPosition);
		player.getDirection().setX(0);
		player.getDirection().setY(-1);
		player.setGrowth(5);
	}

	private void showLevelMessage(final int level) {
		gameTickTimer.stop();
		showMessageScreen();
		messageScreen.countTilLevel(level, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				showGameScreen();
				gameTickTimer.start();
			}
			
		});
	}
	
	private void setGameOver() {
		gameState=null;
		gameTickTimer.stop();
		showMessageScreen();
		messageScreen.setGameOver();
		twoPlayerGameButton.setEnabled(true);
	}

	private List<Vector2D> getLevelBricks(final int level) {
		final int mode = level % 7;
		final List<Vector2D> bricks = new ArrayList<Vector2D>();
		switch (mode) {
		case 0:
			// no walls;
			break;
		case 2:
			// vertical wall, one face
			for (int y = 0; y < GAME_PANE_HEIGHT; y++) {
				if (y > 3 && y < 12 || y > 15 && y < 28 || y > 30 && y < 43) {
					bricks.add(new Vector2D(16, y));
				}
			}
			// + horizontal wall
		case 1:
			// horizontal wall, one face

			for (int x = 0; x < GAME_PANE_WIDTH; x++) {
				if (x > 3 && x < 12 || x > 15 && x < 24) {
					bricks.add(new Vector2D(x, 22));
				}
			}
			break;
		case 6:
			// horizontal wall
			for (int x = 0; x < GAME_PANE_WIDTH; x++) {
				if (x > 3 && x < 11 || x > 17 && x < 25) {
					bricks.add(new Vector2D(x, 3));
					bricks.add(new Vector2D(x, 17));
					bricks.add(new Vector2D(x, 25));
					bricks.add(new Vector2D(x, 39));
				}
			}

			// small vertical walls attached.
			for (int y = 0; y < GAME_PANE_HEIGHT; y++) {
				if (y > 3 && y < 10 || y > 17 && y < 25 || y > 32 && y < 39) {
					bricks.add(new Vector2D(4, y));
					if (y < 8 || y > 35) {
						bricks.add(new Vector2D(10, y));
						bricks.add(new Vector2D(18, y));
					}
					bricks.add(new Vector2D(24, y));
				}
			}
			break;
		case 5:
			// vertical wall, two face
			for (int y = 0; y < GAME_PANE_HEIGHT; y++) {
				if (y > 3 && y < 12 || y > 15 && y < 28 || y > 30 && y < 43) {
					bricks.add(new Vector2D(10, y));
					bricks.add(new Vector2D(20, y));
				}
			}
		case 4:
			// horizontal wall, twwo face

			for (int x = 0; x < GAME_PANE_WIDTH; x++) {
				if (x > 3 && x < 12 || x > 15 && x < 24) {
					bricks.add(new Vector2D(x, 15));
					bricks.add(new Vector2D(x, 30));
				}
			}
			break;
		}
		return Collections.unmodifiableList(bricks);
	}

	public List<Vector2D> randomBlocks(final int n, final List<Vector2D> exclusions) {

		final List<Vector2D> result = new ArrayList<Vector2D>(n);
		for (int i = 0; i < n;) {
			final int x = (int) (model.getWidth() * Math.random());
			final int y = (int) (model.getHeight() * Math.random());
			final Vector2D newPoint = new Vector2D(x, y);
			if (exclusions == null || Vector2D.hitTest(exclusions, newPoint, 0) == null) {
				result.add(newPoint);
				i++;
			}
		}
		return result;

	}

	private void setupToolBar(final JToolBar toolbar) {
		twoPlayerGameButton = new JCheckBox("2P");
		twoPlayerGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.setTwoPlayerGame(twoPlayerGameButton.isSelected());
			}
		});

		toolbar.add(twoPlayerGameButton);

		startStopPauseButton = new JButton(START);
		startStopPauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				if (gameState==null) {
					// no game running.
					resetGame(); // start game
					gameTickTimer.start();
					twoPlayerGameButton.setEnabled(false);
				} else if (gameState==GameState.RUNNING) {
					gameState = GameState.PAUSED;
					gameTickTimer.stop();
					startStopPauseButton.setText("continue");
					messageScreen.setPause();
					showMessageScreen();
				} else {
					gameState = GameState.RUNNING;
					gameTickTimer.start();
					
					startStopPauseButton.setText("pause");
					showGameScreen();
				}
				SnakeGame.this.requestFocus();
			}

		});
		toolbar.add(startStopPauseButton);

		final JButton cheatButton = new JButton("c");
		cheatButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				model.setBites(Collections.<Vector2D> emptyList());

			}
		});
		toolbar.add(cheatButton);
	}
	
	public static void main(final String[] args) {
		final SnakeGame snakeGame = new SnakeGame();
		snakeGame.setVisible(true);
	}
}
