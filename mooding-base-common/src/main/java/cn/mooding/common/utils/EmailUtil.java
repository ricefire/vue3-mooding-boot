package cn.mooding.common.utils;


import cn.mooding.common.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 邮件工具类
 * @Author BlueFire
 * @Date 26/8/2021 -上午7:05
 */
@Component
public class EmailUtil {
    /**
     * 获取JavaMailSender bean
     */
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 获取配置文件的username
     */
    @Value("${spring.mail.username}")
    private String username;

    public void sendSimpleMail(String to, HttpServletRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        //设定邮件参数
        //发送者
        message.setFrom(username);
        //接收者
        message.setTo(to);
        //主题
        message.setSubject("修改系统用户邮箱");
        //邮件内容
        // 验证码
        String code = StringUtils.codeGen(4);
        message.setText("【系统】" + "验证码:" + code + "。" + "你正在使用邮箱验证码修改功能，该验证码仅用于身份验证，请勿透露给他人使用");
        // 发送邮件
//        javaMailSender.send(message);
        request.getSession().setAttribute("MAIL", code.toLowerCase());
    }
    public void sendMail(String to, String mailText, Map<String ,String> obj) {
        SimpleMailMessage message = new SimpleMailMessage();
        //设定邮件参数
        //发送者
        message.setFrom(username);
        //接收者
        message.setTo(to);
        //主题
        message.setSubject("修改系统用户邮箱");
        //邮件内容
        // 验证码
        for(String str:obj.keySet()){
            String newStr=obj.get(str);
            mailText=  mailText.replace("<"+str+">",newStr);
        }
        message.setText(mailText);
        // 发送邮件
        javaMailSender.send(message);
    }
}
