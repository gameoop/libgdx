package com.mygdx.game.Playscreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MenuScreen;
import com.mygdx.game.Playscreen.PlayScreen;

public class MyGdxGame extends Game {

	public static final int WIDTH = 640;
	public static final int HETGHT = 480;
	public static final float PPM = 100;

	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short BOY_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ITEM_BIT = 256;
	public static final short HEART_BIT = 256;


	public SpriteBatch batch;



	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
		setScreen(new MenuScreen(this));



	}

	@Override
	public void render () {
   	 	 super.render();


	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();

	}
}
