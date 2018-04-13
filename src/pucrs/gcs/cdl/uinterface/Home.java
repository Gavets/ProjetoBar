package pucrs.gcs.cdl.uinterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pucrs.gcs.cdl.business.BarController;

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
		getClientesNoBarCount();
		getPorcentagemMulheres();
		getPorcentagemSocios();
	}
	
	public void getClientesNoBar() {
		BarController.consultaClientesNoBar();
	}
	
	public void getClientesNoBarCount() {
		lblClientesNoBar.setText(String.valueOf(BarController.consultaTotalClientesNoBar()));
	}
	
	public void getPorcentagemMulheres() {
		lblPorcentagemMulheres.setText(String.valueOf(BarController.consultaPorcentagemFeminino()));
	}
	
	public void getPorcentagemSocios() {
		lblPorcentagemSocios.setText(String.valueOf(BarController.consultaPorcentagemSocios()));
	}
	
	
	
}
