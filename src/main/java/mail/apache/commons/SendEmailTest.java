package mail.apache.commons;

public class SendEmailTest {
	
	private static String mailServerHost = "smtp.yeah.net";
	private static String mailServerPort = "25";
	private static String userName = "xx@yeah.net";
	private static String fromName = "昵称";
	private static String password = "xx";
	private static String toAddress = "jerome_s@qq.com";
	
	public static void main(String[] args) {
		Mail mail = new Mail();
		mail.setHost(mailServerHost); // 设置邮件服务器,如果不用QQ邮箱的,自己找找看相关的
		mail.setPortNumber(mailServerPort); // 设置邮件服务器端口号,默认25
		mail.setSender(userName); // 发送人
		mail.setName(fromName); // 发送人昵称
		mail.setReceiver(toAddress); // 接收人
		mail.setUsername(userName); // 登录账号,一般都是和邮箱名一样
		mail.setPassword(password); // QQ邮箱登录第三方客户端时,密码框请输入“授权码”进行验证。其他的密码具体查看邮件服务器的说明
		mail.setSubject("标题");
		mail.setMessage("<h1>内容</h1>");
		if (new MailUtil().send(mail)) {
			System.out.println("发送成功");
		} else {
			System.out.println("发送失败");
		}
	}
}
