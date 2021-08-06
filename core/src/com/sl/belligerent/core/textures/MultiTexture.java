package com.sl.belligerent.core.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MultiTexture extends CommonTexture {

	public MultiTexture(String path, int x, int y) {
		super(path, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public TextureRegion getTextureRegion(int x, int y, int width, int height) {
		TextureRegion tr = new TextureRegion(getTexture(), x, y, width, height);
		return tr;
	}

}
