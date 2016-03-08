package redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * 导入jar：jedis-2.1.0.jar、commons-pool-1.5.4.jar
 * @ClassName: TestRedis 
 * @Description: Jedis 
 * @author jerome_s@qq.com
 * @date 2015年10月14日 下午4:19:42 
 *<dependency>
	<groupId>redis.clients</groupId>
	<artifactId>jedis</artifactId>
	<version>2.1.0</version>
  </dependency>

 */
public class TestRedis {
	private Jedis jedis;

	public TestRedis() {
		jedis = new Jedis("vrouter.lz-oa.com", 6379);
		// 权限认证
		// jedis.auth("admin");
	}

	/**
	 * redis存储字符串
	 */
	public void testString() {
		jedis.set("name", "Jerome苏");
		jedis.expire("name", 60); // 设置存活时间
		// jedis.del("name"); //删除某个键

		System.out.println("name----->>>" + jedis.get("name"));
		System.out.println("name----->>>" + jedis.lrange("201509180033", 0, -1));
		System.out.println("name----->>>" + jedis.keys("*").toString());
		System.out.println("name----->>>" + jedis.get("201509180033"));
		
		System.out.println("name----->>>" + jedis.info());
//		System.out.println(jedis.select(3));
		
	}

	/**
	 * redis操作Map
	 */
	public void testMap() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "jerome");
		map.put("age", "24");
		map.put("email", "jerome_s@qq.com");
		jedis.hmset("user", map); // 存入redis

		// 取出
		List<String> rsmap = jedis.hmget("user", "name", "age");
		System.out.println(rsmap);

		// 删除map中的某个键值
		// jedis.hdel("user", "age");

		// System.out.println(jedis.hlen("user")); // 返回key为user的键中存放的值的个数2
		// System.out.println(jedis.exists("user"));// 是否存在key为user的记录 返回true
		// System.out.println(jedis.hkeys("user"));// 返回map对象中的所有key
		// System.out.println(jedis.hvals("user"));// 返回map对象中的所有value

	}

	/**
	 * jedis操作List
	 */
	public void testList() {
		// 开始前，先移除所有的内容
		jedis.del("animals");
		// -1表示取得所有
		System.out.println(jedis.lrange("animals", 0, -1));

		// 存入List
		jedis.lpush("animals", "cat");
		jedis.lpush("animals", "dog");
		jedis.lpush("animals", "tiger");
		System.out.println(jedis.lrange("animals", 0, -1));

		// 删除单个
		jedis.rpush("animals", "cat");
		System.out.println(jedis.lrange("animals", 0, -1));
	}

	/**
	 * jedis操作Set
	 */
	public void testSet() {
		// 添加
		jedis.sadd("user", "jerome");
		jedis.sadd("user", "jelly");
		jedis.sadd("user", "jack");
		jedis.sadd("user", "nemo");
		jedis.sadd("user", "who");

		// 移除noname
		jedis.srem("user", "who");

		System.out.println(jedis.smembers("user"));// 获取所有加入的value
		System.out.println(jedis.sismember("user", "who"));// 判断 who是否是user集合的元素
		System.out.println(jedis.srandmember("user"));
		System.out.println(jedis.scard("user"));// 返回集合的元素个数
	}

	/**
	 * redis排序
	 * 
	 * @throws InterruptedException
	 */
	public void testSort() throws InterruptedException {
		// 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
		jedis.del("a");// 先清除数据，再加入数据进行测试
		jedis.rpush("a", "1");
		jedis.lpush("a", "6");
		jedis.lpush("a", "3");
		jedis.lpush("a", "9");
		System.out.println(jedis.lrange("a", 0, -1));// [9, 3, 6, 1]
		System.out.println(jedis.sort("a")); // [1, 3, 6, 9] //输入排序后结果
		System.out.println(jedis.lrange("a", 0, -1));
	}

	public static void main(String[] args) {
		TestRedis redis = new TestRedis();
		redis.testString();
		// RedisUtil.getJedis().set("newname", "中文测试");
		// System.out.println(RedisUtil.getJedis().get("newname"));
	}
}