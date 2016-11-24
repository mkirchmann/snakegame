package de.neuenberger.game.snake.model;

import java.util.ArrayList;
import java.util.List;

public class Vector2D {
	int x;
	int y;
	
	/**
	 * @param x
	 * @param y
	 */
	public Vector2D(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	public void rotate(int direction) {
		int oldx = x;
		int oldy = y;
		x = oldy * direction;
		y = -oldx * direction;
	}
	
	
	public static Integer hitTest(List<Vector2D> list, Vector2D point, int offset) {
		return hitTest(list, point, offset, Integer.MAX_VALUE);
	}
	
	public static Integer hitTest(List<Vector2D> list, Vector2D point, int offset, int maxTest) {
		Integer result = null;
		if (list!=null) {
			for (int i=offset; i<list.size() && i<maxTest; i++) {
				Vector2D vektor = list.get(i);
				
				if (vektor.equals(point)) {
					result = i;
					break;
				}
			}
		}
		return result;
	}
	
	public Vector2D cloneMove(Vector2D move) {
		return new Vector2D(x+move.x,y+move.y);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vektor [x=" + x + ", y=" + y + "]";
	}

	public static List<Vector2D> move(List<Vector2D> list, Vector2D move) {
		List<Vector2D> listOfVectors = new ArrayList<Vector2D>(list.size());
		for (Vector2D vector2d : list) {
			listOfVectors.add(vector2d.cloneMove(move));
		}
		return listOfVectors;
	}
	
	
}
