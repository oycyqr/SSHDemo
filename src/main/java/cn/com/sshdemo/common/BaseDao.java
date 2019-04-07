package cn.com.sshdemo.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author: oyc
 * @Date: 2019-04-07 19:56
 * @Description: BaseDao中主要实现和数据库相关的操作逻辑, 不涉及和视图层或控制层的一些操作;
 * 其它dao可以继承此dao然后扩展;
 * 1.get时主要返回单个对象;
 * 2.find使用的hql语句进行操作,主要返回list;
 */
public interface BaseDao {

    SessionFactory getSessionFactory();

    /**
     * 返回当前的Session,如果为null,返回SessionFactory的CurrentSession;
     *
     * @return
     */
    Session getCurrentSession();

    /**
     * 此时获取当前session,不是从SessionFactory中取Session,可能为null;
     * 如果需要从SessionFactory中取,用getCurrentSession()方法;
     *
     * @return
     */
    Session getBeanSession();

    void setSession(Session session);

    /**
     * 返回存储此对象的主键
     */
    <T> Serializable save(T o);

    <T> void saveByCollection(Collection<T> collection);

    <T> void update(T o);


    <T> void saveOrUpdate(T o);

    /**
     * 更新一个实体中指定的字段
     * 这里columnNames和columnsValues的名字和值得顺序必须一致;
     *
     * @param t
     * @param columnNames
     * @param columnValues
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    <T> void updateByColumns(T t, List<String> columnNames, List<?> columnValues) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

    /**
     * 更新一个实体中除开指定的字段之外的字段
     * 这里columnNames和columnsValues的名字和值得顺序必须一致;
     *
     * @param t
     * @param exceptColumnNames
     * @param columnValues
     * @param <T>
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    <T> void updateByExceptColumns(T t, List<String> exceptColumnNames, List<?> columnValues) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

    <T> T get(Class<T> c, Serializable id);

    <T> T get(String hql);

    <T> T get(String hql, Map<String, Object> params);

    <T> void delete(T o);

    /**
     * 从数据库中找出此id对象并删除
     *
     * @param entityClass
     * @param id
     */
    <T, PK extends Serializable> void delete(Class<T> entityClass, PK id);

    /**
     * hql语句,"delete from "+tableName+" where "+columnName+" in ("+columnValues+")"
     * 用in语句删除指定表中,包含有指定值得指定列的记录;
     *
     * @param tableName
     * @param columnName
     * @param columnValues 如1,3,4这种in语句参数需要的内容
     * @throws Exception
     */
    void deleteByColumns(String tableName, String columnName, String columnValues) throws Exception;

    /**
     * hql语句,"delete from "+tableName+" where "+columnName+" in ("+columnValues+")"
     * 用in语句删除指定表中,包含有指定值得指定列的记录;
     *
     * @param tableName
     * @param columnName
     * @param columnValues 一个参数值的集合
     */
    void deleteByColumns(String tableName, String columnName, Collection<?> columnValues);

    /**
     * 如果有id并存在于数据库中,则更新,否则保存
     *
     * @param model
     */
    <T> void merge(T model);

    <T> List<T> findList(String hql);

    <T> List<T> findList(String hql, Map<String, Object> params);

    /**
     * @param hql
     * @param topCount 返回前topCount条记录
     * @return
     */
    <T> List<T> findTopList(String hql, int topCount);

    /**
     * 用hql语句,得到当前表的所有记录
     *
     * @param tableName
     * @return
     */
    <T> List<T> findAll(String tableName);

    /**
     * @param hql
     * @param params
     * @param page   当前页码
     * @param rows   每页显示的记录数量
     * @return
     */
    <T> List<T> findList(String hql, Map<String, Object> params, int page, int rows);

    <T> List<T> findList(String hql, int page, int rows);

    Long getCountByHql(String hql);


    Long getCountByHql(String hql, Map<String, Object> params);

    /**
     * 根据HQL语句返回一个值,如分布获取总页数
     */
    Object getCountByHql(String hql, Object... params);

    /**
     * 根据HQL语句，获得查找总记录数的HQL语句 如： select ... from Orgnization o where o.parent is
     * null 经过转换，可以得到： select count(*) from Orgnization o where o.parent is null
     *
     * @param hql
     * @return
     */
    /*protected String getCountQuery(String hql);*/

    int executeHql(String hql);

    int executeHql(String hql, Map<String, Object> params);

    int executeHql(String hql, Object... objects);

    /**
     * @param hql
     * @param objects 参数,其顺序应该和?占位符一一对应
     * @return
     */
    int executeHql(String hql, List<?> objects);

    /**
     * @param q
     * @param params 当前支持普通对象,数组,集合三种类型的参数
     *//*
    protected void setParameterToQuery(Query q, Map<String, Object> params);

    *//**
     * @param q
     * @param params 当前支持普通对象,不支持集合与数组
     *//*
    protected void setParameterToQuery(Query q, Object... params);

    *//**
     * @param q
     * @param params 当前支持普通对象,不支持集合与数组
     *//*
    protected void setParameterToQuery(Query q, List<?> params);*/

    /****************************************************************
     ******* 上面是和hql相关的操作,下面是和sql相关的操作****************
     ****************************************************************/

    <T> T getCountBySql(String sql);

    /**
     * 根据SQL语句返回一个值,如分布获取总页数
     */
    <T> T getCountBySql(String sql, Object... params);

    /**
     * 根据SQL语句返回一个值,如分布获取总页数
     */
    <T> T getCountBySql(String sql, Map<String, Object> params);

    List<Map<String, Object>> findListBySql(String sql);

    List<Map<String, Object>> findListBySql(String sql, Map<String, Object> params);

    /**
     * 根据SQL语句返回一个集合
     */
    List<Map<String, Object>> findListBySql(String sql, Object... params);

    /**
     * 调用存储过程
     */
    <T> List<T> execProc(String hql);

    /**
     * <b>function:</b> 执行原生态的sql语句，添加、删除、修改语句
     *
     * @param sql 将要执行的sql语句
     * @return int
     * @throws Exception
     * @createDate 2010-8-2 下午05:33:42
     * @author hoojo
     */
    int executeBySql(String sql) throws Exception;

    <T> List<T> callProcedure(String procString, List<Object> params) throws Exception;

    /**
     * 返回此类的列的属性名称,不包含静态属性和Transient
     *
     * @param entity
     * @return
     *//*
    private List<String> getEntityColumnNameList(Class<?> cls) ;
    *//**
     * 得到除开指定名称的属性列
     *//*
    protected List<String> getEntityColumnNames(Class<?> cls, String... exceptCoulumns) ;

    *//**
     * 得到除开指定名称的属性列
     *//*
    protected List<String> getEntityColumnNames(Class<?> cls, List<String> exceptCoulumns) ;

    *//**
     * 获取主键名称
     *
     * @return 没有逐渐则返回null;
     *//*
    private String getPkName(Class<?> cls) ;

    private Object getPkValue(Object t) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException ;

    private String firstLetterToLower(String srcString) ;
*/

}
