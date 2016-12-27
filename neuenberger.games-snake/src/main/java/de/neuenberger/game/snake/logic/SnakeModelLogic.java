package de.neuenberger.game.snake.logic;

import java.util.List;

import de.neuenberger.game.snake.model.SnakeModel;
import de.neuenberger.game.snake.model.SnakePlayer;
import de.neuenberger.game.snake.model.Vector2D;

public class SnakeModelLogic {
	SnakeModel model;

	public SnakeModelLogic(final SnakeModel model) {
		this.model = model;
	}

	public void nextTick() {
		nextTick(model.getP1());
		if (model.isTwoPlayerGame()) {
			nextTick(model.getP2());
		}
	}

	public void nextTick(final SnakePlayer p) {
		final List<Vector2D> dots = p.getDots();

		movePlayer(p);
		final Vector2D head = dots.get(0);
		final Integer hitBite = Vector2D.hitTest(model.getBites(), head, 0, 10);
		if (hitBite != null) {
			p.setGrowth(3);
			model.getBites().remove((int) hitBite);
			p.addPoints(1);
		}
		final Integer hitBlock = Vector2D.hitTest(model.getBlocks(), head, 0);
		final boolean outOfBounds = head.getX() < 0 || head.getY() < 0 || head.getX() >= model.getWidth()
				|| head.getY() >= model.getHeight();

		final Integer hitSelf = Vector2D.hitTest(p.getDots(), head, 1);
		if (outOfBounds || hitBlock != null || hitSelf != null) {
			model.setLifes(p, p.getLifes() - 1);
		}
	}

	private void movePlayer(final SnakePlayer p) {
		final List<Vector2D> dots = p.getDots();
		final Vector2D vektor = dots.get(0);
		final Vector2D newHead = new Vector2D(vektor.getX() + p.getDirection().getX(), vektor.getY()
				+ p.getDirection().getY());
		dots.add(0, newHead);
		if (p.getGrowth() > 0) {
			p.setGrowth(p.getGrowth() - 1);
		} else {
			dots.remove(dots.size() - 1);
		}

	}
}
