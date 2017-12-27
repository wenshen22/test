package com.tarena.biz;

import java.util.List;

import com.tarena.entity.CartItem;

public interface ICartService {
	
	List<CartItem> getBuyList();//��ȡ�����б�
	List<CartItem> getDeleteList();//��ȡɾ���б�
	boolean add(long cid);//�����Ʒ�������б�
	void delete(long cid);
	void load(String content);//���ع��ﳵ��ʷ��¼
	String store();//���ַ�����ʽ���湺�����Ʒ��Ϣ��session
	String recover(long cid);//�ָ���Ʒ
	String cost();//��Ʒ���ѽ��
	void updateQty(long cid, int qty);//�޸���Ʒ����
	

}
