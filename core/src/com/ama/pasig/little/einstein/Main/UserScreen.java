package ph.com.pasig.thesis.notify.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Calendar;


public class UserScreen implements Screen {

    Stage stage = new Stage();

    private void uiElements() {
        Skin skin = new Skin(Gdx.files.internal("JSON/uiskin.json"));

        final TextField textField = new TextField("", skin);
        Label label = new Label("Enter User:", skin);
        TextButton textButton = new TextButton("SAVE", skin);


        textField.setBounds(400, 200, 200, 60);
        label.setPosition(200, 200);
        textButton.setBounds(650, 200, 100, 60);

        stage.addActor(textField);
        stage.addActor(label);
        stage.addActor(textButton);

        // LOAD JSON TEST_ONLY
        String testString;
        if(UserHelper.loadData().equals("")){
            testString ="No username saved.";
        } else{
            testString = "This is the current user:"+UserHelper.loadData();
        }
        Label testLabel = new Label(testString, skin);

        testLabel.setPosition(200,500);
        stage.addActor(testLabel);

        // SAVE DATA TO JSON FILE
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                UserHelper.save(textField.getText());
                System.out.println(UserHelper.loadData()+", is saved.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new UserScreen());

            }
        });

    }

    @Override
    public void show() {
        stage.setViewport(new ExtendViewport(1280, 800));
        Gdx.input.setInputProcessor(stage);

            uiElements();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
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
        stage.dispose();
    }
}

class UserHelper {

    private static FileHandle file = Gdx.files.local("bin/users.json");

    public static void save(String username) {
        Users users = new Users();
        Json json = new Json();

        users.username = username;
        json.setOutputType(JsonWriter.OutputType.json);
        file.writeString((json.toJson(users)), false);
    }

    public static String loadData() {
        String data = "";
        Users users;
        if (file.exists()) {
            Json json = new Json();
            users = json.fromJson(Users.class, file.readString());
            data = users.username;
        }
        return data;
    }

}

class Users {
    public String username;
}