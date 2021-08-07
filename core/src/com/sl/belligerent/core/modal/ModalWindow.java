package com.sl.belligerent.core.modal;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class ModalWindow {
	protected Group group;
	
	protected Texture bg;
	protected BitmapFont font;
	protected String title;
	
	public ModalWindow(String title, Texture tex) {
		this.title = title;
		this.bg = tex;
	}
}
