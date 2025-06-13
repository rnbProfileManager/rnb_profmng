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
                <a href="/profile/manage" class="active">사용자 관리</a>
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
                <li><a href="/profile/manage" class="active">프로필 조회</a></li>
                <li><a href="/profile/empNo">직원 정보</a></li>
                <li><a href="/profile/projectEmpCd">투입 인력 관리</a></li>
				<li><a href="/profile/empAbility">직무 능력</a></li>
                <li><a href="#">캘린더</a></li>
                <li><a href="#">파일 관리</a></li>
                <li><a href="#">설정</a></li>
            </ul>
        </aside>

        <!-- MAIN CONTENT -->
        <main>
            <h1 class="main-title">프로필 조회</h1>
            <p class="main-subtitle">직원명, 입사기간을 조회해 주세요. <br> doc,docx 파일만 업로드 가능합니다.</p>

			<section class="search-area">
			    <h2>프로필 조회</h2>

			    <form class="search-form" method="get" action="/profile/manage">	
					<div class="form-group">
					    <label for="empNm">직원명</label>
					    <input type="text" id="empNm" name="empNm" value="${param.empNm}">
					</div>
			        <div class="form-group">
			            <label for="startDate">기간</label>
			            <input type="date" id="startDate" name="startDate" value="${param.startDate}">
			            <span>~</span>
			            <input type="date" id="endDate" name="endDate" value="${param.endDate}">
			        </div>
			        <button type="submit" class="btn search">조회</button>
			    </form>

			    <!-- 리스트 영역 -->
			    <div class="grid-area">
					<table class="data-grid">
					    <thead>
					        <tr>
					            <th><input type="checkbox" /></th>
					            <th>직원명</th>
					            <th>이력서</th>
					            <th>경력</th>
					            <th>휴대폰번호</th>
					            <th>이력서 업로드</th>
					        </tr>
					    </thead>

						    <tbody>
								<h3>직원수: ${fn:length(employeeList)}</h3>
									<c:forEach var="emp" items="${employeeList}">
										<tr>
										   <td><input type="checkbox" name="empChk" value="${emp.empCd}" /></td>
										    <td>${emp.empNm}</td>
										    <td>
												<c:choose>
												    <c:when test="${not empty emp.filePath}">
												      <a href="/download?file=${emp.filePath}">${emp.fileName}</a>
												    </c:when>
												    <c:otherwise>등록된 이력서 없음</c:otherwise>
												  </c:choose></td>
										    <td>${emp.jobTitle}</td>
										    <td>${emp.callNumber}</td>
										    <td>
												<input type="hidden" class="empCd" value="${emp.empCd}" />
												<input type="hidden" class="empNm" value="${emp.empNm}" />
												<input type="file" id="fileInput_${emp.empCd}" style="display:none;" />
												<button type="button" onclick="$('#fileInput_${emp.empCd}').click();" class="btn">파일 선택</button>
										    </td>
										</tr>
									</c:forEach>
						  
						    </tbody>
						</table>
						<script>
							$(document).on("change", "input[type=file]", function() {
							    const file = this.files[0];
							    if (!file) return;
							    const $tr = $(this).closest('tr');
							    const empCd = $tr.find('.empCd').val();
							    const empNm = $tr.find('.empNm').val();

							    const formData = new FormData();
							    formData.append("file", file);
							    formData.append("empCd", empCd);
							    formData.append("empNm", empNm);

							    $.ajax({
							        url: "/upload",
							        type: "POST",
							        data: formData,
							        contentType: false,
							        processData: false,
									success: function(response) {
									    if(response === "OK"){
									        alert("업로드 성공");
									        location.reload();
									    } else {
									        alert("업로드 실패: " + response);
									    }
									},
									error: function(xhr, status, error) {
									    alert("업로드 실패: " + error);
									}
							    });
							});
						    </script>
			        <div class="file-actions">
			            
			        </div>

			        <div class="grid-buttons">
			            <button type="button" onclick="location.href='/profile/new'" class="btn new">신규</button>
			            <button type="button" onclick="editSelected()" class="btn edit">수정</button>
			            <button type="button" onclick="deleteSelected()" class="btn delete">삭제</button>
			        </div>
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
</body>
</html>