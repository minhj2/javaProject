<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="Pack01.WeatherDto"%>
<%@page import="Pack01.PagingVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

List<WeatherDto> resultlist = new ArrayList<WeatherDto>();
PagingVO pagingVO = new PagingVO();
resultlist = (List)request.getAttribute("result");
pagingVO = (PagingVO)request.getAttribute("pv");

pagingVO.setEndPage( ((int) Math.ceil(pagingVO.getCurrentPage() / (double) pagingVO.getDisplayPage())) * pagingVO.getDisplayPage() );	//Math.ceil : 소수점 이하를 올림한다
pagingVO.setBeginPage( pagingVO.getEndPage() - (pagingVO.getDisplayPage() - 1) );
pagingVO.setTotalPage( (int) Math.ceil(pagingVO.getTotalCount() / (double) pagingVO.getDisplayRow()) );
String action="/selectEachpageList";
//out.println(pagingVO.getCurrentPage());
//out.println(pagingVO.getTotalCount());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<style>
  table {
    width: 80%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border-bottom: 1px solid #444444;
    border-left: 1px solid #444444;
    border-right: 1px solid #444444;
    padding: 10px;
  }
  th:first-child, td:first-child {
    border-left: none;
  }
</style>

</head>
<body>
<%
if (resultlist != null) {
%>
	기상청데이터
	<table>
		<%	
	for (WeatherDto tmp : resultlist) {
%>
		<tr>
			<td><a href="detail?id=<%=tmp.getId()%>&curpage=<%=pagingVO.getCurrentPage()%>"><%= tmp.getId() %></a></td>
			<td><%= tmp.getYyyy() %>&nbsp;<%= tmp.getMm() %>&nbsp;<%= tmp.getDd() %></td>
			<td><%= tmp.getPlacenm() %></td>
			<td><%= tmp.getNaturenm() %></td>
			<td><%= tmp.getObservedetail() %></td>
		</tr>
		<%}%>
	</table>
	<table>
		<tr>
			<td>
				<!-- 여기부터 페이징처리 -->
				<div id="pagination">
					<center>
						<!-- 변수 매핑 -->
						<c:set var="page" value="<%=pagingVO.getCurrentPage() %>" />
						<c:set var="beginPage" value="<%=pagingVO.getBeginPage() %>" />
						<c:set var="endPage" value="<%=pagingVO.getEndPage() %>" />
						<c:set var="totalPage" value="<%=pagingVO.getTotalPage() %>" />
						<c:set var="action" value="<%=action %>" />
						<c:set var="totcnt" value="<%=pagingVO.getTotalCount()  %>" />
						<c:set var="displayPage" value="<%=pagingVO.getDisplayPage() %>" />
						<!-- 처음으로 -->
						<a href="${ action }?totcnt=0&curpage=1"><span>«</span></a>
						<!-- 이전버튼 -->
						<c:if test="${ page <= 1 }">
							<span>이전</span>
						</c:if>
						<c:if test="${ page > 1 }">
							<a href="${ action }?totcnt=${totcnt }&curpage=${ page - 1 }">이전</a>
						</c:if>

						<!-- 넘버링버튼 for문 -->
						<c:forEach var="item" varStatus="status" begin="${ beginPage }"
							end="${ endPage }" step="1">
							<c:if test="${ page == item }">
                    ${ item }
                			</c:if>
							<c:if test="${ page != item }">
								<a href="${ action }?totcnt=${totcnt }&curpage=${ item }">${ item }</a>
							</c:if>
						</c:forEach>

						<!-- 다음버튼 -->
						<c:if test="${ page >= totalPage }">
							<span>다음</span>
						</c:if>
						<c:if test="${ page < totalPage }">
							<a
								href="${ action }?totcnt=${totcnt }&curpage=${ page + 1 }${ search }">다음</a>
						</c:if>
						<!-- 끝으로 -->
						<a href="${ action }?totcnt=${totcnt }&curpage=${ totalPage }">
							<span>»</span>
						</a>
						<center>
				</div>
			</td>
		</tr>
	</table>
	<%
}%>

</body>
</html>