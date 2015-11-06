package qrocde;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * 
 * @ClassName: Test
 * @Description:Java 生成二维码 (zxing)
 * @author jerome_s@qq.com
 * @date 2015年9月29日 下午6:01:38
 * @see http://www.stepday.com/topic/?877
 * @see http://blog.csdn.net/gaogaoshan/article/details/38037849
 */
public class Test {
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			getQrcode("back"+UUID.randomUUID());
		}
		
	}

	/**
	 * 生成二维码
	 * 
	 * @param text
	 * @throws WriterException
	 * @throws IOException
	 */
	private static void getQrcode(String text) throws Exception {
		int width = 147;
		int height = 147;
		// 二维码的图片格式
		String format = "png";
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		BitMatrix bitMatrix2 = MatrixToImageWriter.deleteWhite(bitMatrix);
		// 生成二维码
		File outputFile = new File("d:" + File.separator +"back"+File.separator+ text + ".png");
		MatrixToImageWriter.writeToFile(bitMatrix2, format, outputFile);
	}

}
