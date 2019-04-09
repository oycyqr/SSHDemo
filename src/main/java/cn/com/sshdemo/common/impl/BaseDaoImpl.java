package cn.com.sshdemo.common.impl;

import cn.com.sshdemo.common.BaseDao;
import cn.com.sshdemo.exception.SshDemoDaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Author: oyc
 * @Date: 2019-04-07 19:56
 * @Description: BaseDao中主要实现和数据库相关的操作逻辑, 不涉及和视图层或控制层的一些操作;
 * 其它dao可以继承此dao然后扩展;
 * 1.get时主要返回单个对象;
 * 2.find使用的hql语句进行操作,主要返回list;
 */
@SuppressWarnings("unchecked")
@Repository
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    private Class<T> clazz;

    public BaseDaoImpl(){
        //使用反射得到T的真实类型
        //获取new的对象的泛型的父类类型
        ParameterizedType pt= (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取第一个类型的真实类型
        this.clazz=(Class<T>) pt.getActualTypeArguments()[0];
        System.out.println("--->clazz"+clazz);
    }

    @Resource
    void setSessionFactory0(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 返回当前的Session,如果为null,返回SessionFactory的CurrentSession,暴露基类session供用户使用
     *
     * @return
     */
    @Override
    public Session getCurrentSession() {
        return this.getSessionFactory().getCurrentSession();
    }

    /**
     * @return HibernateTemplate
     * @Description: 暴露HibernateTemplate模板，当基类（增删改查组件）方法不够用可以用模板进行操作
     */
    @Override
    public HibernateTemplate getTemplate() {
        return this.getHibernateTemplate();
    }

    /**
     * 增加一个entity对象，返回是否添加成功
     *
     * @param entity
     * @return
     * @throws SshDemoDaoException
     */
    @Override
    public boolean add(T entity) throws SshDemoDaoException {
        Serializable io = this.getTemplate().save(entity);
        if (io == null) {
            return false;
        }
        return true;
    }

    /**
     * @param
     * @param entity 将要修改的对象
     * @return boolean true/false 是否修改成功
     * @throws SshDemoDaoException
     * @Description: 修改entity对象，返回是否修改成功
     */
    @Override
    public boolean edit(T entity) throws SshDemoDaoException {
        boolean flag;
        try {
            this.getTemplate().update(entity);
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }


    @Override
    public boolean saveOrUpdateEntity(T entity) throws SshDemoDaoException {
        boolean flog;
        try {
            this.getTemplate().saveOrUpdate(entity);
            flog = true;
        } catch (Exception e) {
            flog = false;
            throw e;
        }
        return flog;
    }

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     * @example
     */
    @Override
    public T getById(Serializable id)  throws SshDemoDaoException {
        if(id==null){
            return null;
        }
        else{

            return (T)getCurrentSession().get(clazz, id);
        }
    }

    /**
     * 删除
     *
     * @param entity
     * @example delete(user)
     */
    @Override
    public boolean delete(T entity) throws SshDemoDaoException {
        Boolean flag = false;
        try {
            this.getCurrentSession().delete(entity);
            flag = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return flag;
    }


    @Override
    public List<T> loadAll() throws SshDemoDaoException {
//        return (List<T>) this.getTemplate().loadAll(clazz.getClass());
        return getCurrentSession().createQuery(
                "FROM "+clazz.getSimpleName())
                .list();
    }

    @Override
    public boolean deleteById(Serializable id) throws SshDemoDaoException {
        Object object = getById(id);
        if (object != null) {
            getCurrentSession().delete(object);
        }
        return false;
    }
}
