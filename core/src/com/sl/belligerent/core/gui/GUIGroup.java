package com.sl.belligerent.core.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.sl.belligerent.core.camera.CommonCamera;

public class GUIGroup extends Actor{
	private Group group;
	
	private CommonCamera camera;

	public GUIGroup(CommonCamera camera) {
		super();
		this.camera = camera;
		group = new Group();
		
		setPosition(camera.getCamera().position.x - camera.getCamera().viewportWidth / 2, camera.getCamera().position.y - camera.getCamera().viewportHeight / 2);
		group.setPosition(getX(), getY());
	}
	
	public void add(Actor actor) {
		group.addActor(actor);
	}
	
	public Group getGroup() {
		return group;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		setPosition(camera.getCamera().position.x - camera.getCamera().viewportWidth / 2, camera.getCamera().position.y - camera.getCamera().viewportHeight / 2);
		group.setPosition(getX(), getY());
	}
	
	@Override
	public void draw(Batch sb, float parentAlpha) {
		group.draw(sb, parentAlpha);
	}
}
