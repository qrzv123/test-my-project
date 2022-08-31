<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/common/common.jsp" %>
memberList.jsp<br>
<script>
function insert(){
		location.href="registerForm.mem";
	}
</script>
<center>
<h2>회원 리스트 화면</h2>
<form action="list.mem">
	<select name="whatColumn" >
		<option value="">전체 검색</option>
		<option value="name">이름 검색</option>
		<option value="gender">성별 검색</option>
	</select>
	<input type="text" name="keyword" value="여">
	<input type="submit" value="검색">
</form>
<table border="1">
		<tr>
			<td colspan="9" align="right">
				<!-- 이부분사용해도 된다<input type="button" value="추가하기" onclick="location.href='insert.prd'"> -->
				<input type="button" value="추가하기" onclick="insert()">
			</td>
		</tr>
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>비번</th>
			<th>성별</th>
			<th>취미</th>
			<th>주소</th>
			<th>포인트</th>
			<th>삭제</th>
			<th>수정</th>
		</tr>
		<c:forEach var="member" items="${lists }">
			<tr>
				<td>${member.id }</td>
				<td>${member.name }</td>
				<td>${member.password }</td>
				<td>${member.gender }</td>
				<td>${member.hobby }</td>
				<td>${member.address1} ${member.address2}</td>
				<td>${member.mpoint }</td>
				<td><a href="">삭제</a></td>
				<td>
					<input type="button" value="수정" onclick="">
				</td>
				<!-- 삭제/수정은 id넘겨서 해보아라 -->
			</tr>
		</c:forEach>
	</table>
	${pageInfo.pagingHtml}
</center>