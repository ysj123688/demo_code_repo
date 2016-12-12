package util;

/**
 * 系统工具类
 *
 */
public class SystemUtil {

	public static void main(String[] args) {
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

	/**
	 * 判断当前操作是否Linux.
	 * 
	 * @return true
	 */
	public static boolean isLinuxOS() {
		boolean isLinuxOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("linux") > -1) {
			isLinuxOS = true;
		}
		return isLinuxOS;
	}
}
