package com.ama.pasig.little.einstein.Main;

import com.ama.pasig.little.einstein.Assets.Assets;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by frankensteenie on 3/20/2015.
 */
public class Settings extends Stage {
    public static Image musicOn, musicOff, soundOn, soundOff, exitt;
    static Stage stage = new Stage();
    public static boolean sounds = true;
    public static boolean music = true;

    public static void settings(final Stage stage){
        Settings.stage = stage;
        musicoff();
        soundoff();
        Exit();
    }

    private static void Exit(){
        exitt = new Image(Assets.btn[10]);
        exitt.addListener(new ClickListener(){

        });
        stage.addActor(exitt);
    }

    public static void soundoff(){
        soundOff = new Image(Assets.btn[9]);
        soundOff.setPosition(350,310);
        soundOff.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                sounds = false;
                soundon();
            }
            private void soundon() {
                soundOn = new Image(Assets.btn[8]);
                soundOn.setPosition(350, 310);
                soundOn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        sounds = true;
                        soundoff();
                    }
                });
                stage.addActor(soundOn);
            }
        });
        stage.addActor(soundOff);
    }

    public static void musicoff() {
        musicOff = new Image(Assets.btn[7]);
        musicOff.setPosition(250,310);
        musicOff.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                music = false;
                musicon();
            }
            private void musicon() {
                musicOn = new Image(Assets.btn[6]);
                musicOn.setPosition(250,310);
                musicOn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        music = true;
                        musicoff();
                    }
                });
                stage.addActor(musicOn);
            }
        });
        stage.addActor(musicOff);
    }
}
