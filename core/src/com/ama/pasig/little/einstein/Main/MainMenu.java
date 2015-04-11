package com.ama.pasig.little.einstein.Main;

import com.ama.pasig.little.einstein.Assets.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by frankensteenie on 3/3/2015.
 */
public class MainMenu implements Screen {

    private Stage stage = new Stage();
    private Image bg, start, setting;

    @Override
    public void show() {
        stage.setViewport(new ExtendViewport(1240, 720));
        Gdx.input.setInputProcessor(stage);

        bg = new Image(Assets.bg[3]);
        bg.setFillParent(true);
        stage.addActor(bg);

        start = new Image(Assets.btn[5]);
        start.setPosition(80, 390);
        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                stage.addAction(Actions.sequence(Actions.fadeOut(1.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new CategoryList());
                    }
                })));
            }
        });
        stage.addActor(start);

        setting = new Image(Assets.btn[4]);
        setting.setPosition(80,300);
        setting.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Settings.settings(stage);
            }
        });
        stage.addActor(setting);

        stage.addAction(Actions.fadeIn(2));
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
        if (stage != null) stage.dispose();

    }
}
