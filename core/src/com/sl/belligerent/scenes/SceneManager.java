package com.sl.belligerent.scenes;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SceneManager {
	private Stack<Scene> scenes;
	
	public SceneManager() {
		scenes = new Stack<Scene>();
	}
	
	public void push(Scene scene) {
		scenes.push(scene);
	}
	
	public void pop() {
		scenes.pop().dispose();
	}
	
	public void set(Scene scene) {
		scenes.pop().dispose();
		scenes.push(scene);
	}
	
	public void update(float dt) {
		scenes.peek().update(dt);
	}
	
	public void render(SpriteBatch sb) {
		scenes.peek().render(sb);
	}
}
