package com.ama.pasig.little.einstein;

import com.ama.pasig.little.einstein.Assets.Assets;

import com.ama.pasig.little.einstein.Main.CategoryList;
import com.ama.pasig.little.einstein.Main.Coloring.ColorBookAnswers;
import com.ama.pasig.little.einstein.Main.MainMenu;
import com.ama.pasig.little.einstein.Main.Numbers.NumberCounting;
import com.ama.pasig.little.einstein.Main.Numbers.NumbersDND;
import com.badlogic.gdx.Game;

public class LittleEinstein extends Game{

    @Override
    public void create(){
        Assets.load();
        Assets.mg.finishLoading();
        setScreen(new MainMenu());
        //setScreen(new NumberCounting());
    }

    @Override
    public void resume(){
        Assets.load();
        super.resume();
    }

    @Override
    public void pause(){
        super.pause();
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void resize(int width, int height){
        super.resize(width, height);
    }

    @Override
    public void dispose(){
        Assets.dispose();
        super.dispose();
    }
}