package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;

    private static Integer score;

    static Label  scoreLable;
    Label levelLable;
    Label GdxLable;
    Label StateLable;

    public Hud(SpriteBatch sb){
        score = 0;
        viewport = new FitViewport(MyGdxGame.WIDTH,MyGdxGame.HETGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLable = new Label(String.format("%04d",score),new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        levelLable = new Label("1-3",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        GdxLable = new Label("ChubbyBoy",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        StateLable =  new Label("Level",new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(GdxLable).expandX().padTop(10);
        table.add(StateLable).expandX().padTop(10);
        table.row();
        table.add(scoreLable).expandX();
        table.add(levelLable).expandX();

        stage.addActor(table);

    }
    public static void addScore(int value){
        score += value;
        scoreLable.setText(String.format("%04d",score));


    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
