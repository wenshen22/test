package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Computer;

public interface IComputerDao {
	List<Computer> findAll();//����ȫ����Ʒ
	Computer findById(long cid);//��id������Ʒ
	Computer findById2(long cid);

}
