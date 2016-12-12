package util;

/**
 * 系统工具类
 *
 */
public class SystemUtil {

	public static void main(String[] args) {
		System.out.println(isWindowsOS());
	}

	/**
	 * 判断当前操作是否Windows.
	 * 
	 * @return true
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}
}
