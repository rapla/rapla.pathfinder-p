package com.pathfinder.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.pathfinder.model.DataModel;

public class DataLoaderPresenter {
	private BufferedReader br;
	private Gson gson = new Gson();
	private DataModel data;

	public void LoadRooms() {

		try {
			br = new BufferedReader(
					new InputStreamReader(
							new URL(
									"http://localhost:8051/rapla/json/RaplaJsonService?method=getResources&jsonrpc=2.0&params=%5B%22rooms%22%2C%22%22%5D")
									.openStream()));
		} catch (MalformedURLException e) {
			System.out.println("Error loading URL (Rooms)");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		data = gson.fromJson(br, DataModel.class);
		if (data != null) {
			DataModel.setAllRooms(data.getResult());
			System.out.println("All Rooms are loaded");
		}
	}

	public void LoadCourses() {
		try {
			br = new BufferedReader(
					new InputStreamReader(
							new URL(
									"http://localhost:8051/rapla/json/RaplaJsonService?method=getResources&jsonrpc=2.0&params=%5B%22courses%22%2C%22%22%5D")
									.openStream()));
		} catch (MalformedURLException e) {
			System.out.println("Error loading URL (Courses)");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		data = gson.fromJson(br, DataModel.class);
		if (data != null) {
			DataModel.setAllCourses(data.getResult());
			System.out.println("All Courses are loaded");
		}

	}

	public void LoadPersons() {
		try {
			br = new BufferedReader(
					new InputStreamReader(
							new URL(
									"http://localhost:8051/rapla/json/RaplaJsonService?method=getResources&jsonrpc=2.0&params=%5B%22persons%22%2C%22%22%5D")
									.openStream()));
		} catch (MalformedURLException e) {
			System.out.println("Error loading URL (Persons)");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		data = gson.fromJson(br, DataModel.class);
		if (data != null) {
			DataModel.setAllPersons(data.getResult());
			System.out.println("All Persons are loaded");
		}
	}

}
