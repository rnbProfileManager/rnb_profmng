<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
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
				<img class="logo-circle" src="${pageContext.request.contextPath}/images/rnbsoft_logo.png">
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
				<li><a href="/profile/manage">프로필 조회</a></li>
				<li><a href="/profile/empBas" class="active">직원 정보</a></li>
				<li><a href="/profile/projectEmpInfo">투입 인력 관리</a></li>
				<li><a href="/profile/empAbility">직무 능력</a></li>
				<li><a href="#">캘린더</a></li>
				<li><a href="#">파일 관리</a></li>
				<li><a href="#">설정</a></li>
			</ul>
        </aside>

        <!-- MAIN CONTENT -->
        <main>
            <h1 class="main-title">직원정보 추가</h1>
            <p class="main-subtitle">신규 직원정보를 입력해주세요</p>

            <div class="content-grid">
                <article class="content-card">
					<section class="search-area">
					    <h2>직원정보</h2>
					    <form class="edit-form" method="post">
							<div class="edit-group">
							    <label for="searchInput">사원코드: </label>
							    <input type="text" name="empId" class="searchInput" value="${param.empId}" readonly>
							</div>
							<div class="edit-group">
								<label for="searchInput">사원명: </label>
								<input type="text"  name="empNm" class="searchInput" value="${param.empNm}" readonly>
							</div>
							<div class="edit-group">
								<label for="dateInput">입사일자: </label>
								<input type="date" name="efctStartDate" class="dateInput" value="${param.efctStartDate}" readonly>
							</div>
							<div class="edit-group">
								<label for="jobGradeCd">직급:</label>
								<select class="jobGradeCd" name="jobGradeCd">
								  	<option value="전체">전체</option>
									<option value="부사장">부사장</option>
									<option value="전무">전무</option>
								  	<option value="상무">상무</option>
									<option value="이사">이사</option>
									<option value="부장">부장</option>
									<option value="차장">차장</option>
									<option value="과장">과장</option>
									<option value="대리">대리</option>
									<option value="사원">사원</option>
								</select>
							</div>
							<div class="edit-group">
								<label for="jobTitleCd">직위:</label>
								<select class="jobTitleCd" name="jobTitleCd">
								  	<option value="전체">전체</option>
								  	<option value="본부장">본부장</option>
									<option value="실장">실장</option>
									<option value="팀장">팀장</option>
								  	<option value="팀원">팀원</option>
								</select>
							</div>
							<div class="edit-group">
								<label for="searchInput">주소:</label>
								<input type="text" name="homeAddr" class="searchInput" placeholder="예: KT DS">
							</div>
							<div class="edit-group">
								<label for="searchInput">전화번호:</label>
								<input type="tel" name="callNumber" class="searchInput" placeholder="예: 010-1234-5678">
							</div>
							<div class="edit-group">
								<label for="departmentSelect">부서:</label>
								<select class="departmentSelect" name="orgCd">
								  	<option value="전체">전체</option>
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
							<div class="edit-group">
								<label for="departmentSelect">고용형태:</label>
								<select class="departmentSelect" name="empTypeCd">
								  <option value="전체">전체</option>
								  <option value="정직원">정직원</option>
								  <option value="계약직">계약직</option>
								  <option value="프리랜서">프리랜서</option>
								</select>
							</div>
							<div class="grid-buttons">
							  <button type="button" onclick="edit()" class="btn edit">수정</button>
							</div>
						</form>
					    <!-- 리스트 영역 -->
					    <div class="grid-area">
							<c:choose>
								<c:when test="${editResult eq 'success'}">
								    <div class="result-area">✅ 직원정보 수정에 성공했습니다.</div>
								</c:when>
								<c:when test="${editResult eq 'exception'}">
								    <div class="result-area">❌ 직원정보 수정에 실패했습니다.</div>
								</c:when>
							    <c:otherwise>
							        <!-- 아무 메시지도 출력 안 함 -->
							    </c:otherwise>
							</c:choose>
					    </div>
					</section>
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
	<script>
	function edit() {
			const form = document.querySelector('.edit-form');

			if (!form.checkValidity()) {
			    form.reportValidity();
			    return;
			}

			form.action = "/profile/editEmpBas";
			form.method = "post";

			form.submit();
	}
	</script>
</body>
</html>