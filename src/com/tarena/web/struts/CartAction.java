package com.tarena.web.struts;

import java.util.List;

import com.tarena.biz.ICartService;
import com.tarena.biz.impl.CartServiceImpl;
import com.tarena.entity.CartItem;
import com.tarena.util.Constant;
import com.tarena.util.CookieUtil;

@SuppressWarnings("serial")
public class CartAction extends BaseAction{
	
	//private ICartService cartService;
	private ICartService cart;
	private List<CartItem> buyList;//已购买的商品列表
	private List<CartItem> deleteList;//删除的商品列表
	private long cid;//用户要购买的商品id(web传过来的值)
	private boolean ok = true;//是否购买成功
	private int qty;//要更改的商品数量(web传过来的值)
	private double saveCost;//节省的总金额
	private double totalCost;//总金额
	private String saveCostStr="";
	private String totalCostStr="";
	private String recoverStr;//执行恢复操作返回的信息
	
	/**
	 * 获取购物车清单
	 * @return
	 */
	public String cartList(){
		try {
			cart = (ICartService) sessionMap.get(Constant.CART_KEY);
			if (cart==null) {
				cart = new CartServiceImpl();
				sessionMap.put(Constant.CART_KEY, cart);
			}
			buyList = cart.getBuyList();//购买列表
			deleteList = cart.getDeleteList();//删除列表
			if (buyList.size()==0&&deleteList.size()==0) {
				cart.load(CookieUtil.findCookie(Constant.CART_KEY, request));
			}
			CookieUtil.addCookie(Constant.CART_KEY, cart.store(), response);
			String[] cost = cart.cost().split("-");
			saveCost = Double.parseDouble(cost[0]);
			totalCost = Double.parseDouble(cost[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "cart_list";
	}
	
	/**
	 * 购买商品
	 * @return
	 */
	public String doBuy(){
		try {
			//获取session中的购物车
			cart = (ICartService) sessionMap.get(Constant.CART_KEY);
			if (cart==null) {//如果没有，则新建一个存入sessionMap
				cart = new CartServiceImpl();
				sessionMap.put(Constant.CART_KEY, cart);	
			}
			//ok=cartService.add(cid);
			ok=cart.add(cid);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "buy_success";
	}
	
	/**
	 * 删除购物车商品
	 * @return
	 */
	public String doDelete(){
		try {
			cart = (ICartService)sessionMap.get(Constant.CART_KEY);
			cart.delete(cid);
			CookieUtil.addCookie(Constant.CART_KEY, cart.store(), response);
			
			String[] cost = cart.cost().split("-");
			saveCost = Double.parseDouble(cost[0]);
			totalCost = Double.parseDouble(cost[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "delete_success";
	}
	
	/**
	 * 恢复删除列表中的商品
	 * @return
	 */
	public String doRecover(){
		try {
			cart = (ICartService)sessionMap.get(Constant.CART_KEY);
			recoverStr = cart.recover(cid);
			CookieUtil.addCookie(Constant.CART_KEY, cart.store(), response);
			
			String[] cost = cart.cost().split("-");
			saveCost = Double.parseDouble(cost[0]);
			totalCost = Double.parseDouble(cost[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "recover_success";
	}
	
	/**
	 * 修改商品数量
	 * @return
	 */
	public String updateQty(){
		try {
			//ICartService cart  = (ICartService)sessionMap.get(Constant.CART_KEY);
			//用此方法的到的cart对象,saveCostStr和totalCostStr必须清空
			//否则会在原字符串上追加新字符串，如第一次修改时的金额为2000.0
			//第二次就变为2000.04000.0
			cart  = (ICartService)sessionMap.get(Constant.CART_KEY);
			cart.updateQty(cid,qty);
			CookieUtil.addCookie(Constant.CART_KEY, cart.store(), response);
			
			String[] cost = cart.cost().split("-");
			saveCost=Double.parseDouble(cost[0]);
			totalCost = Double.parseDouble(cost[1]);
			saveCostStr="";//把字符串清空
			totalCostStr="";
			saveCostStr+=saveCost;//利用json将字符串返回页面
			totalCostStr+=totalCost;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "update_success";
	}
	

	public List<CartItem> getBuyList() {
		return buyList;
	}

	public void setBuyList(List<CartItem> buyList) {
		this.buyList = buyList;
	}

	public List<CartItem> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<CartItem> deleteList) {
		this.deleteList = deleteList;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getSaveCost() {
		return saveCost;
	}

	public void setSaveCost(double saveCost) {
		this.saveCost = saveCost;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getSaveCostStr() {
		return saveCostStr;
	}

	public void setSaveCostStr(String saveCostStr) {
		this.saveCostStr = saveCostStr;
	}

	public String getTotalCostStr() {
		return totalCostStr;
	}

	public void setTotalCostStr(String totalCostStr) {
		this.totalCostStr = totalCostStr;
	}

	public String getRecoverStr() {
		return recoverStr;
	}

	public void setRecoverStr(String recoverStr) {
		this.recoverStr = recoverStr;
	}
	

	/*public void setCartService(ICartService cartService) {
		this.cartService = cartService;
	}*/
	
}
