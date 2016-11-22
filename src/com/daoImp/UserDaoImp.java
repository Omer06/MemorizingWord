package com.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.dao.UserDao;
import com.logger.Logger;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

	private static final Logger log = new Logger(UserDaoImp.class);

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
		log.info("veritabanýndan " + entityName + " isimli entityin listesi çekiliyor By Dest " + " descField"
				+ " alaný olarak");
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
		log.info("Veritabanýndan class " + clazz.getName() + " id ' i : " + id + " olan entity çekiliyor");
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

	@SuppressWarnings("deprecation")
	@Override
	public boolean isHasByAttribute(String columnName, String value) {
		log.info(columnName + " : " + value + " olan kullanýcý var mý ?");
		try {
			return !(sessionFactory.getCurrentSession()
					.createQuery("from user where " + columnName + " = '" + value + "'").list().isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Hata oluþtu.");
			return false;
		}
	}

	@Override
	public boolean updatePassword(String username, String password) {
		log.info(username + " isimli kullanýcýmýzýn yeni þifresi : " + password);
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("UPDATE user set password=:password where username=:username")
					.setParameter("password", password).setParameter("username", username).executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Hata oluþtu.");
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isHasByAttributes(String columnName1, String value1 , String columnName2 , String value2) {
		log.info(columnName1 + " : " + value1 +  "  " + columnName2 + " : " + value2 + " ' özelliklere sahip kullanýcýlara bakýlýyor.");
		try {
			return !sessionFactory.getCurrentSession()
					.createQuery("from user where "+columnName1+"=:value1 and "+columnName2+"=:value2")
					.setParameter("value1", value1).setParameter("value2", value2)
					.list().isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Hata oluþtu.");
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getPassword(String username) {
		try{
			return (String) sessionFactory.getCurrentSession().createQuery("select password from user where username=:username")
					.setParameter("username", username)
					.list().iterator().next();
		}catch(Exception e){
			e.printStackTrace();
			log.error("Hata oluþtu.");
			return null;
		}
	}

}
