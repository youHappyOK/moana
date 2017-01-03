package rush.io.lab.moana.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Repository;

import rush.io.lab.moana.model.SuccessRush;
import rush.io.lab.moana.vo.RushMovieCallBack;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 邮件发送类
 *
 * @author Jin
 * @since 2016年12月31日
 */

@Repository
public class SendMail {
	
	//内部类
	class MailAuthenticator extends Authenticator {

		/**
		 * 用户名
		 */
		private String username;
		/**
		 * 密码
		 */
		private String password;

		/**
		 * 创建一个新的实例 MailAuthenticator.
		 * 
		 * @param username
		 * @param password
		 */
		public MailAuthenticator(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public String getPassword() {
			return password;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}

		public String getUsername() {
			return username;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void setUsername(String username) {
			this.username = username;
		}
	}
	public void sendMessage(SuccessRush successRush) throws Exception {
		// 配置信息
		Properties pro = new Properties();
		pro.put("mail.smtp.host", "smtp.163.com");
		pro.put("mail.smtp.auth", "true");
		// SSL加密
		MailSSLSocketFactory sf = null;
		sf = new MailSSLSocketFactory();
		// 设置信任所有的主机
		sf.setTrustAllHosts(true);
		pro.put("mail.smtp.ssl.enable", "true");
		pro.put("mail.smtp.ssl.socketFactory", sf);
		// 根据邮件的会话属性构造一个发送邮件的Session，这里需要注意的是用户名那里不能加后缀，否则便不是用户名了
		// 还需要注意的是，这里的密码不是正常使用邮箱的登陆密码，而是客户端生成的另一个专门的授权码
		MailAuthenticator authenticator = new MailAuthenticator("luojin_mail",
				"lj6234466");
		Session session = Session.getInstance(pro, authenticator);
		// 根据Session 构建邮件信息
		Message message = new MimeMessage(session);
		// 创建邮件发送者地址
		Address from = new InternetAddress("luojin_mail@163.com");
		// 设置邮件消息的发送者
		message.setFrom(from);
		// 验证收件人邮箱地址
		List<String> toAddressList = new ArrayList<>();
		toAddressList.add(successRush.getUserMail());
		StringBuffer buffer = new StringBuffer();
		if (!toAddressList.isEmpty()) {
			String regEx = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern p = Pattern.compile(regEx);
			for (int i = 0; i < toAddressList.size(); i++) {
				Matcher match = p.matcher(toAddressList.get(i));
				if (match.matches()) {
					buffer.append(toAddressList.get(i));
					if (i < toAddressList.size() - 1) {
						buffer.append(",");
					}
				}
			}
		}
		String toAddress = buffer.toString();
		if (!toAddress.isEmpty()) {
			// 创建邮件的接收者地址
			Address[] to = InternetAddress.parse(toAddress);
			// 设置邮件接收人地址
			message.setRecipients(Message.RecipientType.TO, to);
			// 邮件主题
			message.setSubject("恭喜抢购成功");
			// 邮件容器
			MimeMultipart mimeMultiPart = new MimeMultipart();
			// 设置HTML
			BodyPart bodyPart = new MimeBodyPart();
			// 邮件内容
			String htmlText = successRush.getUserMail() + "你已经成功抢购电影票   请尽快付款！";
			bodyPart.setContent(htmlText, "text/html;charset=utf-8");
			mimeMultiPart.addBodyPart(bodyPart);
			message.setContent(mimeMultiPart);
			message.setSentDate(new Date());
			// 保存邮件
			message.saveChanges();
			// 发送邮件
			Transport.send(message);
		}
	}
}