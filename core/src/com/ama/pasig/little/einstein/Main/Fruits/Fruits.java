package com.ama.pasig.little.einstein.Main.Fruits;

import com.ama.pasig.little.einstein.Assets.Assets;
import com.ama.pasig.little.einstein.Main.Congratulations;
import com.ama.pasig.little.einstein.Main.Randomize;
import com.ama.pasig.little.einstein.Main.Settings;
import com.ama.pasig.little.einstein.PauseScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by frankensteenie on 3/7/15.
 */
public class Fruits implements Screen {

    static Stage stage = new Stage();
    static Image[] fruits = new Image[7];
    static Image[] fruitsNoColor = new Image[7];
    static Image rectangleBar;
    Sprite Background = new Sprite(Assets.bg[2]);

    public static void showanswerandquestion() {
        float x = 250;
        float y = 150;
        //displaying answers
        for (int i = 0; i < fruits.length; i++) {
            if(i == 3){
                x = 400;
                y = 120;
            } if( i == 6){
                x = 550;
                y = 150;
            }
            fruits[i] = new Image(Assets.fruitsWithColor[i]);
            fruits[i].setPosition(x, y);
            fruits[i].setScale(0.4f);
            stage.addActor(fruits[i]);
//            x = x + fruits[i].getPrefWidth();
            y = y + fruits[i].getPrefWidth()-3;
        }

        //displaying questions
        float xNoColor = 750;
        float yNoColor = 250;
        for (int i = 0; i < 1; i++) {
            int index = Randomize.getRandomImage(i);
            fruitsNoColor[i] = new Image(Assets.fruitsNoColor[index]);
            fruitsNoColor[i].setPosition(xNoColor, yNoColor);
            fruitsNoColor[i].getPrefWidth();
            stage.addActor(fruitsNoColor[i]);
        }

        for(int answer = 0; answer < 7; answer++){
            dragProcess(fruits[answer], fruitsNoColor, Assets.fruitsWithColor[answer], answer);
        }

    }

    private static void dragProcess(final Image sourceImage, final Image[]targetImage, final TextureRegion draggedImage, final int index) {
        DragAndDrop dragAndDrop = new DragAndDrop();
        dragAndDrop.setDragActorPosition(-(sourceImage.getWidth() / 2), sourceImage.getHeight() / 2);
        dragAndDrop.addSource(new DragAndDrop.Source(sourceImage) {
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                final Image image = new Image(draggedImage);
                payload.setDragActor(image);
                return payload;
            }
        });
        for(int i = 0; i < 1; i++){
            final int questionIndex = i;
            dragAndDrop.addTarget(new DragAndDrop.Target(targetImage[i]) {
                @Override
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    if (index == Randomize.getRandomImage(questionIndex)) {
                        getActor().setColor(Color.PINK);
                        if(Settings.sounds) {
                            long id = Assets.soundfx[0].play();
                            Assets.soundfx[0].setLooping(id, false);
                        } else {
                            Assets.soundfx[0].pause();
                        }
                        return true;
                    } else {
                        getActor().setColor(Color.BLACK);
                        if(Settings.sounds) {
                            long id = Assets.soundfx[1].play();
                            Assets.soundfx[1].setLooping(id, false);
                        } else {
                            Assets.soundfx[1].pause();
                        }
                        return false;
                    }
                }

                @Override
                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    sourceImage.remove();
                    getActor().remove();
                    Randomize.setRandomImage(Randomize.randomizeArray(1,7));
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new Congratulations(new Fruits()));
                    Assets.musicfx[0].pause();
                }
            });
        }
    }

    @Override
    public void show() {
        stage.setViewport(new ExtendViewport(1280, 800));
        Gdx.input.setInputProcessor(stage);

        Image BACK = new Image(Background);
        BACK.setPosition(0, 0);
        BACK.setFillParent(true);
        stage.addActor(BACK);

        if(Settings.music){
            Assets.musicfx[0].play();
            Assets.musicfx[0].setLooping(true);
        }else {
            Assets.musicfx[0].pause();
        }

        rectangleBar = new Image(Assets.bg[6]);
        rectangleBar.setBounds(0,0,2048,250);
        //stage.addActor(rectangleBar);

        showanswerandquestion();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PauseScreen(new Fruits()));
        }

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
        if (Assets.musicfx[0] != null)
            Assets.musicfx[0].dispose();
        if (Assets.soundfx[0] != null)
            Assets.soundfx[0].dispose();
        if (Assets.soundfx[1] != null)
            Assets.soundfx[1].dispose();
    }
}
