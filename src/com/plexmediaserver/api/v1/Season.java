package com.plexmediaserver.api.v1;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.StartElementListener;

public class Season {
	private int mId;
	private String mKey;
	private String mTitle;
	private String mThumb;
	private int mEpisodeCount;
	private int mViewedEpisodeCount;
	
	public static List<Season> appendArrayListener(final Element parent, int depth) {
		final List<Season> seasons = new ArrayList<Season>();
		final Season season = new Season();
		
		Element seasonElement = parent.getChild("Directory");
		seasonElement.setEndElementListener(new EndElementListener() {
			@Override
			public void end() {
				seasons.add(season.copy());
				season.clear();
			}
		});
		
		appendCommonListeners(seasonElement, season, depth);
		
		return seasons;
	}
	
	private static void appendCommonListeners(final Element seasonElement, final Season season, int depth) {
		seasonElement.setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				String value = attributes.getValue("ratingKey");
				if (value != null) {
					season.setId(Integer.parseInt(value));
				}
				
				value = attributes.getValue("key");
				season.setKey(value);
				
				value = attributes.getValue("title");
				season.setTitle(value);
				
				value = attributes.getValue("thumb");
				season.setThumb(value);
				
				value = attributes.getValue("leafCount");
				season.setEpisodeCount(Integer.parseInt(value));
				
				value = attributes.getValue("viewedLeafCount");
				season.setViewedEpisodeCount(Integer.parseInt(value));
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
	
	public String getThumb() {
		return mThumb;
	}
	
	public void setThumb(String thumb) {
		mThumb = thumb;
	}
	
	public int getEpisodeCount() {
		return mEpisodeCount;
	}
	
	public void setEpisodeCount(int count) {
		mEpisodeCount = count;
	}
	
	public int getViewedEpisodeCount() {
		return mViewedEpisodeCount;
	}
	
	public void setViewedEpisodeCount(int count) {
		mViewedEpisodeCount = count;
	}
	
	public int getUnwatchedEpisodeCount() {
		return mEpisodeCount - mViewedEpisodeCount;
	}
	
	public Season copy() {
		Season s = new Season();
		s.setId(this.getId());
		s.setKey(this.getKey());
		s.setTitle(this.getTitle());
		s.setThumb(this.getThumb());
		s.setEpisodeCount(this.getEpisodeCount());
		s.setViewedEpisodeCount(this.getViewedEpisodeCount());
		return s;
	}
	
	public void clear() {
		this.setId(-1);
		this.setKey("");
		this.setTitle("");
		this.setThumb("");
		this.setEpisodeCount(0);
		this.setViewedEpisodeCount(0);
	}
}
