package com.sl.belligerent.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Packer {
	public static void main (String[] args) throws Exception {
		TexturePacker.process("Textures/Buttons", "Textures/Atlas", "buttons_menu");
	}
}
