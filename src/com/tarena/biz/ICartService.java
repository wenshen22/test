package com.tarena.biz;

import java.util.List;

import com.tarena.entity.CartItem;

public interface ICartService {
	
	List<CartItem> getBuyList();//获取购买列表
	List<CartItem> getDeleteList();//获取删除列表
	boolean add(long cid);//添加商品到购买列表
	void delete(long cid);
	void load(String content);//加载购物车历史记录
	String store();//以字符串形式保存购买的商品信息到session
	String recover(long cid);//恢复商品
	String cost();//商品消费金额
	void updateQty(long cid, int qty);//修改商品数量
	

}
