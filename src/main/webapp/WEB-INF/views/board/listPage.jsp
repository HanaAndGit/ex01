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
								<td><a href="${pageContext.request.contextPath }/board/readPage?bno=${b.bno}&page=${pageMaker.cri.page}">${b.title }</a></td>
								<td>${b.writer }</td>
								<td><fmt:formatDate value="${b.regdate }" pattern="yyyy-MM-dd" /></td>  
								<td><span class="badge bg-red">${b.viewcnt }</span></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="box-footer">
					<%-- <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
						${idx },
					</c:forEach> --%>
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pageMaker.prev == true }">
								<li><a href="listPage?page=${pageMaker.startPage-1 }">&laquo;</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
								<li class="${pageMaker.cri.page == idx ?'active':''}"><a href="listPage?page=${idx }">${idx }</a></li>
								<!-- 주소의 시작이 /로 시작하지 않고 localhost:8080..으로 시작하면 맨 마지막 것만 바꿔주면됨 -->
							</c:forEach>
							<c:if test="${pageMaker.next == true }">
								<li><a href="listPage?page=${pageMaker.endPage+1 }">&raquo;</a></li>
							</c:if>
						</ul>
					</div>
				</div>  
			</div>
		</div>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>