package com.mygdx.game.Playscreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Playscreen.MyGdxGame;
import com.mygdx.game.Playscreen.PlayScreen;

/**
 * Created by com on 12/12/2559.
 */
public class MenuScreen implements Screen{
    private Viewport viewport;
    private Stage stage;

    private Game game;
    private SpriteBatch batch;
    Texture start;



    public MenuScreen(Game game){
        this.game = game;
        viewport = new FitViewport(MyGdxGame.WIDTH,MyGdxGame.HETGHT,new OrthographicCamera());
        stage = new Stage(viewport,((MyGdxGame)game).batch);


        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

       // Label gameOverLable = new Label("START", font);
        Label playagainLable = new Label("Press SPACE to play ", font);

        //table.add(gameOverLable).expandX();
        table.row();
        table.add(playagainLable).expandX().padTop(10f);

        stage.addActor(table);
        batch = new SpriteBatch();
        start = new Texture("startgame.png");

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new PlayScreen((MyGdxGame) game));
            dispose();
        }
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(start,50,250);
        batch.end();
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {

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

        stage.dispose();


    }
}
