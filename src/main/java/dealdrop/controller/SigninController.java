package dealdrop.controller;

import dealdrop.entity.User;
import dealdrop.manager.UIManager;
import dealdrop.service.UserService;
import dealdrop.session.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SigninController implements Initializable {
    @FXML
    private TextField useremailTF;
    @FXML
    private PasswordField userpasswordPF;
    @FXML
    private Button signinBUTTON;
    @FXML
    private  Button signupBUTTON;
    @FXML
    private Hyperlink forgotpasswordLINK;
    public void signin()
    {
        if(useremailTF.getText().isEmpty() || userpasswordPF.getText().isEmpty()){
            Validator.alert("Please fill all the fields");
        }
        if(!Validator.isEmail(useremailTF.getText()))
        {
            Validator.alert("The email format is invalid");
        }
        User user=new User();
        UserService userService=new UserService();
        String email=useremailTF.getText();
        String password =userpasswordPF.getText();
        user=userService.login(email,password);
        System.out.println(user.getEmail());
        if(user!=null) {

            UserSession.setSession(user);
            UserinfoController info=(UserinfoController) UIManager.getOuterPage("principal").getInnerPage("profile").getController();
            info.setFields();
            UIManager.displayPage("principal");
        }


    }
    public void loadHome()
    {
        UIManager.displayPage("principal");
    }
    public void redirectToSignUP()
    {
        UIManager.displayPage("signup");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void forgotPassword(ActionEvent actionEvent) {
        UIManager.getOuterPage("forgotpassword");
    }
}
