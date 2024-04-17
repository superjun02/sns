<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-6">
	<div class="pt-3 pb-2">
		<h2>회원가입</h2>
	</div>
	<div class="loginForm p-3">
		<form method="post" action="#">
			<div class="pt-2">
				<label for="loginId">ID</label>
				<div class="d-flex">
					<input type="text" id="loginId" name="loginId"
						class="form-control col-7" placeholder="ID를 입력해주세요">
					<div>
						<button type="button" id="loginIdChkBtn" class="btn btn-info">중복확인</button>
					</div>
				</div>
				<div id="idCheckLength" class="small text-danger d-none">ID를
					4자 이상 입력해주세요.</div>
				<div id="idCheckDuplicated" class="small text-danger d-none">이미
					사용중인 ID입니다.</div>
				<div id="idCheckOk" class="small text-success d-none">사용 가능한
					ID 입니다.</div>
			</div>
			<div class="pt-2">
				<label for="password">password</label> <input type="password"
					id="password" name="password" class="form-control col-6">
			</div>
			<div class="pt-2">
				<label for="passwordChk">confirm password</label> <input type="password"
					id="passwordChk" name="passwordChk" class="form-control col-6">
			</div>
			<div class="pt-2">
				<label for="name">이름</label> <input type="text" id="name"
					name="name" class="form-control col-7" placeholder="이름을 입력해주세요">
			</div>
			<div class="pt-2">
				<label for="email">이메일</label>
				<input type="text" id="email"
					name="email" class="form-control col-7" placeholder="이메일을 입력해주세요">
			</div>
			<div class="pt-3 d-flex justify-content-center">
				<input type="submit" value="가입하기" class="btn btn-primary">
			</div>
		</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('#loginIdChkBtn').on('click', function() {
			$('#idCheckLength').addClass("d-none");
			$('#idCheckDuplicated').addClass("d-none");
			$('#idCheckOk').addClass("d-none");
			
			let loginId = $("input[name=loginId]").val().trim();
			if (loginId.length < 4) {
				$('#idCheckLength').removeClass("d-none");
				return;
			}
			
			$.get("/user/is-duplicated-id", {"loginId" : loginId})
			.done(function(data) {
				if (data.code == 200) {
					if (data.is_duplicated_id) {
						$('#idCheckDuplicated').removeClass("d-none");
					} else {
						$('#idCheckOk').removeClass("d-none");
					}
				} else {
					alert(data.error_message);
				}
				
			});
		});
	});
</script>