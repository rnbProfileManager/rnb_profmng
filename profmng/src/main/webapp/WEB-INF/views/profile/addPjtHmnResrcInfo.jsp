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
				<li><a href="/profile/empBas">직원 정보</a></li>
				<li><a href="/profile/pjtHmnResrcInfo" class="active">투입 인력 관리</a></li>
				<li><a href="/profile/empAbilityInfo">직무 능력</a></li>
				<li><a href="#">캘린더</a></li>
				<li><a href="#">파일 관리</a></li>
				<li><a href="#">설정</a></li>
			</ul>
        </aside>

        <!-- MAIN CONTENT -->
        <main>
            <h1 class="main-title">프로젝트 수정</h1>
            <p class="main-subtitle">수정할 데이터를 입력해주세요</p>

            <div class="content-grid">
                <article class="content-card">
					<section class="search-area">
					    <h2>프로젝트 조회</h2>
					    <form class="edit-form" method="post">
							<div class="edit-group">
							    <label for="projectCd">프로젝트코드: <span class="required">*</span></label>
							    <input type="text" name="projectCd" class="searchInput" placeholder="예: 0001" required>
							</div>
							<div class="edit-group">
								<label for="searchInput">사원코드: <span class="required">*</span></label>
								<input type="text"  name="empId" class="searchInput" placeholder="예: 0001" required>
							</div>
							<div class="edit-group">
								<label for="searchInput">프로젝트명: <span class="required">*</span></label>
								<input type="text"  name="projectNm" class="searchInput" placeholder="예: ICIS-TR" required>
							</div>
							<div class="edit-group">
								<label for="dateInput">시작일자: <span class="required">*</span></label>
								<input type="date" name="startDate" class="dateInput" required>
							</div>
							<div class="edit-group">
								<label for="dateInput">종료일자:</label>
								<input type="date" name="endDate" class="dateInput">
							</div>
							<div class="edit-group">
								<label for="departmentSelect">역할:</label>
								<select class="departmentSelect" name="userRole">
								  <option value="전체">전체</option>
								  <option value="PM">PM</option>
								  <option value="PL">PL</option>
								  <option value="BA">BA</option>
								  <option value="개발">개발</option>
								</select>
							</div>
							<div class="grid-buttons">
							  <button type="button" onclick="add()" class="btn edit">추가</button>
							</div>
						</form>
					    <!-- 리스트 영역 -->
					    <div class="grid-area">
							<c:choose>
								<c:when test="${addResult eq 'success'}">
								    <div class="result-area">✅ 투입 인력 추가에 성공했습니다.</div>
								</c:when>
								<c:when test="${addResult eq 'duplicate'}">
								    <div class="result-area">❌ 중복된 코드가 있습니다.</div>
								</c:when>
								<c:when test="${addResult eq 'exception'}">
								    <div class="result-area">❌ 투입 인력 추가에 실패했습니다.</div>
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
	function add() {
		const form = document.querySelector('.edit-form');

		if (!form.checkValidity()) {
		    form.reportValidity();
		    return;
		}

		form.action = "/profile/addPjtHmnResrcInfo";
		form.method = "post";

		form.submit();
	}
	</script>
</body>
</html>