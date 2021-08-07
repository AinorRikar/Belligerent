package com.sl.belligerent.core.hordes;

import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.sl.belligerent.core.textures.MultiTexture;
import com.sl.belligerent.core.units.MovableUnit;
import com.sl.belligerent.core.units.StaticUnit;
import com.sl.belligerent.core.world.MapManager;

public class CommonHorde {
	
	protected Group units;
	
	protected int stones, logs, meat, herbs;
	
	public CommonHorde() {
		super();
		units = new Group();
		units.setPosition(0, 0);
		
		stones = 0;
		logs = 0;
		meat = 0;
		herbs = 0;
	};
	
	public int getStones() {
		return stones;
	}

	public void setStones(int stones) {
		this.stones = stones;
	}
	
	public void addStones(int stones) {
		this.stones += stones;
	}

	public int getLogs() {
		return logs;
	}

	public void setLogs(int logs) {
		this.logs = logs;
	}

	public void addLogs(int logs) {
		this.logs += logs;
	}
	
	public int getMeat() {
		return meat;
	}

	public void setMeat(int meat) {
		this.meat = meat;
	}
	
	public void addMeat(int meat) {
		this.meat += meat;
	}

	public int getHerbs() {
		return herbs;
	}

	public void setHerbs(int herbs) {
		this.herbs = herbs;
	}
	
	public void addHerbs(int herbs) {
		this.herbs += herbs;
	}

	public void createHorde(int countOfUnits) {
		StaticUnit building = new StaticUnit(new MultiTexture("Textures/Sprites/Units/main.png", 192, 192), "Main House", this, true);
		addActor(building);
		Random r = new Random();
		for (int i = 0; i < countOfUnits; i++) {
			int type = r.nextInt(2);
			MovableUnit unit;
			if(type == 0)
				unit = new MovableUnit(new MultiTexture("Textures/Sprites/Units/Mummai.png", 128, 128), "Blue Mummai", this, 2f);
			else
				unit = new MovableUnit(new MultiTexture("Textures/Sprites/Units/Mummai_red.png", 128, 128), "Red Mummai", this, 2f);
			
			unit.getDamage(r.nextInt(80) + 10);
			addActor(unit);
		}
	}
	
	public Group getGroup() {
		return units;
	}
	
	public void addActor(Actor actor) {
		units.addActor(actor);
		MapManager.addUnit(actor);
	}
	
	public void removeActor(Actor actor) {
		units.removeActor(actor);
		MapManager.removeUnit(actor);
	}
}
