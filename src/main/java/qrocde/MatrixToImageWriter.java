package qrocde;

import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;

/**
 * 
 * @ClassName: MatrixToImageWriter
 * @Description: 生成二维码工具类
 * @author jerome_s@qq.com
 * @date 2015年9月28日 下午2:14:43
 * 需要添加 core-3.1.0.jar 链接：http://pan.baidu.com/s/1oJ9sM 密码：t6t1
 * 	<dependency>
		<groupId>com.google.zxing</groupId>
		<artifactId>core</artifactId>
		<version>3.2.1</version>
	</dependency>
 *
 */
public final class MatrixToImageWriter {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private MatrixToImageWriter() {
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}

	/**
	 * 去白边
	 * 
	 * @param matrix
	 * @return
	 */
	public static BitMatrix deleteWhite(BitMatrix matrix) {
		int[] rec = matrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (matrix.get(i + rec[0], j + rec[1]))
					resMatrix.set(i, j);
			}
		}
		return resMatrix;
	}

}