<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/common/common.jsp" %>
<style type="text/css">
	.err{
	font-size: 9pt;
	color: red;
	font-weight: bold;
	}
</style>

<%
	String hobbyArr[] ={"마라톤","영화감상","게임","공부"};
	//pageContext.setAttribute("hobbyArr", hobbyArr);
%>
<c:set var="hobbyArr" value="<%=hobbyArr %>"/>

</head>
<body>
MemberRegisterController => memberRegisterForm.jsp<br>
<h2>회원 가입 화면</h2>
	<form:form commandName="member" method="post" action="registerForm.mem"> 
		<p>
			<label for="id">아이디</label>
			<input type="text" name="id" id="id" value="${member.id }">
			<form:errors cssClass="err" path="id"></form:errors>
		</p>
		<p>
			<label for="name">이름</label>
			<input type="text" name="name" id="name" value="${member.name }">
			<form:errors cssClass="err" path="name"></form:errors>
		</p>
		<p>
			<label for="password">비번</label>
			<input type="text" name="password" id="password" value="${member.password }">
			<form:errors cssClass="err" path="password"></form:errors>
		</p>	
		<p>
			<label for="gender">성별</label>
			<input type="radio" name="gender" id="gender" value="남자" <c:if test="${member.gender eq '남자' }">checked</c:if>>남자
			<input type="radio" name="gender" id="gender" value="여자" <c:if test="${member.gender eq '여자' }">checked</c:if>>여자
			<form:errors cssClass="err" path="gender"></form:errors>
		</p>	
		<p>
		 
			<label for="hobby">취미</label>
			<%-- 
			<input type="checkbox" name="hobby" id="hobby" value="마라톤" <c:if test="${fn:contains(member.hobby, '마라톤') }">checked</c:if>>마라톤
			<input type="checkbox" name="hobby" id="hobby" value="영화감상" <c:if test="${fn:contains(member.hobby, '영화감상') }">checked</c:if>>영화감상
			<input type="checkbox" name="hobby" id="hobby" value="게임" <c:if test="${fn:contains(member.hobby, '게임') }">checked</c:if>>게임
			<input type="checkbox" name="hobby" id="hobby" value="공부" <c:if test="${fn:contains(member.hobby, '공부') }">checked</c:if>>공부
			 --%>
			 <%-- 이거아직미완성--%>
			 <c:forEach var="i" begin="0" end="${fn:length(hobbyArr)-1 }" step="1">
				<input type="checkbox" name="hobby" id="hobby" value="${hobbyArr[i] }" <c:if test="${fn:contains(member.hobby, hobbyArr[i]) }">checked</c:if>>${hobbyArr[i] }
			</c:forEach>
			 
			<form:errors cssClass="err" path="hobby"></form:errors>
			
		</p>	
		<p>
			<label for="address1">주소1</label>
			<input type="text" name="address1" id="address1" value="${member.address1 }">
			<form:errors cssClass="err" path="address1"></form:errors>
		</p>	
		<p>
			<label for="address2">주소2</label>
			<input type="text" name="address2" id="address2" value="${member.address2 }">
		</p>	
		
		<p>
			<label for="price">적립포인트</label>
			<input type="text" name="mpoint" id="mpoint" value="${member.mpoint }">
		</p>	
		<p class="btnRow">
			<input type="submit" value="추가하기" id="btnSubmit">		
		</p>
	</form:form>
