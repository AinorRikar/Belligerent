package com.sl.belligerent.core.hordes;

import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.sl.belligerent.core.textures.MultiTexture;
import com.sl.belligerent.core.units.MovableUnit;
import com.sl.belligerent.core.units.StaticUnit;

public class CommonHorde {
	
	protected Group units;
	
	public Vector<Actor> unitsList;
	
	public CommonHorde() {
		super();
		units = new Group();
		units.setPosition(0, 0);
		unitsList = new Vector<Actor>();
	};
	
	public void createHorde(int countOfUnits) {
		StaticUnit building = new StaticUnit(new MultiTexture("Textures/Sprites/Units/main.png", 192, 192), "Main House", true);
		addActor(building);
		
		for (int i = 0; i < countOfUnits; i++) {
			Random r = new Random();
			int type = r.nextInt(2);
			MovableUnit unit;
			if(type == 0)
				unit = new MovableUnit(new MultiTexture("Textures/Sprites/Units/Mummai.png", 128, 128), "Blue Mummai", 2f);
			else
				unit = new MovableUnit(new MultiTexture("Textures/Sprites/Units/Mummai_red.png", 128, 128), "Red Mummai", 2f);
			addActor(unit);
		}
	}
	
	public Group getGroup() {
		return units;
	}
	
	public void addActor(Actor actor) {
		units.addActor(actor);
		unitsList.add(actor);
	}
	
	public void removeActor(Actor actor) {
		units.removeActor(actor);
		for(int i = 0; i < unitsList.size(); i++) {
			if(actor == unitsList.get(i)) {
				unitsList.remove(i);
				break;
			}
		}
	}
	
	public int getUnitsCount() {
		return unitsList.size();
	}
	
	public boolean isUnitInPos(int X, int Y, int w, int h, Actor testingActor) {
		for(int i = 0; i < unitsList.size(); i++) {
			Actor actor = unitsList.get(i);
			
			float posX = actor.getX();
			float posY = actor.getY();
			float posX2 = actor.getWidth() + posX;
			float posY2 = actor.getHeight() + posY;
			
			int x = X * 32;
			int y = Y * 32;
			
			if(x >= posX - w && x <= posX2 - 1 && y >= posY - h && y <= posY2 - 1 && actor != testingActor) {
				return true;
			}
		}
		return false;
	}
	
	public Vector2 getCenter() {
		if(unitsList.size() == 0) {
			return new Vector2(0, 0);
		}
		else {
			for(int i = 0; i < unitsList.size(); i++) {
				if(unitsList.get(i) instanceof StaticUnit) {
					StaticUnit unit = (StaticUnit) unitsList.get(i);
					if(unit.isMain()) return new Vector2(unit.getX() + unit.getWidth() / 2, unit.getY() + unit.getHeight() / 2);
				}
			}
			return new Vector2(0, 0);
		}
	}
}
