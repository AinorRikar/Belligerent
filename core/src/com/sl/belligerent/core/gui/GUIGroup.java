package com.sl.belligerent.core.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.sl.belligerent.core.camera.CommonCamera;

public class GUIGroup extends Group{
	
	private CommonCamera camera;

	public GUIGroup(CommonCamera camera) {
		super();
		this.camera = camera;
		
		setPosition(camera.getCamera().position.x - camera.getCamera().viewportWidth / 2, camera.getCamera().position.y - camera.getCamera().viewportHeight / 2);
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		setPosition(camera.getCamera().position.x - camera.getCamera().viewportWidth / 2, camera.getCamera().position.y - camera.getCamera().viewportHeight / 2);
	}
}
