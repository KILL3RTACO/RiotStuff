package com.kill3rtaco.riotstuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Game {
	
	public static final String	BASE_URL	= RiotStuff.BASE_URL + "v1.3/game/by-summoner/";
	
	private static void printGame(JSONObject jo) {
		try {
			JSONArray champs = Champion.getAllChamps(false);
			long champId = jo.getLong("championId");
			System.out.println("Game " + jo.getLong("gameId"));
			System.out.println("-----------------------------");
			System.out.println("Champion:                    " + champId + " (" + Champion.getChampName(champs, champId) + ")");
			System.out.println("Create Date:                 " + Util.getFriendlyDate(jo.getLong("createDate")));
			System.out.println("Fellow Players:\n");
			JSONArray players = jo.getJSONArray("fellowPlayers");
			String ids = "";
			for(int i = 0; i < players.length(); i++) {
				JSONObject o = players.getJSONObject(i);
				ids += o.getLong("summonerId") + ",";
			}
			ids = ids.substring(0, ids.lastIndexOf(","));
			JSONObject names = Summoner.getSummonersById(ids);
			for(int i = 0; i < players.length(); i++) {
				JSONObject o = players.getJSONObject(i);
				champId = o.getLong("championId");
				System.out.println("    " + names.getJSONObject(o.getLong("summonerId") + "").getString("name"));
				System.out.println("    -----------------------------");
				System.out.println("    Champion:                    " + champId + "(" + Champion.getChampName(champs, champId) + ")");
				System.out.println("    Team:                        " + (o.getInt("teamId") == 100 ? "Blue" : "Purple/Red"));
				System.out.println();
			}
			int mapId = jo.getInt("mapId");
			System.out.println("Map:                         " + mapId + " [" + GameConstants.getMapName(mapId) + "]");
			System.out.println("Game Mode:                   " + jo.getString("gameMode"));
			System.out.println("Game Type:                   " + jo.getString("gameType"));
			System.out.println("Game Sub-Type:               " + jo.getString("subType"));
//			System.out.println("Invalid                      " + jo.getBoolean("invalid"));
			System.out.println("Level:                       " + jo.getInt("level"));
			System.out.println("Spell 1:                     " + jo.getInt("spell1"));
			System.out.println("Spell 2:                     " + jo.getInt("spell2"));
			System.out.println("Team:                        " + (jo.getInt("teamId") == 100 ? "Blue" : "Purple/Red") + "\n");
			JSONObject stats = jo.getJSONObject("stats");
			System.out.println("    Stats");
			System.out.println("    -----------------------------");
			System.out.println("    Win:                         " + stats.getBoolean("win"));
			System.out.println("    Nexus Final Blow:            " + stats.optBoolean("nexusKilled", false));
			System.out.println("    K/D/A/CS:                    " + stats.optInt("championsKilled") + "/" + stats.optInt("numDeaths") + "/" + stats.optInt("assists") + "/" + stats.optInt("minionsKilled"));
			System.out.println("    Gold Earned:                 " + Util.getFriendlyNum(stats.getLong("goldEarned")));
			System.out.println("        Spent:                   " + Util.getFriendlyNum(stats.getLong("goldSpent")));
			System.out.println("    Time:                        " + Util.getTime(stats.getInt("timePlayed")));
			System.out.println();
			System.out.println("    Items");
			System.out.println("    =============================");
			System.out.println("    Items:                       " + stats.optInt("item0") + "/" + stats.optInt("item1") + "/" + stats.optInt("item2") + "/" + stats.optInt("item3") + "/" + stats.optInt("item4") + "/" + stats.optInt("item5") + "/" + stats.optInt("item6"));
			System.out.println("    Items Purchased:             " + stats.optInt("itemsPurchased"));
			System.out.println("    Consumables Purchased:       " + stats.optInt("consumablesPurchased"));
			System.out.println("    Legendary Items Built:       " + stats.optInt("lengendaryItemsCreated"));
			System.out.println("    VisionWards Bought:          " + stats.optInt("visionWardsBought"));
			System.out.println("    SightWards Bought:           " + stats.optInt("sightWardsBought"));
			System.out.println("    Wards Placed:                " + stats.optInt("wardPlaced"));
			System.out.println("    Wards Killed:                " + stats.optInt("wardKilled"));
			System.out.println();
			System.out.println("    Damage");
			System.out.println("    =============================");
			System.out.println("    Killing Sprees:              " + stats.optInt("killingSprees"));
			System.out.println("    Double Kills:                " + stats.optInt("doubleKills"));
			System.out.println("    Triple Kills:                " + stats.optInt("tripleKills"));
			System.out.println("    Quadra Kills:                " + stats.optInt("quadraKills"));
			System.out.println("    Penta Kills:                 " + stats.optInt("pentaKills"));
			System.out.println("    Unreal Kills:                " + stats.optInt("unrealKills"));
			System.out.println("    Largest Killing Spree:       " + stats.optInt("largestKillingSpree"));
			System.out.println("    Largest MultiKill:           " + stats.optInt("largestMultiKill"));
			System.out.println("    Largest Critical Strike:     " + Util.getFriendlyNum(stats.optInt("largestCriticalStrike")));
			System.out.println("    Magic Damage Dealt:          " + Util.getFriendlyNum(stats.optInt("magicDamageDealtPlayer")));
			System.out.println("        To Champions:            " + Util.getFriendlyNum(stats.optInt("magicDamageDealtToChampions")));
			System.out.println("    Magic Damage Taken:          " + Util.getFriendlyNum(stats.optInt("magicDamageTaken")));
			System.out.println("    Minions Killed:              " + stats.optInt("minionsKilled"));
			System.out.println("        Denied:                  " + stats.optInt("minionsDenied"));
			System.out.println("    Neutral Minions Killed:      " + stats.optInt("neutralMinionsKilled"));
			System.out.println("        Your Jungle:             " + stats.optInt("neutralMinionsKilledYourJungle"));
			System.out.println("        Enemy Jungle:            " + stats.optInt("neutralMinionsKilledEnemyJungle"));
			System.out.println("    Physical Damage Dealt:       " + Util.getFriendlyNum(stats.optInt("physicalDamageDealt")));
			System.out.println("        To Champions:            " + Util.getFriendlyNum(stats.optInt("physicalDamageDealtToChampions")));
			System.out.println("    Physical Damage Taken:       " + Util.getFriendlyNum(stats.optInt("physicalDamageTaken")));
			System.out.println("    Super Monsters Killed:       " + stats.optInt("superMonsterKilled"));
			System.out.println("    Total Damage Dealt:          " + Util.getFriendlyNum(stats.optInt("totalDamageDealtPlayer")));
			System.out.println("        To Champions:            " + Util.getFriendlyNum(stats.optInt("totalDamageDealtToChampions")));
			System.out.println("    Total Damage Taken:          " + Util.getFriendlyNum(stats.optInt("totalDamageTaken")));
			System.out.println("    Total Heal:                  " + Util.getFriendlyNum(stats.optInt("totalHeal")));
			System.out.println("    Total CC Time Dealt:         " + Util.getTime(stats.optInt("totalCrowdControlTimeDealt")));
			System.out.println("    Total Units Killed:          " + stats.optInt("totalUnitsKilled"));
			System.out.println("    True Damage Dealt:           " + Util.getFriendlyNum(stats.optInt("trueDamageDealtPlayer")));
			System.out.println("        To Champions:            " + Util.getFriendlyNum(stats.optInt("trueDamageDealtToChampions")));
			System.out.println("    True Damage Taken:           " + Util.getFriendlyNum(stats.optInt("trueDamageTaken")));
			System.out.println("    Turrets Killed:              " + stats.optInt("turretsKilled"));
			System.out.println();
			System.out.println("    Spells");
			System.out.println("    =============================");
			System.out.println("    Q/W/E/R Casts:               " + stats.optInt("spell1Cast") + "/" + stats.optInt("spell2Cast") + "/" + stats.optInt("spell3Cast") + "/" + stats.optInt("spell4Cast"));
			System.out.println("    D/F Casts:                   " + stats.optInt("summonSpell1Cast") + "/" + stats.optInt("summonSpell2Cast"));
			System.out.println();
			if(jo.getString("gameType").equalsIgnoreCase("ODIN")) {
				System.out.println("    Dominion");
				System.out.println("    =============================");
				System.out.println("    Barracks Killed:             " + stats.getInt("barracksKilled"));
				System.out.println("    CombatPlayerScore:           " + Util.getFriendlyNum(stats.getInt("combatPlayerScore")));
				System.out.println("    Nodes Captured:              " + stats.getInt("nodeCapture"));
				System.out.println("        Assists:                 " + stats.getInt("nodeCaptureAssist"));
				System.out.println("    Nodes Neutralized:           " + stats.getInt("nodeNeutralize"));
				System.out.println("        Assists:                 " + stats.getInt("nodeNeutralizeAssist"));
				System.out.println("    Objective Score:             " + Util.getFriendlyNum(stats.getInt("objectivePlayerScore")));
				System.out.println("    Total Player Score:          " + Util.getFriendlyNum(stats.getInt("totalPlayerScore")));
				System.out.println("    Total Score Rank:            " + Util.getFriendlyNum(stats.getInt("totalScoreRank")));
				System.out.println("    Victory Point Total:         " + Util.getFriendlyNum(stats.getInt("victoryPointTotal")));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject getAllRecentGames(long id) {
		String url = BASE_URL + id + "/recent?api_key=" + RiotStuff.API_KEY;
		return RiotStuff.getJSONObject(url);
	}
	
	public static JSONObject getRecentGame(long id, int gameIndex) {
		try {
			JSONArray games = getAllRecentGames(id).getJSONArray("games");
			return games.getJSONObject(gameIndex);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void printAllRecentGames(long id) {
		try {
			JSONArray games = getAllRecentGames(id).getJSONArray("games");
			String name = Summoner.getSummonerName(id);
			System.out.println("Recent Games for " + name + ":\n");
			for(int i = 0; i < games.length(); i++) {
				JSONObject game = games.getJSONObject(i);
				printGame(game);
				System.out.println("\n=========================================================\n");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static void printRecentGame(long id, int gameIndex) {
		try {
			JSONArray games = getAllRecentGames(id).getJSONArray("games");
			JSONObject game = games.getJSONObject(gameIndex);
			printGame(game);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static void printAllRecentGames(String name) {
		printAllRecentGames(Summoner.getSummonerId(name));
	}
	
	public static void printRecentGame(String name, int gameIndex) {
		printRecentGame(Summoner.getSummonerId(name), gameIndex);
	}
	
}
