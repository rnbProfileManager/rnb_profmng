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
				<li><a href="/selectProfile">프로필 조회</a></li>
                <li><a href="/empProfileInsert">사원 정보 추가</a></li>
				<li><a href="/empProfileManage" class="active">사원 정보 관리</a></li>
                <li><a href="/empProjectInsert">투입 인력 추가</a></li>
				<li><a href="/empProjectManage">투입 인력 관리</a></li>
				<li><a href="/empAbilityInsert">직무 능력 추가</a></li>
				<li><a href="/empAbilityManage">직무 능력 관리</a></li>
            </ul>
        </aside>

        <!-- MAIN CONTENT -->
        <main>
            <h1 class="main-title">사원 정보 관리</h1>
            <p class="main-subtitle">사원 코드를 입력해주세요</p>

			<div class="content-grid">
                <article class="content-card">
					<form action="/selectData" method="post">
						<div class="">
							<label for="searchInput">프로젝트 코드: <span class="required">*</span></label>
							<input type="text" name="projectCd" class="searchInput" placeholder="예: 0001" required>
	                    </div>
						<div class="">
						  <button type="submit">입력</button>
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