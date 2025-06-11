<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <li><a href="/insertProject">프로젝트 입력</a></li>
                <li><a href="/selectProject" class="active">프로젝트 조회</a></li>
                <li><a href="/updateProject">프로젝트 수정</a></li>
            </ul>
        </aside>

        <!-- MAIN CONTENT -->
        <main>
            <h1 class="main-title">프로젝트 입력</h1>
            <p class="main-subtitle">프로젝트 코드를 입력해주세요</p>

            <div class="content-grid">
                <article class="content-card">
                    <h3 class="card-title">신규 프로젝트</h3>
					<form action="/selectData" method="post">
						<div class="">
							<label for="searchInput">프로젝트 코드: <span class="required">*</span></label>
							<input type="text" name="projectCd" class="searchInput" placeholder="예: 0001" required>
	                    </div>
						<div class="">
						  <button type="submit">검색</button>
						</div>
					</form>
					<c:choose>
					    <c:when test="${selectAll eq 'success'}">
					        <div class="success-msg">✅ 프로젝트 조회에 성공했습니다.</div>
							<div class="">
								<label for="searchInput">프로젝트 코드: ${projectCd}</label>
					        </div>
							<div class="">
								<label for="searchInput">프로젝트 명: ${projectNm}</label>
							</div>
							<div class="">
								<label for="dateInput">시작 일자 선택: ${startDate}</label>
							</div>
							<div class="">
								<label for="dateInput">종료 일자 선택: ${endDate}</label>
							</div>
							<div class="">
								<label for="searchInput">PM 명: ${pmId}</label>
							</div>
							<div class="">
								<label for="searchInput">발주기관: ${client}</label>
							</div>
							<div class="">
								<label for="searchInput">주 수행사: ${contractor}</label>
							</div>
							<div class="">
								<label for="number">총 투입 공수 입력: ${manMonth}</label>
							</div>
							<div class="">
								<label for="number">총 수주 금액 입력: ${totAmt}</label>
							</div>
							<div class="">
								<label for="departmentSelect">프로젝트 유형 선택: ${projectType}</label>
							</div>
					    </c:when>
						<c:when test="${selectAll eq 'no Data'}">
						    <div class="error-msg">❌ 일치하는 데이터가 없습니다.</div>
						</c:when>
					    <c:when test="${selectAll eq 'fail'}">
					        <div class="error-msg">❌ 조회 중 오류가 발생했습니다.</div>
					    </c:when>
					    <c:when test="${selectAll eq 'duplicate'}">
					        <div class="error-msg">❌ 프로젝트 코드가 이미 존재합니다.</div>
					    </c:when>
					    <c:otherwise>
					        <!-- 아무 메시지도 출력 안 함 -->
					    </c:otherwise>
					</c:choose>
					<c:choose>
					    <c:when test="${selectResult eq 'success'}">
					        <div class="success-msg">✅ 프로젝트 조회에 성공했습니다.</div>
							<div class="">
								<label for="searchInput">프로젝트 코드: ${projectCd}</label>
		                    </div>
							<div class="">
								<label for="searchInput">프로젝트 명: ${projectNm}</label>
							</div>
							<div class="">
								<label for="dateInput">시작 일자 선택: ${startDate}</label>
							</div>
							<div class="">
								<label for="dateInput">종료 일자 선택: ${endDate}</label>
							</div>
							<div class="">
								<label for="searchInput">PM 명: ${pmId}</label>
							</div>
							<div class="">
								<label for="searchInput">발주기관: ${client}</label>
							</div>
							<div class="">
								<label for="searchInput">주 수행사: ${contractor}</label>
							</div>
							<div class="">
								<label for="number">총 투입 공수 입력: ${manMonth}</label>
							</div>
							<div class="">
								<label for="number">총 수주 금액 입력: ${totAmt}</label>
							</div>
							<div class="">
								<label for="departmentSelect">프로젝트 유형 선택: ${projectType}</label>
							</div>
							<form action="/updateButton" method="post">
								<div class="">
									<c:choose>
										<c:when test="${projectCd != null}">
											<input type="hidden" name="projectCd" value="${projectCd}" />
											<button type="submit">수정</button>
										</c:when>
										<c:otherwise>
										    <button type="submit">수정</button>
										</c:otherwise>	
									</c:choose>    
								</div>
							</form>
							<form action="/deleteButton" method="post" onsubmit="return confirmDelete();">
								<div class="">
									<input type="hidden" name="projectCd" value="${projectCd}" />
									<button type="submit">삭제</button>
								</div>
							</form>
							<script>
							function confirmDelete() {
							    return confirm("정말 삭제하시겠습니까?");
							}
							</script>
					    </c:when>
						<c:when test="${selectResult eq 'no Data'}">
						    <div class="error-msg">❌ 일치하는 데이터가 없습니다.</div>
						</c:when>
					    <c:when test="${selectResult eq 'fail'}">
					        <div class="error-msg">❌ 조회 중 오류가 발생했습니다.</div>
					    </c:when>
					    <c:when test="${selectResult eq 'duplicate'}">
					        <div class="error-msg">❌ 프로젝트 코드가 이미 존재합니다.</div>
					    </c:when>
						<c:when test="${deleteResult eq 'success'}">
						    <div class="error-msg">✅ 프로젝트 삭제에 성공했습니다.</div>
						</c:when>
						<c:when test="${deleteResult eq 'no Data'}">
						    <div class="error-msg">❌ 일치하는 데이터가 없습니다.</div>
						</c:when>
						<c:when test="${deleteResult eq 'fail'}">
						    <div class="error-msg">❌ 조회 중 오류가 발생했습니다.</div>
						</c:when>
						<c:when test="${deleteResult eq 'duplicate'}">
						    <div class="error-msg">❌ 프로젝트 코드가 이미 존재합니다.</div>
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