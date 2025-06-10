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
                    <h3 class="card-title">진행 중인 프로젝트</h3>
                    <div class="card-content">
                        현재 5개의 프로젝트가 진행 중입니다. 이번 주 마감 예정인 프로젝트가 2개 있으니 확인해 주세요.
                    </div>
                    <div class="card-date">2024.12.01</div>
                </article>

                <article class="content-card">
                    <h3 class="card-title">팀 업무 현황</h3>
                    <div class="card-content">
                        개발팀 85% 완료, 디자인팀 92% 완료, QA팀 78% 완료 상태입니다. 전체적으로 순조로운 진행 상황입니다.
                    </div>
                    <div class="card-date">2024.12.01</div>
                </article>

                <article class="content-card">
                    <h3 class="card-title">시스템 알림</h3>
                    <div class="card-content">
                        서버 점검이 이번 주 토요일 새벽 2시에 예정되어 있습니다. 약 2시간 소요될 예정입니다.
                    </div>
                    <div class="card-date">2024.11.30</div>
                </article>

                <article class="content-card">
                    <h3 class="card-title">월간 리포트</h3>
                    <div class="card-content">
                        11월 프로젝트 완료율 94%, 고객 만족도 4.8/5.0으로 목표를 달성했습니다. 수고하셨습니다!
                    </div>
                    <div class="card-date">2024.11.29</div>
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