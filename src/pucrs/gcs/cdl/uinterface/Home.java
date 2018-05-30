package pucrs.gcs.cdl.uinterface;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
		lblPorcentagemMulheres.setText(String.format("%.1f", BarController.consultaPorcentagemFeminino()));
	}
	
	public void getPorcentagemSocios() {
		lblPorcentagemSocios.setText(String.format("%.1f", BarController.consultaPorcentagemSocios()));
	}
	
	public void registrarEntrada(ActionEvent e) {
		try {
			Parent s = FXMLLoader.load(getClass().getResource("RegistrarEntrada.fxml"));
			btnRegistrarEntrada.getScene().setRoot(s);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void registrarSaida(ActionEvent e) {
		try {
			Parent s = FXMLLoader.load(getClass().getResource("RegistrarSaida.fxml"));
			btnRegistrarSaida.getScene().setRoot(s);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void exportarArquivo(ActionEvent e) {
		FileChooser dialog = new FileChooser();
		dialog.setTitle("Salvar arquivo");
		dialog.setInitialFileName("bar_clientes.csv");
		dialog.setSelectedExtensionFilter(new ExtensionFilter("Arquivo CSV", ".csv"));
		File file = dialog.showSaveDialog(btnExportar.getScene().getWindow());
		if(file != null)
			BarController.exportaClientes(file);
	}
	
}
