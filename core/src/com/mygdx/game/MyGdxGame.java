package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {

	public static final int WIDTH = 640;
	public static final int HETGHT = 480;
	public static final float PPM = 100;

	public static final short GROUND_BIT = 1;
	public static final short BOY_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 62;






	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();


	}
	
	@Override
	public void dispose () {

	}
}
