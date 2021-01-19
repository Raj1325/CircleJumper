package com.circlejumper.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.circlejumper.CircleJumper;
import com.circlejumper.config.WorldConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)WorldConfig.SCREEN_WIDTH;
		config.height = (int)WorldConfig.SCREEN_HEIGHT;
		new LwjglApplication(new CircleJumper(), config);
	}
}
