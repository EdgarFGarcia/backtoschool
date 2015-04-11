package com.ama.pasig.little.einstein.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets{
    public static final AssetManager mg = new AssetManager();
    public static final String Assets = "Main/littleEinstein.pack";
    public static final String assets = "Main/shapes.pack";
    public static TextureRegion[] animals = new TextureRegion[15];
    public static TextureRegion[] bg = new TextureRegion[9];
    public static TextureRegion[] btn = new TextureRegion[12];
    public static TextureRegion[] color = new TextureRegion[10];
    public static TextureRegion[] letter = new TextureRegion[26];
    public static TextureRegion[] number = new TextureRegion[10];
    public static TextureRegion[] shapes = new TextureRegion[8];
    public static TextureRegion[] fruitsNoColor = new TextureRegion[7];
    public static TextureRegion[] fruitsWithColor = new TextureRegion[7];
    public static TextureRegion[] shapesempty = new TextureRegion[8];
    public static TextureRegion[] lettersAnimals = new TextureRegion[11];
    public static TextureRegion[] buttonCategory = new TextureRegion[6];
    public static TextureRegion[] squareBox = new TextureRegion[4];
    public static Sound[] soundfx = new Sound[3];
    public static Music[] musicfx = new Music[2];
    public static BitmapFont labelfont;
    public static BitmapFont labelfontSmall;
    public static TextureRegion squares = new TextureRegion();
    public static TextureAtlas atlas, atlasShapes;

    public static void load(){
        mg.load(Assets, TextureAtlas.class);
        mg.load(assets, TextureAtlas.class);
        loadImages();
        loadSound();
        loadMusic();
    }

    public static BitmapFont getLabelfont(){
        return labelfont;
    }

    public static BitmapFont getLabelfontSmall(){
        return labelfontSmall;
    }

    private static void loadSound(){
        for(int i = 0; i < 3; i++){
            soundfx[i] = Gdx.audio.newSound(Gdx.files.internal(Variables.sounds[i]));
        }
    }

    private static void loadMusic(){
        for(int i = 0; i < 2; i++){
            musicfx[i] = Gdx.audio.newMusic(Gdx.files.internal(Variables.music[i]));
        }
    }

    private static void loadImages() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Variables.fontPath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        labelfont = generator.generateFont(parameter);
        parameter.size = 36;
        labelfontSmall = generator.generateFont(parameter);
        generator.dispose();

        for(int i = 0; i < 4; i++){
            squareBox[i] = getAtlas().findRegion("shapes/" + Variables.square[i]);
        }
        squares = getAtlas().findRegion("shapes/" + Variables.square[0]);

        for(int i = 0; i < 11; i++){
            lettersAnimals[i] = getAtlas().findRegion("letters/"+Variables.lettersAnimals[i]);
        }
        for(int i = 0; i < 6; i++){
            buttonCategory[i] = getAtlas().findRegion("cbtn/" + Variables.buttonsCategory[i]);
        }
        for(int i = 0; i < 11; i++){
            animals[i] = getAtlas().findRegion("animals/" +  Variables.animals[i]);
        }
        for(int i = 0; i < 9; i ++){
            bg[i] = getAtlas().findRegion("bg/" + Variables.bg[i]);
        }
        for(int i = 0; i < 12; i++){
            btn[i] = getAtlas().findRegion("buttons/" + Variables.btn[i]);
        }
        for(int i = 0; i < 10; i++){
            color[i] = getAtlas().findRegion("colors/" + Variables.color[i]);
        }
        for(int i = 0; i < 26; i++){
            letter[i] = getAtlas().findRegion("letters/" + Variables.letter[i]);
        }
        for(int i = 0; i < 10; i++){
            number[i] = getAtlas().findRegion("numbers/" + Variables.number[i]);
        }
        for(int i = 0; i < 8; i++){
            shapes[i] = getAtlasShapes().findRegion(Variables.shapes[i]);
        }
        for(int i = 0; i < 8; i++){
            shapesempty[i] = getAtlas().findRegion("shapesEmpty/" + Variables.shapesempty[i]);
        }
        for(int i = 0; i < 7; i++){
            fruitsNoColor[i] = getAtlas().findRegion("fruits/" + Variables.fruitsWithoutColor[i]);
        }
        for(int i = 0; i < 7; i++){
            fruitsWithColor[i] = getAtlas().findRegion("fruitsColored/"+ Variables.fruitsWithColor[i]);
        }

    }

    public static TextureAtlas getAtlas(){
        if(atlas == null){
            atlas = new TextureAtlas(Gdx.files.internal(Assets));
        }
        return atlas;
    }

    public static TextureAtlas getAtlasShapes(){
        if(atlasShapes == null){
            atlasShapes = new TextureAtlas(Gdx.files.internal(assets));
        }
        return atlasShapes;
    }

    public static void unload(){
        mg.unload("Main/littleEinstein.pack");
        mg.unload("Main/shapes.pack");
    }

    public static void dispose(){
        unload();
        mg.dispose();
        labelfont.dispose();
    }
}