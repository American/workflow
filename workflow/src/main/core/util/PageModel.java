package main.core.util;

import java.util.List;

public class PageModel<T> {
	
	private int total;

	private List<T> datas;

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
