package com.tarena.util.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class ExportUtil {
	public static void main(String[] args) {
		Configuration c = new Configuration()
				.configure("hibernate/cfg/hibernate.cfg.xml");
		SchemaExport export = new SchemaExport(c);
		export.create(true,false);
	}

}
