package com.ama.pasig.little.einstein.Main;

import com.ama.pasig.little.einstein.Assets.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by frankensteenie on 3/20/2015.
 */
public class Home extends Stage {

    public static Image homeBtn;
    static Stage stage = new Stage();

    public static void showHome(Stage stage){
        Home.stage = stage;
        homeBtn = new Image(Assets.btn[11]);
        homeBtn.setScale(0.5f);
        homeBtn.setPosition(250,650);
        homeBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
            }
        });
        stage.addActor(homeBtn);
    }
}
