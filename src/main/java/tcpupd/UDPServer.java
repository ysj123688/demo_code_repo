package tcpupd;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 服务端,发送消息给客户端
 * @author JeromeThinkPad
 * @see 《Java开发实战经典》
 *
 */
public class UDPServer {
	public static void main(String args[]) throws Exception {
		DatagramSocket ds = null; // 定义发送数据报的对象
		DatagramPacket dp = null; // 声明DatagramPacket对象
		ds = new DatagramSocket(3000); // 服务端在3000端口上等待服务器发送信息\
		String str = "hello World!!!";
		dp = new DatagramPacket(str.getBytes(), str.length(), InetAddress.getByName("localhost"), 9000); // 所有的信息使用buf保存
		System.out.println("发送信息。");
		ds.send(dp); // 发送信息出去
		ds.close();
	}
}