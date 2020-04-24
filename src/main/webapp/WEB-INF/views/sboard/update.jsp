<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<script>
	$(function(){
		
	})

</script>                     

<div class="content">	   
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ</h3>
				</div>
				<form action="update" method="post">
				<div class="box-body">
					<div class="form-group">
						<label>bno</label>
						<input type="text" class="form-control" value="${vo.bno }" name="bno">
					</div>
					<div class="form-group">
						<label>Title</label>
						<input type="text" class="form-control" value="${vo.title }" name="title">
					</div>
					<div class="form-group">
						<label>Content</label>
						<textarea rows="5" cols="30" class="form-control" name="content">${vo.content }</textarea>
					</div>
					<div class="form-group">
						<label>Wrtier</label>
						<input type="text" class="form-control" value="${vo.writer }" name="writer">
					</div>
					<input type="hidden" value="${cri.page }" name="page">
					<input type="hidden" value="${cri.searchType }" name="searchType">
					<input type="hidden" value="${cri.keyword }" name="keyword">
					<div class="box-footer">
						<button class="btn btn-warning" id="mod" type="submit">Modify</button>
						
					</div>  
				</div>  
				</form>  
			</div>
		</div>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>