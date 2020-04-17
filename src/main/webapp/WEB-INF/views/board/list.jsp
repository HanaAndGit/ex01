<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<div class="content">	
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">LIST</h3>
				</div>
				<div class="box-body text-right">
					<a href="${pageContext.request.contextPath}/board/register ">register</a>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width:30px;">번호</th>
							<th>제목</th>
							<th>작성자 아이디</th>
							<th>작성 날짜</th>
							<th style="width:30px;">조회수</th>
						</tr>     
						<c:forEach items="${list }" var="b">
							<tr>
								<td>${b.bno }</td>
								<td><a href="${pageContext.request.contextPath }/board/read?bno=${b.bno}">${b.title }</a></td>
								<td>${b.writer }</td>
								<td><fmt:formatDate value="${b.regdate }" pattern="yyyy-MM-dd" /></td>  
								<td><span class="badge bg-red">${b.viewcnt }</span></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>