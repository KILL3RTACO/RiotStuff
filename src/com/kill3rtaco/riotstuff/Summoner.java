package com.kill3rtaco.riotstuff;

import org.json.JSONException;
import org.json.JSONObject;

public class Summoner {
	
	public static final String	BASE_URL	= RiotStuff.BASE_URL + "v1.3/summoner/";
	
	private static void printSummoner(JSONObject jo) {
		try {
			System.out.println("Name:                         " + jo.getString("name"));
			System.out.println("Level:                        " + jo.getInt("summonerLevel"));
			System.out.println("------------------------------");
			System.out.println("ID:                           " + jo.getInt("id"));
			System.out.println("Revision Date:                " + Util.getFriendlyDate(jo.getLong("revisionDate")));
			System.out.println("SummonerIcon:                 " + jo.getInt("profileIconId"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static void printSummoner(long id) {
		printSummoner(getSummonerById(id));
	}
	
	public static void printSummoner(String name) {
		printSummoner(getSummonerByName(name));
	}
	
	public static JSONObject getSummonersByName(String name) {
		String url = BASE_URL + "by-name/" + name + "?api_key=" + RiotStuff.API_KEY;
		return RiotStuff.getJSONObject(url);
	}
	
	public static JSONObject getSummonersById(String ids) {
		String url = BASE_URL + ids + "?api_key=" + RiotStuff.API_KEY;
		return RiotStuff.getJSONObject(url);
	}
	
	public static JSONObject getSummonerById(long id) {
		try {
			JSONObject summoners = getSummonersById(id + "");
			return summoners.getJSONObject((String) summoners.names().get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject getSummonerByName(String name) {
		try {
			JSONObject summoners = getSummonersByName(name);
			return summoners.getJSONObject((String) summoners.names().get(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static long getSummonerId(String name) {
		try {
			return getSummonerByName(name).getLong("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static String getSummonerName(long id) {
		try {
			return getSummonerById(id).getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
