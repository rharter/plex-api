package com.plexmediaserver.api.v1;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.StartElementListener;

public class Show {

	private int mId;
	private String mKey;
	private String mStudio;
	private String mTitle;
	private String mContentRating;
	private String mSummary;
	private float mRating;
	private int mYear;
	private String mThumb;
	private String mArt;
	private String mBanner;
	private String mTheme;
	private int mDuration;
	private int mEpisodeCount;
	private int mViewedEpisodeCount;
	
	public static List<Show> appendArrayListener(final Element parent, int depth) {
		final List<Show> shows = new ArrayList<Show>();
		final Show show = new Show();
		
		Element showElement = parent.getChild("Directory");
		showElement.setEndElementListener(new EndElementListener() {
			@Override
			public void end() {
				shows.add(show.copy());
				show.clear();
			}
		});
		
		appendCommonListeners(showElement, show, depth);
		
		return shows;
	}
	
	private static void appendCommonListeners(final Element showElement, final Show show, int depth) {
		showElement.setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				String value = attributes.getValue("ratingKey");
				show.setId(Integer.parseInt(value));
				
				value = attributes.getValue("key");
				show.setKey(value);
				
				value = attributes.getValue("studio");
				show.setStudio(value);
				
				value = attributes.getValue("title");
				show.setTitle(value);
				
				value = attributes.getValue("contentRating");
				show.setContentRating(value);
				
				value = attributes.getValue("summary");
				show.setSummary(value);
				
				value = attributes.getValue("rating");
				show.setRating(Float.parseFloat(value));
				
				value = attributes.getValue("year");
				show.setYear(Integer.parseInt(value));
				
				value = attributes.getValue("thumb");
				show.setThumb(value);
				
				value = attributes.getValue("art");
				show.setArt(value);
				
				value = attributes.getValue("banner");
				show.setBanner(value);
				
				value = attributes.getValue("theme");
				show.setTheme(value);
				
				value = attributes.getValue("duration");
				show.setDuration(Integer.parseInt(value));
				
				value = attributes.getValue("leafCount");
				show.setEpisodeCount(Integer.parseInt(value));
				
				value = attributes.getValue("viewedLeafCount");
				show.setViewedEpisodeCount(Integer.parseInt(value));
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
	
	public String getStudio() {
		return mStudio;
	}
	
	public void setStudio(String studio) {
		mStudio = studio;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public void setTitle(String title) {
		mTitle = title;
	}
	
	public String getContentRating() {
		return mContentRating;
	}
	
	public void setContentRating(String contentRating) {
		mContentRating = contentRating;
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
	
	public String getArt() {
		return mArt;
	}
	
	public void setArt(String art) {
		mArt = art;
	}
	
	public String getBanner() {
		return mBanner;
	}
	
	public void setBanner(String banner) {
		mBanner = banner;
	}
	
	public String getTheme() {
		return mTheme;
	}
	
	public void setTheme(String theme) {
		mTheme = theme;
	}
	
	public int getDuration() {
		return mDuration;
	}
	
	public void setDuration(int duration) {
		mDuration = duration;
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
	
	public void clear() {
		this.setId(-1);
		this.setKey("");
		this.setStudio("");
		this.setTitle("");
		this.setContentRating("");
		this.setSummary("");
		this.setRating(0.0f);
		this.setYear(2000);
		this.setThumb("");
		this.setArt("");
		this.setBanner("");
		this.setTheme("");
		this.setDuration(0);
		this.setEpisodeCount(0);
		this.setViewedEpisodeCount(0);
	}
	
	public Show copy() {
		Show newShow = new Show();
		newShow.setId(this.getId());
		newShow.setKey(this.getKey());
		newShow.setStudio(this.getStudio());
		newShow.setTitle(this.getTitle());
		newShow.setContentRating(this.getContentRating());
		newShow.setSummary(this.getSummary());
		newShow.setRating(this.getRating());
		newShow.setYear(this.getYear());
		newShow.setThumb(this.getThumb());
		newShow.setArt(this.getArt());
		newShow.setBanner(this.getBanner());
		newShow.setTheme(this.getTheme());
		newShow.setDuration(this.getDuration());
		newShow.setEpisodeCount(this.getEpisodeCount());
		newShow.setViewedEpisodeCount(this.getViewedEpisodeCount());
		return newShow;
	}
}
