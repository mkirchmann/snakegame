package de.neuenberger.game.snake.logic;

import java.util.List;

import de.neuenberger.game.snake.model.SnakeModel;
import de.neuenberger.game.snake.model.SnakePlayer;
import de.neuenberger.game.snake.model.Vector2D;

public class SnakeModelLogic {
	SnakeModel model;
	public SnakeModelLogic(SnakeModel model) {
		this.model = model;
	}
	
	public void nextTick() {
		nextTick(model.getP1());
	}
	
	public void nextTick(SnakePlayer p) {
		List<Vector2D> dots = p.getDots();
		
		movePlayer(p);
		Vector2D head = dots.get(0);
		Integer hitBite = Vector2D.hitTest(model.getBites(), head, 0, 10);
		if (hitBite!=null) {
			p.setGrowth(3);
			model.getBites().remove((int)hitBite);
		}
		Integer hitBlock = Vector2D.hitTest(model.getBlocks(), head, 0);
		boolean outOfBounds = head.getX()<0 || head.getY()<0 || head.getX()>=model.getWidth() || head.getY()>=model.getHeight();
		
		Integer hitSelf = Vector2D.hitTest(model.getP1().getDots(), head, 1);
		if (outOfBounds || hitBlock!=null || hitSelf!=null) {
			model.setLifes(p, p.getLifes()-1);
		}
	}
	
	private void movePlayer(SnakePlayer p) {
		List<Vector2D> dots = p.getDots();
		Vector2D vektor = dots.get(0);
		Vector2D newHead = new Vector2D(vektor.getX()+p.getDirection().getX(), vektor.getY()+p.getDirection().getY());
		dots.add(0,newHead);
		if (p.getGrowth()>0) {
			p.setGrowth(p.getGrowth()-1);
		} else {
			dots.remove(dots.size()-1);
		}
		
	}
}
