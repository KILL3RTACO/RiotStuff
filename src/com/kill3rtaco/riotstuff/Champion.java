package com.kill3rtaco.riotstuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Champion {
	
	public static String	BASE_URL	= RiotStuff.BASE_URL + "v1.1/champion";
	
	private static void printChampion(JSONObject jo) {
		try {
			System.out.println(jo.getString("name"));
			System.out.println("--------------------------");
			System.out.println("Active:                   " + jo.getBoolean("active"));
			System.out.println("AD Rating:                " + jo.getInt("attackRank"));
			System.out.println("Bot Enabled:              " + jo.getBoolean("botEnabled"));
			System.out.println("Bot Enabled (Match Made): " + jo.getBoolean("botMmEnabled"));
			System.out.println("Defense Rating:           " + jo.getInt("defenseRank"));
			System.out.println("Difficulty:               " + jo.getInt("difficultyRank"));
			System.out.println("Free to Play:             " + jo.getBoolean("freeToPlay"));
			System.out.println("ID:                       " + jo.getInt("id"));
			System.out.println("AP Rating:                " + jo.getInt("magicRank"));
			System.out.println("Ranked Play Enabled:      " + jo.getBoolean("rankedPlayEnabled"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static JSONArray getAllChamps(boolean freeToPlay) {
		String url = BASE_URL + "?api_key=" + RiotStuff.API_KEY + (freeToPlay ? "&freeToPlay=true" : "");
		try {
			return RiotStuff.getJSONObject(url).getJSONArray("champions");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void printAllChamps(boolean freeToPlay) {
		try {
			JSONArray champs = getAllChamps(freeToPlay);
			for(int i = 0; i < champs.length(); i++) {
				printChampion(champs.getJSONObject(i));
				System.out.println("\n===================================\n");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject getChampById(JSONArray champs, long id) {
		try {
			for(int i = 0; i < champs.length(); i++) {
				JSONObject jo = champs.getJSONObject(i);
				if(jo.getInt("id") == id) {
					return jo;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject getChampById(long id) {
		return getChampById(getAllChamps(false), id);
	}
	
	public static JSONObject getChampByName(JSONArray champs, String name) {
		try {
			for(int i = 0; i < champs.length(); i++) {
				JSONObject jo = champs.getJSONObject(i);
				if(jo.getString("name").equalsIgnoreCase(name)) {
					return jo;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject getChampByName(String name) {
		return getChampByName(getAllChamps(false), name);
	}
	
	public static int getChampId(String name) {
		return getChampId(getAllChamps(false), name);
	}
	
	public static int getChampId(JSONArray champs, String name) {
		try {
			return getChampByName(champs, name).getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static String getChampName(long id) {
		return getChampName(getAllChamps(false), id);
	}
	
	public static String getChampName(JSONArray champs, long id) {
		try {
			return getChampById(champs, id).getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void printChamp(long id) {
		printChampion(getChampById(id));
	}
	
	public static void printChamp(String name) {
		printChampion(getChampByName(name));
	}
	
}
