package com.tarena.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.tarena.dao.IComputerDao;
import com.tarena.entity.Computer;

public class ComputerDaoImplTest {
	
	private static Log log = LogFactory.getLog(ComputerDaoImplTest.class);
	private IComputerDao icomputerDao;
	@Before
	public void setUp(){
		/*
		 * 对象实例化
		 * 对象从spring容器中获取
		 */
		//icomputerDao = new IComputerDao()
		ApplicationContext ac = 
			new FileSystemXmlApplicationContext("classpath:spring/applicationContext.xml");
		icomputerDao = (IComputerDao) ac.getBean("computerDaoImpl");
	}
	
	@Test
	public void testFindAll(){
		List<Computer> computers = this.icomputerDao.findAll();
		for (Computer computer : computers) {
			log.info(computer.getModel());
		}
	}

}
