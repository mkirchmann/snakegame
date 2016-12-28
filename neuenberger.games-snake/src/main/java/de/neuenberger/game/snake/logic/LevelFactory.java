package de.neuenberger.game.snake.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.neuenberger.game.snake.model.Vector2D;

public class LevelFactory {

	private static final int GAME_PANE_WIDTH = 30;
	private static final int GAME_PANE_HEIGHT = 48;

	public List<Vector2D> getLevelBricks(final int level) {
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
}
