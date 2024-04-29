<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="content-box col-5">
	<c:if test="${not empty userId}">
		<div id="postInputBox" class="mt-3">
			<textarea id="content" rows="3" class="w-100 form-control" placeholder="내용을 입력해주세요"></textarea>
			<div class="d-flex justify-content-between">
				<div class="d-flex">
					<input id="file" type="file" class="d-none" accept=".jpg, .png, .gif"/>
					<button id="imgBtn"><img alt="이미지 삽입" src="/static/img/image_icon.png"></button>
					<div id="fileName" class="mt-2"></div>
				</div>
				
				<div>
					<button id="saveBtn" class="btn btn-info">게시</button>
				</div>
			</div>
		</div>
	</c:if>
	<c:forEach items="${cardList}" var="card">
		<div class="timelineBox mt-5">
			<div class="d-flex justify-content-between userNameBox">
				<div class="mt-1 mb-1 ml-3">
					<b>${card.postUserId}</b>
				</div>
				<div class="ml-3">
					<button class="infoBtn"><img alt="이미지 삽입" src="/static/img/more-icon.png" width="28px" height="28px"></button>
				</div>
			</div>
			<div>
				<div>
					<img alt="" src="${card.post.imagePath}" width="431px">
				</div>
			</div>
			<div class="likeBox">
				<div class="mt-3 pl-2">
					<c:choose>
						<c:when test="${card.like}">
							<button class="likeBtn" value="${card.post.id}"><img alt="이미지 삽입" src="/static/img/heart-icon.png" width="28px" height="28px"></button>
						</c:when>
						<c:otherwise>
							<button class="likeBtn" value="${card.post.id}"><img alt="이미지 삽입" src="/static/img/empty-heart-icon.png" width="28px" height="28px"></button>	
						</c:otherwise>
					</c:choose>		
					<b>좋아요 ${card.likeNum}개</b>
				</div>
				<div class="pl-3">
					<b>${card.postUserId}</b> ${card.post.content}
				</div>
			</div>
			<div class="commentBox">
				<div class="mt-1 mb-1 ml-3">
					<small><b>댓글</b></small>
				</div>
			</div>
			<div class="mt-2 commentTable">
				<table>
					<c:forEach items="${card.replyList}" var="reply">
						<tr>
							<th class="col-3">${reply.commentUserLoginId}</th>
							<td class="col-9">${reply.comment.content}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<c:if test="${not empty userId}">
				<div id="commentInputBox" class="input-group mt-2">
					<input type="text" class="form-control inputComment" id="inputComment${card.post.id}" name="inputComment" placeholder="댓글 내용을 입력해주세요">
					<div class="input-group-prepend">
						<button class="inputCommentBtn text-primary" value="${card.post.id}">게시</button>
					</div>
				</div>
			</c:if>
		</div>
	</c:forEach>
</div>
<script>
	$(document).ready(function() {
		$('.infoBtn').on('click', function() {
			alert('더보기 버튼 클릭성공');
		});
		
		$('#imgBtn').on('click', function() {
			let file = document.getElementById("file");
			file.click();
		});
		
		$('#saveBtn').on('click', function() {
			let content = $('#content').val();	
			let fileName = $('#file').val();	
			
			if (!content) {
				alert("내용을 입력하세요");
				return;
			}
			
			if (fileName) {
				let extension = fileName.split(".").pop().toLowerCase();
				if ($.inArray(extension, ['jpg', 'png', 'gif']) == -1) {
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$('#file').val("");
					$('#originName').text("");
					return;
				}
			}
			
			let formData = new FormData();
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]);
			
			$.ajax({
				type:'POST'
				, url:"/post/post-create"
				, data:formData
				, enctype:"multipart/form-data"
				, processData:false
				, contentType:false
				, success:function(data) {
					if (data.code == 200) {
						alert("작성 되었습니다.");
						location.href = "/timeline/timeline-view";
					} else {
						alert(data.error_message);
					}
				}
				, error:function(e) {
					alert("글을 작성하는데 실패하였습니다.")
				}
			});
		});
		
		$('.likeBtn').on('click', function() {
			let postId = $(this).val();

			$.ajax({
				url:"/like/" + postId
				, success:function(data) {
					if (data.code == 200) {
						location.reload(true);
					} else {
						alert(data.error_message);
					}
				}
				, error:function(e) {
					alert("좋아요버튼을 작동하는데 실패하였습니다.")
				}
			});
		});
		
		$('.inputCommentBtn').on('click', function() {
			let postId = $(this).val();
			let inputComment = $('#inputComment' + postId).val();	
			
			if (!inputComment) {
				alert("내용을 입력하세요");
			}
			
			$.ajax({
				type:'POST'
				, url:"/comment/comment-create"
				, data: {"content":inputComment, "postId":postId}
				, success:function(data) {
					if (data.code == 200) {
						location.reload(true);
					} else {
						alert(data.error_message);
					}
				}
				, error:function(e) {
					alert("댓글글을 작성하는데 실패하였습니다.")
				}
			});
		});
		
		let target = document.getElementById('file');
		target.addEventListener('change',function(){
			
			if (target.value.length){
				$('#fileName').text(target.files[0].name);
			} else {
				$('#fileName').text("");
			}
		});
	});
</script>