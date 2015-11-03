package json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: TestJson 
 * @Description: 
 * @author jerome_s@qq.com
 * @date 2015年9月30日 下午4:52:45 
 * 
 * json-lib-2.4-jdk15.jar所需全部JAR包
 * 1. commons-beanutils-1.8.0.jar
 * 2. commons-collections-3.2.1.jar
 * 3. commons-lang-2.5.jar
 * 4. commons-logging-1.1.1.jar
 * 5. ezmorph-1.0.6.jar
 * 6. json-lib-2.4-jdk15.jar
 * pom
	<dependency>
	  <groupId>net.sf.json-lib</groupId>
	  <artifactId>json-lib</artifactId>
	  <version>2.4</version>
	  <classifier>jdk15</classifier>
	</dependency>
 */
public class TestJson {
	public static void main(String[] args) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("legend", Arrays.asList(new String[] { "销售认购金额","销售认购金额2"}));
		map.put("category", Arrays.asList(new String[] { "年度认购金额", "年度认购指数", "全案认购金额", "全案认购指数" }));
		map.put("series", Arrays.asList(
				new ArrayList<Integer>(Arrays.asList(21,23,28,26)),
				new ArrayList<Integer>(Arrays.asList(25,22,21,21))
				));
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json.toString());
	}
}
