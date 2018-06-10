<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>考试管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		function getQueRanDiv(){
			var que_level = $("#que_level").val();
			var que_level_text = $("#que_level").find("option:selected").attr("label");
			var que_type = $("#que_type").val();
			var que_type_text = $("#que_type").find("option:selected").attr("label");
			var que_num = $("#que_num").val();
			var que_tatal = $("#que_total_num").val()
			var str = '<tr><td style="width: 35px"><input class="que_level" type="hidden" value="'+que_level+'"><input class="que_type" type="hidden" value="'+que_type+'"><input class="que_num" type="hidden" value="'+que_num+'"></td><td>随机试题:  '+que_level_text+'---'+que_type_text+'---共 '+que_num+' 题</td><td ><input type="text" class="form-control ran_input" onkeyup="value=value.replace(/[^\\d]/g,\'\')"></td><td><button onclick="del_que(this)" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</button></td></tr>';
			if(que_num != 0 && ((que_num-0) <= (que_tatal-0))){
				return str;
			}else{
				return false;
			}
		}
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="">
			<span>试题难度：</span>
				<select id="que_level" class="form-control required">
					<option value="1" label="简单">简单</option>
					<option value="2" label="一般">一般</option>
					<option value="3" label="难">难</option>
				</select>
			<span>试题类型：</span>
				<select id="que_type" class="form-control required">
					<option value="1" label="单选">单选</option>
					<option value="2" label="多选">多选</option>
					<option value="3" label="填空">填空</option>
					<option value="4" label="判断">判断</option>
					<option value="5" label="简答">简答</option>
				</select>
			<span>试题数量：</span>
				<input id="que_num" type="text" maxlength="50" class="form-control input-sm" value="0" onkeyup="value=value.replace(/[^\d]/g,'')"/>
				<input id="que_total_num" type="hidden" value="${totalNum }">
				试题总量/${totalNum }
		</div>	
	</div>
</body>
</html>