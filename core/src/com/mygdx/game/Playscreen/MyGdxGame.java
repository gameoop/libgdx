package com.mygdx.game.Playscreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    public static AssetManager manager;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/Music/273539__tristan-lohengrin__8bit-introduction.wav", Music.class);
		manager.load("audio/sound/242857__plasterbrain__coin-get.ogg", Sound.class);
		manager.load("audio/sound/262893__kwahmah-02__videogame-jump.wav", Sound.class);
		manager.load("audio/sound/159408__noirenex__life-lost-game-over.wav", Sound.class);
		manager.load("audio/sound/350925__cabled-mess__hurt-c-08.wav", Sound.class);

		manager.finishLoading();
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
