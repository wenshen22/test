package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Computer;

public interface IComputerDao {
	List<Computer> findAll();//查找全部商品
	Computer findById(long cid);//按id查找商品
	Computer findById2(long cid);

}
