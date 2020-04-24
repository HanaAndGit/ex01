<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<div class="content">	
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">SEARCH BOARD LIST</h3>
				</div>
				<div class="box-body">
				<!-- 검색 작업에서 유지될 데이터 
					1. 현재 페이지의 번호
					2. 페이지당 보여지는 데이터의 수
					3. 검색의 종류
					4. 검색의 키워드
				 -->
					<select name="searchType" id="searchType">
						<option value="n" ${cri.searchType==null? 'selected' : '' }>-----</option>
						<option value="t" ${cri.searchType=='t'? 'selected' : '' }>Title</option>
						<option value="c" ${cri.searchType=='c'? 'selected' : '' }>Content</option>
						<option value="w" ${cri.searchType=='w'? 'selected' : '' }>Writer</option>
						<option value="tc" ${cri.searchType=='tc'? 'selected' : '' }>Title or Content</option>
						<option value="cw" ${cri.searchType=='cw'? 'selected' : '' }>Content or Writer</option>
						<option value="tcw" ${cri.searchType=='tcw'? 'selected' : '' }>Title or Content or Writer</option>
					</select>
					<input type="text" name="keyword" id="keywordInput" value="${cri.keyword}">
					<button id="btnSearch">Search</button>
					<button id="btnRegister">New Board</button>
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
								<td><a href="read?bno=${b.bno}&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}">${b.title }</a></td>
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
								<li><a href="listPage?page=${pageMaker.startPage-1 }&searchType=${cri.searchType}&keyword=${cri.keyword}">&laquo;</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
								<li class="${pageMaker.cri.page == idx ?'active':''}"><a href="listPage?page=${idx }&searchType=${cri.searchType}&keyword=${cri.keyword}">${idx }</a></li>
								<!-- 주소의 시작이 /로 시작하지 않고 localhost:8080..으로 시작하면 맨 마지막 것만 바꿔주면됨 -->
							</c:forEach>
							<c:if test="${pageMaker.next == true }">
								<li><a href="listPage?page=${pageMaker.endPage+1 }&searchType=${cri.searchType}&keyword=${cri.keyword}">&raquo;</a></li>
							</c:if>
						</ul>
					</div>
				</div>  
			</div>         
		</div>
	</div>
</div>

<script>
	$("#btnSearch").click(function(){
		var searchType = $("#searchType").val();
		var keyword = $("#keywordInput").val();
		location.href = "listPage?searchType="+searchType+"&keyword="+keyword;
		//searchBoardController의 listPage GET 으로 받음 
		
	})
	
	$("#btnRegister").click(function(){
		location.href = "register";
	})
</script>

<%@ include file="../include/footer.jsp"%>