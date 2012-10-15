package com.plexmediaserver.api.v1;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.StartElementListener;

public class Section {
	private boolean mRefreshing;
	private int mKey;
	private String mType;
	private String mTitle;
	private String mArt;
	
	public static List<Section> appendArrayListener(final Element parentElement, int depth) {
		final List<Section> sections = new ArrayList<Section>();
		final Section section = new Section();
		
		Element sectionElement = parentElement.getChild("Directory");
		sectionElement.setEndElementListener(new EndElementListener() {
			@Override
			public void end() {
				sections.add(section.copy());
				section.clear();
			}
		});
		
		appendCommonListeners(sectionElement, section, depth);
		
		return sections;
	}
	
	private static void appendCommonListeners(final Element sectionElement, final Section section, int depth) {
		sectionElement.setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				String value = attributes.getValue("refreshing");
				section.setRefreshing(value == "1");
				
				value = attributes.getValue("key");
				section.setKey(Integer.parseInt(value));
				
				value = attributes.getValue("type");
				section.setType(value);
				
				value = attributes.getValue("title");
				section.setTitle(value);
				
				value = attributes.getValue("art");
				section.setArt(value);
			}
		});
	}
	
	public boolean isRefreshing() {
		return mRefreshing;
	}
	
	public void setRefreshing(boolean refreshing) {
		mRefreshing = refreshing;
	}
	
	public int getKey() {
		return mKey;
	}
	
	public void setKey(int key) {
		mKey = key;
	}
	
	public String getType() {
		return mType;
	}
	
	public void setType(String type) {
		mType = type;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public void setTitle(String title) {
		mTitle = title;
	}
	
	public String getArt() {
		return mArt;
	}
	
	public void setArt(String art) {
		mArt = art;
	}
	
	public Section copy() {
		Section newSection = new Section();
		
		newSection.setRefreshing(this.isRefreshing());
		newSection.setKey(this.getKey());
		newSection.setType(this.getType());
		newSection.setTitle(this.getTitle());
		newSection.setArt(this.getArt());
		
		return newSection;
	}
	
	public void clear() {
		this.setRefreshing(false);
		this.setKey(-1);
		this.setType("");
		this.setTitle("");
		this.setArt("");
	}
}
