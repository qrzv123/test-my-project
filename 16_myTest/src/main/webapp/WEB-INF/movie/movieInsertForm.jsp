<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/common/common.jsp" %>
    
movieInsertForm.jsp<br>
<!--
이런 방식도 가능
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->

<!-- webapp밑의 resources에 js파일 가져다 놓아야 한다
그리고 하단2개중 1개 사용해주면 된다 -->
<c:set var = "path" value="<%=request.getContextPath() %>"/> <!-- 자바코드이용한것 -->
<%-- <c:set var="path" value="${pageContext.request.contextPath}"/> --%> <!-- 자바스크립트코드 이용한것 -->

<!-- 내가만든 프로젝트명 마지막 패키지 이름인 ex가 path에 들어가게된다 그런데 ex만 나오지만 webapp까지 포함된다-->
<script type="text/javascript" src="${path }/resources/js/jquery.js"></script>

<script type="text/javascript">
	var f_selbox = new Array('아시아','아프리카','유럽','아메리카','오세아니아');

	var s_selbox = new Array();
	s_selbox[0] = new Array('한국','중국','베트남','네팔');
	s_selbox[1] = new Array('케냐', '가나', '세네갈');
	s_selbox[2] = new Array('스페인', '영국','덴마크','러시아','체코');
	s_selbox[3] = new Array('미국', '캐나다'); 
	s_selbox[4] = new Array('뉴질랜드','오스트레일리아');
	
	function init(f,mc,mn){//form의 name은 f인데 form의 정보를 넘긴다 
// 		alert(mc,mn);
// 		var f_sel = f.continent;	//대륙
// 		var s_sel = f.nation;	//나라
		
		//options는 자바스크립트에서 사용하는 배열
		for(var i=0;i<f_selbox.length;i++){
			f.continent.options[i+1] = new Option(f_selbox[i],f_selbox[i]);
			//Option(1,2)에서 1이 text 2가 value 둘이 동일하면 하나만 써도된다
			
			if(f.continent.options[i+1].value == mc){
				f.continent.options[i+1].selected = true;
			}
		}
		
		
		for(i=0;i<s_selbox.length;i++){
			s_sel.options[i+1] = new Option(s_selbox[i]);
		}
	}//첫번째 select박스 내용 입력
	
	function firstChange(f){
		var f_sel = f.continent;	//대륙
		var s_sel = f.nation;		//나라
		
		f_index = f_sel.selectedIndex;
		f_val = f_sel[f_index].value;
		alert(f_index+"/"+f_val);//1/아시아	2/아프리카
		
		for(var i=s_sel.length;i>0;i--){
			s_sel.options[i+1] = null;
		}
		
		for(var i=0;i<s_selbox[f_index-1].length;i++){
			//내가선택한번호에서 하나뺀것이 행번호
			s_sel.options[i+1] = new Option(s_selbox[f_index-1][i]); 
			//i가 열에 접근
		}
				
	}//firstChange
	
	
	$(document).ready(function(){
		//alert(11);
		
		var isCheck = false;
		var isChange = false;
		
		$('#title_check').click(function() {
			//alert(3);
			isCheck = true;
			
			$.ajax({
				url : "title_check_proc.mv",
				data : ({
					inputtitle : $("input[name=title]").val()
				}),
				success : function(data){
					alert('data:' + data);
					if ($('input[name="title"]').val() == "") {
						$('#titleMessage').html("<font color=red>title입력 누락</font>");
						$('#titleMessage').show();
					}

					else if(data=='YES'){
						$('#titleMessage').html("<font color=red>사용 가능합니다.</font>");
						$('#titleMessage').show();
						use = "possible";
						isChange = false;
					}
					else{
						$('#titleMessage').html("<font color=red>이미 등록한 제목입니다.</font>");
						$('#titleMessage').show();
						use = "impossible";
					}
				}//success
			}); // ajax
		}); // click

		$('input[name=title]').keydown(function(){
			$('#titleMessage').css('display','none');
			isChange = true;
			use = "";
		});
		
		$('#btnSubmit').click(function(){
			
			if(use == "impossible" ){
				alert('이미 사용중인 제목입니다.');
				return false;
			}
			else if(isCheck== false || isChange == true){
				alert("중복체크를 하세요");
				return false;
			}
		});//click
		
	});//ready
