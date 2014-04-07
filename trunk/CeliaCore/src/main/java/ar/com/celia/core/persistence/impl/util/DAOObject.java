package ar.com.celia.core.persistence.impl.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.celia.common.reflection.ReflectionUtil;

public abstract class DAOObject extends HibernateDaoSupport {
	
	public Object getObject(Class clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}
	
	public List getObjects(Class clazz) {
		return getHibernateTemplate().loadAll(clazz);
	}
	
	public List getObjectsByQuery(String query, Object[] parms) {
		if (parms == null) {
			return getHibernateTemplate().find(query);
		} else {
			return getHibernateTemplate().find(query, parms);
		}
	}
	
	protected void addDependenciesFilters(Object p_example,Criteria cri) throws Exception
	{
		Method methods[]=p_example.getClass().getDeclaredMethods();
		for(Method method:methods)
		{
			if(method.isAnnotationPresent(javax.persistence.ManyToOne.class))
			{
				Object obj=method.invoke(p_example,null);
				Object objId=null;
				if(obj!=null)
					objId=ReflectionUtil.getValueOfIdField(p_example);
				
				if(objId!=null)
				{
					String claseSimpleName=obj.getClass().getSimpleName();
					String fieldNameFirstLetter=claseSimpleName.toLowerCase().substring(0,1);
					String fieldNameRest=claseSimpleName.substring(1);
					
					cri.createCriteria(fieldNameRest).add(Restrictions.idEq(objId));
				}
			}
		}
	}
	
	public void saveObject(Object object) {
		getHibernateTemplate().save(object);
	}
	
	public void updateObject (Object object) {
		getHibernateTemplate().update(object);
	}
	
	public void deleteObject(Object object) {
		getHibernateTemplate().delete(object);
	}

	public void deleteObject(Class clazz, Serializable id) {
		getHibernateTemplate().delete(getObject(clazz, id));
	}
}
