<%@page contentType="text/html;charset=utf-8"%>
<%@include file="/common/taglib.jsp"%>
<link href="${pageContext.request.contextPath }/css/book_head090107.css" rel="stylesheet" type="text/css" />
<div class="head_container">
	<div class="head_welcome">
		<div class="head_welcome_right">
			<span class="head_welcome_text"> </span>
			<span class="head_welcome_text"> <span class="little_n">
					| <a href="#" name="mydangdang" class="head_black12a">我的当当</a> | <a
					href="#" name="helpcenter" class="head_black12a" target="_blank">帮助</a>
					| </span> </span>
			<div class="cart gray4012">
				<s:if test="#session.loginUser != null">
					<a href="${pageContext.request.contextPath }/cart/cartAction!cartList">购物车</a>
				</s:if>
				<s:else>
					<a href="${pageContext.request.contextPath }/user/userAction!toLogin">购物车</a>
				</s:else>
			</div>
		</div>
		
		<span class="head_toutext" id="logininfo">
		<s:if test="#session.loginUser != null">
			<b>您好，<s:property value="#session.loginUser.nickname"/>!&nbsp欢迎光临当当网</b>
			[&nbsp;<a href="${pageContext.request.contextPath }/user/userAction!doLogout" class="b">登出</a>&nbsp;]
		</s:if>
		<s:else>
			[&nbsp;<a href="${pageContext.request.contextPath }/user/userAction!toLogin" class="b">登录</a>|<a
			href="${pageContext.request.contextPath }/user/userAction!toReg" class="b">注册</a>&nbsp;]
		</s:else>
		</span>
		
	</div>
	<div class="head_head_list">
		<div class="head_head_list_left">
			<span class="head_logo"><a href="#" name="backtobook"><img
						src="${pageContext.request.contextPath }/images/booksaleimg/book_logo.gif" /> </a> </span>
		</div>
		<div class="head_head_list_right">

			<div class="head_head_list_right_b">
			</div>
		</div>
	</div>
	<div class="head_search_div">

	</div>
	<div class="head_search_bg"></div>
</div>
