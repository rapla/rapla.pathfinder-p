package com.pathfinder.presenter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.pathfinder.model.DataModel;
import com.pathfinder.model.RoomPojo;

@Deprecated
public class dataloader {

	public static void main(String[] args) {

		// InputStream input = new
		// URL("http://localhost:8051/rapla/json/RaplaJsonService?method=getResources&jsonrpc=2.0&params=%5B%22room%22%2C%22%22%5D").openStream();
		// Reader reader = new InputStreamReader(input, "UTF-8");

		BufferedReader br;
		Gson gson = new Gson();
		try {
			br = new BufferedReader(new FileReader("c:\\RaplaJsonService"));

			DataModel data = gson.fromJson(br, DataModel.class);

			List<RoomPojo> rooms = data.getAllRooms();

			System.out.println(rooms);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
