package main.core.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * <p>Title: Page.java</p>
 * <p>Description: 分页对象. 包含当前页数据及分页信息. </p>
 * <p>Copyright: ZHJY'S Copyright (c) 2006</p>
 * <p>Company: ZHJY</p>
 * @since 2006-12-25 10:43:23
 * @author rkyj_project_team
 * @version 1.0
 */
public class Page<E> extends HibernateDaoSupport implements Serializable {
	/**
     * long
     * serialVersionUID
     */
    private static final long serialVersionUID = -1582121222488045159L;

    public static final int PAGE_SIZE = 15;

	public static final int PAGE_SIZE_SMALL = 5;

	public static final int PAGE_SIZE_NORMAL = 10;

	public static final int PAGE_SIZE_BIG = 15;

	public static final int PAGE_SIZE_BIGGER = 20;

	public static final int PAGE_SIZE_BIGGEST = 50;
	
	/**
	 * 构造方法，只构造空页
	 * 
	 */
	public Page() {
		new Page<E>(0, new ArrayList<E>());
	}
	
	/**
	 * 结果集列表
	 */
	List<E> objects;

	/**
	 * 开始记录号，从0开始
	 */
	private int start;
	/**
	 * 结束记录号
	 */
	private int end;
	/**
	 * 显示在页面开始记录号，从1开始
	 */
	private int viewStart;
	/**
	 * 显示在页面结束记录号
	 */
	private int viewEnd;
	/**
	 * 是否有上一页的开关
	 */
	private boolean hasPrevious;
	/**
	 * 上一页的页码
	 */
	private int previousPageNumber;
	/**
	 * 是否有下一页的开关
	 */
	private boolean hasNext;
	/**
	 * 下一页的页码
	 */
	private int nextPageNumber;
	/**
	 * 一共有多少行记录
	 */
	private int total;
	/**
	 * 一共有多少页
	 */
	private int totalPage;
	/**
	 * 当前是第几页
	 */
	private int currentPageNumber;
	/**
	 * 每页有多少行
	 */
	private int pageSize;

	/**
	 * 构造器,创建页面
	 * @param: l 结果集
	 * @param: s 开始记录号，从0开始
	 * @param: hasNext 是否有下一页的开关
	 */
	public Page(int s, List<E> l) {
		objects = new ArrayList<E>(l);
		this.currentPageNumber = s / PAGE_SIZE + 1;
		this.pageSize = PAGE_SIZE;
		this.total = 0;
		if (total == 0)
			this.currentPageNumber = 0;
		else
			autoCalculate();
	}

	/**
	 * 构造器,创建页面
	 * @param: l 结果集
	 * @param: num 当前是第几页
	 * @param: size 每页有多少行
	 * @param: total 一共有多少行记录
	 */
	public Page(int num, int size, int total, List<E> l) {
		this.objects = new ArrayList<E>(l);
		this.currentPageNumber = num;
		this.pageSize = size;
		this.total = total;
		if (total == 0)
			this.currentPageNumber = 0;
		else
			autoCalculate();
	}

	/**
	 * 构造器,创建页面
	 * @param: l 结果集
	 * @param: start 开始记录
	 * @param: size 每页有多少行
	 * @param: total 一共有多少行记录
	 */
	public Page( List<E> l,int start, int size, int total) {
		new Page<E>((start / size) + 1,size,total,l);
	}

	/**
	 * 构造器,创建一个页面,不分页
	 * @param: l 结果集
	 */
	public Page(List<E> l) {
		this.objects = new ArrayList<E>(l);
		this.currentPageNumber = 1;
		this.pageSize = l.size();
		this.total = l.size();
		if (total == 0)
			this.currentPageNumber = 0;
		else
			autoCalculate();
	}

	/**
	 * 自动计算，根据当前页、页大小、总行数计算出其它属性的值
	 */
	private void autoCalculate() {
		start = (currentPageNumber - 1) * pageSize;
		end = start + pageSize - 1;
		if (end > total) {
			end = total;
		}
		viewStart = start + 1;
		viewEnd = end + 1;
		totalPage = (total + pageSize - 1) / pageSize;
		if (currentPageNumber == 1) {
			hasPrevious = false;
			previousPageNumber = 1;
		} else {
			hasPrevious = true;
			previousPageNumber = currentPageNumber - 1;
		}
		if (currentPageNumber == totalPage) {
			hasNext = false;
			nextPageNumber = totalPage;
		} else {
			hasNext = true;
			nextPageNumber = currentPageNumber + 1;
		}
	}

	/**
	 * 获得结果集
	 * @return: List 结果集
	 */
	public List<E> getList() {
		return objects;
	}
    
    public List<E> getResult() {
        return objects;
    }

	/**
	 * 获得显示在页面的开始记录号，从1开始
	 * @return: int 显示在页面的开始记录号
	 */
	public int getViewStart() {
		return viewStart;
	}
	/**
	 * 获得显示在页面的结束记录号
	 * @return: int 显示在页面的结束记录号
	 */
	public int getViewEnd() {
		return viewEnd;
	}
	/**
	 * 是否有下一页
	 * @return: boolean 是否有下一页的开关
	 */
	public boolean hasNextPage() {
		return hasNext;
	}
	/**
	 * 是否有上一页
	 * @return: boolean 是否有上一页的开关
	 */
	public boolean hasPreviousPage() {
		return hasPrevious;
	}
	/**
	 * 获得上一页的页码
	 * @return: int 上一页的页码
	 */
	public int getPreviousPageNumber() {
		return previousPageNumber;
	}
	/**
	 * 获得下一页的页码
	 * @return: int 下一页的页码
	 */
	public int getNextPageNumber() {
		return nextPageNumber;
	}
	/**
	 * 获得结果集中记录总行数
	 * @return: int 一共有多少行记录
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * 获得总页数
	 * @return: int 一共有多少页
	 */
	public int getTotalPage() {
		return totalPage;
	}
	/**
	 * 获得当前页码
	 * @return: int 当前页码
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	/**
	 * 获得每页多少行记录
	 * @return: int 页大小
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 获得下一页在结果集中开始的记录号，从0开始
	 * @return: int 下一页在结果集中开始的记录号
	 */
	public int getStartOfNextPage() {
		return start + PAGE_SIZE;
	}
	/**
	 * 获得上一页在结果集中开始的记录号，从0开始
	 * @return: int 下一页在结果集中开始的记录号
	 */
	public int getStartOfPreviousPage() {
		return Math.max(start - PAGE_SIZE, 0);
	}
	
	/**
	 * 获得最后一页的开始记录号
	 * @return
	 */
	public int getStartOfEndPage(){
		return getStartNumber(totalPage,pageSize);
	}
	
	/**
	 * 根据页号和记录数得到起始记录
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static int getStartNumber(int pageNo, int pageSize) {
		int startNumber = 0;
		startNumber = (pageNo - 1) * pageSize;
		return startNumber;
	}
	
	/**
	 * 将数组内的记录转换成分页
	 * @param object
	 * @param pageNo
	 * @param fetch
	 * @return
	 */
	public static Page<Object> getPageForSub(Object[] object, int pageNo, int fetch) {
		Page<Object> page = null;
		int savNo = getStartNumber(pageNo, fetch);

		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < fetch; i++) {
			if (i + savNo < object.length) {
				list.add(object[i + savNo]);
			}
		}
		page = new Page<Object>(pageNo, fetch, object.length, list);

		return page;
	}
    
 
	
}