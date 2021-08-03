package com.sl.belligerent.core.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class CommonTexture {

	private String texturePath;
	private int xSize, ySize;
	
	private Texture texture;
	
	public CommonTexture(String path, int x, int y) {
		texturePath = path;
		xSize = x;
		ySize = y;
		
		Pixmap defaultPM = new Pixmap(Gdx.files.internal(texturePath));
		Pixmap finalPM = new Pixmap(x, y, defaultPM.getFormat());
		finalPM.drawPixmap(defaultPM,
				0, 0, defaultPM.getWidth(), defaultPM.getHeight(),
				0, 0, finalPM.getWidth(), finalPM.getHeight());
		
		texture = new Texture(finalPM);
		
		defaultPM.dispose();
		finalPM.dispose();
	}

	public String getTexturePath() {
		return texturePath;
	}

	public int getXSize() {
		return xSize;
	}

	public int getYSize() {
		return ySize;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void dispose() {
		texture.dispose();
	}
}
