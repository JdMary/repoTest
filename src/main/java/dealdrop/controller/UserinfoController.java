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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserinfoController implements Initializable {
    @FXML
    private TextField userfirstnameTF;
    @FXML
    private TextField userlastnameTF;
    @FXML
    private TextField useraddressTF;
    @FXML
    private  TextField userphoneTF;
    @FXML
    private Button savechnagesBUTTON;
    @FXML
    private Button backButton,logoutBUTTON;

    @FXML
    public void setFields()
    {
        if(UserSession.getSession()!=null && UserSession.getSession().getUser()!=null) {
            userfirstnameTF.setText(UserSession.getSession().getUser().getFirstName());
            userlastnameTF.setText(UserSession.getSession().getUser().getLastName());
            useraddressTF.setText(UserSession.getSession().getUser().getAddress());
            String userPhone = String.valueOf(UserSession.getSession().getUser().getPhone());
            userphoneTF.setText(userPhone);

        }
        else {
            UserSession.setSession(new User());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       this.setFields();
    }
    @FXML
    public void redirectToHome()
    {
        UIManager.getOuterPage("principal").swapInnerPage("content");

    }
    @FXML
    public void update()
    {
        try {
            if(userfirstnameTF.getText().isEmpty() || userlastnameTF.getText().isEmpty()||useraddressTF.getText().isEmpty()
            || userphoneTF.getText().isEmpty())
            {
                Validator.alert("fields should not be blank");
            }
            else {
                if (userphoneTF.getText().length() != 8 || !userphoneTF.getText().matches("[0-9]+")) {
                    Validator.alert("Enter a valid phone number");
                }
                else {
                    UserService us = new UserService();
                    User user = UserSession.getSession().getUser();
                    user.setFirstName(userfirstnameTF.getText());
                    user.setLastName(userlastnameTF.getText());
                    user.setAddress(useraddressTF.getText());
                    String phone = userphoneTF.getText();
                    int userphone = Integer.parseInt(phone);
                    user.setPhone(userphone);
                    us.updateUser(user, user.getEmail());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void logout(ActionEvent actionEvent) {
        System.out.println("session:" +UserSession.getSession());
        UserSession.clearSession();
        System.out.println("session:" +UserSession.getSession());
        UIManager.displayPage("signin");


    }
}
