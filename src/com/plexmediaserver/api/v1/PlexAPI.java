package com.plexmediaserver.api.v1;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.sax.RootElement;
import android.util.Log;
import android.util.Xml;

public class PlexAPI {
	
	public static List<Section> getSections(String address) {
		HttpGet request = new HttpGet(address + "/library/sections");
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			System.err.println("Failed to connect to server.");
			e.printStackTrace();
		}
		
		RootElement root = new RootElement("MediaContainer");
		
		List<Section> sections = Section.appendArrayListener(root, 0);
		
		try {
			Xml.parse(response.getEntity().getContent(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sections;
	}
	
	public static List<Movie> getAllMoviesForSection(String address, int section) {
		HttpGet request = new HttpGet(address + "/library/sections/" + section + "/all");
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RootElement root = new RootElement("MediaContainer");
		List<Movie> movies = Movie.appendArrayListener(root, 0);
		
		try {
			Xml.parse(response.getEntity().getContent(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("PlexAPI", "Found " + movies.size() + " movies.");
		
		return movies;
	}
	
	public static List<Show> getAllShowsForSection(String address, int section) {
		HttpGet request = new HttpGet(address +	"/library/sections/" + section + "/all");
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RootElement root = new RootElement("MediaContainer");
		
		List<Show> shows = Show.appendArrayListener(root, 0);
		
		try {
			Xml.parse(response.getEntity().getContent(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return shows;
	}
	
	public static List<Season> getSeasonsForShow(String address, int show) {
		HttpGet request = new HttpGet(address + "/library/metadata/" + show + "/children");
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RootElement root = new RootElement("MediaContainer");
		List<Season> seasons = Season.appendArrayListener(root, 0);
		
		try {
			Xml.parse(response.getEntity().getContent(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return seasons;
	}
	
	public static List<Episode> getEpisodesForSeason(String address, int season) {
		HttpGet request = new HttpGet(address + "/library/metadata/" + season + "/children");
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RootElement root = new RootElement("MediaContainer");
		List<Episode> episodes = Episode.appendArrayListener(root, 0);
		
		try {
			Xml.parse(response.getEntity().getContent(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Log.d("PlexAPI", "Got Episodes: " + episodes.toString());
		
		return episodes;
	}
}
