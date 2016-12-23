package util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 微信相关工具类
 * 
 * @author jerome_s@qq.com
 */
public class WechatUtil {
	public static void main(String[] args) {
		
		// Test wechatPaySign(SortedMap packageParams) {
//		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
//		parameters.put("appid", "wx72447b50ef380ba9");
//		parameters.put("mch_id", "1398390602");
//		parameters.put("attach", "支付测试");
//		parameters.put("body", "");
//		parameters.put("nonce_str", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
//		System.out.println(wechatPaySign(parameters));
		
		// dealBody(String body)
		System.out.println(dealBody("支付测试付测试速度啊支付测试付测试速度啊支付测试付测试速度啊支付测试付测试速度啊多"));

		
	}

	/**
	 * 微信支付 签名
	 * 
	 * @see https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
	 * @author jerome_s@qq.com
	 * @param packageParams
	 * @return
	 */
	public static String wechatPaySign(SortedMap packageParams) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = String.valueOf(entry.getKey());
			String value = String.valueOf(entry.getValue());
			if (null != value && !"".equals(value) && !"sign".equals(key) && !"key".equals(key)) {
				sb.append(key + "=" + value + "&");
			}
		}

		// 支付密钥必须参与加密，放在字符串最后面
		String PAY_KEY = "fab017940af446de9ba36288446883b3";
		sb.append("key=" + PAY_KEY);
		String signValue = MD5Util.md5(sb.toString()).toUpperCase();
		return signValue;
	}
	
	/**
	 * 处理商品描述
	 * 
	 * @author jerome_s@qq.com
	 * @return
	 */
	public static String dealBody(String body) {
		String result = body;
		if (body.length() > 40) {
			result = result.substring(0, 40) + "...";
		}
		return result;
	}
}
