package cn.stuty.jk.factory.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.stuty.jk.factory.entity.Factory;

public class FactoryDaoHibernate extends HibernateDaoSupport implements
		FactoryDao {
	// 执行持久化操作的HibernateTemplate实例
	private SessionFactory hibernatesessionFactory;

	// 该DAO组件持久化操作所需的SessionFactory对象
	public SessionFactory getHibernatesessionFactory() {
		return hibernatesessionFactory;
	}

	public void setHibernatesessionFactory(SessionFactory hibernatesessionFactory) {
		this.hibernatesessionFactory = hibernatesessionFactory;
		super.setSessionFactory(getHibernatesessionFactory());
	}

	public Factory getById(String id) {
//		Session currentSession = getSessionFactory().getCurrentSession();
//		SQLQuery sq = currentSession.createSQLQuery("select * from factory_c");
//		List<Factory> list = sq.list();
//		System.out.println(list.size());
//		return list.get(0);
		return (Factory) getHibernateTemplate().get(Factory.class,id);
	}

	public void save(Factory f) {
		getHibernateTemplate().save(f);
	}

	public void update(Factory f) {
		getHibernateTemplate().update(f);
	}
	public void delete(String id) {
		getHibernateTemplate().delete(
				getHibernateTemplate().get(Factory.class, id));
	}
	public void delete(Factory f) {
		getHibernateTemplate().delete(f);
	}

}
