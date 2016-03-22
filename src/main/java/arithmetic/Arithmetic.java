package arithmetic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Java常用算法
 * 
 * @author JeromeThinkPad
 * @see 可以参考：http://www.blogjava.net/todayx-org/archive/2012/01/08/368091.html
 */
public class Arithmetic {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(sha1("0582a39c545648488051778916516618"));
		// 快速排序
		/*String[] strVoid = new String[] { "11", "66", "22", "0", "55", "22", "0", "32" };
		quickSort(strVoid, 0, strVoid.length - 1);
		for (int i = 0; i < strVoid.length; i++) {
			System.out.println(strVoid[i] + " ");
		}*/
	}

	/**
	 * 快速排序
	 * 
	 * @param strDate
	 * @param left
	 * @param right
	 */
	public static void quickSort(String[] strDate, int left, int right) {
		String middle, tempDate;
		int i, j;
		i = left;
		j = right;
		middle = strDate[(i + j) / 2];
		do {
			while (strDate[i].compareTo(middle) < 0 && i < right)
				i++; // 找出左边比中间值大的数
			while (strDate[j].compareTo(middle) > 0 && j > left)
				j--; // 找出右边比中间值小的数
			if (i <= j) { // 将左边大的数和右边小的数进行替换
				tempDate = strDate[i];
				strDate[i] = strDate[j];
				strDate[j] = tempDate;
				i++;
				j--;
			}
		} while (i <= j); // 当两者交错时停止

		if (i < right) {
			quickSort(strDate, i, right);// 从
		}
		if (j > left) {
			quickSort(strDate, left, j);
		}
	}

	/**
	 * 冒泡排序
	 */
	public static void bubbling() {
		// 冒泡排序
		int arrInt[] = { 10, 5, 6, 56, 7, 3 };
		// 拿第一个和下面的每个对比，外层每循环一次，最小的就浮上来
		for (int i = 0; i < arrInt.length; i++) {
			for (int j = i; j < arrInt.length; j++) {
				if (arrInt[i] > arrInt[j]) {
					int temp = arrInt[j];
					arrInt[j] = arrInt[i];
					arrInt[i] = temp;
				}
			}
		}

		for (int i : arrInt) {
			System.out.println(i + " ");
		}
	}

	/**
	 * 插入排序算法
	 * @param arr
	 * @see 图 http://pan.baidu.com/s/1qXo32va
	 */
	public static void insertSort(int[] arr) {
		// 功能
		for (int x = 1; x < arr.length; x++) {
			for (int y = x; y > 0; y--) {
				// 判断
				if (arr[y] < arr[y - 1]) {
					int temp = arr[y];
					arr[y] = arr[y - 1];
					arr[y - 1] = temp;
				} else {
					break;
				}
			}
		}

	}

	public static String sha1(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
