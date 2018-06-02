package pucrs.gcs.cdl.uinterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import pucrs.gcs.cdl.business.BarController;
import pucrs.gcs.cdl.business.CPF;
import pucrs.gcs.cdl.business.Cliente;

public class RegistrarEntrada implements Initializable{

	protected enum Genero {
		MASCULINO("Masculino"),
		FEMININO("Feminino");

		private String label;

		Genero(String label) {
			this.label = label;
		}

		public String toString() {
			return label;
		}

		public boolean toBoolean() {
			return label.equals(FEMININO.toString());
		}
	}

	protected enum Socio {
		TRUE("Sim"),
		FALSE("Não");

		private String label;

		Socio(String label) {
			this.label = label;
		}

		public String toString() {
			return label;
		}

		public boolean toBoolean() {
			return label.equals(TRUE.toString());
		}
	}

	@FXML
	private TextField iptCpf;

	@FXML
	private TextField iptNome;

	@FXML
	private TextField iptIdade;

	@FXML
	private ComboBox<String> cbxGenero;

	@FXML
	private ComboBox<String> cbxSocio;

	@FXML
	private TextField iptNumSocio;

	@FXML
	private Button btnRegistrar;

	@FXML
	private Button btnVoltar;

	private boolean isRegistering;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		isRegistering = false;
		cbxGenero.getItems().setAll(Genero.MASCULINO.toString(), Genero.FEMININO.toString());
		cbxSocio.getItems().setAll(Socio.TRUE.toString(), Socio.FALSE.toString());
	}

	public void validaCpf(KeyEvent event) {
		String cpf = iptCpf.textProperty().get();

		if(cpf.length() > 11) {
			iptCpf.setText(cpf.substring(0, 11));
			iptCpf.positionCaret(11);
		}

		if (cpf.length() == 11 && !CPF.isValidCPF(cpf)){
			iptCpf.setText("");

			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("CPF invalido");
			alerta.setContentText("CPF invalido, por favor digite um CPF valido ou retorna a tela anterior.");
			alerta.showAndWait();
			return;
		}

		else if(!isRegistering && cpf.length() == 11) {
			if(!BarController.clienteExists(cpf)) {
				isRegistering = true;
				showRegisterControls();
			} else {
				BarController.clienteEntra(cpf);
			}
		}
	}

	public void handleBtnRegistrar(ActionEvent event) {
		if(isRegistering) {
			Cliente cliente = new Cliente(
					iptNome.getText(),
					Integer.parseInt(iptIdade.getText()),
					iptCpf.getText(),
					cbxGenero.getValue().equals("Feminino"),
					cbxSocio.getValue().equals("Sim"),
					iptNumSocio.getText()
			);
			BarController.cadastraCliente(cliente);
			BarController.clienteEntra(cliente);
		} else {
			BarController.clienteEntra(iptCpf.getText());
		}

		handleBtnVoltar(event);
	}

	public void handleBtnVoltar(ActionEvent event) {
		try {
			Parent s = FXMLLoader.load(getClass().getResource("Home.fxml"));
			btnVoltar.getScene().setRoot(s);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void showRegisterControls() {
		iptNome.setVisible(true);
		iptIdade.setVisible(true);
		cbxGenero.setVisible(true);
		cbxSocio.setVisible(true);
		iptNumSocio.setVisible(true);
	}

}
