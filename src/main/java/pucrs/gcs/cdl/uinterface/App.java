package pucrs.gcs.cdl.uinterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import pucrs.gcs.cdl.business.BarController;
import pucrs.gcs.cdl.business.Cliente;

public class App extends Application {
	public static void main(String[] args) {		
		List<Cliente> cs = csvImport();
		for(Cliente c : cs)
			BarController.cadastraCliente(c);

		for(int i = 0; i < 5; i++)
			BarController.clienteEntra(cs.get(i));
		
		launch(args);
	}

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Bar Project");
        stage.setResizable(false);
        stage.show();

    }
	
	public static List<Cliente> csvImport() {
		List<Cliente> clientes = new ArrayList<>();
		Path csvFile = Paths.get(System.getProperty("user.dir"), "db", "nomes.csv");
		try {
			List<String> lines = Files.readAllLines(csvFile);
			for(int i = 1; i < 11; i++) {
				String[] line = lines.get(i).split(",");
				clientes.add(new Cliente(line[2], Integer.parseInt(line[3]), line[4], (line[1].equals("true"))));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clientes;
	}

}
