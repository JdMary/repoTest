package manager;

import controller.*;
import page.InnerPage;
import page.OuterPage;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class UIManager {
    private static Stage stage;
    private static Scene scene;
    private static ArrayList<OuterPage<?>> outerPages = new ArrayList<>();
    private static UIManager uiManager = null;
    public UIManager(){}
    private UIManager(Stage s){
        stage = s;
        stage.setTitle("hello");

    }
    public static UIManager create(Stage s){
        if(uiManager == null){
            uiManager = new UIManager(s);
        }
        return uiManager;
    }
    public static void initialize() throws IOException {
        OuterPage<MainPageController> pagePrincipal = new OuterPage<>("/dealdrop/GUI/dealdrop-view.fxml","principal", "#layout");
        InnerPage<Object> content1 = new InnerPage<>("/dealdrop/GUI/content1.fxml", "content1") ;
        InnerPage<Object> content2 = new InnerPage<>("/dealdrop/GUI/content2.fxml", "content2") ;
        InnerPage<UserinfoController> userProfile=new InnerPage<>("/dealdrop/GUI/userinfo.fxml","profile");
        InnerPage<AdminController> adminDashboard=new InnerPage<>("/dealdrop/GUI/admin.fxml","admin");
        pagePrincipal.addPage(content1,content2);
        OuterPage<SigninController> signinpage = new OuterPage<>("/dealdrop/GUI/signin.fxml","signin", " ");
        OuterPage<SignupController> signuppage = new OuterPage<>("/dealdrop/GUI/signup.fxml","signup", " ");
        pagePrincipal.addPage(userProfile);
        pagePrincipal.addPage(adminDashboard);
        outerPages.add(pagePrincipal);
        outerPages.add(signinpage);
        outerPages.add(signuppage);
        scene = new Scene(pagePrincipal.getRoot());
        stage.setScene(scene);
    }
    public static void displayPage(String name){
        Optional<OuterPage<?>> desiredPage = outerPages.stream().filter((page)->page.getName().equals(name)).findFirst();
        if(desiredPage.isPresent()){
            scene.setRoot(desiredPage.get().getRoot());
        }
    }
    public static OuterPage<?> getOuterPage(String name){
        Optional<OuterPage<?>> desiredPage = outerPages.stream().filter((page)->page.getName().equals(name)).findFirst();
        if(desiredPage.isPresent()){
            return desiredPage.get();
        }
        return null;
    }
    public static void show(){
        stage.show();
    }
}
