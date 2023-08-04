package com.shopme.customer;

import com.shopme.Utility;
import com.shopme.common.exception.CustomerNotFoundException;
import com.shopme.setting.EmailSettingBag;
import com.shopme.setting.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {

    @Autowired
    CustomerService customerService;
    @Autowired
    SettingService settingService;

    @GetMapping("/forgot_password")
    public String showRequestForm() {
        return "customer/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String showRequestForm(HttpServletRequest request, Model model){
        String email = request.getParameter("email");
        try {
            String token = customerService.updateRessetPasswordToken(email);
            String link = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(link, email);

            model.addAttribute("message", "We have sent a reset password link to your email."
                    + " Please check.");
        }catch (CustomerNotFoundException e){
            model.addAttribute("error", e.getMessage());
        }catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Could not send email");
        }
        return "customer/forgot_password_form";
    }

    private void sendEmail(String link, String email)
            throws MessagingException, UnsupportedEncodingException {
        EmailSettingBag emailSettings = settingService.getEmailSettings();
        JavaMailSenderImpl mailSender = Utility.prepareMailSender(emailSettings);

        String toAdrress = email;
        String subject = "Here is the link to reset your password";

        String content = "<p>Hello, </p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password: </p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p> "
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setFrom(emailSettings.getFromAddress(), emailSettings.getSenderName());
        mimeMessageHelper.setTo(toAdrress);
        mimeMessageHelper.setSubject(subject);

        mimeMessageHelper.setText(content,true);
        mailSender.send(mimeMessage);
    }
}
