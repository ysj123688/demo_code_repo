package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * HTML工具类 解析等
 * @see http://dwz.cn/4NRgPO
 * @author jerome_s@qq.com
 */
public class HTMLUtil {

	/**
	 * 从url获取html文档对象
	 * 
	 * @author jerome_s@qq.com
	 * @param url
	 *            请求的url
	 * @return
	 */
	public static Document getHTMLDocumentFromUrl(String url) {
		// 文档对象，用来接收html页面
		Document document = null;
		try {
			// 获取指定网址的页面内容
			document = Jsoup.connect(url).timeout(50000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * 从一段html中拿到<img>标签中的图片路径
	 * 
	 * @author：suzhida
	 * @param htmlText
	 * @return
	 */
	public static List<String> getImgsSrc(String htmlText) {
		List<String> imgUrls = new ArrayList<String>();
		if (StringUtils.isNotBlank(htmlText)) {
			Document doc = Jsoup.parse(htmlText);
			for (Element elem : doc.select("img")) {
				if (StringUtils.isBlank(elem.attr("src"))) {
					continue;
				}
				imgUrls.add(elem.attr("src"));
			}
		}
		return imgUrls;
	}

	public static void main(String[] args) {
//		Document htmlDocument = getHTMLDocumentFromUrl("http://www.baidu.com/");
//		System.out.println(htmlDocument.toString());
		
		/**
		 * <div style="text-align:center;">
		 *	    <img src="http://dev-lzmh-shop-per.ufile.ucloud.cn/201611-5837a70949deb0e49b2ce76e.jpg" alt="" />
		 *	    <img src="http://dev-lzmh-shop-per.ufile.ucloud.cn/201611-5837a71649deb0e49c2ce76e.jpg" alt="" />
		 *	    <img src="http://dev-lzmh-shop-per.ufile.ucloud.cn/201611-5837a72749deb0e49d2ce76e.gif" alt="" />
		 * </div>
		 */
		String str = "<div style=\"text-align:center;\"><img src=\"http://dev-lzmh-shop-per.ufile.ucloud.cn/201611-5837a70949deb0e49b2ce76e.jpg\" alt=\"\" /><img src=\"http://dev-lzmh-shop-per.ufile.ucloud.cn/201611-5837a71649deb0e49c2ce76e.jpg\" alt=\"\" /><img src=\"http://dev-lzmh-shop-per.ufile.ucloud.cn/201611-5837a72749deb0e49d2ce76e.gif\" alt=\"\" /></div>";
		List<String> imgsSrcList = getImgsSrc(str);
		for (String imgSrc : imgsSrcList) {
			System.out.println(imgSrc);
		}
	}
}
