package com.plexmediaserver.api.v1;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.StartElementListener;

public class Movie {
	private int mId;
	private String mKey;
	private String mStudio;
	private String mTitle;
	private String mSummary;
	private String mTagline;
	private String mContentRating;
	private float mRating;
	private int mYear;
	private String mThumb;
	private String mArt;
	private String mVideoLink;
	
	public static List<Movie> appendArrayListener(final Element parent, int depth) {
		final List<Movie> movies = new ArrayList<Movie>();
		final Movie movie = new Movie();
		
		Element movieElement = parent.getChild("Video");
		movieElement.setEndElementListener(new EndElementListener() {
			@Override
			public void end() {
				movies.add(movie.copy());
				movie.clear();
			}
		});
		
		appendCommonListeners(movieElement, movie, depth);
		
		return movies;
	}
	
	private static void appendCommonListeners(final Element movieElement, final Movie movie, int depth) {
		movieElement.setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				String value = attributes.getValue("ratingKey");
				movie.setId(Integer.parseInt(value));
				
				value = attributes.getValue("key");
				movie.setKey(value);
				
				value = attributes.getValue("studio");
				movie.setStudio(value);
				
				value = attributes.getValue("title");
				movie.setTitle(value);
				
				value = attributes.getValue("summary");
				movie.setSummary(value);
				
				value = attributes.getValue("tagline");
				movie.setTagline(value);
				
				value = attributes.getValue("contentRating");
				movie.setContentRating(value);
				
				value = attributes.getValue("rating");
				if (value != null)
					movie.setRating(Float.parseFloat(value));
				
				value = attributes.getValue("year");
				if (value != null)
					movie.setYear(Integer.parseInt(value));
				
				value = attributes.getValue("thumb");
				movie.setThumb(value);
				
				value = attributes.getValue("art");
				movie.setArt(value);
			}
		});
		
		movieElement.getChild("Media").getChild("Part").setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				String value = attributes.getValue("key");
				movie.setVideoLink(value);
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
	
	public String getSummary() {
		return mSummary;
	}
	
	public void setSummary(String summary) {
		mSummary = summary;
	}
	
	public String getTagline() {
		return mTagline;
	}
	
	public void setTagline(String tagline) {
		mTagline = tagline;
	}
	
	public String getContentRating() {
		return mContentRating;
	}
	
	public void setContentRating(String contentRating) {
		mContentRating = contentRating;
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
	
	public String getVideoLink() {
		return mVideoLink;
	}
	
	public void setVideoLink(String link) {
		mVideoLink = link;
	}
	
	public void clear() {
		this.setId(-1);
		this.setKey("");
		this.setStudio("");
		this.setTitle("");
		this.setSummary("");
		this.setTagline("");
		this.setContentRating("");
		this.setRating(0.0f);
		this.setYear(2000);
		this.setThumb("");
		this.setArt("");
		this.setVideoLink("");
	}
	
	public Movie copy() {
		Movie newMovie = new Movie();
		newMovie.setId(this.getId());
		newMovie.setKey(this.getKey());
		newMovie.setStudio(this.getStudio());
		newMovie.setTitle(this.getTitle());
		newMovie.setSummary(this.getSummary());
		newMovie.setTagline(this.getTagline());
		newMovie.setContentRating(this.getContentRating());
		newMovie.setRating(this.getRating());
		newMovie.setYear(this.getYear());
		newMovie.setThumb(this.getThumb());
		newMovie.setArt(this.getArt());
		newMovie.setVideoLink(this.getVideoLink());
		return newMovie;
	}
}
