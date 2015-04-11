package com.ama.pasig.little.einstein.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ama.pasig.little.einstein.LittleEinstein;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new LittleEinstein(), config);
        config.width = 1240;
        config.height = 720;
	}
}
