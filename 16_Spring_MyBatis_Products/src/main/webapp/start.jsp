<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

start.jsp <br>

<%
	String viewProduct = request.getContextPath() +"/list.prd";	//http://localhost:8080/ex/list.prd
//	String viewProduct ="list.prd"쓰면 //http://localhost:8080/ex/list.prd
//	String viewProduct ="/list.prd"쓰면 //http://localhost:8080/list.prd

	String viewOrder = request.getContextPath() +"/order.mall";
%>

<a href="<%=viewProduct%>">상품 목록 보기</a> <br><br>

<a href="<%=viewOrder%>">나의 주문 내역</a> <br><br>
