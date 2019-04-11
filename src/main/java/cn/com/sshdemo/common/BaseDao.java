package cn.com.sshdemo.common;

import cn.com.sshdemo.exception.SshDemoDaoException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: oyc
 * @Date: 2019-04-07 19:56
 * @Description: BaseDao中主要实现和数据库相关的操作逻辑, 不涉及和视图层或控制层的一些操作;
 * 其它dao可以继承此dao然后扩展;
 */
public interface BaseDao<T> {


    /**
     * 获取CurrentSession
     *
     * @return CurrentSession
     */
    Session getCurrentSession();

    /**
     * @return HibernateTemplate
     * @Description: 暴露HibernateTemplate模板，当基类（增删改查组件）方法不够用可以用模板进行操作
     */
    HibernateTemplate getTemplate();

    /**
     * 增加一个entity对象，返回是否添加成功
     *
     * @param entity
     * @return
     * @throws SshDemoDaoException
     */
    boolean add(T entity) throws SshDemoDaoException;

    /**
     * @param
     * @param entity 将要修改的对象
     * @return boolean true/false 是否修改成功
     * @throws SshDemoDaoException
     * @Description: 修改entity对象，返回是否修改成功
     */

    boolean edit(T entity) throws SshDemoDaoException;


    /**
     * 保持或者更新实体
     *
     * @param entity
     * @return
     * @throws SshDemoDaoException
     */
    boolean saveOrUpdateEntity(T entity) throws SshDemoDaoException;

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     * @example
     */
     T getById(Serializable id) throws SshDemoDaoException;

    /**
     * 删除
     *
     * @param entity
     * @throws SshDemoDaoException
     */
    boolean delete(T entity) throws SshDemoDaoException;

    /**
     * 根据Id删除实体
     *
     * @param id
     * @throws SshDemoDaoException
     */
    boolean deleteById(Serializable id) throws SshDemoDaoException;

    /**
     * 返回SessionFactory的CurrentSession;
     *
     * @return
     * @throws SshDemoDaoException
     */
    List<T> loadAll() throws SshDemoDaoException;


}
