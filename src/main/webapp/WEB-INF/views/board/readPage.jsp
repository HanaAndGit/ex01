<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<script>
	$(function(){
		$("#mod").click(function(){
			var bno = $("input[name='bno']").val();
			location.href= "${pageContext.request.contextPath }/board/updatePage?bno="+bno+"&page=${cri.page}"; 
		})
		$("#del").click(function(){
			var bno = $("input[name='bno']").val();
			location.href = "${pageContext.request.contextPath }/board/deletePage?bno="+bno+"&page=${cri.page}"; 
		})
		$("#go").click(function(){
			location.href = "${pageContext.request.contextPath }/board/listPage?page=${cri.page}"; 
		})
	})

</script>

<div class="content">	
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<label>bno</label>
						<input type="text" class="form-control" value="${vo.bno }" readonly="readonly" name="bno">
					</div>
					<div class="form-group">
						<label>Title</label>
						<input type="text" class="form-control" value="${vo.title }" readonly="readonly" name="title">
					</div>
					<div class="form-group">
						<label>Content</label>
						<textarea rows="5" cols="30" class="form-control" readonly="readonly" name="content">${vo.content }</textarea>
					</div>
					<div class="form-group">
						<label>Wrtier</label>
						<input type="text" class="form-control" value="${vo.writer }" readonly="readonly" name="writer">
					</div>
					<div class="box-footer">
						<button class="btn btn-warning" id="mod">Modify</button>
						<button class="btn btn-danger" id="del">Remove</button>
						<button class="btn btn-primary" id="go">Go List</button>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>