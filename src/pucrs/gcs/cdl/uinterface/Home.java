package pucrs.gcs.cdl.uinterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Home implements Initializable {
	
	@FXML
	private Button btnRegistrarEntrada;
	
	@FXML
	private Button btnRegistrarSaida;
	
	@FXML
	private Button btnExportar;
	
	@FXML
	private Label lblClientesNoBar;
	
	@FXML
	private Label lblPorcentagemMulheres;
	
	@FXML
	private Label lblPorcentagemSocios;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		updateStats();
	}
	
	public void updateStats() {
		getClientesNoBar();
		getPorcentagemMulheres();
		getPorcentagemSocios();
	}
	
	public void getClientesNoBar() {
		
	}
	
	public void getPorcentagemMulheres() {
		
	}
	
	public void getPorcentagemSocios() {
		
	}
	
	
	
}
