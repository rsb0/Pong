package com.pong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.sprites.Ball;
import com.pong.game.sprites.LeftPaddle;
import com.pong.game.sprites.RightPaddle;
import com.pong.game.states.EndState;
import com.pong.game.states.GameStateManager;
import com.pong.game.states.MenuState;

import java.awt.Menu;
import java.awt.MenuShortcut;

//EXERCISE TASK 4

//main class of the game
public class Pong extends ApplicationAdapter {
	public static final int HEIGHT = 620;
	public static final int WIDTH = 600;
	public static final String TITLE = "Task 4: Pong";
	private SpriteBatch batch;
	private GameStateManager gsm;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 1);

		//start by adding new MenuSate to the top of GameStateManager's state-stack.
		gsm.push(new MenuState(gsm));

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//GameStateManager will call the update method of the state at the top of it's state-stack
		gsm.update(Gdx.graphics.getDeltaTime());

		//GameStateManager will call the render method of the state at the top of it's state-stack
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
