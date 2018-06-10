<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息查询管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.caretR{
			  display: inline-block;
			  width: 0;
			  height: 0;
			  margin-left: 2px;
			  vertical-align: middle;
			  border-left: 8px dashed;
			  border-left: 8px solid \9;
			  border-bottom: 8px solid transparent;
			  border-top: 8px solid transparent;
		}
		.caretD{
			  display: inline-block;
			  width: 0;
			  height: 0;
			  margin-left: 2px;
			  vertical-align: middle;
			  border-top: 8px dashed;
			  border-top: 8px solid \9;
			  border-right: 8px solid transparent;
			  border-left: 8px solid transparent;
		}	
	</style>
	<script type="text/javascript">	
	
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
	
		$(document).ready(function() {
			
			
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			
			// 添加学员
			$("#btn_add").click(function () {
				$("#myModalLabel").text("学员通讯录");
				$('#myModal').modal();
				});
			// 清空
			$("#btn_remove").click(function(){
				$("#strNames").val("");
				$("#strIds").val("");
			})
			
			//添加学员确定按钮
			$("#btn_submit").click(function(){
				
				var ids = [];
				var names = [];
				
				$("#strNames").val("");
				$("#strIds").val("");
				$(".ysChecks").each(function(){
					if(this.checked){
						ids.push(this.value);
						names.push($(this).next().val());
						
					
					}
				})
				var strIds = ids.join(",");
				var strNames = names.join(", ");
				$("#strNames").val(strNames);
				$("#strIds").val(strIds);
				ids = [];
				names = [];
			})
	
			// 树形菜单点击
			$(".depLi span").click(function(){
				if($(this).attr('class') == "caretD"){ 
					$(this).attr("class","caretR");
				} else {
					$(this).attr("class","caretD");
				}
				 
				
				var UL = $(this).parent().next("ul"); 
				if(UL.css("display")=="none"){ 
					UL.css("display","block"); 
				} 
				else{ 
					UL.css("display","none"); 
				} 
			}); 
		
			// 复选框点击事件
			$('.checkAll').click(function(){
				var ysChecks = ".ysChecks"+this.value;
				if (this.checked){ 
					
		            $(ysChecks).each(function(){   
		                  $(this).attr("checked", true)
		            });  
		        } else {     
		            $(ysChecks).each(function() {     
		                  $(this).attr("checked", false);
		            });
		        }
			})
			
			$(".ysChecks").click(function(){
				if (!this.checked){
					$(this).parent().parent().prev().children(".checkAll").attr("checked", false);
				}
			})	
		
		});
		
	</script>
</head>
<body class="gray-bg">  
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="inputForm" modelAttribute="ysInform" action="${ctx}/inform/ysInform/save" method="post" class="form-inline">
		<h2>通知</h2>
		<br>
		<div class="">
			<div>
				<input type="hidden" name="proId" value="${ysInform.proId }">
			</div>
			<div>
			<span style="font-size:16px;">通知主题：</span>
				<input id="informName" name="informName" type="text" maxlength="50" class="form-control input-sm" value=""/>
			</div><br>
			<div>
			<span style="font-size:16px;">通知人员：</span>
				<input id="strNames" name="" value="" class="form-control" readonly="readonly"/>
				<input id="strIds" name=strIds type="hidden" value="">
				<button id="btn_add" class="btn btn-primary" >添加</button>
				<button id="btn_remove" class="btn btn-primary" >清空</button><br>
			</div><br>
			<div>
			<span style="font-size:16px;">通知内容：</span>
				<form:textarea path="content" htmlEscape="false" rows="6"    class="form-control required"/>
			</div><br>
		</div>	
	</form:form>
	<br/>
	<!-- 学员列表 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="myModalLabel">学员通讯录</h4>
            	</div>
				<div class="ibox-content">
					<input class="form-control required" placeholder="输入关键字进行过滤"/>			
				</div>
  
               <div class="modal-body">
                    <div class="form-group">
                        <div class="ibox-content">
						<ul id="" class="list-unstyled">
						<c:forEach items="${fns:getYsDepartmentList() }" var="ysDep" varStatus="ysDepList">
							<li class="depLi" style="list-style-type:none;font-size:16px;margin:10px auto" value="${ysDep.id }">
							<span id="caretA" class="caretR"  style="margin:auto 5px" ></span><input value="${ysDep.id }" class="checkAll" type="checkbox"> ${ysDep.depName }</li>
								<ul id="" class="user${ysDep.id }" class="list-unstyled" style="display:none">
								<c:forEach items="${fns:getYsUserListByDepId(ysDep.id) }" var="ysUser" varStatus="ysUserList">
									<li class="" style="list-style-type:none;font-size:14px;margin:10px 20px"><input class="ysChecks${ysDep.id } ysChecks" value="${ysUser.id }" type="checkbox" ><input type="hidden" value="${ysUser.fullName }"> ${ysUser.fullName }</li>
								</c:forEach>
								</ul>
						</c:forEach>
						</ul>
					</div>
                    </div>
        	   	</div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                    <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
               </div>
           </div>
        </div>
    </div>
	</div>
	</div>
	</div>
</body>
</html>