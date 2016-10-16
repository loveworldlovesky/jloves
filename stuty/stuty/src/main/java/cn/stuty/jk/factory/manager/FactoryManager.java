package cn.stuty.jk.factory.manager;

import cn.stuty.jk.factory.dao.FactoryDao;
import cn.stuty.jk.factory.entity.Factory;
import cn.stuty.utils.UtilsUUID;

public class FactoryManager {
	
	FactoryDao factoryDao;

	public void setFactoryDao(FactoryDao factoryDao) {
		this.factoryDao = factoryDao;
	}

	public void add() {
		Factory f = new Factory();
		f.setFactoryId(UtilsUUID.getId());
		f.setFactoryName("北京京天威科技发展有限公司");
		factoryDao.save(f);
	}
	
	public Factory getById(String id){
		Factory factory = factoryDao.getById(id);
		return factory;
	}
	
	
}
