package controller;

import entity.User;
import manager.UIManager;
import service.UserService;
import session.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
            System.out.println("whta"+UserSession.getSession());
            UserinfoController info=(UserinfoController) UIManager.getOuterPage("principal").getInnerPage("profile").getController();
            if(user.getRoles().equals("[\"ROLE_MEMBER\"]"))
            {
                info.setFields();
            }
            if(user.getRoles().equals("[\"ROLE_ADMIN\"]"))
            {
                AdminController list=(AdminController) UIManager.getOuterPage("principal").getInnerPage("admin").getController();
                list.initialize();
                UIManager.getOuterPage("principal").swapInnerPage("admin");
            }
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
        UIManager.displayPage("forgotpassword");
    }
}
