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
				<li><a href="/profile/empBas" class="active">직원 정보</a></li>
				<li><a href="/profile/pjtHmnResrcInfo">투입 인력 관리</a></li>
				<li><a href="/profile/empAbilityInfo">직무 능력</a></li>
				<li><a href="#">캘린더</a></li>
				<li><a href="#">파일 관리</a></li>
				<li><a href="#">설정</a></li>
			</ul>
        </aside>

        <!-- MAIN CONTENT -->
        <main>
            <h1 class="main-title">직원 정보 조회</h1>
            <p class="main-subtitle">사원명을 입력해주세요</p>

			<section class="search-area">
			    <h2>직원 정보 조회</h2>

			    <form class="search-form" method="get" action="/profile/empBas/manage">	
					<div class="form-group">
					    <label for="empNm">사원명</label>
					    <input type="text" id="empNm" name="empNm" value="${param.empNm}">
					</div>
			        <button type="submit" class="btn search">조회</button>
			    </form>
			    <!-- 리스트 영역 -->
			    <div class="grid-area">
					<table class="data-grid">
					    <thead>
					        <tr>
					            <th><input type="checkbox" id="checkAll" /></th>
					            <th>사원명</th>
					            <th>입사일</th>
					            <th>퇴사일</th>
					            <th>직급</th>
								<th>직위</th>
								<th>주소</th>
								<th>전화번호</th>
								<th>부서</th>
								<th>고용형태</th>
					        </tr>
					    </thead>
						    <tbody>
								<h3>총 사원수: ${fn:length(empBasList)}</h3>
									<c:forEach var="empBas" items="${empBasList}">
										<tr>
										   <td><input type="checkbox" name="empId" value="${empBas.empId}"
												data-empid="${empBas.empId}"
												data-empnm="${empBas.empNm}"
											    data-efctstartdate="${empBas.efctStartDate}" /></td>
										    <td>${empBas.empNm}</td>
										    <td>${empBas.efctStartDate}</td>
										    <td>${empBas.efctEndDate}</td>
											<td>${empBas.jobGradeCd}</td>
											<td>${empBas.jobTitleCd}</td>
											<td>${empBas.homeAddr}</td>
											<td>${empBas.callNumber}</td>
											<td>${empBas.orgCd}</td>
											<td>${empBas.empTypeCd}</td>
										</tr>
									</c:forEach>
						    </tbody>
						</table>
			        <div class="grid-buttons">
			            <button type="button" onclick="location.href='/profile/addEmpBas'" class="btn new">신규</button>
			            <button type="button" onclick="editSelected()" class="btn edit">수정</button>
			            <button type="button" onclick="deleteSelected()" class="btn delete">삭제</button>
			        </div>
					<form id="editForm" method="POST" action="/project/editEmpBas">
					    <input type="hidden" name="empNm" id="editEmpBas" />
					</form>
					<form id="deleteForm" method="POST" action="/project/deleteEmpBas">
					    <input type="hidden" name="empId" id="deleteEmpBas" />
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
	        $('input[name="empId"]').prop('checked', isChecked);
	    });
	});
	function editSelected() {
	    const checkedItems = document.querySelectorAll('input[name="empId"]:checked');

	    if (checkedItems.length === 0) {
	        alert("프로젝트를 선택하세요.");
	        return;
	    }

	    if (checkedItems.length > 1) {
	        alert("하나의 프로젝트만 선택해주세요.");
	        return;
	    }
		
		
		const item = checkedItems[0];
	    
		const empId = item.dataset.empid;
		const empNm = item.dataset.empnm;
	    const efctStartDate = item.dataset.efctstartdate;
			
		if (!empId || !efctStartDate) {
		    alert("데이터가 잘못되었습니다. 선택한 항목의 값을 확인하세요.");
		    return;
		}

		window.location.href = "/profile/editEmpBas"
		    + "?empId=" + encodeURIComponent(empId)
			+ "&empNm=" + encodeURIComponent(empNm)
		    + "&efctStartDate=" + encodeURIComponent(efctStartDate);
	}
	function deleteSelected() {
	    const checkedItems = document.querySelectorAll('input[name="empId"]:checked');

	    if (checkedItems.length === 0) {
	        alert("프로젝트를 선택하세요.");
	        return;
	    }

	    if (!confirm("정말 삭제하시겠습니까?")) {
	        return;
	    }

	    const empIds = Array.from(checkedItems).map(item => item.value);
	    const param = empIds.map(cd => "empId=" + encodeURIComponent(cd)).join("&");

	    window.location.href = "/profile/deleteEmpBas?" + param;
	}
	</script>
</body>
</html>