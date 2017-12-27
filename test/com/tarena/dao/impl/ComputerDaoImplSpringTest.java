package com.tarena.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tarena.dao.IComputerDao;
import com.tarena.entity.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class ComputerDaoImplSpringTest {
	
	private static Log log = LogFactory.getLog(ComputerDaoImplSpringTest.class);
	@Autowired
	private IComputerDao icomputerDao;
	
	@Test
	@Ignore
	public void testFindAll(){
		List<Computer> comoputers = icomputerDao.findAll();
		for (Computer computer : comoputers) {
			log.info(computer.getModel());
		}
	}
	
	@Test
	public void testFindbyId(){
		Computer computer = icomputerDao.findById(1);
		log.info(computer.getModel());
	}
	
	@Test
	@Ignore
	public void testFindbyId2(){
		Computer computer = icomputerDao.findById2(1);
		log.info(computer.getModel());
	}
	
}
