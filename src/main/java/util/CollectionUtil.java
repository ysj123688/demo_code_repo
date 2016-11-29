package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.management.ObjectName;

import org.apache.commons.collections.CollectionUtils;

/**
 * 常用的集合方法整理
 * 
 * @author jerome
 */
public class CollectionUtil {
	public static void main(String[] args) {
	}

	/**
	 * 集合排序
	 */
	public static void sortList() {
		// Collections.sort(重写toString()进行排序区分)
		// 或者根据自己的业务字段进行排序o1.getAge().compareTo(o2.getAge());
		List<ObjectName> list = new ArrayList<ObjectName>();
		Collections.sort(list, new Comparator<ObjectName>() {
			public int compare(ObjectName o1, ObjectName o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
	}

	/**
	 * 数组排序
	 */
	public static void sortArrays() {
		ObjectName[] arr = new ObjectName[10];
		Arrays.sort(arr, new Comparator<ObjectName>() {
			public int compare(ObjectName o1, ObjectName o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
	}

	/**
	 * 对List进行分页
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
	@SuppressWarnings({ "unchecked", "unused" })
	private List<String> getListPage(int pageNo, int pageSize, List<String> vouchers) {
		List<String> result = new ArrayList<String>();
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
		}
		return (List<String>) ((CollectionUtils.isNotEmpty(vouchers)) ? result : Collections.emptyList());
	}

	/**
	 * 分批list
	 * 
	 * @param sourceList
	 *            要分批的list
	 * @param batchCount
	 *            每批list的个数
	 * @return List<List<Object>>
	 */
	public static List<List<?>> batchList(List<?> sourceList, int batchCount) {
		List<List<?>> returnList = new ArrayList<List<?>>();
		int startIndex = 0; // 从第0个下标开始
		while (startIndex < sourceList.size()) {
			int endIndex = 0;
			if (sourceList.size() - batchCount < startIndex) {
				endIndex = sourceList.size();
			} else {
				endIndex = startIndex + batchCount;
			}
			returnList.add(sourceList.subList(startIndex, endIndex));
			startIndex = startIndex + batchCount; // 下一批
		}
		return returnList;
	}
}
