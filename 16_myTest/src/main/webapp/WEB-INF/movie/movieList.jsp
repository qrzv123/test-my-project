<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/common.jsp"%>
<script type="text/javascript">
function insert(){
	location.href="insert.mv";
}
function update(num,pageNumber){
	location.href="update.mv?num="+num+"&pageNumber="+pageNumber;
}
</script>
movieList.jsp<br>

<center>
	<h2>영화 정보 리스트 화면</h2>
	<form action="list.mv" method="post">
		<select name="whatColumn">
			<option value="">전체 검색</option>
			<option value="genre">장르</option>
			<option value="grade">등급</option>
			<option value="actor">출연배우</option>
		</select>
		<input type="text" name="keyword"> <input type="submit" value="검색">
	</form>
	<table border="1">
		<tr align=right>
			<td colspan=9><input type="button" value="추가하기" onclick="insert()">
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>대륙</th>
			<th>제작국가</th>
			<th>장르</th>
			<th>등급</th>
			<th>출연배우</th>
			<th>삭제</th>
			<th>수정</th>
		</tr>
		<c:forEach var="l" items="${lists }">
			<tr>
				<td>${l.num }</td>
				<td>${l.title }</td>
				<td>${l.continent }</td>
				<td>${l.nation }</td>
				<td>${l.genre }</td>
				<td>${l.grade }</td>
				<td>${l.actor }</td>
				<td><a href="">삭제</a></td>
				<td><input type="button" value="수정" onclick="update('${l.num }','${pageInfo.pageNumber}')"></td>
			</tr>
		</c:forEach>
	</table>
</center>
<center>${pageInfo.pagingHtml}</center>