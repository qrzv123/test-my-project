<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
productDetail.jsp<br>
application.getRealPath("/resources") : <%=application.getRealPath("/resources") %><br>
<br>
application.getContextPath() 는 => <%=application.getContextPath() %><br>
request.getContextPath() 는 => <%=request.getContextPath() %><br>
상단2라인은 \ex이지만
C:\Spring_jhs\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\16_Spring_MyBatis_Products\ex의 경로가 포함되어 있다
<center>
	<h2>상품 상세 화면${pb.num }</h2>
	<table border="1">
		<tr>
			<td rowspan="6">
				<img height="70" width="70" src="<%=application.getContextPath() %>/resources/${pb.image }">
				<!-- 하단2개도 동일하다 -->
				<img height="70" width="70" src="<%=application.getRealPath("/resources") %>/${pb.image }">
				<img height="70" width="70" src="<%=request.getContextPath() %>/resources/${pb.image }">
			</td>
			<!--
			application.getRealPath("/resources")결과는
			C:\Spring_jhs\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\16_Spring_MyBatis_Products\resources
			-->
			<td>상품명</td>
			<td>${pb.name }</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>${pb.price }</td>
		</tr>
		<tr>
			<td>재고 수량</td>
			<td>${pb.stock }</td>
		</tr>
		<tr>
			<td>설명</td>
			<td>${pb.contents }</td>
		</tr>
		<tr>
			<td>주문 수량</td>
			<td>주문수량2
			<input type="text" value="1">
			<input type="button" value="주문">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><a href="list.prd?pageNumber=${pageNumber }">상품 리스트</a></td>
		</tr>
	</table>
</center>