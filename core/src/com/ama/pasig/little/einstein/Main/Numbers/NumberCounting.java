package com.ama.pasig.little.einstein.Main.Numbers;

import com.ama.pasig.little.einstein.Assets.Assets;
import com.ama.pasig.little.einstein.Helper.Randomizer;
import com.ama.pasig.little.einstein.Main.Congratulations;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

/**
 * Created by frankensteenie on 3/24/2015.
 */
public class NumberCounting implements Screen {
    static Stage stage = new Stage();
    static Random random = new Random();

    private static void showBoxes(TextureRegion textureRegion) {
        int[] randomNumber = Randomizer.randmizeArray(0, 7);
        int[] xAxis = {90, 227, 364, 501, 638, 775, 912, 1049};
        int[] yAxis = {200, 300, 350, 400, 450, 500, 550, 600};
        int index;
        for (int i = 0; i < 8; i++) {
            index = randomNumber[i];
            int y = random.nextInt(8);
            int x = random.nextInt(8);
            Image image = new Image(Assets.shapes[i]);
            image.setPosition(xAxis[index], yAxis[index]);
            image.setScale(0.8f);
            stage.addActor(image);
        }
        showAnswers(Assets.shapes[0]);
    }

    private static void showAnswers(TextureRegion region) {
        float x = 20f;
        float y = 50f;
        for (int i = 0; i < 8; i++) {
            Image image = new Image(Assets.shapes[i]);
            image.setPosition(x, y);
            image.setScale(0.8f);
            stage.addActor(image);
            x = x + image.getImageWidth() + 170f;
        }
    }

    private void uiComponents() {
        Image bg = new Image(Assets.bg[7]);
        bg.setFillParent(true);
        stage.addActor(bg);

        uiQuestions();
        uiAnswers();
        redo();
    }

    Image[] questionImages = new Image[8];

    private void uiQuestions() {
        int[] randomQuestionIndex = Randomizer.randmizeArray(0, 7);
        int[] xAxis = {182, 324, 466, 608, 750, 892, 1034, 1176};

        int[] yAxis3 = {550, 500, 550, 500, 550, 500, 550, 500};
        //int index;

        for (int i = 0; i < 8; i++) {
            int yAxis = 450 + (new Random().nextInt(50));
            int xAxis2 = 182 + (new Random().nextInt(954));
            int index = randomQuestionIndex[i];
            questionImages[index] = new Image(Assets.shapes[index]);
            float x = xAxis[i] - ((questionImages[index].getPrefWidth() * .8f) / 2);
            float y = yAxis3[i] - ((questionImages[index].getPrefHeight() * .8f) / 2);
            questionImages[index].setPosition(x, y);
            questionImages[index].setScale(0.8f);
            stage.addActor(questionImages[index]);
        }
        //showAnswers(Assets.shapes[0]);
    }

    private void uiAnswers() {
        //int[] xAxis = {182, 324, 466, 608, 750, 892, 1034, 1176};
        int[] xAxis = {305, 410, 515, 620, 725, 830, 935, 1040};

        for (int i = 0; i < 8; i++) {
            Image image = new Image(Assets.shapes[i]);
            float x = xAxis[i] - ((image.getPrefWidth() * .5f) / 2);
            float y = 230 - ((image.getPrefHeight() * .5f) / 2);
            image.setPosition(x, y);
            image.setScale(0.5f);
            stage.addActor(image);

            dragProcess(image, questionImages, Assets.shapes[i], i);
        }
    }

    private void redo() {
        Skin skin = new Skin(Gdx.files.internal("JSON/uiskin.json"));
        skin.getFont("default-font").setScale(4f);
        TextButton textButton = new TextButton("REDO", skin);
        textButton.setPosition(10, 10);
        stage.addActor(textButton);

        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new NumberCounting());
            }
        });
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

        for (int i = 0; i < 8; i++) {
            final int questionIndex = i;
            dragAndDrop.addTarget(new DragAndDrop.Target(targetImage[i]) {
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    if (index == questionIndex) {
                        if(Settings.sounds){
                            long id = Assets.soundfx[0].play();
                            Assets.soundfx[0].setLooping(id, false);
                        } else {
                            Assets.soundfx[0].pause();
                        }
                        return true;
                    } else {
                        if(Settings.sounds){
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
                    if (counter == 8) {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new Congratulations(new NumberCounting()));
                        counter = 0;
                    }
                }
            });
        }
    }

    static int counter = 0;

    @Override
    public void show() {
        stage.setViewport(new ExtendViewport(1280, 800));
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        uiComponents();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PauseScreen(new NumberCounting()));
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
