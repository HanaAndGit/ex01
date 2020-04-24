<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<script>
	$(function(){
		$("#mod").click(function(){
			var bno = $("input[name='bno']").val();
			location.href= "${pageContext.request.contextPath }/sboard/update?bno="+bno+"&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}"; 
		})
		$("#del").click(function(){
			var bno = $("input[name='bno']").val();
			location.href = "${pageContext.request.contextPath }/sboard/delete?bno="+bno+"&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}"; 
		})
		$("#go").click(function(){
			location.href = "${pageContext.request.contextPath }/sboard/listPage?page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}"; 
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


<div class="content">
<div class="row">
	<div class="col-xs-12">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">ADD NEW REPLY</h3>
			</div>
			<div class="box-body">
				<label>Writer</label>
				<input type="text" class="form-control" placeholder="User ID" id="newReplyWriter">
				<label>Reply Text</label>
				<input type="text" class="form-control" placeholder="text" id="newReplyText">
			</div>
			<div class="box-footer">
				<button class="btn btn-primary" id="btnReplyAdd">REPLY</button>
			</div>
		</div>
		<ul class="timeline">
			<li class="time-label" id="repliesDiv">
				<span class="bg-green">Replies List</span>
			</li>
		</ul>
		<div class="text-center">
			<ul id="pagination" class="pagination pagination-sm no-margin">
				
			</ul>
		</div>
	</div>
</div>
</div>

<script id="template" type="text/x-handlebars-template">
{{#each.}}
<li class="replyli" data-rno="{{rno}}">
	<i class="fa fa-comments bg-blue"></i>
	<div class="timeline-item">
		<span class="time">
			<i class="fa fa-clock-o"></i>{{dateHelper regdate}}
		</span>
		<h3 class="timeline-header"><strong>{{rno}}</strong> - {{replyer}}</h3>
		<div class="timeline-body">{{replytext}}</div>
		<div class="timeline-footer">
			<a class="btn btn-primary btn-xs mod" data-rno={{rno}} data-text={{replytext}}>Modify</a>
			<a class="btn btn-danger btn-xs del" data-rno={{rno}}>Delete</a>
		</div>
	</div>
</li>
{{/each}}
</script>
<script>

var currentPage = 1;
function getPageList(page){
	var bno = ${vo.bno }; // 상세보기의 게시글 번호가 자동으로 들어와야함. 무조건 그 글의 댓글이 되어야 하기 때문임.  
	$.ajax({   
		/* replies/bno/page -> ~~~sboard/replies/bno/page  -> sboard가 빠져야함 */
		/*  -> 프로젝트 이름까지 돌려줌 */
		url : "${pageContext.request.contextPath}/replies/"+bno+"/"+page,
		type : "get", 
		dataType : "json",                            
		success: function(rs){                  
			console.log(rs);
			$(".replyli").remove(); 
			Handlebars.registerHelper("dateHelper", function(value){
			var d = new Date(value);
			var year = d.getFullYear();
			var month = d.getMonth()+1;
			var date = d.getDate();
			var day = d.getDay();
			
			var week = new Array("월", "화", "수", "목", "금", "토", "일");
			
			return year + "/" + month + "/" + date + " " + week[day] + "요일";
			
		})	
			
			/* Handlebars 탬플릿 */
			var source = $("#template").html();
			var func = Handlebars.compile(source);
			$(".timeline").append(func(rs.list));
			$("#pagination").empty();
			for(var i = rs.pageMaker.startPage; i<= rs.pageMaker.endPage; i++){
				var $li = $("<li>");
				if(i == currentPage){
					$li.addClass("active");
				}
				var $a = $("<a>").html(i);
				$li.append($a);
				/* var $li = $("<li>").html(i); */
				$("#pagination").append($li);   
			}  
		}   
	})   
}
	//페이지 번호 클릭시 해당 페이지로 이동하는 부분
	$(document).on("click", "#pagination li",function(){
		var page = $(this).text();
		currentPage = page;
		getPageList(page);
	})
	
	$(document).on("click", ".del", function(){
			//data-rno (btnDel 버튼)
			var rno = $(this).attr("data-rno");   
			$.ajax({
				url : "${pageContext.request.contextPath}/replies/"+rno,
				method : "DELETE",
				dataType : "text",
				success : function(res){   
					console.log(res);
					if(res == "SUCCESS"){
						alert(rno + "번 댓글이 삭제되었습니다.");
						getPageList(1);
					}
				}
				
			})
		})

	/* 	$(document).on("click", ".mod", function(){
			$("#modPopup").css("display", "block");
			var rno = $(this).attr("data-rno");
			var text = $(this).attr("data-text");
			
			$(".modRno").html(rno);   
			$("#modText").val(text);
			
			    
		})
		 */

		 $("#btnReplyAdd").click(function(){
				//댓글 등록
				var bno = ${vo.bno };
				var replyer = $("#newReplyWriter").val();
				var text = $("#newReplyText").val();
				//서버로 보내기 (서버 주소 : /replies/ )
				//@RequestBody 서버에서 사용시
				//1. headers - "Content-type" : "application/json"
				//2. 보내는 data는 String 으로 변형해서 보내야됨   - "{bno:bno}"
				var json = JSON.stringify({"bno":bno, "replyer":replyer, "replytext":text});
				$.ajax({
					url : "${pageContext.request.contextPath}/replies/",
					method : "post",
					headers : {"Content-Type":"application/json"},
					data : json,
					dataType : "text",
					success : function(res){
						//console.log(res);
						if(res == "SUCCESS"){
							alert("댓글이 등록되었습니다.");
							$("#replyer").val("");
							$("#replytext").val("");
							
							//리스트 갱신
							getPageList(1);
						}
					}
					
				})
				
			})
			
			
	$("#repliesDiv").click(function(){
		getPageList(1);
	})

</script>






<%@ include file="../include/footer.jsp"%>