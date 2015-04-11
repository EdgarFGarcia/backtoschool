package com.ama.pasig.little.einstein.Main.Numbers;

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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class NumbersDND implements Screen {

    static Stage stage = new Stage();
    static Image[] number = new Image[10];
    static Image[] question = new Image[10];

    static Image bg;

    static int counter = 0;

    public static void showquestionandanswer() {
        Randomize.setRepeatLetters(Randomize.getRepeatLetter() + 1);
        int y;
        float x = 250;
        counter = 0;

        for (int i = 0; i < number.length; i++) {
            if (i % 2 == 0) {
                y = 110;
            } else {
                y = 170;
            }

            number[i] = new Image(Assets.number[i]);
            number[i].setPosition(x, y);
            number[i].setScale(0.3f);
            stage.addActor(number[i]);
            x = x + number[i].getPrefWidth() - 70;
        }

        x = 250;
        for (int i = 0; i < 5; i++) {
            int index = Randomize.getRandomNumber(i);
            question[i] = new Image(Assets.number[index]);
            question[i].setPosition(x, 400);
            stage.addActor(question[i]);
            x = x + 180;
        }

        for (int answerI = 0; answerI < 10; answerI++) {
            dragProcess(number[answerI], question, Assets.number[answerI], answerI);
        }
    }

    private static void dragProcess(final Image sourceImage, final Image[] targetImage, final TextureRegion draggedImage, final int index) {
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

        for (int i = 0; i < 5; i++) {
            final int questionIndex = i;
            dragAndDrop.addTarget(new DragAndDrop.Target(targetImage[i]) {
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    if (index == Randomize.getRandomNumber(questionIndex)) {
                        getActor().setColor(Color.PINK);
                        if (Settings.sounds) {
                            long id = Assets.soundfx[0].play();
                            Assets.soundfx[0].setLooping(id, false);
                        } else {
                            Assets.soundfx[0].pause();
                        }
                        return true;
                    } else {
                        getActor().setColor(Color.BLACK);
                        if (Settings.sounds) {
                            long id = Assets.soundfx[1].play();
                            Assets.soundfx[1].setLooping(id, false);
                        } else {
                            Assets.soundfx[1].pause();
                        }
                        return false;
                    }
                }

                public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
                    getActor().setColor(Color.WHITE);
                }

                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    sourceImage.remove();
                    getActor().remove();
                    counter++;
                    if (counter == 5) {
                        Randomize.setRandomNumbers(Randomize.randomizeArray(1, 10));
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new Congratulations(new NumbersDND()));
                        counter = 0;
                        Assets.musicfx[0].dispose();
                    }
                }
            });
        }
    }

    @Override
    public void show() {
        stage.setViewport(new ExtendViewport(1200, 800));
        Gdx.input.setInputProcessor(stage);

        bg = new Image(Assets.bg[5]);
        bg.setFillParent(true);
        stage.addActor(bg);

        if (Settings.music) {
            Assets.musicfx[0].play();
            Assets.musicfx[0].setLooping(true);
        } else {
            Assets.musicfx[0].pause();
        }
        showquestionandanswer();
        Home.showHome(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PauseScreen(new NumbersDND()));
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
        ((Game) Gdx.app.getApplicationListener()).setScreen(new Congratulations(new NumbersDND()));
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