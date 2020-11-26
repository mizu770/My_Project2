package com.project.Trip.domain;

import java.sql.Date;

public class Trip {
	
	
	private String channel_id;
	private String channel_title ;
	private Date collected_date ;
	private int channel_view_count;
	private int channel_subscriber_count;
	private String video_tag;
	private String video_id;
	private String video_title;
	private Date video_publishedAt;
	public Trip() {}
	
	public Trip( String channel_id, String channel_title, Date collected_date,int channel_subscriber_count,int channel_view_count,String video_id,String video_title,Date video_publishedAt,String video_tag) {
		this.channel_id=channel_id;
		this.channel_title=channel_title;
		this.collected_date=collected_date;
		this.channel_subscriber_count=channel_subscriber_count;
		this.channel_view_count=channel_view_count;
		this.video_tag=video_tag;
		this.video_id=video_id;
		this.video_title=video_title;
		this.video_publishedAt=video_publishedAt;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_title() {
		return channel_title;
	}

	public void setChannel_title(String channel_title) {
		this.channel_title = channel_title;
	}

	public Date getCollected_date() {
		return collected_date;
	}

	public void setCollected_date(Date collected_date) {
		this.collected_date = collected_date;
	}

	public int getChannel_view_count() {
		return channel_view_count;
	}

	public void setChannel_view_count(int channel_view_count) {
		this.channel_view_count = channel_view_count;
	}

	public int getChannel_subscriber_count() {
		return channel_subscriber_count;
	}

	public void setChannel_subscriber_count(int channel_subscriber_count) {
		this.channel_subscriber_count = channel_subscriber_count;
	}

	public String getVideo_tag() { 
		return video_tag; 
		}
 
    public void setVideo_tag(String video_tag) {
    	this.video_tag = video_tag; 
    	}

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public String getVideo_title() {
		return video_title;
	}

	public void setVideo_title(String video_title) {
		this.video_title = video_title;
	}

	public Date getVideo_publishedAt() {
		return video_publishedAt;
	}

	public void setVideo_publishedAt(Date video_publishedAt) {
		this.video_publishedAt = video_publishedAt;
	}

	
	
}
