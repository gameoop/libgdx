package com.mygdx.game.Playscreen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by com on 2/12/2559.
 */
public class GameOverScreen implements Screen {
    private Viewport viewport;
    private Stage stage;
    private Hud hud;
   private Game game;
    private SpriteBatch batch;
    private String score;
    Texture gameover;
    Texture bg;
    Texture press;


    public GameOverScreen(Game game){
        this.game = game;
        viewport = new FitViewport(MyGdxGame.WIDTH, MyGdxGame.HETGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((MyGdxGame) game).batch);
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(),Color.BLACK);

        Table table = new Table();
        Table table2 = new Table();
        table.setPosition(0,110);
        table2.setPosition(310,300);
        table.setFillParent(true);


        score=hud.score.toString();


        Label highscore = new Label("HighScore ", font);
        highscore.setFontScale(2);
        table.row();
        table.add(highscore).expandX().padTop(10f);
        Label iscore = new Label(score,font);
        iscore.setFontScale(2);
        table2.row();
        table2.add(iscore).expandX().padTop(10f);
        stage.addActor(table);
        stage.addActor(table2);
        batch = new SpriteBatch();
        bg = new Texture("stategameover.png");
        press = new Texture("pressplayagain.png");
        gameover = new Texture("gameover.png");



    }
    @Override
    public void show() {



    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new MenuScreen((Game) game));
            dispose();
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(bg,0,0);
        batch.draw(gameover,50,150);
        batch.draw(press,80,40);
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
