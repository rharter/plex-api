package com.plexmediaserver.api.v1;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.StartElementListener;

public class Episode {
	private int mId;
	private String mKey;
	private String mTitle;
	private String mSummary;
	private float mRating;
	private int mYear;
	private String mThumb;
	private String mVideoLink;
	
	public static List<Episode> appendArrayListener(final Element parent, int depth) {
		final List<Episode> episodes = new ArrayList<Episode>();
		final Episode episode = new Episode();
		
		Element episodeElement = parent.getChild("Video");
		episodeElement.setEndElementListener(new EndElementListener() {
			@Override
			public void end() {
				episodes.add(episode.copy());
				episode.clear();
			}
		});
		
		appendCommonListeners(episodeElement, episode, depth);
		
		return episodes;
	}
	
	private static void appendCommonListeners(final Element episodeElement, final Episode episode, int depth) {
		episodeElement.setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				String value = attributes.getValue("ratingKey");
				episode.setId(Integer.parseInt(value));
				
				value = attributes.getValue("key");
				episode.setKey(value);
				
				value = attributes.getValue("title");
				episode.setTitle(value);
				
				value = attributes.getValue("summary");
				episode.setSummary(value);
				
				value = attributes.getValue("rating");
				if (value != null)
					episode.setRating(Float.parseFloat(value));
				
				value = attributes.getValue("year");
				episode.setYear(Integer.parseInt(value));
				
				value = attributes.getValue("thumb");
				episode.setThumb(value);
			}
		});
		
		episodeElement.getChild("Media").getChild("Part").setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				String value = attributes.getValue("key");
				episode.setVideoLink(value);
			}
		});
	}
	
	public int getId() {
		return mId;
	}
	
	public void setId(int id) {
		mId = id;
	}
	
	public String getKey() {
		return mKey;
	}
	
	public void setKey(String key) {
		mKey = key;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public void setTitle(String title) {
		mTitle = title;
	}
	
	public String getSummary() {
		return mSummary;
	}
	
	public void setSummary(String summary) {
		mSummary = summary;
	}
	
	public float getRating() {
		return mRating;
	}
	
	public void setRating(float rating) {
		mRating = rating;
	}
	
	public int getYear() {
		return mYear;
	}
	
	public void setYear(int year) {
		mYear = year;
	}
	
	public String getThumb() {
		return mThumb;
	}
	
	public void setThumb(String thumb) {
		mThumb = thumb;
	}
	
	public String getVideoLink() {
		return mVideoLink;
	}
	
	public void setVideoLink(String link) {
		mVideoLink = link;
	}
	
	public void clear() {
		this.setId(-1);
		this.setKey("");
		this.setTitle("");
		this.setSummary("");
		this.setRating(0.0f);
		this.setYear(2000);
		this.setThumb("");
		this.setVideoLink("");
	}
	
	public Episode copy() {
		Episode newEpisode = new Episode();
		newEpisode.setId(this.getId());
		newEpisode.setKey(this.getKey());
		newEpisode.setTitle(this.getTitle());
		newEpisode.setSummary(this.getSummary());
		newEpisode.setRating(this.getRating());
		newEpisode.setYear(this.getYear());
		newEpisode.setThumb(this.getThumb());
		newEpisode.setVideoLink(this.getVideoLink());
		return newEpisode;
	}
}
