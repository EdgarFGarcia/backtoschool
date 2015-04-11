package com.ama.pasig.little.einstein.Main.Alphabet;

import com.ama.pasig.little.einstein.Assets.Assets;
import com.ama.pasig.little.einstein.Main.Congratulations;
import com.ama.pasig.little.einstein.Main.Home;
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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by iamAxylle on 3/3/2015.
 */
public class Letters implements Screen {

    static Stage stage = new Stage();
    static Image[] letter = new Image[26];
    static Sprite[] letterSprite = new Sprite[26];
    static Image[] question = new Image[26];
    static Sprite[] questionSprite = new Sprite[26];
    static Image Rect, Background;
    static int counter = 0;

    static void showLetters() {
        Randomize.setRepeatLetters(Randomize.getRepeatLetter() + 1);
        int yAxis = 100;
        int xAxis = 0;
        counter = 0;
        // Displaying Choices
        for (int i = 0; i < letter.length; i++) {
            if (i == 13) {
                yAxis = 10;
                xAxis = 0;
            }

            letterSprite[i] = new Sprite(Assets.letter[i]);
            letter[i] = new Image(letterSprite[i]);
            letter[i].setPosition(15 + (xAxis * 95), yAxis);
            stage.addActor(letter[i]);
            xAxis++;
        }

        // Displaying Questions
        xAxis = 375;
        for (int i = 0; i < 5; i++) {
            questionSprite[Randomize.getRandomNumber(i)] = new Sprite(Assets.letter[Randomize.getRandomNumber(i)]);
            question[Randomize.getRandomNumber(i)] = new Image(questionSprite[Randomize.getRandomNumber(i)]);
            question[Randomize.getRandomNumber(i)].setPosition(xAxis, 400);
            stage.addActor(question[Randomize.getRandomNumber(i)]);
            xAxis = xAxis + 120;
            dragProcess(letter[Randomize.getRandomNumber(i)], question[Randomize.getRandomNumber(i)], Assets.letter[Randomize.getRandomNumber(i)], true);
        }
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < letter.length; k++) {
                if (k != Randomize.getRandomNumber(i)) {
                    dragProcess(letter[k], question[Randomize.getRandomNumber(i)], Assets.letter[k], false);
                }
            }
        }
    }

    private static void dragProcess(final Image sourceImage, final Image targetImage, final TextureRegion draggedImage, final boolean imageMatch) {
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

        dragAndDrop.addTarget(new DragAndDrop.Target(targetImage) {
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                if (imageMatch) {
                    //getActor().setColor(Color.GRAY);
                    long id = Assets.soundfx[0].play();
                    Assets.soundfx[0].setLooping(id, false);
                    return true;
                } else {
                    long id = Assets.soundfx[1].play();
                    Assets.soundfx[1].setLooping(id, false);
                    Assets.soundfx[1].setVolume(id, 0.5f);
                    Assets.soundfx[1].play();
                    return false;
                }
            }

            public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
                getActor().setColor(Color.WHITE);
            }

            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                getActor().setPosition(targetImage.getX(), targetImage.getY());
                sourceImage.setPosition(targetImage.getX(), targetImage.getY());
                counter++;
                if (counter == 5) {
                    Randomize.setRandomNumbers(Randomize.randomizeArray(1, 26));
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new Congratulations(new Letters()));
                    counter = 0;
                    Assets.musicfx[0].dispose();
                }
            }
        });
    }

    @Override
    public void show() {
        //stage
        stage.setViewport(new ExtendViewport(1240, 720));
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        if (Settings.music) {
            Assets.musicfx[0].play();
            Assets.musicfx[0].setLooping(true);
        } else {
            Assets.musicfx[0].pause();
        }

        Background = new Image(Assets.bg[4]);
        Background.setPosition(0, 0);
        Background.setFillParent(true);
        stage.addActor(Background);

        Rect = new Image(Assets.bg[6]);
        Rect.setSize(1240, 200);
        Rect.setPosition(0, 0);
        stage.addActor(Rect);

        showLetters();

        Home.showHome(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PauseScreen(new Letters()));
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
        Assets.load();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if (stage != null) stage.dispose();
        if (Assets.musicfx[0] != null) Assets.musicfx[0].dispose();
        if (Assets.soundfx[0] != null) Assets.soundfx[0].dispose();
        if (Assets.soundfx[1] != null) Assets.soundfx[1].dispose();
    }
}
