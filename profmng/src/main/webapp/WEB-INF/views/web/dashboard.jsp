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
                <a href="#" class="active">대시보드</a>
            </div>
            <div class="nav-item">
                <a href="/project">프로젝트 관리</a>
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
                <li><a href="#" class="active">대시보드</a></li>
                <li><a href="#">새 프로젝트</a></li>
                <li><a href="#">작업 현황</a></li>
                <li><a href="#">팀 관리</a></li>
                <li><a href="#">캘린더</a></li>
                <li><a href="#">파일 관리</a></li>
                <li><a href="#">설정</a></li>
            </ul>
        </aside>

        <!-- MAIN CONTENT -->
        <main>
            <h1 class="main-title">대시보드</h1>
            <p class="main-subtitle">프로젝트 현황과 주요 업무를 한눈에 확인하세요</p>

            <div class="content-grid">
                <article class="content-card">
                    <h3 class="card-title">최근 진행 중인 프로젝트1</h3>
					<div class="card-content">프로젝트 코드 : ${dashboard_projectCd1}</div>
					<div class="card-content">프로젝트 명 : ${dashboard_projectNm1}</div>
                    <div class="card-date">${dashboard_startDate1} ~ ${dashboard_endDate1}</div>
                </article>

                <article class="content-card">
                    <h3 class="card-title">최근 진행 중인 프로젝트2</h3>
					<div class="card-content">프로젝트 코드 : ${dashboard_projectCd2}</div>
					<div class="card-content">프로젝트 명 : ${dashboard_projectNm2}</div>
                    <div class="card-date">${dashboard_startDate2} ~ ${dashboard_endDate2}</div>
                </article>

                <article class="content-card">
                    <h3 class="card-title">최근 진행 중인 프로젝트3</h3>
					<div class="card-content">프로젝트 코드 : ${dashboard_projectCd3}</div>
					<div class="card-content">프로젝트 명 : ${dashboard_projectNm3}</div>
                    <div class="card-date">${dashboard_startDate3} ~ ${dashboard_endDate3}</div>
                </article>

                <article class="content-card">
                    <h3 class="card-title">최근 진행 중인 프로젝트4</h3>
                    <div class="card-content">프로젝트 코드 : ${dashboard_projectCd4}</div>
					<div class="card-content">프로젝트 명 : ${dashboard_projectNm4}</div>
                    <div class="card-date">${dashboard_startDate4} ~ ${dashboard_endDate4}</div>
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