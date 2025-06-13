<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
				<li><a href="/profile/empNo">직원 정보</a></li>
				<li><a href="/profile/projectEmpInfo" class="active">투입 인력 관리</a></li>
				<li><a href="/profile/empAbility">직무 능력</a></li>
				<li><a href="#">캘린더</a></li>
				<li><a href="#">파일 관리</a></li>
				<li><a href="#">설정</a></li>            
			</ul>
        </aside>

        <!-- MAIN CONTENT -->
        <main>
            <h1 class="main-title">투입 인력 조회</h1>
            <p class="main-subtitle">사원 코드를 입력해주세요</p>

			<section class="search-area">
			    <h2>투입 인력 조회</h2>

			    <form class="search-form" method="get" action="/profile/projectEmpInfo/manage">	
					<div class="form-group">
					    <label for="empCd">사원 코드</label>
					    <input type="text" id="empCd" name="empCd" value="${param.empCd}">
					</div>
			        <button type="submit" class="btn search">조회</button>
			    </form>
			    <!-- 리스트 영역 -->
			    <div class="grid-area">
					<table class="data-grid">
					    <thead>
					        <tr>
					            <th><input type="checkbox" id="checkAll" /></th>
					            <th>프로젝트코드</th>
					            <th>사원코드</th>
								<th>프로젝트명</th>
					            <th>시작일자</th>
					            <th>종료일자</th>
					            <th>역할</th>
					        </tr>
					    </thead>
						    <tbody>
								<h3>총 건수: ${fn:length(projectEmpInfoList)}</h3>
									<c:forEach var="projectEmpInfo" items="${projectEmpInfoList}">
										<tr>
										   <td><input type="checkbox" name="empCd" value="${projectEmpInfo.empCd}"
												data-projectcd="${projectEmpInfo.projectCd}"
									       		data-empcd="${projectEmpInfo.empCd}"
												data-projectnm="${projectEmpInfo.projectNm}"
											    data-startdate="${projectEmpInfo.startDate}" /></td>
											<td>${projectEmpInfo.projectCd}</td>
										    <td>${projectEmpInfo.empCd}</td>
										    <td>${projectEmpInfo.projectNm}</td>
										    <td>${projectEmpInfo.startDate}</td>
										    <td>${projectEmpInfo.endDate}</td>
											<td>${projectEmpInfo.userRole}</td>
										</tr>
									</c:forEach>
						    </tbody>
						</table>
			        <div class="grid-buttons">
			            <button type="button" onclick="location.href='/profile/addProjectEmpInfo'" class="btn new">신규</button>
			            <button type="button" onclick="editSelected()" class="btn edit">수정</button>
			            <button type="button" onclick="deleteSelected()" class="btn delete">삭제</button>
			        </div>
					<form id="editForm" method="POST" action="/profile/editProjectEmpInfo">
					    <input type="hidden" name="empCd" id="editProjectEmpInfo" />
					</form>
					<form id="deleteForm" method="POST" action="/profile/deleteProjectEmpInfo">
					    <input type="hidden" name="empCd" id="deleteProjectEmpInfo" />
					</form>
					<c:choose>
						<c:when test="${deleteResult eq 'success'}">
						    <div class="result-area">✅ 프로젝트 삭제에 성공했습니다.</div>
						</c:when>
						<c:when test="${deleteResult eq 'exception'}">
						    <div class="result-area">❌ 프로젝트 삭제에 실패했습니다.</div>
						</c:when>
					    <c:otherwise>
					        <!-- 아무 메시지도 출력 안 함 -->
					    </c:otherwise>
					</c:choose>
			    </div>
			</section>
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
	$(document).ready(function () {
	    $('#checkAll').on('change', function () {
	        const isChecked = $(this).is(':checked');
	        $('input[name="empCd"]').prop('checked', isChecked);
	    });
	});
	function editSelected() {
	    const checkedItems = document.querySelectorAll('input[name="empCd"]:checked');

	    if (checkedItems.length === 0) {
	        alert("프로젝트를 선택하세요.");
	        return;
	    }

	    if (checkedItems.length > 1) {
	        alert("하나의 프로젝트만 선택해주세요.");
	        return;
	    }
		
		const item = checkedItems[0];
	    const projectCd = item.dataset.projectcd;
		const empCd = item.dataset.empcd;
	    const projectNm = item.dataset.projectnm;
	    const startDate = item.dataset.startdate;
			
		if (!projectCd || !empCd || !projectNm || !startDate) {
		    alert("데이터가 잘못되었습니다. 선택한 항목의 값을 확인하세요.");
		    return;
		}

		window.location.href = "/profile/editProjectEmpInfo"
		    + "?projectCd=" + encodeURIComponent(projectCd)
			+ "&empCd=" + encodeURIComponent(empCd)
		    + "&projectNm=" + encodeURIComponent(projectNm)
		    + "&startDate=" + encodeURIComponent(startDate);
	}
	function deleteSelected() {
	    const checkedItems = document.querySelectorAll('input[name="empCd"]:checked');

	    if (checkedItems.length === 0) {
	        alert("프로젝트를 선택하세요.");
	        return;
	    }

	    if (!confirm("정말 삭제하시겠습니까?")) {
	        return;
	    }

	    const empCds = Array.from(checkedItems).map(item => item.value);
	    const param = empCds.map(cd => "empCd=" + encodeURIComponent(cd)).join("&");

	    window.location.href = "/profile/deleteProjectEmpInfo?" + param;
	}
	</script>
</body>
</html>