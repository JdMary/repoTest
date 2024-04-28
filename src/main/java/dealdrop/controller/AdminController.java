package dealdrop.controller;

import dealdrop.entity.User;
import dealdrop.manager.UIManager;
import dealdrop.service.AdminService;
import dealdrop.service.UserService;
import dealdrop.session.UserSession;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController {
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnLogout;
    @FXML
    private ListView<User> listView;
    private  UserSession userSession;
    private List<User> users=new ArrayList<>();

    public Button getBtnLogout() {
        return btnLogout;
    }

    public void setBtnLogout(Button btnLogout) {
        this.btnLogout = btnLogout;
    }

    public ListView<User> getListView() {
        return listView;
    }

    public void setListView(ListView<User> listView) {
        this.listView = listView;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public void initialize() {
        System.out.println("in initialize");
        btnDelete.setVisible(false);
        userSession = UserSession.getSession();
        // User user = userSession.getUser();
        // User userCell = new User();
        UserService userService = new UserService();
        this.setUsers(userService.selectAllUsers());
        System.out.println("select");
        EventHandler<MouseEvent> customEvent = (event2) -> {
            if (event2.getClickCount() == 1) {
                setUser(this.getListView().getSelectionModel().getSelectedItem());
                System.out.println("ena fel USERRRRS  = "+ getUser());
                btnDelete.setVisible(true);
            }
        };

        AdminService.listObjectsOn_listView(listView, users, customEvent);
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        userSession.clearSession();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);
        stage.show();
        System.out.println("ena fel logout      session  = "+ UserSession.getSession());


    }


    @FXML
    public void delete(ActionEvent actionEvent) {
        UserService userService = new UserService();
        userService.deleteUser(getUser().getEmail());
        initialize();
        UIManager.displayPage("principal");
    }


}
