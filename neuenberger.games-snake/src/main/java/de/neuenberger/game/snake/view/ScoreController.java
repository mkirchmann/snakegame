package de.neuenberger.game.snake.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.neuenberger.game.snake.model.SnakePlayer;

public class ScoreController implements PropertyChangeListener {
	private final ScorePanel scorePanel;
	private final SnakePlayer snakePlayer;

	public ScoreController(final SnakePlayer p1) {
		this(new ScorePanel(), p1);
	}

	public ScoreController(final ScorePanel scorePanel, final SnakePlayer snakePlayer) {
		this.scorePanel = scorePanel;
		this.snakePlayer = snakePlayer;
		update();
		snakePlayer.addPropertyChangeListener(this);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		update();
	}

	private void update() {
		scorePanel.setLives(snakePlayer.getLifes());
		scorePanel.setScore(snakePlayer.getPoints());
	}

	public ScorePanel getScorePanel() {
		return scorePanel;
	}

}
