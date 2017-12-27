package com.tarena.biz;

import java.util.List;

import com.tarena.entity.Computer;

public interface IComputerService {
	List<Computer> finAll();
	Computer findById(long cid);

}
