package com.pathfinder.model;

import java.io.BufferedReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Model for the keyboard
 * 
 * @author Myracle
 */
public class KeyboardModel {
	private static final Logger LOGGER = LogManager
			.getLogger(KeyboardModel.class.getName());

	public static final String PROPERTY_SEARCHSTRING = "searchString";

	private static final List<String> BLACKLIST = new ArrayList<>();
	private static final String BLACKLIST_NAME = "search_blacklist.txt";
	private static final String COMMENT_CHAR = "#";

	static {
		loadBlacklist();
	}

	private String searchString = "";

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		if (checkIfSearchStringInBlackList(searchString)) {
			this.searchString = "";
		} else {
			this.searchString = searchString;
		}
	}

	private boolean checkIfSearchStringInBlackList(String searchString) {
		boolean inBlackList = false;
		for (String blackListString : BLACKLIST) {
			if (searchString.contains(blackListString)) {
				inBlackList = true;
				break;
			}
		}
		return inBlackList;
	}

	private static void loadBlacklist() {
		URL url = KeyboardModel.class.getClassLoader().getResource(
				BLACKLIST_NAME);
		if (url != null) {
			try (BufferedReader br = Files.newBufferedReader(
					Paths.get(url.toURI()), Charset.forName("utf-8"))) {
				String line;
				while ((line = br.readLine()) != null) {
					if (line.length() > 0 && !line.startsWith(COMMENT_CHAR)) {
						BLACKLIST.add(line);
					}
				}
			} catch (Exception ex) {
				LOGGER.error("Couldn't load blacklist for search string!", ex);
			}

		}
	}
}