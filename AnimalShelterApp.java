import javafx.application.Application;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import java.util.*;

public class AnimalShelterApp extends Application
{
    private ObservableList<Pet> observable;
    private LinkedList<Pet> adoptedPets = new LinkedList<>();
    public void start(Stage mainStage)
    {
        // Create a new border pane
        BorderPane root = new BorderPane();
        root.setStyle("-fx-font-size: 15;");
        
        // Set the scene
        Scene scene = new Scene(root, 450,500);
        mainStage.setTitle("Animal Shelter");
        mainStage.setScene(scene);
        
        // Initialize our shelter
        AnimalShelter shelter = new AnimalShelter();
        observable = shelter.toObservableList();
        
        // Set up the main layout
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        root.setCenter(mainLayout);
        
        // Create the table
        TableView<Pet> table = new TableView();
        mainLayout.getChildren().add(table);
        table.setItems(observable);
        
        // Name Column
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        nameCol.setMinWidth(80);
        table.getColumns().add(nameCol);
        
        // Name Tag # Column
        TableColumn nameTagCol = new TableColumn("Name Tag #");
        nameTagCol.setCellValueFactory(new PropertyValueFactory("nameTag"));
        nameTagCol.setMinWidth(80);
        table.getColumns().add(nameTagCol);
        
        // Type Column
        TableColumn typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory("type"));
        typeCol.setMinWidth(80);
        table.getColumns().add(typeCol);
        
        // remove extra columns/rows
        table.setMaxWidth(244);
        table.setMaxHeight(490);
        
        // Set the menu for functionality
        MenuBar topMenu = new MenuBar();
        root.setTop(topMenu);
        
        // File menu
        Menu fileMenu = new Menu("File");
        topMenu.getMenus().add(fileMenu);
        
        // Menu item - adopt a pet from the shelter
        MenuItem adoptItem = new MenuItem("Adopt a Pet");
        adoptItem.setOnAction((event->{
            int nameTag = shelter.enterNameTag();
            Pet p = shelter.adopt(nameTag, adoptedPets);
            if (p == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Pet Not Found");
                alert.setHeaderText("Pet Not Found");
                alert.setContentText("There is no pet with the name tag #: " + nameTag);
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pet Adopted");
                alert.setHeaderText("Pet Adopted");
                alert.setContentText("Pet Adopted Successfully: " + p.toString());
                alert.showAndWait();
            }
            observable.remove(p);
            table.refresh();
        }));
        
        // Menu item - surrender a pet already adopted
        MenuItem surrenderItem = new MenuItem("Surrender an Adopted Pet");
        surrenderItem.setOnAction((event->{
            int nameTag = shelter.enterNameTag();
            Pet p = shelter.surrender(nameTag, adoptedPets);
            if (p == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Pet Not Found");
                alert.setHeaderText("Pet Not Found");
                alert.setContentText("There is no pet with the name tag #: " + nameTag);
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pet Surrendered");
                alert.setHeaderText("Pet Surrendered");
                alert.setContentText("Pet Surrendered to Shelter Successfully: " + p.toString());
                alert.showAndWait();
            }
            observable.add(p);
            table.refresh();
        }));
        
        // Menu item - view all pets currently adopted
        MenuItem viewItem = new MenuItem("View all Currently Adopted Pets");
        viewItem.setOnAction((event->
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Currently Adopted Pets");
            alert.setHeaderText("Currently Adopted Pets");
            if (!adoptedPets.isEmpty()) {
                Iterator<Pet> iter  = adoptedPets.iterator();
                String pets = "";
                while (iter.hasNext())
                {
                    pets += iter.next().toString() + "; ";
                }
                alert.setContentText(pets);
            }
            else
            {
                alert.setContentText("You have no currently adopted pets.");
            }
            alert.showAndWait();
        }));
        
        // Menu item - Quit
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction((e->
        {
            mainStage.close();
        }));
        fileMenu.getItems().addAll(adoptItem, surrenderItem, viewItem, quitItem);
        // Show stage once everything is processed
        mainStage.show();
    }
    public static void main(String[] args)
    {
        
    }
}
