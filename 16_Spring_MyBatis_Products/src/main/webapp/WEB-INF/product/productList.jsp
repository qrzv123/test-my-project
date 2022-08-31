<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
productList.jsp <br>
<%@ include file="../common/common.jsp" %> 
<script type="text/javascript">
	function insert(){
		location.href="insert.prd";
	}
	function update(num,pageNumber){
		location.href="update.prd?num="+num+"&pageNumber="+pageNumber;
	}

</script>

<a href="start.jsp">시작 페이지</a>
<a href="">로그아웃</a>

<center>
	<form>
		<h2>상품 리스트 화면</h2>
		<br>
		<h2>ProductList.jsp</h2>

		<select name="whatColumn">
			<option value="">전체검색
			<option value="name">상품명
			<option value="contents">상품설명
		</select> 
		<input type="text" name="keyword">
		<input type="submit" value="검색">
	</form>

	<table border="1">
		<tr>
			<td colspan="6" align="right">
				<!-- 이부분사용해도 된다<input type="button" value="추가하기" onclick="location.href='insert.prd'"> -->
				<input type="button" value="추가하기" onclick="insert()">
			</td>
		</tr>
		<tr>
			<th>상품번호</th>
			<th>상품명</th>
			<th>설명</th>
			<th>가격</th>
			<th>삭제</th>
			<th>수정</th>
		</tr>
		<c:forEach var="p" items="${lists }">
			<tr>
				<td>${p.num }</td>
				<td><a href="detail.prd?num=${p.num }&pageNumber=${pageInfo.pageNumber}">${p.name }</a></td>
				<td>${p.contents }</td>
				<td>${p.price }</td>
				<td><a href="delete.prd?num=${p.num }&pageNumber=${pageInfo.pageNumber}">삭제</a></td>
				<td>
					<input type="button" value="수정" onclick="update('${p.num}','${pageInfo.pageNumber}')">
				</td>
			</tr>
		</c:forEach>
	</table>
	${pageInfo.pagingHtml}
</center>
