package com.sl.belligerent.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sl.belligerent.GameCore;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = GameCore.HEIGHT;
		config.width = GameCore.WIDTH;
		config.title = GameCore.TITLE;
		config.fullscreen = true;
		new LwjglApplication(new GameCore(), config);
	}
}
