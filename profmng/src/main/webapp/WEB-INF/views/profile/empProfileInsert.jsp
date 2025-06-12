<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/index.css">
    <script src=""></script>
    <title>R&B 알앤비소프트 - 사내프로젝트</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="/js/user/userScript.js"></script>
</head>
<body>
	<!-- HEADER -->
    <header>
        <div class="header-content">
            <div class="logo-header">
                <img class="logo-circle" src="images/rnbsoft_logo.png">
                <h1>알앤비소프트</h1>
            </div>
            <div id="userInfoArea" class="user-info">
                <a href="/user/showLogin" class="logout-btn">로그인</a>
            </div>
        </div>
    </header>

    <!-- NAVIGATION -->
    <nav>
        <div class="nav-content">
            <div class="nav-item">
                <a href="/dashboard">대시보드</a>
            </div>
            <div class="nav-item">
                <a href="/project">프로젝트 관리</a>
            </div>
            <div class="nav-item">
                <a href="/profile" class="active">사용자 관리</a>
            </div>
            <div class="nav-item">
                <a href="/system">시스템 설정</a>
            </div>
            <div class="nav-item">
                <a href="/report">리포트</a>
            </div>
        </div>
    </nav>

    <div class="main-layout">
        <!-- SIDEBAR -->
        <aside>
            <h2 class="sidebar-title">빠른 메뉴</h2>
            <ul class="sidebar-menu">
				<li><a href="/selectProfile">프로필 조회</a></li>
                <li><a href="/empProfileInsert" class="active">사원 정보 추가</a></li>
				<li><a href="/empProfileManage">사원 정보 관리</a></li>
                <li><a href="/empProjectInsert">투입 인력 추가</a></li>
				<li><a href="/empProjectManage">투입 인력 관리</a></li>
				<li><a href="/empAbilityInsert">직무 능력 추가</a></li>
				<li><a href="/empAbilityManage">직무 능력 관리</a></li>
            </ul>
        </aside>

        <!-- MAIN CONTENT -->
        <main>
            <h1 class="main-title">사원 정보 추가</h1>
            <p class="main-subtitle">신규 사원 정보를 입력해주세요</p>

			<div class="content-grid">
                <article class="content-card">
					<form action="/insertProfileData" method="post">
						<div class="">
							<label for="searchInput">사원코드: <span class="required">*</span></label>
							<input type="text" name="empCd" class="searchInput" placeholder="예: 0001" required>
	                    </div>
						<div class="">
							<label for="searchInput">사원명: <span class="required">*</span></label>
							<input type="text" name="empNm" class="searchInput" placeholder="예: 홍길동" required>
						</div>
						<div class="">
							<label for="dateInput">입사일: <span class="required">*</span></label>
							<input type="date" name="startDate" class="dateInput" required>
						</div>
						<div class="">
							<label for="jobGrade">직급:</label>
							<select class="jobGrade" name="jobGrade">
							  	<option value="전체">전체</option>
							  	<option value="대표이사">대표이사</option>
								<option value="부사장">부사장</option>
								<option value="전무">전무</option>
							  	<option value="상무">상무</option>
								<option value="팀원">팀원</option>
								<option value="이사">이사</option>
								<option value="부장">부장</option>
								<option value="차장">차장</option>
								<option value="과장">과장</option>
								<option value="대리">대리</option>
								<option value="사원">사원</option>
							</select>
						</div>
						<div class="">
							<label for="jobTitle">직위:</label>
							<select class="jobTitle" name="jobTitle">
							  	<option value="전체">전체</option>
								<option value="사장">사장</option>
							  	<option value="본부장">본부장</option>
								<option value="실장">실장</option>
								<option value="팀장">팀장</option>
							  	<option value="팀원">팀원</option>
							</select>
						</div>
						<div class="">
							<label for="address">주소:</label>
							<input type="text" id="address" name="address" placeholder="예: 서울특별시 강남구 테헤란로 123">
						</div>
						<div class="">
							<label for="phone">전화번호:</label>
							<input type="tel" id="phone" name="phone" placeholder="010-1234-5678">
						</div>
						<div class="">
							<label for="department">부서:</label>
							<select class="department" name="department">
							  	<option value="전체">전체</option>
							  	<option value="경영부문">경영부문</option>
							  	<option value="사업개발실">사업개발실</option>
							  	<option value="경영지원실">경영지원실</option>
							  	<option value="AICT 본부">AICT 본부</option>
							  	<option value="금융 1본부">금융 1본부</option>
							  	<option value="금융 2본부">금융 2본부</option>
							  	<option value="의료본부">의료본부</option>
							  	<option value="신사업 TF">신사업 TF</option>
							  	<option value="남부 TF">남부 TF</option>
							  	<option value="연구소">연구소</option>
							  	<option value="인사업무">인사업무</option>
							</select>
						</div>
						<div class="">
							<label for="employmentType">고용형태:</label>
							<select class="employmentType" name="employmentType">
							  	<option value="전체">전체</option>
							  	<option value="정직원">정직원</option>
							  	<option value="계약직">계약직</option>
							  	<option value="프리랜서">프리랜서</option>
							</select>
						</div>
						<div class="">
						  <button type="submit">입력</button>
						</div>
					</form>
					<c:choose>
					    <c:when test="${insertResult eq 'success'}">
					        <div class="success-msg">✅ 사원 정보 등록에 성공했습니다.</div>
					    </c:when>
					    <c:when test="${insertResult eq 'fail'}">
					        <div class="error-msg">❌ 등록 중 오류가 발생했습니다.</div>
					    </c:when>
					    <c:when test="${insertResult eq 'duplicate'}">
					        <div class="error-msg">❌ 사원 코드가 이미 존재합니다.</div>
					    </c:when>
					    <c:otherwise>
					        <!-- 아무 메시지도 출력 안 함 -->
					    </c:otherwise>
					</c:choose>
                </article>
            </div>
        </main>
    </div>

    <!-- FOOTER -->
    <footer>
        <div class="footer-content">
            <h3 class="footer-title">R&B 알앤비소프트</h3>
            <p class="footer-text">혁신적인 소프트웨어 솔루션으로 비즈니스의 성장을 지원합니다</p>
            <div class="footer-links">
                <a href="#">회사소개</a>
                <a href="#">서비스</a>
                <a href="#">고객지원</a>
                <a href="#">개인정보처리방침</a>
                <a href="#">이용약관</a>
            </div>
        </div>
    </footer>
</body>
</html>