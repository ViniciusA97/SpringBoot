package com.example.projeto.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MailSenderTest {




    private JavaMailSender javaMailSender;

    @Autowired
    public MailSenderTest( JavaMailSender  javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    public void sendEmail(String email, String senha) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setFrom("viniaq123@gmail.com");
        msg.setSubject("Senha");
        msg.setText("Olá, aqui está sua senha : "+senha);
        javaMailSender.send(msg);
    }

}
