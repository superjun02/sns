<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-6">
	<div class="pt-3 pb-2">
		<h2>로그인</h2>
	</div>
	<div
		class="loginForm p-3 d-flex justify-content-center align-items-center">
		<form id="signInForm" method="post" action="/user/sign-in">
			<div>
				<div class="input-group mb-2">
					<div class="input-group-prepend">
						<div class="input-group-text">ID</div>
					</div>
					<input type="text" class="form-control" id="loginId" name="loginId">
				</div>
				<div class="input-group mb-2">
					<div class="input-group-prepend">
						<div class="input-group-text">PW</div>
					</div>
					<input type="password" class="form-control" id="password" name="password">
				</div>
				<div class="pt-1">
					<input type="submit" value="로그인" class="btn btn-primary col-12">
				</div>
				<div class="pt-1">
					<a href="/user/sign-up-view" class="btn btn-secondary col-12">회원가입</a>
				</div>
			</div>
		</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		// 로그인
		$('form').on('submit', function(e) {
			e.preventDefault();
			
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val().trim();
			
			if (!loginId) {
				alert("아이디를 입력해주세요")
				return false;
			}
			if (!password) {
				alert("패스워드를 입력해주세요")
				return false;
			}
			
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			$.post(url, params)
			.done(function(data) {
				if (data.result == "성공") {
					location.href = "/timeline/timeline-view";
				} else {
					alert(data.error_message);
				}
			});
		});
	});
</script>