package controller;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import manager.UIManager;
import service.EmailSendHtmlMsg;
import service.SmtpEmail;
import service.UserService;
import session.UserSession;

import javax.mail.MessagingException;
import javax.mail.UIDFolder;

public class ForgotPasswordController {
    @FXML
    private Button btnEnvoyer;

    @FXML
    private TextField tfMail;

    @FXML
    void resetForgotPasswordEmail(ActionEvent event)throws MessagingException {
        UserService us=new UserService();
        if(tfMail.getText()!=null) {
            User user=us.searchUser(tfMail.getText());
            if(user!=null)
            {
                UserSession.setSession(user);
                SmtpEmail smtpEmail=new SmtpEmail();
                int code = (int) (Math.random() * 9999);
                user.setResetToken(""+code);
                us.updateUser(user, user.getEmail());
                String html = EmailSendHtmlMsg.htmlResetPassword(user.getFirstName(),code,"DEALDROP");
                smtpEmail.sendEmail(new String[]{tfMail.getText()},"reset Password", "" ,html);

            }
            else
            {
                System.out.println("Uncorrect Email");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Uncorrect Email");
                alert.show();
            }


        }
        else
        {
            System.out.println("Empty Field");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("PLease fill the field to reset the password");
            alert.show();
        }
    }
}
