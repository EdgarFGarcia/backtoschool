package com.ama.pasig.little.einstein.Main;

import com.ama.pasig.little.einstein.Assets.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by frankensteenie on 3/19/2015.
 */
public class Congratulations implements Screen {

    private Stage stage = new Stage();
    private Image congrats, next, home;
    Screen screen;

    public Congratulations(Screen screen){
        this.screen = screen;
//        if(Settings.music) {
//            Assets.musicfx[0].play();
//            Assets.musicfx[0].setLooping(true);
//        }else {
//            Assets.musicfx[0].pause();
//        }
        Assets.musicfx[0].play();
        Assets.musicfx[0].setLooping(true);
    }

    @Override
    public void show() {
        stage.setViewport(new ExtendViewport(1280, 800));
        Gdx.input.setInputProcessor(stage);

        congrats = new Image(Assets.btn[2]);
        congrats.setPosition(560,300);
        stage.addActor(congrats);

        next = new Image(Assets.btn[3]);
        next.setPosition(900,200);
        next.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game) Gdx.app.getApplicationListener()).setScreen(screen);
                Assets.musicfx[0].dispose();
            }
        });
        stage.addActor(next);

        home = new Image(Assets.btn[11]);
        home.setPosition(250, 200);
        home.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new CategoryList());
                Assets.musicfx[0].dispose();
            }
        });
        stage.addActor(home);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        if (stage != null)
            stage.dispose();
    }
}
