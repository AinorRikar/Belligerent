package com.sl.belligerent.core.world;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.sl.belligerent.core.hordes.CommonHorde;

public class MapManager {
	
	private static MapManager inst = new MapManager();
	
	private static Map map;
	private static CommonHorde horde;
	
	private MapManager () {
		
	}
	
	public static MapManager inst() {
		return inst;
	}
	
	public static void setMap(Map map) {
		inst.map = map;
	}
	
	public static void setHorde(CommonHorde horde) {
		inst.horde = horde;
	}
	
	public static boolean isCellMoveable(int x, int y) {
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getMap().getLayers().get("Ground");
		layer.getCell(x, y);
		
		if(layer.getCell(x, y) == null) return false;
		
		if(horde.isUnitInPos(x, y)) return false;
		
		return true;
	}
	
	public static Vector2 getCurrentMapSize() {
		return new Vector2(
				((TiledMapTileLayer)map.getMap().getLayers().get(0)).getWidth(), 
				((TiledMapTileLayer)map.getMap().getLayers().get(0)).getHeight());
	}
}
