<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<link href="${pageContext.request.contextPath }/css/book.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/css/second.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/css/shopping_vehicle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.5.1.min.js"></script>
		
		<script type="text/javascript">
			$(function(){
				$(".update").click(function(){
					var t = this;
					var qty=$(this).prev().val();
					var reg = "/(^*s)|(\s*$)/g";
					qty = qty.repalce(reg,'');//去掉字符串两边空格
					var reg2 = /^[0-9]+$/;
					if (reg2.test(qty)) {//判断qty是否为数字
						alert("必须为0-9的数字");
						return;
					}
					$(t).parent().prev().html(qty);
					var id = $(t).next().val();
					$.post(
						"${pageContext.request.contextPath}/cart/cartAction!updateQty",
						{"cid":id,"qty":qty},
						function(data){
							$("#total_economy").html(data.saveCostStr);
							$("#total_account").html(data.totalCostStr);
						}
					);
				});
			});	
		</script>
		
	</head>
	<body>
		<br />
		<br />
		<div class="my_shopping">
			<img class="pic_shop" src="${pageContext.request.contextPath }/images/pic_myshopping.gif" />

		</div>
		<div id="div_choice" class="choice_merch">
			<h2 id="cart_tips">
				您已选购以下商品
			</h2>
			<div class="choice_bord">
				<table class="tabl_buy" id="tbCartItemsNormal">
					<tr class="tabl_buy_title">
						<td class="buy_td_6">
							<span>&nbsp;</span>
						</td>
						<td>
							<span class="span_w1">商品名</span>
						</td>
						<td class="buy_td_5">
							<span class="span_w2">市场价</span>
						</td>
						<td class="buy_td_1">
							<span class="span_w2">数量</span>
						</td>
						<td class="buy_td_2">
							<span>变更数量</span>
						</td>
						<td class="buy_td_1">
							<span>删除</span>
						</td>
					</tr>
					<tr class='objhide' over="no">
						<td colspan="8">
							&nbsp;
						</td>
					</tr>
					
                      <!-- 购物列表开始 -->
                      <s:iterator value="buyList">
						<tr class='td_no_bord'>
							<td style='display: none'>
								9317290
							</td>
							<td class="buy_td_6">
								<span class="objhide"><img /> </span>
							</td>
							<td>
								<a href="#"><s:property value="computer.model"/></a>
							</td>
							<td class="buy_td_5">
								<span class="c_gray">
									<s:property value="computer.price"/>
								</span>
							</td>
							<td class="buy_td_1" style="text-align:center">
								<s:property value="qty"/>
							</td>
							<td>
								<input class="del_num" type="text" size="3" maxlength="4"/>
								<a href="javascript:;" class="update">
									变更
								</a>
								<input type="hidden" value="${computer.id }" id="cid"/>
							</td>
							<td>
								<a href="${pageContext.request.contextPath }/cart/cartAction!doDelete?cid=${computer.id}">
									删除
								</a>
							</td>
						</tr>
					</s:iterator>
					<!-- 购物列表结束 -->
					
				</table>
				
				<s:if test="buyList.size()==0">
                <!--<div id="div_no_choice" class="objhide">-->
				<div id="div_no_choice">
					<div class="choice_title"></div>
					<div class="no_select">
						您的购物车现在是空的
					</div>
				</div>
				</s:if>
				
				<div class="choice_balance">
					<div class="select_merch">
						<a href='${pageContext.request.contextPath }/mainAction'> 继续挑选商品>></a>
					</div>
					<div class="total_balance">
						<div class="save_total">
							您共节省：
							<span class="c_red"> ￥<span id="total_economy">
									<s:property value="saveCost"/>
								</span>
							</span>
							<span id='total_vip_economy' class='objhide'> ( 其中享有优惠： <span
								class="c_red"> ￥<span id='span_vip_economy'>0.00</span> </span>
								) </span>
							<span style="font-size: 14px">|</span>
							<span class="t_add">商品金额总计：</span>
							<span class="c_red_b"> ￥<span id='total_account'>
									<s:property value="totalCost"/>
								</span>
							</span>
						</div>
						<div id="balance" class="balance">
							<s:if test="buyList.size()!=0">
							<a name='checkout' href='#' > 
								<img src="../images/butt_balance.gif" alt="结算" border="0" title="结算" />
							</a>
							</s:if>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 用户删除恢复区 -->


		<div id="divCartItemsRemoved" class="del_merch">
			<s:if test="deleteList.size()!=0">
			<div class="del_title">
				您已删除以下商品，如果想重新购买，请点击“恢复”
			</div>
			</s:if>
			<h4 style="text-align:center;color:red;"><s:property value="recoverStr"/></h4>
			<table class=tabl_del id=del_table>
				<tbody>


				<s:iterator value="deleteList">
					<tr>
						<td width="58" class=buy_td_6>
							&nbsp;
						</td>
						<td width="365" class=t2>
							<a href="#">
								<s:property value="computer.model"/>
							</a>
						</td>
						<td width="106" class=buy_td_5>
							<s:property value="computer.price"/>
						</td>
						<td width="56" class=buy_td_1>
							<a href="${pageContext.request.contextPath }/cart/cartAction!doRecover?cid=${computer.id}">
								恢复
							</a>
						</td>
						<td width="16" class=objhide>
							&nbsp;
						</td>
					</tr>
				</s:iterator>



					<tr class=td_add_bord>
						<td colspan=8>
							&nbsp;
						</td>
					</tr>
				


				</tbody>
			</table>
		</div>

		<br />
		<br />
		<br />
		<br />
		<!--页尾开始 -->
		<%@include file="/common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>