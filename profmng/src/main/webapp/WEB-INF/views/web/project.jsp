<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <a href="#" class="active">프로젝트 관리</a>
            </div>
            <div class="nav-item">
                <a href="/profile">사용자 관리</a>
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
                <li><a href="#" class="active">프로젝트 입력</a></li>
                <li><a href="#">프로젝트 조회</a></li>
                <li><a href="#">프로젝트 수정</a></li>
            </ul>
        </aside>

        <!-- MAIN CONTENT -->
        <main>
            <h1 class="main-title">프로젝트 입력</h1>
            <p class="main-subtitle">신규 프로젝트 데이터를 입력해주세요</p>

            <div class="content-grid">
                <article class="content-card">
                    <h3 class="card-title">신규 프로젝트</h3>
					<form action="insertProject.jsp" method="post">
						<div class="">
							<label for="searchInput">프로젝트 코드:</label>
							<input type="text" name="projectCd" class="searchInput" placeholder="예: 0001">
	                    </div>
						<div class="">
							<label for="searchInput">프로젝트 명:</label>
							<input type="text" name="projectNm" class="searchInput" placeholder="예: ICIS-TR 프로젝트">
						</div>
						<div class="">
							<label for="dateInput">시작 일자 선택:</label>
							<input type="date" name="startDate" class="dateInput">
						</div>
						<div class="">
							<label for="dateInput">종료 일자 선택:</label>
							<input type="date" name="endDate" class="dateInput">
						</div>
						<div class="">
							<label for="searchInput">PM 명:</label>
							<input type="text" name="pmId" class="searchInput" placeholder="예: 김상수">
						</div>
						<div class="">
							<label for="searchInput">발주기관:</label>
							<input type="text" name="client" class="searchInput" placeholder="예: KT">
						</div>
						<div class="">
							<label for="searchInput">주 수행사:</label>
							<input type="text" name="contractor" class="searchInput" placeholder="예: KT DS">
						</div>
						<div class="">
							<label for="number">총 투입 공수 입력:</label>
							<input type="number" name="manMonth" class="price" name="price" step="any" required>
						</div>
						<div class="">
							<label for="number">총 수주 금액 입력:</label>
							<input type="number" name="totAmt" class="price" name="price" step="any" required>
						</div>
						<div class="">
							<label for="departmentSelect">프로젝트 유형 선택:</label>
							<select class="departmentSelect" name="projectType">
							  <option value="전체">전체</option>
							  <option value="솔루션">솔루션</option>
							  <option value="컨설팅">컨설팅</option>
							  <option value="ITO">ITO</option>
							  <option value="PoC">PoC</option>
							  <option value="SI">SI</option>
							  <option value="SA">SA</option>
							  <option value="SM">SM</option>
							</select>
						</div>
						<div class="">
						  <button onclick="searchData()">입력</button>
						</div>
						<div class="">
						  <h2>검색 결과:</h2>
						  <ul id="resultList"></ul>
						</div>
					</form>
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