package tcpupd.socket.long长连接;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.Date;
import java.util.UUID;

/**
 * socket长连接
 * @author JeromeThinkPad
 *
 */
public class Client {
//	private String host = "localhost";
	private String host = "siponets.lz-qs.com";
	private int port = 1920;

	class SendThread implements Runnable {
		private Socket socket;

		public SendThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			while (true) {
				try {
					System.out.println("kaifa 发数据");
					String pushId = UUID.randomUUID().toString().substring(0, 4) + new Date().getTime(); // 推送标识,这里使用随机4位数+时间戳
					StringBuffer sbPushData = new StringBuffer();
					sbPushData.append("PUSHMESSAGE\r\nCLGNUMBER:").append("123123");
					sbPushData.append("\r\nCLDNUMBER:").append("123123");
					sbPushData.append("\r\nCONTENT:").append("{'msg_type':'MSG_UNLOCK_PHONE_REG'}");
					sbPushData.append("\r\nPUSHID:").append(pushId);
					String pushData = sbPushData.toString();
					String pushDataAscii = toASCII(pushData); // 将字符编码转换成US-ASCII码

					// 设置包头
					byte[] byteLength = hexInt(pushDataAscii.length()); // 将数据长度转换成byte数组
					byte head[] = { (byte) 0xFF, (byte) 0xFE, (byte) 0xFF, (byte) 0xFE, (byte) 0xFF, (byte) 0xFE,
							(byte) 0xFF, (byte) 0xFE, byteLength[3], byteLength[2], byteLength[1], byteLength[0] };
					// 包体
					byte body[] = pushDataAscii.getBytes();
					byte[] pushDataByte = new byte[head.length + body.length]; // 声明固定长度要推送的字节数组
					// 将两个字节数组合并到pushDataByte
					System.arraycopy(head, 0, pushDataByte, 0, head.length);
					System.arraycopy(body, 0, pushDataByte, head.length, body.length);
					 PrintWriter pw = new PrintWriter(new
					 OutputStreamWriter(socket.getOutputStream()));
					 pw.write(pushDataByte.toString());
					 pw.flush();
//					OutputStream out = null; // 发送流
//					out = socket.getOutputStream();
//					out.write(pushDataByte);
//					out.flush();
					Thread.sleep(2000);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}

	}

	class ReceiveThread implements Runnable {
		private Socket socket;

		public ReceiveThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			System.out.println("收数据");
//			while (true) {
//				try {
//					Reader reader = new InputStreamReader(socket.getInputStream());
//					CharBuffer buf = CharBuffer.allocate(8192);
//					int charIndex = -1;
//					while ((charIndex = reader.read(buf)) != -1) {
//						buf.flip();
//						System.out.println("client：" + buf.toString());
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}

		}
	}

	private void start() {
		try {
			Socket socket = new Socket(host, port);
			new Thread(new SendThread(socket)).start();
			new Thread(new ReceiveThread(socket)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client().start();
	}

	/**
	 * 将字符编码转换成US-ASCII码
	 */
	public static String toASCII(String str) throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, "US-ASCII");
		}
		return null;
	}

	/**
	 * 将int转换成byte数组
	 * 
	 * @param hexint
	 * @return
	 */
	public static byte[] hexInt(int hexint) {
		byte[] a = new byte[4];
		a[0] = (byte) (0xff & hexint);
		a[1] = (byte) ((0xff00 & hexint) >> 8);
		a[2] = (byte) ((0xff0000 & hexint) >> 16);
		a[3] = (byte) ((0xff000000 & hexint) >> 24);
		return a;

	}
}
