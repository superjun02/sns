<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="content-box col-5">
	<c:if test="${not empty userId}">
		<div id="postInputBox" class="mt-3">
			<textarea id="postBox" rows="3" class="w-100 form-control" placeholder="내용을 입력해주세요"></textarea>
			<div class="d-flex justify-content-between">
				<div>
					<button id="imgBtn"><img alt="이미지 삽입" src="/static/img/image_icon.png"></button>
				</div>
				<div>
					<button class="btn btn-info">게시</button>
				</div>
			</div>
		</div>
	</c:if>
	<c:forEach items="${postList}" var="post">
		<div class="timelineBox mt-5">
			<div id="userNameBox" class="d-flex justify-content-between">
				<div class="mt-1 mb-1 ml-3">
					<b>${post.userId}</b>
				</div>
				<div class="ml-3">
					<button id="infoBtn"><img alt="이미지 삽입" src="/static/img/more-icon.png" width="28px" height="28px"></button>
				</div>
			</div>
			<div>
				<div>
					<img alt="" src="${post.imagePath}" width="432px">
				</div>
			</div>
			<div id="likeBox">
				<div class="mt-3">
					<button id="likeBtn"><img alt="이미지 삽입" src="/static/img/empty-heart-icon.png" width="28px" height="28px"></button>				
					<b>좋아요 11개</b>
				</div>
				<div>
					<b>${post.userId}</b> ${post.content}
				</div>
			</div>
			<div id="commentBox">
				<div class="mt-1 mb-1 ml-3">
					<small><b>댓글</b></small>
				</div>
			</div>
			<div>
				<table>
					<tr>
						<th class="col-3">userId</th>
						<td class="col-9">분류가 잘되었군요</td>
					</tr>
					<tr>
						<th class="col-3">userId</th>
						<td class="col-9">이게 모야???</td>
					</tr>
					<tr>
						<th class="col-3">userId</th>
						<td class="col-9">철이 없었죠 분류를 위해 클러스터를 썼다는게</td>
					</tr>
				</table>
			</div>
			<div class="input-group mt-2">
				<input type="text" class="form-control" id="inputComment" name="inputComment" placeholder="댓글 내용을 입력해주세요">
				<div class="input-group-prepend">
					<button id="inputCommentBtn" class="text-primary">게시</button>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<script>
	$(document).ready(function() {
		$('#infoBtn').on('click', function() {
			alert('더보기 버튼 클릭');
		});
		$('#imgBtn').on('click', function() {
			alert('이미지 버튼 클릭');
		});
		$('#likeBtn').on('click', function() {
			alert('좋아요 버튼 클릭');
		});
		$('#inputCommentBtn').on('click', function() {
			alert('댓글입력 버튼 클릭');
		});
	});
</script>