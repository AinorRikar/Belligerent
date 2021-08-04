package com.sl.belligerent.core.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.sl.belligerent.GameCore;

public class CommonCamera {
	protected OrthographicCamera camera;
	
	public CommonCamera(OrthographicCamera camera) {
		super();
		this.camera = camera;
	}
	
	public void zoom(float zoom) {
		if(zoom > 0 && camera.zoom >= 0.4 || zoom < 0 && camera.zoom <= 3) camera.zoom -= zoom;
	}
	
	public void setZoom(float zoom) {
		camera.zoom = zoom;
	}
	
	public float getZoom() {
		return camera.zoom;
	}
	
	public void setPos(float x, float y, float z) {
		camera.position.set(x, y, z);
	}
	
	public void translate(Vector2 vec) {
		camera.translate(vec);
	}
	
	public void update() {
		camera.update();
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}
	
	
}
