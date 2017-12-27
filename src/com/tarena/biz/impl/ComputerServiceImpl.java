package com.tarena.biz.impl;

import java.util.List;

import com.tarena.biz.IComputerService;
import com.tarena.dao.IComputerDao;
import com.tarena.entity.Computer;

public class ComputerServiceImpl implements IComputerService {

	private IComputerDao icomputerDao;

	public List<Computer> finAll() {

		return icomputerDao.findAll();
	}
	
	public Computer findById(long cid) {
		
		return icomputerDao.findById(cid);
	}

	public void setIcomputerDao(IComputerDao icomputerDao) {
		this.icomputerDao = icomputerDao;
	}

}
