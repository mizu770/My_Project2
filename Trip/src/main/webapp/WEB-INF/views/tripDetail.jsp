<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    .axis path,
    .axis line 
    {
        fill: none;
        stroke: black;
        shape-rendering: crispEdges;
    }
    .chart--container {
      height: 10%;
      width: 100%;
      min-height: 300px;
      border: 1px solid black;
      border-radius: 20px;
      
    }

    .zc-ref {
      display: none;
    }
</style>
<article>
<%-- <form name="checkForm" id="checkForm">
	<input type="hidden" name="id" id="id" value="${ trip.channelsId }"/>
	<input type="hidden" name="pageNum" value="${ pageNum }" />
</form> --%>
<table class="contentTable">
	<tr>
		<td colspan="4" class="boardTitle">
			<h1>상세보기</h1>
		</td>
	</tr>
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td class="contentTh">채널&nbsp;&nbsp;이름</td>
		<td class="contentTd" colspan="4">${ trip.channel_title }</td>		
	</tr>
	<tr>
		<td class="contentTh">구독자수</td>
		<td class="contentTd">${ trip.channel_subscriber_count }</td>
		<td class="contentTh">전체 조회수</td>
		<td class="contentTd">${ trip.channel_view_count }</td>	
	</tr>
	<tr>
		<td class="contentTh">해당 유튜브 채널로 바로가기</td>
		<td class="contentTd" colspan ="4">
			<input type="button" value="${ trip.channel_title }" onclick="window.open('https://www.youtube.com/channel/${trip.channel_id}')">
		</td>
	</tr>
	<tr >
				
		<td class="readContent" colspan="4">
		<h3 style=text-align:center>일일 조회수와 구독자수 변화량(단위 : 천)</h3>
			<div id="Line_Controls_Chart">
      <!-- 라인 차트 생성할 영역 -->
          <div id="lineChartArea" style="padding:0px 20px 0px 0px;"></div>
      <!-- 컨트롤바를 생성할 영역 -->
          <div id="controlsArea" style="padding:0px 20px 0px 0px;"></div>
        </div>
			
			<script type="text/javascript">
			google.charts.load('current', {packages: ['corechart']});
			google.charts.setOnLoadCallback(chartDrowFun);
			var json = ${json}
			for(var s=0; s<json.length; s++){
				json[s].channel_view_count=json[s].channel_view_count/1000};
				//json[s].subscriberCount=json[s].subscriberCount/10};
			
			
				
			var chartDrowFun = {
					 
				    chartDrow : function(){
				        var chartData = '';
				 
				        //날짜형식 변경
				        var chartDateformat     = 'yyyy년MM월dd일';
				        //라인차트의 라인 수
				        var chartLineCount    = 30;
				        //컨트롤러 바 차트의 라인 수
				        var controlLineCount    = 30;
				 
				 
				        function drawDashboard() {
				 
				          var data = new google.visualization.DataTable();
				          //그래프에 표시할 컬럼 추가
				          data.addColumn('datetime' , '날짜');
				          data.addColumn('number'   , '조회수');
				          data.addColumn('number'   , '구독자수');
				 
				          //그래프에 표시할 데이터
				          var dataRow = [];
				 		  var datetimes =[];
				          for(var i = 0; i < json.length; i++){ //데이터 생성				 
				            dataRow = [new Date('2020','10',5+i),json[i].channel_view_count,json[i].channel_subscriber_count]

				            data.addRow(dataRow);
				          }
				 	
				 
				          var chart = new google.visualization.ChartWrapper({
				              chartType   : 'LineChart',
				              containerId : 'lineChartArea', //라인 차트 생성할 영역
				              options     : {
				                              isStacked   : 'percent',
				                              focusTarget : 'category',
				                              height          : 500,
				                              width              : '100%',
				                              legend          : { position: "top", textStyle: {fontSize: 13}},
				                              pointSize        : 5,
				                              tooltip          : {textStyle : {fontSize:12}, showColorCode : true,trigger: 'both'},
				                              hAxis              : {format: chartDateformat, gridlines:{count:chartLineCount,units: {
				                                                                  years : {format: ['yyyy년']},
				                                                                  months: {format: ['MM월']},
				                                                                  days  : {format: ['dd일']}}
				                                                                },textStyle: {fontSize:12}},
				                vAxis              : {minValue: 30000,viewWindow:{min:0},gridlines:{count:-1},textStyle:{fontSize:12}},
				                animation        : {startup: true,duration: 1000,easing: 'in' },
				                annotations    : {pattern: chartDateformat,
				                                textStyle: {
				                                fontSize: 15,
				                                bold: true,
				                                italic: true,
				                                color: '#871b47',
				                                auraColor: '#d799ae',
				                                opacity: 0.8,
				                                pattern: chartDateformat
				                              }
				                            }
				              }
				            });

				 
				            var control = new google.visualization.ControlWrapper({
				              controlType: 'ChartRangeFilter',
				              containerId: 'controlsArea',  //control bar를 생성할 영역
				              options: {
				                  ui:{
				                        chartType: 'LineChart',
				                        chartOptions: {
				                        chartArea: {'width': '60%','height' : 80},
				                          hAxis: {'baselineColor': 'none', format: chartDateformat, textStyle: {fontSize:12},
				                            gridlines:{count:controlLineCount,units: {
				                                  years : {format: ['yyyy년']},
				                                  months: {format: ['MM월']},
				                                  days  : {format: ['dd일']}}
				                            }}
				                        }
				                  },
				                    filterColumnIndex: 0
				                }
				            });
				 
				            var date_formatter = new google.visualization.DateFormat({ pattern: chartDateformat});
				            date_formatter.format(data, 0);
				 
				            var dashboard = new google.visualization.Dashboard(document.getElementById('Line_Controls_Chart'));
				            window.addEventListener('resize', function() { dashboard.draw(data); }, false); //화면 크기에 따라 그래프 크기 변경
				            dashboard.bind([control], [chart]);
				            dashboard.draw(data);
				 
				        }
				          google.charts.setOnLoadCallback(drawDashboard);
				      }
		      
				    }	
	
				$(document).ready(function(){
				  google.charts.load('current', {'packages':['line','controls']});
				  chartDrowFun.chartDrow(); //chartDrow() 실행 
				});
			</script>
			<h3>Word Cloud</h3>
			<div id="myChart" class="chart--container">
    <a href="https://www.zingchart.com/" rel="noopener" class="zc-ref">Powered by ZingChart</a>
  </div>
 
  <script >  
  ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9", "b55b025e438fa8a98e32482b5f768ff5"]; // CHART CONFIG
  // -----------------------------
  let chartConfig = {
    type: 'wordcloud',
    options: {
       
      text: '${v_json}',
      aspect: 'spiral',
      colorType: 'palette',
      backgroundColor:'black',
      maxItems: 50,
      maxFontSize: 50,
      minLength: '2px',
      palette: ['#D32F2F', '#1976D2', '#9E9E9E', '#E53935', '#1E88E5', '#7E57C2', '#F44336', '#2196F3', '#A1887F'],
      rotate: true,
      style: {
        tooltip: {
          text:'%text:%hits',
          padding: '5px',
          alpha: 0.9,
          backgroundColor: 'black',
          borderColor: 'none',
          borderRadius: '3px',
          fontColor: 'white',
          fontFamily: 'Georgia',
          textAlpha: 1,
          visible: true,
          width: '400px',
          wrapText: true
        },
        fontFamily: 'Merriweather',
        hoverState: {
          backgroundColor: '#1976D2',
          borderColor: 'none',
          borderRadius: '3px',
          fontColor: 'white'
        }
      }
    }
  };
  // RENDER CHARTS
  // -----------------------------
  zingchart.render({
    id: 'myChart',
    data: chartConfig
   
  });
 
  </script>
  <h3>유튜브 최근 동영상 10개</h3>
			<c:forEach var="v" items="${ video }" varStatus="status" >
				<div><a href="https://www.youtube.com/watch?v=${v.video_id }" target="_blank">${v.video_title}</a> ${v.video_publishedAt }</div>			
			</c:forEach>	
		</td>
	</tr>
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="4" class="botSpan">		
			<input type="button" value="목록보기" onclick=
				"javascript:window.location.href='tripList?pageNum=${pageNum}'"/>	
		</td>
	</tr>
</table>
</article>