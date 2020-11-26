package com.project.Trip.controller;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Trip.domain.Trip;
import com.project.Trip.service.TripService;

@Controller
public class TripController {
	
	
	@Autowired
	private TripService tripService;
	
	public void setTripService(TripService tripService) {
		this.tripService = tripService;
	}
        
      @RequestMapping(value= {"/tripList","/trip"},method=RequestMethod.GET)
      public String tripList(Model model,@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum) {
    	  // service 클래스를 이용해서 게시글 리스트를 가져옴
    	  Map<String,Object>modelMap=tripService.tripList(pageNum);
    	  model.addAllAttributes(modelMap);
    	  System.out.println(modelMap);
    	  return "tripList";
      }
	
      @RequestMapping("/tripDetail")
      public String tripDetail(Model model, String channel_id,@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum) {
    	  String json="";
    	  String v_json="";
    	  Trip trip = tripService.getTirp(channel_id);
    	  List<Trip> trips =tripService.getTripList(channel_id);
    	  List<Trip> video =tripService.getVideo(channel_id);
    	  List<String>tag =tripService.getTag(channel_id);
    	  ObjectMapper objectMapper = new ObjectMapper();
    	  
    	  try {
    	      json = objectMapper.writeValueAsString(trips);
    	  } catch (Exception e) {
    		  e.printStackTrace();
		}
    	    	 
    	  for(int i=0; i<tag.size(); i++) {
    		  v_json=tag.get(i);
    	  }
    	  try {
    		  if (v_json == null) {
    			  v_json ="태그를 사용하지 않습니다";
    		  }
    	  v_json=v_json.replaceAll("\\[","");
    	  v_json=v_json.replaceAll("\\]","");
    	  } catch (Exception e){
    		  e.printStackTrace();
    	  }
    	  System.out.println(v_json);
    	  model.addAttribute("trip",trip);
    	  model.addAttribute("pageNum",pageNum);
    	  model.addAttribute("json",json);  
    	  model.addAttribute("video",video);
    	  model.addAttribute("v_json",v_json);
    	  
    	  return "tripDetail";
      }
      
    
	
	
}
