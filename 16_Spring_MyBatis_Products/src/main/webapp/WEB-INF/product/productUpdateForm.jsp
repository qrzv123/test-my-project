<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../common/common.jsp"%>
<style type="text/css">
.err {
	font-size: 9pt;
	color: red;
	font-weight: bold;
}
</style>
productUpdateForm.jsp
<br>

<form:form commandName="product" method="post" enctype="multipart/form-data" action="update.prd">
	<center>
		<h1>상품 수정 화면</h1>
		<input type="hidden" name="num" value="${product.num }">
		<input type="hidden" name="pageNumber" value="${pageNumber }">
		<input type="hidden" name="originalImg" value="${product.image }">
		
		<table border="1">
			<tr>
				<td>*상품명</td>
				<td><input type="text" name="name" id="name" value="${product.name }">
					<form:errors cssClass="err" path="name"></form:errors>
				</td>
			</tr>
			<tr>
				<td>제조 회사</td>
				<td><input type="text" name="company" id="company" value="${product.company }">
				</td>
			</tr>
			<tr>
				<td>*가격</td>
				<td><input type="text" name="price" id="price"
					value="${product.price }"> <form:errors cssClass="err" path="price"></form:errors>
				</td>
			</tr>
			<tr>
				<td>재고 수량</td>
				<td><input type="text" name="stock" id="stock" value="${product.stock }">
				</td>
			</tr>
			<tr>
				<td>적립 포인트</td>
				<td><input type="text" name="point" id="point" value="${product.point }">
				</td>
			</tr>
			<tr>
				<td>*설명</td>
				<td><input type="text" name="contents" id="contents" value="${product.contents }">
					<form:errors cssClass="err" path="contents"></form:errors>
				</td>
			</tr>
			<tr>
				<td>*그림 파일</td>
				<td>
					<%-- 
					<img height=70 width=70 src="<%=application.getRealPath("/resources")%>/${product.image}"><br>
					<img height=70 width=70 src="<%=application.getContextPath()%>/resources/${product.image}"><br>
					 --%>
					<img height=70 width=70 src="<%=request.getContextPath()%>/resources/${product.image}"><br>
					<input type="file" name="upload" id="upload" value="${product.upload }">
					<form:errors cssClass="err" path="image" />
					<!-- type="file"에는 기본값으로 파일을 불러올 수 없다 보안문제로인해 유효성걸리면 이미지도안보이고 이미지파일이름도 안보인다-->
				</td>
			</tr>
			<tr>
				<td>입고 일자</td>
				<td><fmt:parseDate value="${product.inputdate }" pattern="yyyy-MM-dd" var="parseDate" />
					<fmt:formatDate var="formatDate" value="${parseDate }" type="Date" pattern="yyyy-MM-dd" />
					<input type="text" name="inputdate" id="inputdate" value="${formatDate }">
				</td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="수정하기"></td>
			</tr>
		</table>
	</center>
</form:form>
