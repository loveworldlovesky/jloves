package cn.stuty.jk.factory.dao;

import cn.stuty.jk.factory.entity.Factory;

public interface FactoryDao {

	public void save(Factory f);
	public Factory getById(String id);


}
