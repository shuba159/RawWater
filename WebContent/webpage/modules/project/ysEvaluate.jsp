<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>项目管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.eval_container{
			width:600px;
		}
		.eval_title{
			display:block;
			font-size: 20px;
			color:#0099ff;
			margin: 10px 40px;
		}
		
		.eval_left{
			margin-left: 40px;
		}
		 
		.eval_left,.eval_right{
			display: inline-block;
			width:270px;
			height:80px;
			font-size: 14px;
		}
		.left_list{
			display:block;
			line-height: 36px;
		}
		.eval_content{
			margin-top: 10px;
			margin-left: 40px;
			font-size: 14px;
		}
		.star_box{
			display:inline-block;
			width:200px;
			margin-right:30px;
			vertical-align: middle;
		}
		.star_box img{
			vertical-align: text-bottom;
		}
		.eval_degree{
			display: inline-block;
			width:46px;
			margin-right: 30px;
		}
	</style>
</head>
<body class="gray-bg">
	<div class="eval_container">
		<span class="eval_title">项目评价</span>
		<div class="eval_left">
			<span class="left_list">学习时间：<span><fmt:formatDate value="${ysEvaluate.startTime }" pattern="yyyy-MM-dd"/> 
				&nbsp;~&nbsp;<fmt:formatDate value="${ysEvaluate.endTime }" pattern="yyyy-MM-dd"/></span></span>	
			<span class="left_list">参与评价：<span>${ysEvaluate.evaluateCount }人</span></span>
		</div>
		<div class="eval_right">
			<span class="left_list">应学人数：<span>${ysEvaluate.totalCount }</span>人</span>	
			<span class="left_list">正在学习：<span>${ysEvaluate.studyingCount }</span>人</span>
		</div>
		<div class="eval_content">
			<div class="star_list">
				<span class="star_box">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
				</span>
				<span class="eval_degree">非常好</span>
				<span class="eval_num"><span>${ysEvaluate.evaluateList[4] }</span>人评价</span>
			</div>
			<div class="star_list">
				<span class="star_box">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
				</span>
				<span class="eval_degree">好</span>
				<span class="eval_num"><span>${ysEvaluate.evaluateList[3] }</span>人评价</span>
			</div>
						<div class="star_list">
				<span class="star_box">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
				</span>
				<span class="eval_degree">一般</span>
				<span class="eval_num"><span>${ysEvaluate.evaluateList[2] }</span>人评价</span>
			</div>
						<div class="star_list">
				<span class="star_box">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
				</span>
				<span class="eval_degree">差</span>
				<span class="eval_num"><span>${ysEvaluate.evaluateList[1] }</span>人评价</span>
			</div>
						<div class="star_list">
				<span class="star_box">
					<img src="${ctxStatic }/common/myImg/xingxing.png" height="28" width="28" alt="">
				</span>
				<span class="eval_degree">很差</span>
				<span class="eval_num"><span>${ysEvaluate.evaluateList[0] }</span>人评价</span>
			</div>
		</div>
	</div>
</body>
</html>