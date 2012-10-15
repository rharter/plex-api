package com.plexmediaserver.api.v1;

import java.util.*;
import java.io.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.xml.sax.SAXException;

import android.location.Location;
import android.net.Uri;
import android.sax.RootElement;
import android.util.Xml;
import android.util.Log;

public class PlexAPI {
	
	private List<Server> mServers;
	
	public static List<Section> getSections() {
		Server server = new Server();
		HttpGet request = new HttpGet("http://" + server.getHost() + ":" + server.getPort() + "/library/sections");
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
	
	public static List<Movie> getAllMoviesForSection(int section) {
		Server server = new Server();
		HttpGet request = new HttpGet(server.getAddress() + "/library/sections/" + section + "/all");
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
	
	public static List<Show> getAllShowsForSection(int section) {
		Server server = new Server();
		HttpGet request = new HttpGet(server.getAddress() +	"/library/sections/" + section + "/all");
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
	
	public static List<Season> getSeasonsForShow(int show) {
		Server server = new Server();
		HttpGet request = new HttpGet(server.getAddress() + "/library/metadata/" + show + "/children");
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
	
	public static List<Episode> getEpisodesForSeason(int season) {
		Server server = new Server();
		HttpGet request = new HttpGet(server.getAddress() + "/library/metadata/" + season + "/children");
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
	
	public static class Server {
		private String mHost;
		private int mPort;
		
		public Server() {
			mHost = "192.168.1.8";
			mPort = 32400;
		}
		
		public String getHost() {
			return mHost;
		}
		
		public int getPort() {
			return mPort;
		}
		
		public String getAddress() {
			return "http://" + mHost + ":" + mPort;
		}
	}
}
