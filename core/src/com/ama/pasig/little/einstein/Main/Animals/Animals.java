package com.ama.pasig.little.einstein.Main.Animals;

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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by frankensteenie on 3/7/15.
 */
public class Animals implements Screen {

    static Stage stage = new Stage();

    static Image[] animals = new Image[11];
    static Image[] alphabetAnimals = new Image[11];
    static Label label;
    static Label.LabelStyle labelStyle = new Label.LabelStyle(Assets.getLabelfont(), Color.BLUE);

    static Image bg;

    public static void showquestionandanswer() {
        Randomize.setRepeatLetters(Randomize.getRepeatLetter() + 1);
        int y = 100;
        float x = 250;


        for (int i = 0; i < alphabetAnimals.length; i++) {
            alphabetAnimals[i] = new Image(Assets.lettersAnimals[i]);
            alphabetAnimals[i].setPosition(x, y);
            stage.addActor(alphabetAnimals[i]);
            stage.addAction(Actions.fadeIn(2));
            x = x + alphabetAnimals[i].getPrefWidth() + 10;
        }

        x = 375;
        for (int i = 0; i < 1; i++) {
            int index = Randomize.getRandomImage(i);
            animals[i] = new Image(Assets.animals[index]);
            animals[i].setPosition(x, 300);
            animals[i].setScale(0.5f);
            stage.addActor(animals[i]);
        }
        for(int answer = 0; answer < 11; answer++){
            dragProcess(alphabetAnimals[answer], animals, Assets.animals[answer], answer);
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

        for (int i = 0; i < 1; i++) {
            final int questionIndex = i;
            dragAndDrop.addTarget(new DragAndDrop.Target(targetImage[i]) {
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

                public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
                    getActor().setColor(Color.WHITE);
                }

                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    sourceImage.remove();
                    getActor().remove();
                        Randomize.setRandomImage(Randomize.randomizeArray(1, 11));
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new Congratulations(new Animals()));
                        Assets.musicfx[0].dispose();
                    }
            });
        }
    }

    @Override
    public void show() {
        stage.setViewport(new ExtendViewport(1280, 800));
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        bg = new Image(Assets.bg[0]);
        bg.setFillParent(true);
        stage.addActor(bg);

        label();
    }

    public static void label(){
        label = new Label("Fill in the missing letter", labelStyle);
        label.setPosition(0,630);
        label.addAction(Actions.sequence(Actions.fadeIn(1), Actions.run(new Runnable() {
            @Override
            public void run() {
                showquestionandanswer();
            }
        })));
        stage.addActor(label);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PauseScreen(new Animals()));
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
    }
}
