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
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.Timer;

import de.neuenberger.game.snake.logic.SnakeModelLogic;
import de.neuenberger.game.snake.model.SnakeModel;
import de.neuenberger.game.snake.model.Vector2D;
import de.neuenberger.game.snake.view.ScoreController;
import de.neuenberger.game.snake.view.SnakeController;

public class SnakeGame extends JFrame {
	private static final int GAME_PANE_WIDTH = 30;
	private static final int GAME_PANE_HEIGHT = 48;
	private final SnakeModel model;
	private final SnakeController controller;

	boolean blockKey = false;

	java.awt.event.KeyEvent blockedKeyEvent = null;

	KeyAdapter keyAdapter = new KeyAdapter() {
		@Override
		public void keyPressed(final java.awt.event.KeyEvent e) {
			if (e.getKeyCode() == 37) {
				if (blockKey == false) {
					model.getP1().getDirection().rotate(1);
					blockKey = true;
				} else {
					blockedKeyEvent = e;
				}
			} else if (e.getKeyCode() == 39) {
				if (blockKey == false) {
					model.getP1().getDirection().rotate(-1);
					blockKey = true;
				} else {
					blockedKeyEvent = e;
				}
			}
		};
	};

	ActionListener timerHandler = new ActionListener() {

		@Override
		public void actionPerformed(final ActionEvent e) {
			logic.nextTick();
			controller.update();

			final List<Vector2D> bites = model.getBites();
			if (bites != null && bites.size() == 0) {
				setupLevel(model, model.getLevel() + 1);
			}
			blockKey = false;
			if (blockedKeyEvent != null) {
				keyAdapter.keyPressed(blockedKeyEvent);
				blockedKeyEvent = null;
			}
		}

	};

	PropertyChangeListener lifeHandler = new PropertyChangeListener() {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			final int lifes = model.getP1().getLifes();
			if (lifes >= 0) {
				setupLevel(model, model.getLevel());
			} else {
				resetGame();
			}
		}
	};

	Timer timer = new Timer(500, timerHandler);
	private final SnakeModelLogic logic;

	SnakeGame() {
		this.setSize(325, 540);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model = new SnakeModel();
		controller = new SnakeController(model);
		this.add(controller.getPanel(), BorderLayout.CENTER);
		logic = new SnakeModelLogic(model);

		final JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		this.add(toolbar, BorderLayout.NORTH);
		setupToolBar(toolbar);
		this.requestFocus();
		resetGame();
		this.addKeyListener(keyAdapter);
		final ScoreController scoreController = new ScoreController(model.getP1());
		this.add(scoreController.getScorePanel(), BorderLayout.SOUTH);
		model.addPropertyChangeListener(SnakeModel.LIFES, lifeHandler);
	}

	private void resetGame() {
		model.getP1().clearPoints();
		model.setLifes(model.getP1(), 3);
		setupLevel(model, 0);
	}

	private void setupLevel(final SnakeModel model, final int i) {
		model.setLevel(i);

		model.getP1().getDots().clear();
		model.getP1().getDots().add(new Vector2D(15, 24));
		model.getP1().getDirection().setX(0);
		model.getP1().getDirection().setY(-1);
		model.getP1().setGrowth(5);
		timer.setDelay(Math.max(70, 150 - i * 10));

		final int mode = i % 7;
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

		final int maxItems = 10 + i * 5;
		model.setBites(randomBlocks(maxItems, bricks));
		model.setBlocks(bricks);
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
		final JButton button = new JButton("start");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				if (timer.isRunning()) {
					timer.stop();
					button.setText("continue");
				} else {
					timer.start();
					button.setText("pause");
				}
				SnakeGame.this.requestFocus();
			}
		});
		toolbar.add(button);

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
