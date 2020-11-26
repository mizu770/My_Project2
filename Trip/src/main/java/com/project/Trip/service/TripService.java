package com.project.Trip.service;


import java.util.List;
import java.util.Map;

import com.project.Trip.domain.Trip;

public interface TripService {
	
	public abstract Map<String, Object>tripList(int pageNum);
	
	public abstract Trip getTirp(String channel_id);
	
	public abstract List<Trip> getTripList(String channel_id);
	
	public abstract List<Trip> getVideo(String channel_id);
	
	public abstract List<String> getTag(String channel_id);
	
}
