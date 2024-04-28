package controller;


import entity.User;
import manager.UIManager;
import service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    @FXML
    private TextField useremailTF;
    @FXML
    private TextField userfirstnameTF;
    @FXML
    private TextField userlastnameTF;
    @FXML
    private TextField userphoneTF;
    @FXML
    private TextField usercinTF;
    @FXML
    private DatePicker userdobDP;
    @FXML
    private TextField useraddressTF;
    @FXML
    private PasswordField userpasswordPF;
    @FXML
    private Button signupBUTTON;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void signup() {
            if(useremailTF.getText().isEmpty() || userpasswordPF.getText().isEmpty() ||
                userfirstnameTF.getText().isEmpty() || userlastnameTF.getText().isEmpty() ||
                userphoneTF.getText().isEmpty() ||  usercinTF.getText().isEmpty() ||
                userdobDP.getValue()==null || useraddressTF.getText().isEmpty() || userpasswordPF.getText().isEmpty()
        ){
            Validator.alert("Please fill all the fields");
        }
        if(!Validator.isEmail(useremailTF.getText()))
        {
            Validator.alert("The email format is invalid");
        }
        if (userphoneTF.getText().length() != 8 || !userphoneTF.getText().matches("[0-9]+")) {
            Validator.alert("Enter a valid phone number");
        }
        if(!usercinTF.getText().matches("[0-9]+") || userphoneTF.getText().length() != 8 )
        {
            Validator.alert("Enter a valid cin number");
        }
        if (userpasswordPF.getText().length() < 6) {
           Validator.alert("Password must be at least 6 caracters");
        }


        User user = new User();
        UserService userService = new UserService();
        user.setEmail(useremailTF.getText());
        user.setFirstName(userfirstnameTF.getText());
        user.setLastName(userlastnameTF.getText());
        user.setAddress(useraddressTF.getText());
        String cinString = usercinTF.getText();
        String phoneString = userphoneTF.getText();

        int userCin = Integer.parseInt(cinString);
        int userPhone = Integer.parseInt(phoneString);
        user.setCin(userCin);
        user.setPhone(userPhone);

         LocalDate localDate = userdobDP.getValue(); // Get the selected date from the DatePicker
         Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault())); // Convert LocalDate to Instant
         Date date = Date.from(instant); // Convert Instant to Date

         user.setBirthDate(date);
         user.setJoiningDate(new Date());
         user.setRoles("[\"ROLE_MEMBER\"]");
         user.setType("Member");
         user.setVerified(false);
         user.setPassword(userpasswordPF.getText());
         if(userService.searchUser(user.getEmail())!=null)
         {
             Validator.alert("This email is already used");
         }else
         {
             userService.createUser(user);
             UIManager.displayPage("signin");
         }

    }

    public void loadLogin(){

        UIManager.displayPage("signin");
    }

}
