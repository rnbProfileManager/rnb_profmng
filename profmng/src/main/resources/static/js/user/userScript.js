$(document).ready(function() {
    // 로그인 상태를 확인하고 UI를 업데이트하는 함수
    function checkLoginStatus() {
        $.ajax({
            url: '/user/api/user/status', // 로그인 상태를 확인할 올바른 API 엔드포인트 URL
            method: 'GET',
            success: function(response) {
                var userInfoArea = $('#userInfoArea'); // HTML에 id="userInfoArea"를 가진 요소가 있다고 가정
                if (response.loggedIn && response.loggedInUserNm) {
                    // 로그인된 경우: 사용자 이름과 로그아웃 버튼 표시
                    userInfoArea.html(
                        '<span class="logged-in-userId">' + response.loggedInUserNm + '님</span>' +
                        '<a href="/user/logout" class="logout-btn">로그아웃</a>' // Spring Controller에 /user/logout 엔드포인트가 있는지 확인하세요
                    );
                } else {
                    // 로그인되지 않은 경우: 로그인 버튼 표시
                    userInfoArea.html(
                        '<a href="/user/showLogin" class="logout-btn">로그인</a>' // 로그인 페이지로 연결
                    );
                }
            },
            error: function(xhr, status, error) {
                console.error("로그인 상태 확인 실패:", status, error);
                // 오류 발생 시 로그인 버튼 표시로 대체
                $('#userInfoArea').html(
                    '<a href="/user/showLogin" class="login-btn">로그인</a>'
                );
            }
        });
    }

    // 문서 로드 시 로그인 상태 확인 함수 호출
    checkLoginStatus();	
});