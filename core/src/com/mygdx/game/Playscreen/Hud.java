package com.mygdx.game.Playscreen;

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

    public static Integer score;
    public  static Integer heart;
    public Integer worldTimer;
    private float timeCount;

    static Label  scoreLable;

    Label heartLabel;
    Label countDownLabel;
    Label GdxLable;
    Label timeLabel;
    static Label countDownHeartLabel;



    public Hud(SpriteBatch sb){
        worldTimer = 100;
        timeCount = 0;
        score = 0;
        heart = 3;
        viewport = new FitViewport(MyGdxGame.WIDTH,MyGdxGame.HETGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%02d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreLable = new Label(String.format("%04d",score),new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        GdxLable = new Label("SCORE",new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        countDownHeartLabel = new Label(String.format("x"+"%01d",heart),new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        heartLabel  = new Label("HEART",new Label.LabelStyle(new BitmapFont(), Color.BLACK));




        table.add(GdxLable).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(heartLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLable).expandX();
        table.add(countDownLabel).expandX();
        table.add(countDownHeartLabel).expandX();


        stage.addActor(table);

    }
    public static void addScore(int value){
        score += value;
        scoreLable.setText(String.format("%04d",score));


    }

    public static void Lift(int value){
        heart += value;
        countDownHeartLabel.setText(String.format("x"+"%01d",heart));

    }
    public void update(float dt){
        timeCount += dt;
        if(timeCount>=1){
            worldTimer--;
            countDownLabel.setText(String.format("%02d",worldTimer));
            timeCount = 0;
        }

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
