<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>R&B 알앤비소프트 - 로그인</title>
    <link rel="stylesheet" href="<c:url value='/css/login.css' />">
</head>
<body>
    <div class="login-container">
        <div class="logo-section">
            <!-- 여기에 실제 로고 이미지를 넣으세요 -->
            <!-- <img src="<c:url value='/images/logo.png' />" alt="R&B Logo" style="width: 80px; height: 80px;"> -->
            <div class="logo">
                <div class="logo-text">R&B</div>
            </div>
            <h1 class="company-name">알앤비소프트</h1>
            <p class="company-subtitle">RnB Software Solutions</p>
        </div>

        <form action="<c:url value='/login' />" method="post">
            <div class="form-group">
                <input type="text" name="username" class="form-input" placeholder="계정" required>
            </div>
            
            <div class="form-group">
                <input type="password" name="password" class="form-input" placeholder="비밀번호" required>
            </div>
            
            <button type="submit" class="login-button">로그인</button>
            
            <div class="checkbox-container">
                <input type="checkbox" id="saveLogin" name="saveLogin" class="checkbox">
                <label for="saveLogin" class="checkbox-label">계정 저장</label>
            </div>
            
            <!-- CSRF 토큰 (Spring Security 사용 시) -->
            <c:if test="${not empty _csrf}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </c:if>
        </form>

        <div class="additional-links">
            <a href="<c:url value='/forgot-password' />" class="link">비밀번호 찾기</a>
            <span style="color: #ddd;">|</span>
            <a href="<c:url value='/register' />" class="link">회원가입</a>
        </div>
        
        <!-- 에러 메시지 표시 -->
        <c:if test="${not empty error}">
            <div class="error-message">
                ${error}
            </div>
        </c:if>
        
        <!-- 성공 메시지 표시 -->
        <c:if test="${not empty message}">
            <div class="success-message">
                ${message}
            </div>
        </c:if>
    </div>
</body>
</html>