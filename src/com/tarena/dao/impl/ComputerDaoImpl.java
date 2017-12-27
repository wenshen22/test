package com.tarena.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.tarena.dao.IComputerDao;
import com.tarena.entity.Computer;

public class ComputerDaoImpl extends HibernateDaoSupport implements IComputerDao {
	
	@SuppressWarnings("unchecked")
	public List<Computer> findAll() {
		String hql="from Computer computer";
		return this.getHibernateTemplate().find(hql);
	}

	
	public Computer findById(long cid) {
			String hql = "from Computer c where c.id=?";
			return (Computer) this.getHibernateTemplate().find(hql,cid).get(0);
	}
	
	public Computer findById2(long cid){
		return (Computer) this.getHibernateTemplate().load(Computer.class, cid);
	}

}
