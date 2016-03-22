package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import json.Person;

/**
 * List相关操作工具类
 * 
 * @author JeromeThinkPad
 *
 */
public class ListUtil {

	public static void main(String[] args) {

	}

	/**
	 * Collections.sort(重写实体类toString()进行排序区分)
	 */
	public void listSort() {
		List<Person> list = new ArrayList<Person>();
		Collections.sort(list, new Comparator<Person>() {
			public int compare(Person o1, Person o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
	}

	/**
	 * 对List进行分页（感觉有更好的实现方案）
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            页数
	 * @param vouchers
	 *            所有集合
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private List<Person> getPage(int pageNo, int pageSize, List<Person> vouchers) {
		List<Person> result = new ArrayList<Person>();
		if (CollectionUtils.isNotEmpty(vouchers)) {
			int allCount = vouchers.size();
			int pageCount = (allCount + pageSize - 1) / pageSize;
			if (pageNo >= pageCount) {
				pageNo = pageCount;
			}
			int start = (pageNo - 1) * pageSize;
			int end = pageNo * pageSize;
			if (end >= allCount) {
				end = allCount;
			}
			for (int i = start; i < end; i++) {
				result.add(vouchers.get(i));
			}
			return vouchers;
		} else {
			return Collections.emptyList();
		}

	}
}
