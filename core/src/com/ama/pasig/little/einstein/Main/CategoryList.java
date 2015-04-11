package com.ama.pasig.little.einstein.Main;

import com.ama.pasig.little.einstein.Assets.Assets;
import com.ama.pasig.little.einstein.Main.Alphabet.Letters;
import com.ama.pasig.little.einstein.Main.Animals.Animals;
import com.ama.pasig.little.einstein.Main.Coloring.ColorBookAnswers;
import com.ama.pasig.little.einstein.Main.Fruits.Fruits;
import com.ama.pasig.little.einstein.Main.Numbers.NumberCounting;
import com.ama.pasig.little.einstein.Main.Numbers.NumbersDND;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by iamAxylle on 3/3/2015.
 */
public class CategoryList implements Screen {

    Sprite background, Shapes, Alphabet, Numbers, color, fruits, animal;
    private Stage stage;
    private Label category;
    private Label.LabelStyle labelStyle = new Label.LabelStyle(Assets.getLabelfont(), Color.BLACK);

    @Override
    public void show() {
        stage = new Stage();
        stage.setViewport(new ExtendViewport(1280, 800));
        Gdx.input.setInputProcessor(stage);

        background = new Sprite(Assets.bg[8]);
        Image BACK = new Image(background);
        BACK.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        BACK.setPosition(0,0);
        BACK.setFillParent(true);
        stage.addActor(BACK);

        showText();
    }

    private void showText(){
        category = new Label("Category List", labelStyle);
        category.setPosition(0, 650);
        category.addAction(Actions.sequence(Actions.moveTo(300,650, 1f), Actions.run(new Runnable() {
            @Override
            public void run() {
                showList();
            }
        })));
        stage.addActor(category);
    }

    private void showList(){
        fruits = new Sprite(Assets.buttonCategory[1]);
        final Image prutas = new Image(fruits);
        prutas.setPosition(100, 450);
        prutas.addAction(Actions.sequence(Actions.fadeIn(2)));
        prutas.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Randomize.setRandomImage(Randomize.randomizeArray(1, 7));
                prutas.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new Fruits());
                    }
                })));
            }
        });
        stage.addActor(prutas);

        Shapes = new Sprite(Assets.buttonCategory[4]);
        final Image Shape = new Image(Shapes);
        Shape.setPosition(271, 250);
        Shape.addAction(Actions.sequence(Actions.fadeIn(2)));
        Shape.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Shape.addAction(Actions.sequence(Actions.fadeOut(2), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new NumberCounting());
                    }
                })));
            }
        });
        stage.addActor(Shape);

        animal = new Sprite(Assets.buttonCategory[0]);
        final Image hayop = new Image(animal);
        hayop.setPosition(451, 450);
        hayop.addAction(Actions.sequence(Actions.fadeIn(2)));
        hayop.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Randomize.setRandomImage(Randomize.randomizeArray(1, 11));
                hayop.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new Animals());
                    }
                })));
            }
        });
        stage.addActor(hayop);

        color = new Sprite(Assets.buttonCategory[5]);
        final Image Color = new Image(color);
        Color.setPosition(656, 250);
        Color.addAction(Actions.sequence(Actions.fadeIn(2)));
        Color.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Randomize.setRandomImage(Randomize.randomizeArray(1, 7));
                Color.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new ColorBookAnswers());
                    }
                })));
            }
        });
        stage.addActor(Color);

        Alphabet = new Sprite(Assets.buttonCategory[2]);
        final Image Letter = new Image(Alphabet);
        Letter.setPosition(836,450);
        Letter.addAction(Actions.sequence(Actions.fadeIn(2)));
        Letter.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Randomize.setRandomNumbers(Randomize.randomizeArray(1, 26));
                Letter.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new Letters());
                    }
                })));
            }
        });
        stage.addActor(Letter);

        Numbers = new Sprite(Assets.buttonCategory[3]);
        final Image Num = new Image(Numbers);
        Num.setPosition(1016, 250);
        Num.addAction(Actions.sequence(Actions.fadeIn(2)));
        Num.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Randomize.setRandomNumbers(Randomize.randomizeArray(1, 10));
                Num.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new NumbersDND());
                    }
                })));
            }
        });
        stage.addActor(Num);
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