</script>
<style>
	.err{
		color: red;
	}
</style>
${mb.continent }/${mb.nation }<br>
<body onload="init(f,'${mb.continent }','${mb.nation }')">

<form:form commandName="mb" method="post"  action="insert.mv" name="f">
	<h1><span>영화 정보 등록 화면</span></h1>
	<p>
		<label for="title">영화 제목 : </label>
		<input type="text" name="title" id="title" value="${mb.title }">
		<input type="button" id="title_check" value="중복체크">
		<span id="titleMessage" style="display:none;"></span>
		<form:errors cssClass="err" path="title"/>
	</p>
	<p style="float:left">
		<label for="continent" style="float:left;">대륙 : </label>
		<select name = "continent" id="continent" style="width: 130px;" onchange="firstChange(this.form)"><!-- (f)에 써도 된다 -->
			<option value="">대륙 선택하세요</option> <!-- 0번방 -->
		</select>
		<form:errors cssClass="err" path="continent"/>
	</p>
	
	<p  style="float:left">
		<label for="nation"> &nbsp;&nbsp;&nbsp;나라 : </label>
		<select name = "nation" id="nation" style="width: 130px;">
			<option value="">나라 선택하세요</option> 
		</select>
		<form:errors cssClass="err" path="nation"/>
	</p>
	
	<p  style="clear:left">
		<label for="genre">장르 : </label>
		<input type="checkbox" name="genre" id="genre" value="액션" <c:if test="${fn:contains(mb.genre,'액션') }">checked</c:if>>액션
		<input type="checkbox" name="genre" id="genre" value="스릴러" <c:if test="${fn:contains(mb.genre,'스릴러') }">checked</c:if>>스릴러
		<input type="checkbox" name="genre" id="genre" value="코미디" <c:if test="${fn:contains(mb.genre,'코미디') }">checked</c:if>>코미디
		<input type="checkbox" name="genre" id="genre" value="판타지" <c:if test="${fn:contains(mb.genre,'판타지') }">checked</c:if>>판타지
		<input type="checkbox" name="genre" id="genre" value="애니메이션" <c:if test="${fn:contains(mb.genre,'애니메이션') }">checked</c:if>>애니메이션
		<form:errors cssClass="err" path="genre"/>
	
	</p>
	
	<p>
		<label for="grade">등급 : </label>
		<input type="radio" name="grade" id="grade" value="19" <c:if test="${mb.grade == '19'}">checked</c:if>>19
		<input type="radio" name="grade" id="grade" value="15" <c:if test="${mb.grade == '15'}">checked</c:if>>15
		<input type="radio" name="grade" id="grade" value="12" <c:if test="${mb.grade == '12'}">checked</c:if>>12
		<input type="radio" name="grade" id="grade" value="7" <c:if test="${mb.grade == '7'}">checked</c:if>>7
		<form:errors cssClass="err" path="grade"/>
	</p>
	
	 <p>
		<label for="actor">출연 배우 : </label>
		<input type="text" name="actor" id="actor" value="${mb.actor }">
		<form:errors cssClass="err" path="actor"/>
	</p>
	<p >
			<input type="submit" value="추가하기" id="btnSubmit">		
	</p>
</form:form>

</body>
<!-- 	
추가하기 클릭
alert 중복체크 먼저 하세요

중복체크 클릭
title  입력 누락
제목 입력=>message 사라짐

제목을 입력해주세요
이미 등록된 제목입니다
사용 가능합니다
제목 입력 후 중복체크 클릭하면 사용가능/사용불가능
 -->