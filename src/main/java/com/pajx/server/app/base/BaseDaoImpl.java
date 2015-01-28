package com.pajx.server.app.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.core.ResolvableType;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by taller on 15/1/12.
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {
    private SessionFactory sessionFactory;
    private  Class<T> clazz;
    @Override
    public void save(T entity) {
       getCurrentSession().save(entity);
    }

    public BaseDaoImpl() {
        //获取当前new的对象的泛型的父类类型
        //ParameterizedType pt=(ParameterizedType)this.getClass().getGenericSuperclass();
        ResolvableType rt= ResolvableType.forClass(this.getClass());
        //this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
        this.clazz= (Class<T>) rt.getInterfaces()[0].getGeneric().resolve();
    }

    /**
     * 该类初始化时通过反射机制获取T的真实类型
    */

    @Override
    public void delete(String uuid) {
         T entity= (T) getCurrentSession().get(clazz,uuid);
        if (entity!=null){
            getCurrentSession().delete(entity);
        }
    }

    @Override
    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public T getById(String uuid) {
        return (T)getCurrentSession().get(clazz,uuid);
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createQuery("FROM" + clazz.getSimpleName()).list();
    }

    @Override
    public List<T> findList(String hql) {
        return getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<T> getByJdbcSql(String sql) {
        return getCurrentSession().createSQLQuery(sql).list();
    }
    public Session getCurrentSession(){
        Session session=sessionFactory.getCurrentSession();
        return session;
    }
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
