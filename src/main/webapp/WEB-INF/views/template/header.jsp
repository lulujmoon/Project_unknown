<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="principal" />
<!-- 헤더 -->
<header>
	<div class="header__top">
		<ul class="top__personal">
			<sec:authorize access="isAnonymous()">
				<li><a href="${pageContext.request.contextPath}/member/approve">회원가입</a>&nbsp;|&nbsp;</li>
				<li><a href="${pageContext.request.contextPath}/member/login">로그인</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li><a href="${pageContext.request.contextPath}/member/info">내 정보</a>&nbsp;|&nbsp;</li>
				<li><a href="#" class="btn-noti">알림</a>&nbsp;|&nbsp;</li>
				<li><a href="${pageContext.request.contextPath}/member/logout">로그아웃</a></li>
			</sec:authorize>
		</ul>	
	</div>
	<nav class="navbar nav">
		<div class="nav__left">	
			<i class="fas fa-bars" id="toggle-btn"></i>
			<div class="nav__logo">	
				<!-- 나중에 로고 이미지로 대체 -->
				<i class="fas fa-lemon"></i><span>&nbsp;market</span>
			</div>
		</div>
		<div class="nav__search">	
			<form id="frm" action="${pageContext.request.contextPath}/product/list" class="nav__search-form">
				<input type="hidden" name="page" value="1" id="page">
				<input type="text" class="nav__searchbox" name="keyword" id="keyword" placeholder="상품명을 검색하세요" value="${pager.keyword}">
				<button type="submit" class="search-btn"><i class="fas fa-search"></i></button>
			</form>
		</div>
		<ul class="nav__personal">
			<sec:authorize access="isAnonymous()">
				<li><a href="#" onclick="alertLogin()">판매하기</a>&nbsp;|&nbsp;</li>
				<li><a href="#" onclick="alertLogin()">내 상점</a>&nbsp;|&nbsp;</li>
				<li><a href="#" onclick="alertLogin()">채팅</a></li>	
			</sec:authorize>	
			<sec:authorize access="isAuthenticated()">
				<li><a href="${pageContext.request.contextPath}/product/insert">판매하기</a>&nbsp;|&nbsp;</li>
				<li><a href="${pageContext.request.contextPath}/store/${principal.code}/products">내 상점</a>&nbsp;|&nbsp;</li>
				<li><a href="${pageContext.request.contextPath}/chat/chatList">채팅</a></li>
			</sec:authorize>
		</ul>
	</nav>
	<div class="noti-container"></div>
</header>
<!-- 사이드 메뉴 -->
<div class="side side-menu">
	<div class="side__user">
		<h3>
			<sec:authorize access="isAnonymous()">
				<a href="${pageContext.request.contextPath}/member/login"><i class="fas fa-unlock-alt"></i> 로그인</a>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<a href="${pageContext.request.contextPath}/member/logout"><sec:authentication property="principal.name"/> 님</a>
			</sec:authorize>
		</h3>
	</div>
	<div class="side__categories">
		<h3>카테고리</h3>
		<ul>
			<li><a href="#">디지털/가전</a></li>
			<li><a href="#">가구/인테리어</a></li>
			<li><a href="#">유아동/유아도서</a></li>
			<li><a href="#">생활/가공식품</a></li>
			<li><a href="#">스포츠/레저</a></li>
			<li><a href="#">여성잡화</a></li>
			<li><a href="#">여성의류</a></li>
			<li><a href="#">남성패션/잡화</a></li>
			<li><a href="#">게임/취미</a></li>
			<li><a href="#">뷰티/미용</a></li>
			<li><a href="#">반려동물용품</a></li>
			<li><a href="#">도서/티켓/음반</a></li>
			<li><a href="#">식물</a></li>
			<li><a href="#">기타 중고물품</a></li>	
			<li><a href="#">삽니다</a></li>			
		</ul>
	</div>
	<div class="side__socials">
		<h3>우리동네</h3>
		<ul>
			<li><a href="#">일상</a></li>
			<li><a href="#">같이해요</a></li>
			<li><a href="#">해주세요</a></li>
			<li><a href="#">동네맛집</a></li>
			<li><a href="#">동네소식</a></li>
			<li><a href="#">취미생활</a></li>
			<li><a href="#">고양이</a></li>
			<li><a href="3">강아지</a></li>				
		</ul>
	</div>
</div>