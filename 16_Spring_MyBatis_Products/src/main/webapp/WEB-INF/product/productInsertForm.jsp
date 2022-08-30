<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="/WEB-INF/common/common.jsp" %>
productInsertForm.jsp<br>
    
<style>
	.err {
		font-size: 9px;
		font-weight: bold;
		color: red;
	}
</style>
<!--
파일업로드위해서는 form에 enctype="multipart/form-data"속성 설정과 method="post"속성 설정 필수
-->
<form:form commandName="product" method="post" action="insert.prd" enctype="multipart/form-data">
	<h1><span>상품 추가 화면</span></h1>
	<p>
		<label for="name">*상품명</label>
		<input type="text" name="name" id="name" value="${product.name }">
		<form:errors cssClass="err" path="name"/>
	</p>
	<p>
		<label for="company">제조 회사</label> 
		<input type="text" name="company" id="company" value="${product.company }">
	</p>
	<p>
		<label for="price">*가격</label>
		<input type="text" name="price" id="price" value="${product.price }">
		<form:errors cssClass="err" path="price"/>
	</p>
<p>
		<label for="stock">재고 수량</label>
		<input type="text" name="stock" id="stock" value="${product.stock }">
	</p>	
	<p>
		<label for="point">적립 포인트</label>
		<input type="text" name="point" id="point" value="${product.point }">
	</p>	
	<p>
		<label for="contents">*설명</label>
		<input type="text" name="contents" id="contents" value="${product.contents }">
		<form:errors cssClass="err" path="contents"/>
	</p>	
	<p>
		<label for="upload">*그림 파일</label><!-- upload=a.jpg -->
		<input type="file" name="upload" id="upload" value="${product.image }">
		<form:errors cssClass="err" path="image"/>
	</p>	
	<p>
		<label for="inputdate">입고 일자</label>
		<input type="text" name="inputdate" id="inputdate" value="${product.inputdate }">
	</p>	
	<p>
		<input type="submit" value="추가하기" id="btnSubmit">		
	</p>
</form:form>
	