package com.ama.pasig.little.einstein.Main.Coloring;

import com.ama.pasig.little.einstein.Assets.Assets;
import com.ama.pasig.little.einstein.Assets.Variables;
import com.ama.pasig.little.einstein.Main.Randomize;
import com.ama.pasig.little.einstein.PauseScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class ColorBookAnswers implements Screen {

    static Stage stage = new Stage();

    public ColorBookAnswers() {
        stage.clear();
        stage = new Stage();
    }

    Image[] coloredImage = new Image[10];
    Image[] testImage = new Image[4];

    private void uiComponents() {
        int[] xAxis = {480, 760, 480, 760};
        int[] yAxis = {200, 200, 440, 440};

        int[] randomIndexes = Randomize.randomizeArray(0, 9);
        int[] randomColorIndex = Randomize.randomizeArray(0, 3);

        for (int i = 0; i < 4; i++) {
            int index = randomIndexes[i];

            coloredImage[index] = new Image(Assets.color[index]);
            testImage[i] = coloredImage[index];
            testImage[i].setScale(.8f);

            float imageWidth = testImage[i].getPrefWidth() / 2;
            float imageHeight = testImage[i].getPrefHeight() / 2;

            float x = xAxis[i] - (imageWidth * .8f);
            float y = yAxis[i] - (imageHeight * .8f);

            testImage[i].setPosition(x, y);
            stage.addActor(testImage[i]);

        }
        Label.LabelStyle labelStyle = new Label.LabelStyle(Assets.getLabelfont(), Color.LIGHT_GRAY);
        String test = ("Click the color " + Variables.color[randomIndexes[randomColorIndex[0]]] + ". ");

        final Label questionLabel = new Label(test, labelStyle);
        questionLabel.setPosition((620 - (questionLabel.getPrefWidth() / 2)), 550);
        stage.addActor(questionLabel);

        addAnswerListener(randomIndexes[randomColorIndex[0]], questionLabel);

    }

    private void wrongAnswerListener(final int index, final Label label) {
        for (int i = 0; i < 10; i++) {
            if ((i != index) && (coloredImage[i] != null)) {
                final int instance = i;
                coloredImage[i].addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        label.setText("This is not the color " + Variables.color[index]);
                        label.setPosition((620 - (label.getPrefWidth() / 2)), 550);

                        for (int i = 0; i < 4; i++) {
                            testImage[i].remove();
                        }
                        float imageWidth = coloredImage[instance].getPrefWidth() * 1.5f;

                        float xAxis = 620 - (imageWidth / 2);

                        coloredImage[instance].setScale(1.5f);
                        coloredImage[instance].setPosition(xAxis, 80);
                        stage.addActor(coloredImage[instance]);

                        redo();
                    }
                });
            }
        }
    }

    private void addAnswerListener(final int index, final Label label) {
        coloredImage[index].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                label.setText("Good job");
                label.setPosition((620 - (label.getPrefWidth() / 2)), 550);

                for (int i = 0; i < 4; i++) {
                    testImage[i].remove();
                }
                float imageWidth = coloredImage[index].getPrefWidth() * 1.5f;

                float xAxis = 620 - (imageWidth / 2);

                coloredImage[index].setScale(1.5f);
                coloredImage[index].setPosition(xAxis, 80);
                stage.addActor(coloredImage[index]);

                redo();
            }
        });

        wrongAnswerListener(index, label);
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
                ((Game) Gdx.app.getApplicationListener()).setScreen(new ColorBookAnswers());
            }
        });
    }


    @Override
    public void show() {
        stage.setViewport(new ExtendViewport(1240, 720));
        Gdx.input.setInputProcessor(stage);
        Image bg = new Image(Assets.bg[1]);
        bg.setFillParent(true);
        stage.addActor(bg);

        uiComponents();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PauseScreen(new ColorBookAnswers()));
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
        if (stage != null) stage.dispose();
    }
}
