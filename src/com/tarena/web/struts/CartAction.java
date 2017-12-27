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
	private List<CartItem> buyList;//�ѹ������Ʒ�б�
	private List<CartItem> deleteList;//ɾ������Ʒ�б�
	private long cid;//�û�Ҫ�������Ʒid(web��������ֵ)
	private boolean ok = true;//�Ƿ���ɹ�
	private int qty;//Ҫ���ĵ���Ʒ����(web��������ֵ)
	private double saveCost;//��ʡ���ܽ��
	private double totalCost;//�ܽ��
	private String saveCostStr="";
	private String totalCostStr="";
	private String recoverStr;//ִ�лָ��������ص���Ϣ
	
	/**
	 * ��ȡ���ﳵ�嵥
	 * @return
	 */
	public String cartList(){
		try {
			cart = (ICartService) sessionMap.get(Constant.CART_KEY);
			if (cart==null) {
				cart = new CartServiceImpl();
				sessionMap.put(Constant.CART_KEY, cart);
			}
			buyList = cart.getBuyList();//�����б�
			deleteList = cart.getDeleteList();//ɾ���б�
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
	 * ������Ʒ
	 * @return
	 */
	public String doBuy(){
		try {
			//��ȡsession�еĹ��ﳵ
			cart = (ICartService) sessionMap.get(Constant.CART_KEY);
			if (cart==null) {//���û�У����½�һ������sessionMap
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
	 * ɾ�����ﳵ��Ʒ
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
	 * �ָ�ɾ���б��е���Ʒ
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
	 * �޸���Ʒ����
	 * @return
	 */
	public String updateQty(){
		try {
			//ICartService cart  = (ICartService)sessionMap.get(Constant.CART_KEY);
			//�ô˷����ĵ���cart����,saveCostStr��totalCostStr�������
			//�������ԭ�ַ�����׷�����ַ��������һ���޸�ʱ�Ľ��Ϊ2000.0
			//�ڶ��ξͱ�Ϊ2000.04000.0
			cart  = (ICartService)sessionMap.get(Constant.CART_KEY);
			cart.updateQty(cid,qty);
			CookieUtil.addCookie(Constant.CART_KEY, cart.store(), response);
			
			String[] cost = cart.cost().split("-");
			saveCost=Double.parseDouble(cost[0]);
			totalCost = Double.parseDouble(cost[1]);
			saveCostStr="";//���ַ������
			totalCostStr="";
			saveCostStr+=saveCost;//����json���ַ�������ҳ��
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
