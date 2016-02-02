package tcpupd;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 客户端,先启动等待服务器发送的消息
 * 
 * @author JeromeThinkPad
 * @see 《Java开发实战经典》
 */
public class UDPClient {
	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		DatagramSocket ds = null; // 定义接收数据报的对象
		byte[] buf = new byte[1024]; // 开辟空间，以接收数据
		DatagramPacket dp = null; // 声明DatagramPacket对象
		ds = new DatagramSocket(9000); // 客户端在9000端口上等待服务器发送信息
		dp = new DatagramPacket(buf, 1024); // 所有的信息使用buf保存
		ds.receive(dp); // 接收数据
		String str = new String(dp.getData(), 0, dp.getLength()) + "from " + dp.getAddress().getHostAddress() + "："
				+ dp.getPort();
		System.out.println(str); // 输出内容
	}
}