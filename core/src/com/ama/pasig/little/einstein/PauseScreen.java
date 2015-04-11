package com.ama.pasig.little.einstein;

import com.ama.pasig.little.einstein.Assets.Assets;
import com.ama.pasig.little.einstein.Main.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by frankensteenie on 3/29/2015.
 */
public class PauseScreen implements Screen {
    Screen screen;
    static Stage stage;
    Image resume, home, exit, bg;

    public PauseScreen(Screen screen){
        this.screen = screen;
    }

    @Override
    public void show() {
        stage = new Stage();
        stage.setViewport(new ExtendViewport(1280,800));
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        bg = new Image(Assets.bg[3]);
        bg.setFillParent(true);
        stage.addActor(bg);

        home = new Image(Assets.btn[3]);
        home.setPosition(300,300);
        home.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
            }
        });
        stage.addActor(home);

        resume = new Image(Assets.btn[5]);
        resume.setPosition(100,100);
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game) Gdx.app.getApplicationListener()).setScreen(screen);
            }
        });
        stage.addActor(resume);

        exit = new Image(Assets.btn[10]);
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
        stage.addActor(exit);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
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
        if(stage!=null)
            stage.dispose();
    }
}
