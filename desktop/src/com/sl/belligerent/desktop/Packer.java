package com.sl.belligerent.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Packer {
	public static void main (String[] args) throws Exception {
		TexturePacker.process("Textures/Sprites/Ground", "Textures/Atlas", "ground");
	}
}
