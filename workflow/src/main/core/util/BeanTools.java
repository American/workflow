/**
 * 操作JavaBean的工具集
 */
package main.core.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 操作JavaBean的工具集
 * @author qnwang
 * @version 1.0
 */
public class BeanTools {
	
	/**
	 * 将bean from中的内容通过绑定到bean to中.
	 * @param from 从该参数对应的javabean中拷贝内容
	 * @param to 将从参数from对应的javabean中拷贝的内容，绑定到该参数对应的javabean中
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static void bindBeanProperties(Object from, Object to)
			throws IllegalAccessException, InvocationTargetException, NullPointerException {
		BeanUtils.copyProperties(to, from);
	}
	
	/**
	 * 将Map中的值绑定到bean中去
	 * @param bean 被赋值的bean
	 * @param properties 保存属性值的Map
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void bindMapPropertiesToBean(Object bean, Map<String,?> properties) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.populate(bean, properties);
	}
	
	/**
	 * 给bean的某个属性赋值
	 * @param bean 目标bean
	 * @param name 属性名
	 * @param value 属性值
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void setBeanProperties(Object bean,String name,Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		PropertyUtils.setProperty(bean, name, value);
	}
	
	/**
	 * 获得Bean中的某个属性的只
	 * @param bean 目标bean
	 * @param name 属性名
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static Object getBeanProperty(Object bean,String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return PropertyUtils.getProperty(bean, name);
	}
	
	/**
	 * 拷贝属性变量
	 * @param dest 给此Bean的属性赋值
	 * @param orig 拷贝此Bean的属性值
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyProperties(Object dest, Object orig)
		throws IllegalAccessException, InvocationTargetException {
		PropertyDescriptor origDescriptors[] =
			PropertyUtils.getPropertyDescriptors(orig);
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            if ("class".equals(name)) {
                continue; // No point in trying to set an object's class
            }
            if (PropertyUtils.isReadable(orig, name) &&
            		PropertyUtils.isWriteable(dest, name)) {
                try {
                    Object value =
                    	PropertyUtils.getSimpleProperty(orig, name);
                    
                    if(value!=null){
                    	if(value instanceof Integer){
                    		int num=(Integer)value;
                    		if(num>0){
                    			BeanUtils.copyProperty(dest, name, value);
                    		}
                    	}else if(value instanceof Long){
                    		long num=(Long)value;
                    		if(num>0){
                    			BeanUtils.copyProperty(dest, name, value);
                    		}
                    	}else if(value instanceof Double){
                    		double num=(Double)value;
                    		if(num>0){
                    			BeanUtils.copyProperty(dest, name, value);
                    		}
                    	}else if(value instanceof String){
                    		String text=(String)value;
                    		if(StringUtils.isNotBlank(text)){
                    			if("a_ccipm_blank".equals(text)){
                    				BeanUtils.copyProperty(dest, name, "");
                    			}else{
                    				BeanUtils.copyProperty(dest, name, value);
                    			}
                    		}
                    	}else{
                    		BeanUtils.copyProperty(dest, name, value);
                    	}
                    }
                } catch (NoSuchMethodException e) {
                    ; // Should not happen
                }
            }
        }
	}
	
	/**
	 * 拷贝Bean中的数据到Map中去
	 * @param dest 往此Map中拷贝数据
	 * @param obj 拷贝此Bean的属性值
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public static <V> void copyPropertiesToMap(Map<String,V> dest,Object obj) 
		throws IllegalAccessException, InvocationTargetException{
		if (obj instanceof Map) {
			Set<Entry<Object,Object>> entries = ((Map<Object,Object>) obj).entrySet();
            for (Entry<Object,Object> entry:entries) {
            	Object key=entry.getKey();
            	
            	if(key instanceof String){
            		String name = (String)key;
            		dest.put(name, (V)entry.getValue());
            	}
            }
        } else /* if (orig is a standard JavaBean) */ {
        	PropertyDescriptor[] origDescriptors =
        		PropertyUtils.getPropertyDescriptors(obj);
        	
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (PropertyUtils.isReadable(obj, name)) {
                    try {
                        Object value =PropertyUtils.getSimpleProperty(obj, name);
                        dest.put(name, (V)value);
                    } catch (NoSuchMethodException e) {
                        // Should not happen
                    }
                }
            }
        }
	}
	
	/**
	 * 拷贝Bean中的数据到Map中去，仅拷贝String类型的数据
	 * @param dest 往此Map中拷贝数据
	 * @param obj 拷贝此Bean的属性值
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public static void copyOnlyStringPropertiesToMap(Map<String,String> dest,Object obj) 
		throws IllegalAccessException, InvocationTargetException{
		if (obj instanceof Map) {
			Set<Entry<Object,Object>> entries = ((Map<Object,Object>) obj).entrySet();
            for (Entry<Object,Object> entry:entries) {
            	Object key=entry.getKey();
            	
            	if(key instanceof String){
            		String name = (String)key;
            		
            		Object value=(Object)entry.getValue();
            		
            		if(value instanceof String){
            			dest.put(name, (String)value);
            		}
            	}
            }
        } else /* if (orig is a standard JavaBean) */ {
        	PropertyDescriptor[] origDescriptors =
        		PropertyUtils.getPropertyDescriptors(obj);
        	
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (PropertyUtils.isReadable(obj, name)) {
                    try {
                        Object value =PropertyUtils.getSimpleProperty(obj, name);

                		if(value instanceof String){
                        	dest.put(name, (String)value);
                		}
                    } catch (NoSuchMethodException e) {
                        // Should not happen
                    }
                }
            }
        }
	}
}
