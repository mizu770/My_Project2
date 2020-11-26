package com.project.Trip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Trip.dao.TripDao;
import com.project.Trip.domain.Trip;
@Service
public class TripServerImpl implements TripService {

	//한 페이지에 보여줄 게시 글의 수
	private static final int PAGE_SIZE = 10;
	
	// 한페이지에 보여줄 그룹의 수 
	
	private static final int PAGE_GROUP = 10;
	
	@Autowired
	private TripDao tripDao;
	
	public void setTripDao(TripDao tripDao) {
		this.tripDao = tripDao;
	}
	
	@Override
	public Map<String, Object> tripList(int pageNum) {
		
		int currentPage = pageNum; // 요청 파라미터의 pageNum을 현재 페이지로 설정
		
		int startRow = (currentPage -1) * PAGE_SIZE; // 첫번째 페이지는 0, 두번쨰 페이지는 10으로 시작
		
		int listCount = tripDao.getTripCount(); // 전체 게시 글 수를 얻어옴
		
		// 게시글 리스트가 하나 이상 존재 시 요청페이지에 해당하는 게시글 리스트를 db로 얻어와 Trip객체를 저장하는
		// ArrayList에 저장
		
		if(listCount > 0) {
			List<Trip>tirpList = tripDao.tirpList(startRow, PAGE_SIZE);
			
			int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);
			
			int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1 -(currentPage % PAGE_GROUP ==0 ? PAGE_GROUP : 0);
			// 현재 페이지 그룹의 마지막 페이지
			int endPage = startPage + PAGE_GROUP -1;
			
			// endPage는 10단위로 떨어지지만 10단위가 아닌 43, 37 이런 식으로 페이지가 되면 에러발생하므로
			// 처리가 필요 마지막페이지는 pageCount와 같에 만듬
			
			if(endPage >pageCount) {
				endPage = pageCount;
			}
			Map<String,Object>modelMap = new HashMap<String, Object>();
			
			modelMap.put("tripList",tirpList);
			modelMap.put("pageCount",pageCount);
			modelMap.put("startPage",startPage);
			modelMap.put("endPage",endPage);
			modelMap.put("currentPage",currentPage);
			modelMap.put("listCount",listCount);
			modelMap.put("pageGroup",PAGE_GROUP);
			
			return modelMap;
		}else {
			return null;
		}	
	}

	//해당 channelsId에 대한 글을  읽어옴
	@Override
	public Trip getTirp(String channel_id) {
		
		return tripDao.getTrip(channel_id);
	}

	@Override
	public List<Trip> getTripList(String channel_id) {
		
		return tripDao.getTripList(channel_id);
	}

	@Override
	public List<Trip> getVideo(String channel_id) {
		
		return tripDao.getVideo(channel_id);
	}

	@Override
	public List<String> getTag(String channel_id) {
		
		return tripDao.getTag(channel_id);
	}
	
}
