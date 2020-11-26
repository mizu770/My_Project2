package com.project.Trip.dao;

import java.util.List;

import com.project.Trip.domain.Trip;
import com.sun.accessibility.internal.resources.accessibility;

public interface TripDao {
	
	public abstract List<Trip> tirpList(int startRow,int num);
	
	public abstract Trip getTrip(String channel_id);

	public abstract int getTripCount();
	
	public abstract List<Trip> getTripList(String channel_id );
	
	public abstract List<Trip> getVideo(String channel_id);
	
	public abstract List<String> getTag(String channel_id);
}
