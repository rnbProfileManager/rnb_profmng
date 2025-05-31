<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 상세 정보</title>
</head>
<body>
    <h1>사용자 상세 정보</h1>
    <p>조회된 사용자: <strong>${testUser}</strong></p>
    <p>관련 텍스트: <strong>${testText}</strong></p>

    <h2>엔티티 객체로 접근</h2>
    <p>Test User (from entity): <strong>${entityData.testUser}</strong></p>
    <p>Test Text (from entity): <strong>${entityData.testText}</strong></p>
</body>
</html>