package efficient;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 
 * @ClassName: TestCommons 
 * @Description: apache.commons java中很常用的一个类库
 * @author jerome_s@qq.com
 * @date 2015年11月9日 下午10:54:54 
	<dependency>
		<groupId>commons-collections</groupId>
		<artifactId>commons-collections</artifactId>
		<version>3.2.1</version>
	</dependency>
	<dependency>
		<groupId>org.apache.commons</groupId>
		<artifactId>commons-lang3</artifactId>
		<version>3.3.2</version>
	</dependency>
 *
 */
public class TestCommons {
	public static void main(String[] args) {
//		StringUtils.isNotBlank("");
		System.out.println(NumberUtils.isNumber(null));
		System.out.println(NumberUtils.isNumber("12sdf"));
		System.out.println(NumberUtils.isNumber("123"));
	}
}
