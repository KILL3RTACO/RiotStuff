package com.kill3rtaco.riotstuff;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import org.json.JSONException;
import org.json.JSONObject;

public class RiotStuff {
	
	public static final String	REGION		= "na";
	
	public static final String	BASE_URL	= "http://prod.api.pvp.net/api/lol/" + REGION + "/";
	
	// ;D
	public static final String	API_KEY		= "f72e897c-9c9f-4b07-b66b-5b2b48133c76";
	
	public static JSONObject getJSONObject(String url) {
		try {
			String str = "";
			Scanner x = new Scanner(new URL(url).openStream());
			while (x.hasNextLine()) {
				str += x.nextLine() + "\n";
			}
			return new JSONObject(str);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if(e.getMessage().contains("429")) {
				System.out.println("Request limit of 10 per 10s exceeded");
				System.exit(1);
			} else {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private static boolean eic(String str, String... tests) {
		for(String s : tests) {
			if(str.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}
	
	//category.method [options]
	public static void main(String[] args) {
		//args = new String[]{"game.recent", "--name=Miss%20E2266"};
		if(args.length < 1) {
			System.out.println("Need at least one argument: <category.method> [options]");
			return;
		}
		String str = args[0];
		args = Util.removeFirstArg(args);
		if(!str.matches("[^\\.\\s]+\\.[^.\\s]+")) {
			System.out.println("Incorrect category.method pattern.");
			System.exit(2);
		}
		int index = str.indexOf(".");
		String cat = str.substring(0, index);
		String method = str.substring(index + 1);
		
		OptionParser parser = new OptionParser();
		parser.accepts("name").withRequiredArg().ofType(String.class);
		parser.accepts("id").withRequiredArg().ofType(Long.class);
		parser.accepts("ftp").withOptionalArg().ofType(Boolean.class).defaultsTo(true);
		parser.accepts("game").withRequiredArg().ofType(Integer.class);
		parser.accepts("season").withRequiredArg().ofType(Integer.class);
//		parser.accepts("queue").withRequiredArg();
		parser.accepts("champion").withRequiredArg();
		parser.accepts("combined").withOptionalArg().ofType(Boolean.class).defaultsTo(true);
		
		OptionSet options = parser.parse(args);
		
		try {
			if(eic(cat, "champ", "champion")) {
				if(eic(method, "champs", "champions")) {
					if(options.has("ftp")) {
						Champion.printAllChamps((Boolean) options.valueOf("ftp"));
					} else if(options.has("id")) {
						Champion.printChamp((Long) options.valueOf("id"));
					} else if(options.has("name")) {
						Champion.printChamp((String) options.valueOf("name"));
					} else {
						Champion.printAllChamps(false);
					}
				} else {
					System.out.println("Unknown method");
				}
			} else if(eic(cat, "game")) {
				if(eic(method, "recent")) {
					long id = -1;
					String name = "";
					int gameIndex = -1;
					if(options.has("id")) {
						id = (Long) options.valueOf("id");
					} else if(options.has("name")) {
						name = (String) options.valueOf("name");
					} else {
						System.out.println("Must specify either name or id flag for this method");
						return;
					}
					if(options.has("game")) {
						gameIndex = (Integer) options.valueOf("game");
						if(gameIndex < 0 || gameIndex > 9) {
							System.out.println("Value for game must be between 0 and 9");
							return;
						}
						if(id > 0) {
							Game.printRecentGame(id, gameIndex);
						} else if(!name.isEmpty()) {
							Game.printRecentGame(name, gameIndex);
						}
						return;
					}
					if(id > 0) {
						Game.printAllRecentGames(id);
					} else if(!name.isEmpty()) {
						Game.printAllRecentGames(name);
					}
					return;
				} else {
					System.out.println("Unknown Method");
				}
			} else if(eic(cat, "summoner")) {
				if(eic(method, "name")) {
					if(options.has("id")) {
						long id = (Long) options.valueOf("id");
						System.out.println(Summoner.getSummonerName(id));
					} else {
						System.out.println("id flag required for this method");
						return;
					}
				} else if(eic(method, "id")) {
					if(options.has("name")) {
						String name = (String) options.valueOf("name");
						System.out.println(Summoner.getSummonerId(name));
					} else {
						System.out.println("name flag required for this method");
						return;
					}
				} else if(eic(method, "info")) {
					long id = -1;
					String name = "";
					if(options.has("id")) {
						id = (Long) options.valueOf("id");
					} else if(options.has("name")) {
						name = (String) options.valueOf("name");
					} else {
						System.out.println("Must specify either name or id flag for this method");
						return;
					}
					if(id > 0) {
						Summoner.printSummoner(id);
					} else if(!name.isEmpty()) {
						Summoner.printSummoner(name);
					}
				}
			} else if(eic(cat, "stats")) {
				if(eic(method, "sum", "summary")) {
					long id = -1;
					String name = "";
					if(options.has("id")) {
						id = (Long) options.valueOf("id");
					} else if(options.has("name")) {
						name = (String) options.valueOf("name");
					} else {
						System.out.println("Must specify either name or id flag for this method");
						return;
					}
					int season = Stats.CURRENT_SEASON;
					if(options.has("season")) {
						season = (Integer) options.valueOf("season");
					}
					if(id > 0) {
						Stats.printSummarizedStats(id, season);
					} else if(!name.isEmpty()) {
						Stats.printSummarizedStats(name, season);
					}
				} else if(eic(method, "rank", "ranked")) {
					long id = -1;
					String name = "";
					if(options.has("id")) {
						id = (Long) options.valueOf("id");
					} else if(options.has("name")) {
						name = (String) options.valueOf("name");
					} else {
						System.out.println("Must specify either name or id flag for this method");
						return;
					}
					int season = Stats.CURRENT_SEASON;
					if(options.has("season")) {
						season = (Integer) options.valueOf("season");
					}
					if(options.has("combined")) {
						if(id > 0) {
							Stats.printCombinedStats(id, season);
						} else if(!name.isEmpty()) {
							Stats.printCombinedStats(name, season);
						}
						return;
					}
					if(options.has("champion")) {
						String champ = (String) options.valueOf("champion");
						if(id > 0) {
							Stats.printRankedChampionStats(id, champ, season);
						} else if(!name.isEmpty()) {
							Stats.printRankedChampionStats(name, champ, season);
						}
						return;
					}
					if(id > 0) {
						Stats.printRankedStats(id, season);
					} else if(!name.isEmpty()) {
						Stats.printRankedStats(name, season);
					}
				}
			} else if(eic(cat, "team")) {
				
			} else if(eic(cat, "league")) {
				
			} else {
				System.out.println("Unknown category");
			}
		} catch (OptionException e) {
			System.out.println(e.getMessage());
		}
	}
}
