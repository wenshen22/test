package com.tarena.web.struts;

import java.util.List;

import com.tarena.biz.IComputerService;
import com.tarena.entity.Computer;

public class MainAction {
	
	private List<Computer> computers;
	private IComputerService icomputerService;
	
	/**
	 * œ‘ æ…Ã∆∑
	 * @return
	 */
	public String showProduct(){
		computers = icomputerService.finAll();
		return "success";
	}

	public List<Computer> getComputers() {
		return computers;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}

	public void setIcomputerService(IComputerService icomputerService) {
		this.icomputerService = icomputerService;
	}
	
	

}
