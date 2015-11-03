package util;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;


/**
 * 获取项目参数/配置文件 工具类
 * 
 */
public class ResourceUtil {
	public static void main(String[] args) {
		System.out.println(getConfigByName("server.port"));
	}

	/**
	 * 系统配置文件
	 */
	private static final ResourceBundle sysBundle = java.util.ResourceBundle.getBundle("config");
	
	
	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	/*public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}*/


	/**
	 * 获取系统配置
	 * @param name
	 * @return
	 */
	public static final String getConfigByName(String name)
	{
		return sysBundle.getString(name);
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: getZoom 
	* @Description: TODO(获取区域) 
	* @param @param name
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
//	public static final String getZoom(String name) throws UnsupportedEncodingException{
//		return new String(zoomBundle.getString(name).getBytes("ISO-8859-1"),"utf-8");
//	}
	
	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator).replaceAll("%20", " ");
		return resultPath;
	}

	/**
	 * 获取项目根目录
	 * 
	 * @return
	 */
	public static String getPorjectPath() {
		String nowpath; // 当前tomcat的bin目录的路径 如
						// D:\java\software\apache-tomcat-6.0.14\bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		return tempdir;
	}

	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}
}
