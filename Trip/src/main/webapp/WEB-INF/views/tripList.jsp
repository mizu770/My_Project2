<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<article>
<table class="listTable">
	<tr>
		<td class="boardTitle" colspan="5">
			<h2>여행관련 유투버 랭킹 리스트</h2>
		</td>
	</tr>
	<tr>
		<th class="listThImg">채널배너</th>
		<th class="listThTitle">채널이름</th>
		<th class="listThSub">구독자수</th>
	</tr>
	<c:forEach var="b" items="${ tripList }" varStatus="status" >		
	<tr class="listTr">
		<td class="listTdTitle">
			<a href="tripDetail?channel_id=
				${ b.channel_id }&pageNum=${ currentPage }"><img class="tripImg" src="./resources/images/${b.channel_id }.jpg"></a>
		</td>
		<td class="listTdTitle"><a href="tripDetail?channelsId=${ b.channel_id }&pageNum=${ currentPage }">${ b.channel_title }</a>
		</td>
		<th class="listTdSub">${b.channel_subscriber_count }</th>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="4" class="listPage">
			<%--
			/* 현재 페이지 그룹의 시작 페이지가 pageGroup보다 크다는 것은
			 * 이전 페이지 그룹이 존재한다는 것으로 현재 페이지 그룹의 시작 페이지에
			 * pageGroup을 마이너스 하여 링크를 설정하면 이전 페이지 그룹의
			 * startPage로 이동할 수 있다.
		 	 **/
		 	 --%>
		 	<c:if test="${ startPage > pageGroup }"> 
				<a href="tripList?pageNum=${ startPage - pageGroup }">
					[이전]</a>
			</c:if>	
			<%--
			/* 현재 페이지 그룹의 startPage 부터 endPage 만큼 반복하면서
		 	 * 현재 페이지와 같은 그룹에 속한 페이지를 화면에 출력하고 링크를 설정한다.
		 	 * 현재 페이지는 링크를 설정하지 않는다.
		 	 **/
		 	--%>
			<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
				<c:if test="${ i == currentPage }">
					[ ${ i } ]
				</c:if>			
				<c:if test="${ i != currentPage }">
					<a href="tripList?pageNum=${ i }">[ ${ i } ]</a>
				</c:if>			
			</c:forEach>
			<c:if test="${ endPage < pageCount }">
				<a href="tripList?pageNum=${ startPage + pageGroup }">
					[다음]</a>
			</c:if>		
		</td>
	</tr>
</table>

</article>
