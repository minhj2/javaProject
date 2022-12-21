<%@page import="ch.qos.logback.core.recovery.ResilientSyslogOutputStream"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="Pack01.WeatherDto"%>
<%@page import="Pack01.CodeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

List<CodeDto> placelist = new ArrayList<CodeDto>();
List<CodeDto> naturelist  = new ArrayList<CodeDto>();
List<CodeDto> observelist = new ArrayList<CodeDto>();
WeatherDto wdto = new WeatherDto();
wdto = (WeatherDto)request.getAttribute("result");
placelist = (List)request.getAttribute("placelist");
naturelist = (List)request.getAttribute("naturelist");
observelist = (List)request.getAttribute("observelist");
int curpage = (Integer)request.getAttribute("curpage");
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<style>
  table1 {
    width: 100%;
    border-top: 1px;
    border-collapse: collapse;
  }
  table {
    width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border-bottom: 1px solid #444444;
    border-left: 1px solid #444444;
    padding: 10px;
  }
  th:first-child, td:first-child {
    border-left: none;
  }

</style>
<script>
	function submitf(actionstr) {

		document.form1.action=actionstr;
		document.form1.submit();
		
		//document.getElementById("btn1").addEventListener("click", addNum);
		//if (document.getElementById('updatebtn').clicked)
	    //alert( document.getElementById('placeno').value);
	    // document.update.submit();

	}
</script>
</head>
<body>
<%
	if (wdto != null) {%>
	<table width=100>
		<form name="form1" >
			<tr>
				<td>
				<input type="hidden" name="curpage" value=<%=curpage%>>
				<input type="hidden" name="id" value=<%=wdto.getId() %>>
				id : <%=wdto.getId() %></td>
			</tr>
			<tr>			
				<td>
				<input type="text"  pattern="\d*" name="yyyy" maxlength="4" size=5 value="<%=wdto.getYyyy() %>">
				<input type="text"  pattern="\d*" name="mm" maxlength="4" size=5 value="<%=wdto.getMm() %>">
				<input type="text"  pattern="\d*" name="dd" maxlength="4" size=5 value="<%=wdto.getDd() %>">
				</td>
			</tr>			
			<tr>			
				<td>
				<select name="placeno">
				<%for (CodeDto tmp : placelist) {%>
					<option value="<%=tmp.getCd() %>"
					<% if ( wdto.getPlaceno()==tmp.getCd() ) { out.print("selected");}%>
					><%=tmp.getCdName() %></option>
				<%} %>
				</select>
				</td>
			</tr>
			
			<tr>			
				<td>
				<select name="naturecd">
				<%for (CodeDto tmp : naturelist) {%>
					<option value="<%=tmp.getCd() %>"
					<% if ( wdto.getNaturecd()==tmp.getCd() ) { out.print("selected");}%>
					><%=tmp.getCdName() %></option>
				<%} %>
				</select>
				</td>
			</tr>
			<tr>			
				<td>
				<select name="observecd">
				<%for (CodeDto tmp : observelist) {%>
					<option value="<%=tmp.getCd() %>"
					<% if ( wdto.getObservecd()==tmp.getCd() ) { out.print("selected");}%>
					><%=tmp.getCdName() %></option>
				<%} %>
				</select>
				</td>
			</tr>

			<tr>
				<td><input type="button" id="updatebtn" value="수정" onClick="submitf('update');">
				<input type="button" id="deletebtn"  value="삭제" onClick="submitf('delete');"></td>	
			</tr>
		</form>		
	</table>
<%
	}
%>
</body>
</html>