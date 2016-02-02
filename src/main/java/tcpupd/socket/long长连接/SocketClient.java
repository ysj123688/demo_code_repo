package tcpupd.socket.long长连接;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

public class SocketClient {
	public static void main(String[] args) throws IOException {
		SocketClient client = new SocketClient("siponets.lz-qs.com", 1920, "5");
		String pushId = UUID.randomUUID().toString().substring(0, 4) + new Date().getTime(); // 推送标识,这里使用随机4位数+时间戳
		StringBuffer sbPushData = new StringBuffer();
		sbPushData.append("PUSHMESSAGE\r\nCLGNUMBER:").append("999999");
		sbPushData.append("\r\nCLDNUMBER:").append("999999");
		sbPushData.append("\r\nCONTENT:").append("{'msg_type':'MSG_UNLOCK_PHONE_REG'}");
		sbPushData.append("\r\nPUSHID:").append(pushId);
		String pushData = sbPushData.toString();
		String pushDataAscii = toASCII(pushData); // 将字符编码转换成US-ASCII码

		// 设置包头
		byte[] byteLength = hexInt(pushDataAscii.length()); // 将数据长度转换成byte数组
		byte head[] = { (byte) 0xFF, (byte) 0xFE, (byte) 0xFF, (byte) 0xFE, (byte) 0xFF, (byte) 0xFE,
				(byte) 0xFF, (byte) 0xFE, byteLength[3], byteLength[2], byteLength[1], byteLength[0] };
		// 设置包头
		/*byte head[] = { (byte) 0x7F, (byte) 0x7F, (byte) 0x7F, (byte) 0x7F, 
				(byte) 0x7F, (byte) 0x7F, (byte) 0x7F, (byte) 0x7F};*/
		// 包体
		byte body[] = pushDataAscii.getBytes();
		byte[] pushDataByte = new byte[head.length + body.length]; // 声明固定长度要推送的字节数组
		// 将两个字节数组合并到pushDataByte
		System.arraycopy(head, 0, pushDataByte, 0, head.length);
		System.arraycopy(body, 0, pushDataByte, head.length, body.length);
		
		client.sndData(pushDataByte);
		
		String rcvData = client.rcvData(2048);
		System.out.println(rcvData);
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
	

	private static Logger log = Logger.getLogger(SocketClient.class);

	private Socket s;
	private OutputStream serverOutput = null;
	private InputStream serverInput = null;
	private String host;
	private int port = -1;
	private String timeout = null;

	/**
	 * 构造方法完成初始化
	 * 
	 * @param host,port,timeout
	 */
	public SocketClient(String host, int port, String timeOut) {

		this.host = host;
		this.port = port;
		this.timeout = timeOut;
		long start = System.currentTimeMillis();
		openConnection();
		long end = System.currentTimeMillis();
		log.debug("SocketClient bulid socket connection consume time: " + Long.toString(end - start) + "  ms");

	}

	/**
	 * 打开连接的输入输出流
	 * 
	 * @return boolean
	 */
	public boolean openConnection() {

		try {

			// 连接telnet服务器
			s = new Socket(host, port);

			if (timeout != null && !timeout.equals("")) {

				s.setSoTimeout(Integer.parseInt(timeout));

			} else {

				s.setSoTimeout(3 * 1000);// 3秒

			}
			log.debug("SocketClient:---------->" + host + ":" + port + " Socket连接成功");
			serverOutput = s.getOutputStream();
			serverInput = s.getInputStream();

		} catch (Exception e) {

			log.error("SocketClient:Exception when openConnection()-" + e);
			this.closeConnection();
			return false;

		}
		return true;

	}

	/**
	 * 关闭连接的输入输出流
	 * 
	 * @author mick.ge
	 */
	public void closeConnection() {

		try {

			// 关闭输出
			if (serverOutput != null) {

				serverOutput.close();

			}

		} catch (Exception e) {

			log.error("SocketClient:Exception when closeConnection()-" + e);

		}

		try {

			// 关闭输入
			if (serverInput != null) {

				serverInput.close();

			}

		} catch (Exception e) {

			log.error("SocketClient:Exception when closeConnection()-" + e);

		}

		try {

			if (s != null) {

				s.close();// 关闭socket

			}

		} catch (Exception e) {

			log.error("SocketClient:Exception when closeConnection()-" + e);

		}

		log.debug("SocketClient:---------->" + host + ":" + port + " Socket连接关闭");

	}

	/**
	 * 发送数据
	 * 
	 * @param sndStr
	 * @return boolean
	 */
	public boolean sndData(byte[] sndStr) {

		try {

//			serverOutput.write(sndStr.getBytes());
			serverOutput.write(sndStr);
			serverOutput.flush();
			log.debug("SocketClient:sndData() sent msg is " + sndStr);
			return true;

		} catch (SocketTimeoutException ste) {

			log.error("SocketClient:SocketTimeout when sndData()-" + ste);
			closeConnection();
			return false;

		} catch (Exception e) {

			log.error("SocketClient:Exception when sndData()-" + e);
			closeConnection();
			return false;

		}

	}

	/**
	 * 接收数据
	 * 
	 * @param lenSize
	 * @return 数据字符串
	 * @throws IOException 
	 */
	public String rcvData(int lenSize) throws IOException {
		char[] lenBuffer = new char[lenSize];
		String readLine = null;
		int i = serverInput.read();
		String response = new String(lenBuffer,12,i);
		//LOG.info("实际接收到的数据为：" + response);
		//System.out.println("接收到的数据为：" + response);  
		System.out.println(response);
//		// 解析结果
//		if (response.contains("CODE")) {
//			result = Integer.parseInt(response.substring(
//					response.indexOf("CODE") + 5, response.indexOf("CODE") + 6));
//		}
		
		/*char[] line = null;
		char[] lenBuffer = new char[lenSize];
		String readLine = null;

		try {

			// 读取规定字节数的字符
			for (int i = 0; i < lenSize; i++) {

				lenBuffer[i] = (char) serverInput.read();

			}

			// 取得消息总长度
			int messageLength = Integer.valueOf((new String(lenBuffer)).trim()).intValue();
			line = new char[messageLength];

			// 将已读取的n个字节传给line
			for (int i = 0; i < lenSize; i++) {

				line[i] = lenBuffer[i];

			}

			// 将剩余的n个字节传给line
			for (int i = lenSize; i < messageLength; i++) {

				line[i] = (char) serverInput.read();

			}

			// 生成字符串对象,写入日志
			readLine = new String(line);
			log.debug("SocketClient:rcvData() received msg is " + readLine);

		} catch (SocketTimeoutException ste) {

			readLine = null;
			log.error("SocketClient:SocketTimeout when rcvData()-" + ste);

		} catch (Exception ex) {

			readLine = null;
			log.error("SocketClient:Exception when rcvData()-" + ex);

		}

		finally {

			this.closeConnection();

		}*/
		return readLine;

	}

}
