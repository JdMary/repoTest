package controller;


import entity.User;
import javafx.scene.text.Text;
import manager.UIManager;
import service.UserService;
import session.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserinfoController implements Initializable {
    @FXML
    public Text firstnameError, lastnameError,addressError,phoneError;
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
                errorMessage();
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
                    if(errorMessage()==true)
                    {us.updateUser(user, user.getEmail());}

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
    public boolean errorMessage(){
        int error=0;
        if(userfirstnameTF.getText()==null)
        {
            System.out.println("hh");
            firstnameError.setText("You need to fill First Name Field");
            error+=1;
        }
        else if(userfirstnameTF.getText() != null)
            firstnameError.setText("");

        if(userlastnameTF.getText().isEmpty())
        {
            lastnameError.setText("You need to fill last name field");
            error+=1;
        }
        else if(!userlastnameTF.getText().isEmpty())
            lastnameError.setText("");
        if(useraddressTF.getText().isEmpty())
        {
            addressError.setText("You need to fill address field");
            error+=1;
        }
        else if(!useraddressTF.getText().isEmpty())
            addressError.setText("");
        if(userphoneTF.getText().isEmpty())
        {
            phoneError.setText("You need to fill phone field");
            error+=1;
        }
        else if(!userphoneTF.getText().isEmpty())
            phoneError.setText("");

        return error > 0;
    }
}
