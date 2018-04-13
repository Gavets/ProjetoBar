package Visual;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {

    // Tem coisa pior do que ser obrigado a programar em java?
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("visual.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Bar Project");
        stage.setResizable(false);
        stage.show();

    }
}
