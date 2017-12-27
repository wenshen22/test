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
	
	//�����ȡsession��������Ȼ����dao����ʱ����ֿ�ָ���쳣
	ApplicationContext ac = 
		new FileSystemXmlApplicationContext("classpath:spring/applicationContext.xml");

	/**
	 * ��ȡ��Ʒ�����б�
	 */
	public List<CartItem> getBuyList() {
		return buyList;
	}

	/**
	 * ��ȡɾ���б�
	 */
	public List<CartItem> getDeleteList() {
		return deleteList;
	}

	/**
	 * ����û��������Ʒ
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
	 * ɾ�����ﳵ��Ʒ
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
	 * ���ع��ﳵ��ʷ��¼
	 */
	public void load(String content) {
		//���content����Ϊ�ջ���Ϊ0�����ػָ�
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
			buyList.add(item);//�����Ʒ����Ʒ�б���
		} 
		
	}

	/**
	 * �����ﳵ�е���Ʒת����һ���ַ���
	 * 1,11;3,8
	 * ��ʾidΪ1����Ʒ������11��...
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
	 * ��ɾ���б�ָ���Ʒ
	 */
	public String recover(long cid) {
		for (int i = 0; i < deleteList.size(); i++) {
			CartItem item = deleteList.get(i);
			if (item.getComputer().getId()==cid) {
				deleteList.remove(item);
				for (int j = 0; j < buyList.size(); j++) {
					CartItem item2 = buyList.get(j);
					if (item2.getComputer().getId()==cid) {
						return "���ﳵ�Ѵ��ڴ���Ʒ,����ָ�";
					}
				}
				buyList.add(item);
				break;
			}
		}
		return "";
	}

	/**
	 * ��Ʒ�����ѽ��
	 */
	public String cost() {
		double totalCost=0;//�����ܽ��
		double saveCost=0; //��ʡ���ܽ��
		for (int i = 0; i < buyList.size(); i++) {
			CartItem item = buyList.get(i);
			double cost;//������Ʒ�ܽ��
			cost = item.getComputer().getPrice()*item.getQty();
			totalCost = totalCost+cost; 
			double subPrice;//��Ʒʵ�ۺ��Żݼ۵Ĳ��
			double save;//������Ʒ��ʡ���
			subPrice = item.getComputer().getPrice()-item.getComputer().getPrice();
			save = item.getQty()*subPrice;
			saveCost = saveCost+save;
		}
		return saveCost+"-"+totalCost;
	}

	/**
	 * �޸���Ʒ����
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
