package com.project.Trip.dao;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.Trip.domain.Trip;
@Repository
public class TripDaoImpl implements TripDao {

	private final String NAME_SPACE = "com.project.Trip.mapper.TripMapper";
	
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<Trip> tirpList(int startRow, int num) {
		
		Map<String,Integer>params = new HashMap<String,Integer>();
		params.put("startRow",startRow);
		params.put("num",num);
		return sqlSession.selectList(NAME_SPACE + ".tripList",params);
	}

	@Override
	public Trip getTrip(String channel_id){
		
		return sqlSession.selectOne(NAME_SPACE+".getTrip",channel_id);
		
	}
	@Override
	public int getTripCount() {
		
		return sqlSession.selectOne(NAME_SPACE + ".getTripCount");
	}
	
	public  List<Trip> getTripList(String channel_id ) {
		Map<String,Object>params = new HashMap<String,Object>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		Date collected_dates = new Date();
        String collected_date = format1.format(collected_dates);
		params.put("collected_date",collected_date);
		params.put("channel_id",channel_id);
		return sqlSession.selectList(NAME_SPACE+".getTripList",params);
	}

	@Override
	public List<Trip> getVideo(String channel_id) {
		Map<String,Object>params = new HashMap<String,Object>();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		Date video_publishedAts = new Date();
        String video_publishedAt = format1.format(video_publishedAts);
		params.put("video_publishedAt",video_publishedAt);
		params.put("channel_id",channel_id);
		return sqlSession.selectList(NAME_SPACE+".getVideo",params);
	}

	@Override
	public List<String> getTag(String channel_id) {
		
		return sqlSession.selectList(NAME_SPACE+".getVideo_tag",channel_id);
	}
	
}
