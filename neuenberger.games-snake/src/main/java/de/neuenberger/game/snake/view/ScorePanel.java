package de.neuenberger.game.snake.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	private final JLabel lLives;
	private final JLabel lPoints;

	public static final ImageIcon imageApple = getImage("apple.png");
	public static final ImageIcon imageHeart = getImage("heart.png");

	private static ImageIcon getImage(final String string) {
		final ImageIcon imageIcon = new ImageIcon(ScoreController.class.getResource("res/" + string));
		return imageIcon;
	}

	public ScorePanel() {
		this.setLayout(new GridBagLayout());
		lLives = new JLabel();
		lPoints = new JLabel();
		lLives.setIcon(imageHeart);
		lPoints.setIcon(imageApple);
		final GridBagConstraints lifesGbc = createDefaultScoreGridBagConstraints();
		lifesGbc.gridx = 0;
		this.add(lLives, lifesGbc);
		final GridBagConstraints pointsGbc = createDefaultScoreGridBagConstraints();
		pointsGbc.gridx = 1;
		this.add(lPoints, pointsGbc);
	}

	private GridBagConstraints createDefaultScoreGridBagConstraints() {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		return gbc;
	}

	public void setScore(final int score) {
		lPoints.setText("" + score);
	}

	public void setLives(final int lives) {
		lLives.setText("" + lives);
	}
}
