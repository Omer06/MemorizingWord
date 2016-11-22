package com.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.EntityDao;
import com.logger.Logger;

@Repository
@Transactional
public class EntityDaoImp implements EntityDao {

	private static final Logger log = new Logger(EntityDaoImp.class);

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	@Override
	public List<?> getListBy(String entityName) {
		log.info("veritabanýndan " + entityName + " isimli entity çekiliyor.");
		try {
			return sessionFactory.getCurrentSession().createQuery("from " + entityName).list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Hata oluþtu.");
			return new ArrayList<>();
		}
	}

	@Override
	public boolean delete(Object entity) {
		log.info("veritabanýndan " + entity + " objeli entity siliniyor");
		try {
			sessionFactory.getCurrentSession().delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Hata oluþtu.");
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(Object entity) {
		log.info("veritabanýndan " + entity + " objeli entity kayýt yada update ediliyor");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Hata oluþtu.");
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<?> getListBy(String entityName, String descField) {
		log.info("veritabanýndan " + entityName + " isimli entityin listesi çekiliyor By Dest "
				+ " descField" + " alaný olarak");
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from " + entityName + " ORDER BY " + descField + " desc").list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Hata oluþtu.");
			return new ArrayList<>();
		}
	}

	@Override
	public Object getEntityBy(Class<?> clazz, int id) {
		log.info("Veritabanýndan class " + clazz.getName() + " id ' i : " + id
				+ " olan entity çekiliyor");
		try {
			return sessionFactory.getCurrentSession().get(clazz, id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Hata oluþtu.");
			return clazz.cast(new Object());
		}
	}

	@Override
	public boolean save(Object entity) {
		log.info("veritabanýndan " + entity + " objeli entity kayýt ediliyor");
		try {
			sessionFactory.getCurrentSession().save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Hata oluþtu.");
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Object entity) {
		log.info("veritabanýndan " + entity + " objeli entity güncelleniyor");
		try {
			sessionFactory.getCurrentSession().update(entity);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Hata oluþtu.");
			return false;
		}
		return true;
	}

}
