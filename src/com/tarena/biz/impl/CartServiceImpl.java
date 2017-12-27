package com.tarena.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.tarena.biz.ICartService;
import com.tarena.dao.IComputerDao;
import com.tarena.entity.CartItem;
import com.tarena.entity.Computer;

public class CartServiceImpl implements ICartService {
	
	private List<CartItem> buyList = new ArrayList<CartItem>();
	private List<CartItem> deleteList = new ArrayList<CartItem>();
	private IComputerDao icomputerDao ;
	private Computer computer;
	private CartItem item;
	
	//必需获取session工厂，不然调用dao方法时会出现空指针异常
	ApplicationContext ac = 
		new FileSystemXmlApplicationContext("classpath:spring/applicationContext.xml");

	/**
	 * 获取商品购买列表
	 */
	public List<CartItem> getBuyList() {
		return buyList;
	}

	/**
	 * 获取删除列表
	 */
	public List<CartItem> getDeleteList() {
		return deleteList;
	}

	/**
	 * 添加用户购买的商品
	 */
	public boolean add(long cid) {
		for (int i = 0; i < buyList.size(); i++) {
			CartItem currItem = buyList.get(i);
			if (currItem.getComputer().getId()==cid) {
				return false;
			}
		}
		icomputerDao = (IComputerDao) ac.getBean("computerDaoImpl");
		computer = icomputerDao.findById(cid);
		item = new CartItem();
		item.setComputer(computer);
		buyList.add(item);
		return true;
	}

	/**
	 * 删除购物车商品
	 */
	public void delete(long cid) {
		for (int i = 0; i < buyList.size(); i++) {
			item = buyList.get(i);
			if (item.getComputer().getId()==cid) {
				buyList.remove(item);
				deleteList.add(item);
				return;
			}
		}
		
	}
	
	/**
	 * 加载购物车历史记录
	 */
	public void load(String content) {
		//如果content内容为空或者为0，不必恢复
		if (content==null || content.equals("0")) {
			return;
		}
		String[] contents = content.split(";");
		for (int i = 0; i < contents.length; i++) {
			String contentStr = contents[i];
			String[] itemStr = contentStr.split(",");
			long cid = Long.parseLong(itemStr[0]);
			int qty = Integer.parseInt(itemStr[1]);
			item = new CartItem();
			item.setQty(qty);
			icomputerDao = (IComputerDao) ac.getBean("computerDaoImpl");
			computer = icomputerDao.findById(cid);
			item.setComputer(computer);
			buyList.add(item);//添加商品到商品列表中
		} 
		
	}

	/**
	 * 将购物车中的商品转换成一个字符串
	 * 1,11;3,8
	 * 表示id为1的商品购买了11件...
	 * @return
	 */
	public String store() {
		if (buyList.size()==0) {
			return "0";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buyList.size(); i++) {
			item = buyList.get(i);
			sb.append(item.getComputer().getId()+","+item.getQty()+";");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	/**
	 * 从删除列表恢复商品
	 */
	public String recover(long cid) {
		for (int i = 0; i < deleteList.size(); i++) {
			CartItem item = deleteList.get(i);
			if (item.getComputer().getId()==cid) {
				deleteList.remove(item);
				for (int j = 0; j < buyList.size(); j++) {
					CartItem item2 = buyList.get(j);
					if (item2.getComputer().getId()==cid) {
						return "购物车已存在此商品,无需恢复";
					}
				}
				buyList.add(item);
				break;
			}
		}
		return "";
	}

	/**
	 * 商品的消费金额
	 */
	public String cost() {
		double totalCost=0;//消费总金额
		double saveCost=0; //节省的总金额
		for (int i = 0; i < buyList.size(); i++) {
			CartItem item = buyList.get(i);
			double cost;//单个商品总金额
			cost = item.getComputer().getPrice()*item.getQty();
			totalCost = totalCost+cost; 
			double subPrice;//商品实价和优惠价的差额
			double save;//单个商品节省金额
			subPrice = item.getComputer().getPrice()-item.getComputer().getPrice();
			save = item.getQty()*subPrice;
			saveCost = saveCost+save;
		}
		return saveCost+"-"+totalCost;
	}

	/**
	 * 修改商品数量
	 */
	public void updateQty(long cid, int qty) {
		for (int i = 0; i < buyList.size(); i++) {
			CartItem item = buyList.get(i);
			if (item.getComputer().getId()==cid) {
				item.setQty(qty);
				return;
			}
		}
		
	}

}
