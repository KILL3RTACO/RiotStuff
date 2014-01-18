package com.kill3rtaco.riotstuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Stats {
	
	public static final String	BASE_URL		= RiotStuff.BASE_URL + "v1.2/stats/by-summoner/";
	public static final int		CURRENT_SEASON	= 4;
	
	private static void printAggregatedStats(JSONObject stats, boolean ranked) {
		System.out.println("    Games Played:                       " + Util.getFriendlyNum(stats.optInt("totalSessionsPlayed")));
		System.out.println("        Won:                            " + Util.getFriendlyNum(stats.optInt("totalSessionsWon")));
		System.out.println("        Lost:                           " + Util.getFriendlyNum(stats.optInt("totalSessionsLost")));
		System.out.println();
		System.out.println("    Average Assists: [D]                " + stats.optInt("averageAssists"));
		System.out.println("        Max: [A]                        " + stats.optInt("maxAssists"));
		System.out.println("        Total:                          " + Util.getFriendlyNum(stats.optInt("totalAssists")));
		System.out.println("    Average Kills: [D]                  " + stats.optInt("averageChampionsKilled"));
		System.out.println("        Max: [A]                        " + stats.optInt("maxChampionsKilled"));
		System.out.println("        Total:                          " + Util.getFriendlyNum(stats.optInt("totalChampionKills")));
		System.out.println("    Average Combat PlayerScore: [D]     " + Util.getFriendlyNum(stats.optInt("averageCombatPlayerScore")));
		System.out.println("        Max:                            " + Util.getFriendlyNum(stats.optInt("maxCombatPlayerScore")));
		System.out.println("        Total:                          " + Util.getFriendlyNum(stats.optInt("totalCombatPlayerScore")));
		System.out.println("    Average Nodes Captured: [D]         " + stats.optInt("averageNodeCapture"));
		System.out.println("        Max:                            " + stats.optInt("maxNodeCapture"));
		System.out.println("            Assist:                     " + stats.optInt("maxNodeCaptureAssist"));
		System.out.println("        Total:                          " + Util.getFriendlyNum(stats.optInt("totalNodeCapture")));
		System.out.println("        Assist:                         " + stats.optInt("averageNodeCaptureAssist"));
		System.out.println("    Average Node Neutralize: [D]        " + stats.optInt("averageNodeNeutralize"));
		System.out.println("        Max:                            " + stats.optInt("maxNodeNeutralize"));
		System.out.println("            Assist:                     " + stats.optInt("maxNodeNeutralizeAssist"));
		System.out.println("        Total:                          " + Util.getFriendlyNum(stats.optInt("totalNodeNeutralize")));
		System.out.println("        Assist:                         " + stats.optInt("averageNodeNeutralizeAssist"));
		System.out.println("    Average Deaths: [D]                 " + stats.optInt("averageNumDeaths"));
		if(ranked) {
			System.out.println("        Max:                            " + stats.optInt("maxNumDeaths"));
			System.out.println("        Total:                          " + Util.getFriendlyNum(stats.optInt("totalDeathsPerSession")));
		}
		System.out.println("    Average Objective PlayerScore: [D]  " + Util.getFriendlyNum(stats.optInt("averageObjectivePlayerScore")));
		System.out.println("        Max:                            " + Util.getFriendlyNum(stats.optInt("maxObjectivePlayerScore")));
		System.out.println("        Total:                          " + Util.getFriendlyNum(stats.optInt("totalObjectivePlayerScore")));
		System.out.println("    Average Team ObjectiveScore: [D]    " + Util.getFriendlyNum(stats.optInt("averageTeamObjective")));
		System.out.println("        Max:                            " + Util.getFriendlyNum(stats.optInt("maxTeamObjective")));
		System.out.println("        Total:                          " + Util.getFriendlyNum(stats.optInt("totalTeamObjective")));
		System.out.println("    Average PlayerScore: [D]            " + Util.getFriendlyNum(stats.optInt("averageTotalPlayerScore")));
		System.out.println("        Max:                            " + Util.getFriendlyNum(stats.optInt("maxTotalPlayerScore")));
		System.out.println("        Total:                          " + Util.getFriendlyNum(stats.optInt("totalPlayerScore")));
		System.out.println();
		System.out.println("    Bot Games Played:                   " + Util.getFriendlyNum(stats.optInt("botGamesPlayed")));
		System.out.println("        Normal:                         " + Util.getFriendlyNum(stats.optInt("normalGamesPlayed")));
		System.out.println("        Ranked Premade:                 " + Util.getFriendlyNum(stats.optInt("rankedPremadeGamesPlayed")));
		System.out.println("        Ranked Solo:                    " + Util.getFriendlyNum(stats.optInt("rankedSoloGamesPlayed")));
		System.out.println("    Killing Spree:                      " + stats.optInt("killingSpree"));
		System.out.println();
		System.out.println("    Largest Critical Strike:            " + Util.getFriendlyNum(stats.optInt("maxLargestCriticalStrike")));
		System.out.println("    Largest Killing Spree:              " + Util.getFriendlyNum(stats.optInt("maxLargestKillingSpree")));
		System.out.println("    Total First Blood:                  " + Util.getFriendlyNum(stats.optInt("totalFirstBlood")));
		System.out.println("    Double Kills:                       " + Util.getFriendlyNum(stats.optInt("totalDoubleKills")));
		System.out.println("    Triple Kills:                       " + Util.getFriendlyNum(stats.optInt("totalTripleKills")));
		System.out.println("    Quadra Kills:                       " + Util.getFriendlyNum(stats.optInt("totalQuadraKills")));
		System.out.println("    Penta Kills:                        " + Util.getFriendlyNum(stats.optInt("totalPentaKills")));
		System.out.println("    Unreal Kills:                       " + Util.getFriendlyNum(stats.optInt("totalUnrealKills")));
		System.out.println("    Turret Kills:                       " + Util.getFriendlyNum(stats.optInt("totalTurretKills")));
		System.out.println();
		System.out.println("    Total Magic Damage Dealt:           " + Util.getFriendlyNum(stats.optInt("totalMagicDamageDealt")));
		System.out.println("    Total Physical Damage Dealt:        " + Util.getFriendlyNum(stats.optInt("totalPhysicalDamageDealt")));
		System.out.println("    Total Damage Dealt:                 " + Util.getFriendlyNum(stats.optInt("totalDamageDealt")));
		System.out.println("    Total Damage Taken:                 " + Util.getFriendlyNum(stats.optInt("totalDamageTaken")));
		System.out.println("    Total Heal:                         " + Util.getFriendlyNum(stats.optInt("totalHeal")));
		System.out.println("    Total Minions Killed:               " + Util.getFriendlyNum(stats.optInt("totalMinsionsKilled")));
		System.out.println("    Total Gold Earned:                  " + Util.getFriendlyNum(stats.optInt("totalGoldEarned")));
		System.out.println();
	}
	
	public static void printRankedStats(JSONObject stats) {
		try {
			System.out.println("Modify Date:                     " + Util.getFriendlyDate(stats.getLong("modifyDate")));
			JSONArray champions = stats.getJSONArray("champions");
			System.out.println("Champions");
			System.out.println("---------------------------------");
//			System.out.println();
			for(int i = 0; i < champions.length(); i++) {
				JSONObject c = champions.getJSONObject(i);
				JSONObject cs = c.getJSONObject("stats");
				System.out.println("    " + c.getString("name"));
				System.out.println("    ====================================");
				printAggregatedStats(cs, true);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static void printSummarizedStats(JSONObject stats) {
		try {
			JSONArray summaries = stats.getJSONArray("playerStatSummaries");
			for(int i = 0; i < summaries.length(); i++) {
				JSONObject o = summaries.getJSONObject(i);
				System.out.println(o.getString("playerStatSummaryType"));
				System.out.println("====================================");
				System.out.println("Wins/Losses:                        " + o.optInt("wins") + "/" + o.optInt("losses"));
				System.out.println("Modify Date:                        " + Util.getFriendlyDate(o.getLong("modifyDate")));
				System.out.println();
				System.out.println("    Stats");
				System.out.println("    ====================================");
				printAggregatedStats(o.getJSONObject("aggregatedStats"), true);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject getSummarizedStats(long id) {
		return getSummarizedStats(id, CURRENT_SEASON);
	}
	
	public static JSONObject getSummarizedStats(String name) {
		return getSummarizedStats(name, CURRENT_SEASON);
	}
	
	public static JSONObject getSummarizedStats(long id, int season) {
		String url = BASE_URL + id + "/summary?api_key=" + RiotStuff.API_KEY + "&season=SEASON" + season;
		return RiotStuff.getJSONObject(url);
	}
	
	public static JSONObject getSummarizedStats(String name, int season) {
		return getSummarizedStats(Summoner.getSummonerId(name), season);
	}
	
	public static void printSummarizedStats(long id) {
		printSummarizedStats(getSummarizedStats(id, CURRENT_SEASON));
	}
	
	public static void printSummarizedStats(String name) {
		printSummarizedStats(getSummarizedStats(name, CURRENT_SEASON));
	}
	
	public static void printSummarizedStats(long id, int season) {
		printSummarizedStats(getSummarizedStats(id, season));
	}
	
	public static void printSummarizedStats(String name, int season) {
		printSummarizedStats(getSummarizedStats(name, season));
	}
	
	public static JSONObject getRankedStats(long id) {
		return getRankedStats(id, CURRENT_SEASON);
	}
	
	public static JSONObject getRankedStats(long id, int season) {
		String url = BASE_URL + id + "/ranked/?api_key=" + RiotStuff.API_KEY + "&season=SEASON" + season;
		return RiotStuff.getJSONObject(url);
	}
	
	public static JSONObject getRankedStats(String name) {
		return getRankedStats(name, CURRENT_SEASON);
	}
	
	public static JSONObject getRankedStats(String name, int season) {
		return getRankedStats(Summoner.getSummonerId(name), season);
	}
	
	public static void printRankedStats(long id) {
		printRankedStats(id, CURRENT_SEASON);
	}
	
	public static void printRankedStats(String name) {
		printRankedStats(name, CURRENT_SEASON);
	}
	
	public static void printRankedStats(long id, int season) {
		JSONObject stats = getRankedStats(id, season);
		printRankedStats(stats);
	}
	
	public static void printRankedStats(String name, int season) {
		printRankedStats(Summoner.getSummonerId(name), season);
	}
	
	public static void printRankedChampionStats(long id, String champ) {
		printRankedChampionStats(id, champ, CURRENT_SEASON);
	}
	
	public static void printRankedChampionStats(String name, String champ) {
		printRankedChampionStats(name, champ, CURRENT_SEASON);
	}
	
	public static void printRankedChampionStats(long id, String champ, int season) {
		try {
			JSONObject stats = getRankedStats(id, season);
			JSONArray champions = stats.getJSONArray("champions");
			int champId = Champion.getChampId(champ);
			for(int i = 0; i < champions.length(); i++) {
				JSONObject c = champions.getJSONObject(i);
				if(c.getInt("id") == champId) {
					JSONObject cs = c.getJSONObject("stats");
					System.out.println("" + c.getString("name"));
					System.out.println("====================================");
					printAggregatedStats(cs, true);
					return;
				}
			}
//			System.out.println("Champion '" + champ + "' not found");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static void printRankedChampionStats(String name, String champ, int season) {
		printRankedChampionStats(Summoner.getSummonerId(name), champ, season);
	}
	
	public static JSONObject getCombinedRankedStats(long id) {
		return getCombinedRankedStats(id, CURRENT_SEASON);
	}
	
	public static JSONObject getCombinedRankedStats(String name) {
		return getCombinedRankedStats(Summoner.getSummonerId(name), CURRENT_SEASON);
	}
	
	public static JSONObject getCombinedRankedStats(long id, int season) {
		try {
			JSONObject stats = getRankedStats(id, season);
			JSONArray champions = stats.getJSONArray("champions");
			return champions.getJSONObject(champions.length() - 1);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject getCombinedRankedStats(String name, int season) {
		return getCombinedRankedStats(Summoner.getSummonerId(name), season);
	}
	
	public static void printCombinedStats(long id) {
		printCombinedStats(id, CURRENT_SEASON);
	}
	
	public static void printCombinedStats(String name) {
		printCombinedStats(name, CURRENT_SEASON);
	}
	
	public static void printCombinedStats(long id, int season) {
		try {
			JSONObject stats = getCombinedRankedStats(id, season);
			JSONObject cs = stats.getJSONObject("stats");
			System.out.println("Combined Stats");
			System.out.println("====================================");
			printAggregatedStats(cs, true);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static void printCombinedStats(String name, int season) {
		printCombinedStats(Summoner.getSummonerId(name), season);
	}
	
}
