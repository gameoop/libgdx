package com.mygdx.game.Playscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Sprites.*;
import com.sun.org.apache.xpath.internal.operations.Bool;


public class PlayScreen implements Screen {

    private MyGdxGame game;
    private TextureAtlas atlas;

    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Hud hud;

        private TmxMapLoader mapLoader;
        private TiledMap map;
        private OrthogonalTiledMapRenderer renderer;

        //box2d variable
        private World world;
        private Box2DDebugRenderer b2dr;
        private B2WorldCreator creator;
        public int checkscore;


    //sprite
        private Boy player;

    private Music music;



    //constructor
    public PlayScreen(MyGdxGame game){
        atlas = new TextureAtlas("player.atlas");
        this.game = game;


        gamecam = new OrthographicCamera();
        gameport = new FitViewport(MyGdxGame.WIDTH/MyGdxGame.PPM,MyGdxGame.HETGHT/MyGdxGame.PPM,gamecam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("bgone.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1/MyGdxGame.PPM);
        gamecam.position.set(gameport.getWorldWidth()/2, gameport.getWorldHeight()/2,0);

        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();
        player = new Boy(this);

        world.setContactListener(new WorldContactListener());

        creator = new  B2WorldCreator(this);

        music = MyGdxGame.manager.get("audio/Music/273539__tristan-lohengrin__8bit-introduction.wav", Music.class);
        music.setLooping(true);
        music.play();


    }

    public TextureAtlas getAtlas(){
        return atlas;
    }
    @Override
    public void show() {

    }

    public void handleInput(float dt) {

        if(player.currentState != Boy.State.DEAD) {
            if(player.b2body.getLinearVelocity().y==0)
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                player.b2body.applyLinearImpulse(new Vector2(0, 5f), player.b2body.getWorldCenter(), true);;
                MyGdxGame.manager.get("audio/sound/262893__kwahmah-02__videogame-jump.wav", Sound.class).play();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
                player.b2body.applyLinearImpulse(new Vector2(0.09f, 0), player.b2body.getWorldCenter(), true);

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
                player.b2body.applyLinearImpulse(new Vector2(-0.09f, 0), player.b2body.getWorldCenter(), true);
        }
    }

    public void update(float dt){
        handleInput(dt);

        System.out.println();
        player.update(dt);
        world.step(1/60f,6,2);
        hud.update(dt);



        if(hud.worldTimer == 0 ||  hud.heart == 0){
           Boy.die();
        }


        player.update(dt);


        for( enemy enemy : creator.getCandies()) {
            enemy.update(dt);
            if(enemy.getX() < player.getX() + 224 / MyGdxGame.PPM) {
                enemy.b2body.setActive(true);
            }
        }


        for(Item item : creator.getItem1s())
            item.update(dt);
        for(Item item : creator.getItem2s())
            item.update(dt);

        gamecam.update();
        renderer.setView(gamecam);

    }



    @Override
    public void render(float delta) {


        update(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        b2dr.render(world,gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);


        //enemy
        for(enemy enemy : creator.getCandies())
            enemy.draw(game.batch);

        //item
        for(Item item : creator.getItem1s())
           item.draw(game.batch);

        for(Item item : creator.getItem2s())
            item.draw(game.batch);


        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();



        if(gameOver()){
            game.setScreen(new GameOverScreen(game));
        }
        if(nextScreen()){
            hud.score=checkscore;
            if(map!=null){
                map=mapLoader.load("bgtwo.tmx");
                renderer = new OrthogonalTiledMapRenderer(map,1/MyGdxGame.PPM);
                gamecam.position.set(gameport.getWorldWidth()/2, gameport.getWorldHeight()/2,0);

                world = new World(new Vector2(0,-10),true);
                b2dr = new Box2DDebugRenderer();
                player = new Boy(this);

                world.setContactListener(new WorldContactListener());

                creator = new  B2WorldCreator(this);
            }
        }
        if(nextScreen2()){
            hud.score=checkscore;
            if(map!=null){
                map=mapLoader.load("bgone.tmx");
                renderer = new OrthogonalTiledMapRenderer(map,1/MyGdxGame.PPM);
                gamecam.position.set(gameport.getWorldWidth()/2, gameport.getWorldHeight()/2,0);

                world = new World(new Vector2(0,-10),true);
                b2dr = new Box2DDebugRenderer();
                player = new Boy(this);

                world.setContactListener(new WorldContactListener());

                creator = new  B2WorldCreator(this);
            }
        }
    }



    public boolean gameOver(){
        if(player.currentState == Boy.State.DEAD && player.getStateTimer() > 1 ){
            MyGdxGame.manager.get("audio/sound/159408__noirenex__life-lost-game-over.wav", Sound.class).play();
            return true;
        }
        return false;
    }
    public Boolean nextState2 = true ;
    public Boolean nextState3 = true ;
    public boolean nextScreen() {
        checkscore = hud.score;
        if (checkscore==1000&&nextState2) {
            hud.score=0;
            nextState2 = false;
            return true;

        }
        return false;

    }
    public boolean nextScreen2() {
        checkscore = hud.score;
        if (checkscore==2000&&nextState3){
            hud.score=0;
            nextState3 = false;
            return true;
        }
        return false ;

    }


    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);

    }
    public TiledMap getMap(){
        return map;
    }
    public World getWorld(){
        return world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {


    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
    public Hud getHud(){ return hud; }


}
