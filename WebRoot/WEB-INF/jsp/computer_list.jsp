<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
		<link href="css/main/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.5.1.min.js"></script>
		
		<script type="text/javascript">
			$(function(){
				$(".buy").click(function(){
					var t = this;
					var id = $(this).prev().val();
					$.post(
						"${pageContext.request.contextPath }/cart/cartAction!doBuy",
						{"cid":id},
						function(date){
							//alert(date.ok);
							if (date.ok) {
								$(t).html("<img src='${pageContext.request.contextPath }/myimages/right.gif'/>"+
								"成功添加到购物车！");
							} else {
								$(t).html("您已经购买过该商品");
							}
							return;
						}
					);
				});
			});
		</script>
	</head>
	
	<body topMargin="10">
		<div id="append_parent"></div>
		<table cellSpacing=6 cellPadding=2 width="100%" border="0">
			<tbody>
				<tr>
					<td>
						<table class="guide" cellSpacing="0" cellPadding="0" width="100%"
							border="0">
							<tbody>
								<tr>
									<td>
										<a href='#'>主页</a>&nbsp;/&nbsp;
										<a href='#'>笔记本订购(WEB007)</a>&nbsp;/&nbsp;商品列表
									</td>
								</tr>
							</tbody>
						</table>
						<br />

						<table class="tableborder" cellSpacing="0" cellPadding="0"
							width="100%" border="0">
							<tbody>
								<tr class="header">
									<td class="altbg1" width="15%">
										<b>型号</B>
									</td>
									<td class="altbg1" width="20%">
										<b>产品图片</b>
									</td>
									<td class="altbg1" width="30%">
										<b>产品说明</b>
									</td>

									<td class="altbg1" width="10%">
										<b>产品报价</b>
									</td>
									<td class="altbg1">
									</td>
								</tr>
							</tbody>
							<tbody>
								<s:iterator value="computers">
								<tr>
									<td class="altbg2">
										<s:property value="model"/>
									</td>
									<td class="altbg2">
										<img src="img/d007/${pic }" width="150"
											height="90" />
									</td>
									<td class="altbg2">
										<s:property value="prodDesc"/>
									</td>


									<td class="altbg2">
										<s:property value="price"/>
									</td>
									<td class="altbg2">
											<input type="hidden" value=${id } id="cid"/>
											<a href="javascript:;" class="buy">
												购买
											</a>
											<span id="cartinfo"></span>
										</td>
								</tr>
								</s:iterator>
							</tbody>
						</table>
						<br />
						<center>
							<input class="button" type="button" value="查看购物车"
								name="settingsubmit" 
								onclick="location = '${pageContext.request.contextPath }/cart/cartAction!cartList';">
						</center>
					</td>
				</tr>
			</tbody>
		</table>

	</body>
</html>