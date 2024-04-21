package dealdrop.page;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class OuterPage<T> extends InnerPage<T> {
    private ArrayList<InnerPage<?>> innerPages = new ArrayList<>();
    private AnchorPane container;
    public OuterPage(){}
    public OuterPage(String fxmlPath, String name, String container) throws IOException {
        super(fxmlPath,name);
        this.container = (AnchorPane) root.lookup(container);
    }
    public void addPage(InnerPage<?>... inners){
        innerPages.addAll(Arrays.asList(inners));
    }
    public void swapInnerPage(String name){
        Optional<InnerPage<?>> desiredPage = innerPages.stream().filter((page)->page.getName().equals(name)).findFirst();
        if(desiredPage.isPresent()){
            container.getChildren().clear();
            container.getChildren().add(desiredPage.get().getRoot());
        }
    }
    public InnerPage<?> getInnerPage(String name){
        Optional<InnerPage<?>> desiredPage = innerPages.stream().filter((page)->page.getName().equals(name)).findFirst();
        if(desiredPage.isPresent()){
            return desiredPage.get();
        }
        return null;
    }
}
