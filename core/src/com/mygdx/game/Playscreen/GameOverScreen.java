package com.mygdx.game.Playscreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

/**
 * Created by com on 2/12/2559.
 */
public class GameOverScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;
    Texture bg;
    public SpriteBatch batch;

    public GameOverScreen(Game game){
        this.game = game;
        viewport = new FitViewport(MyGdxGame.WIDTH,MyGdxGame.HETGHT,new OrthographicCamera());
        stage = new Stage(viewport,((MyGdxGame)game).batch);
        bg = new Texture("Untitled-1.png");

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLable = new Label("GAME OVER", font);
        Label playagainLable = new Label("Click to Play Again", font);

        table.add(gameOverLable).expandX();
        table.row();
        table.add(playagainLable).expandX().padTop(10f);

        stage.addActor(table);
        batch = new SpriteBatch();
    }
    @Override
    public void show() {



    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen((MyGdxGame) game));
            dispose();
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
       batch.draw(bg,0,0);
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
        batch.dispose();

    }
}
