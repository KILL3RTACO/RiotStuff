package com.kill3rtaco.riotstuff;


public class GameConstants {
	
	public static String getMapName(int id) {
		switch(id) {
			case 1:
				return "Summoner's Rift (Summer)";
			case 2:
				return "Summoner's Rift (Autumn)";
			case 3:
				return "The Proving Grounds";
			case 4:
				return "Twisted Treeline (Original)";
			case 8:
				return "The Crystal Scar";
			case 10:
				return "Twisted Treeline (Current)";
			case 12:
				return "The Howling Abyss";
			default:
				return "Unknown Map";
		}
	}
	
	public static String getSlotType(int id) {
		switch(id) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				return "MARK";
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
			case 18:
				return "SEAL";
			case 19:
			case 20:
			case 21:
			case 22:
			case 23:
			case 24:
			case 25:
			case 26:
			case 27:
				return "GLYPH";
			case 28:
			case 29:
			case 30:
				return "QUINTESSENCE";
			default:
				return "UNKNOWN";
		}
	}
}
