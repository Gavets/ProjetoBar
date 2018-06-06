package pucrs.gcs.cdl.uinterface;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import pucrs.gcs.cdl.business.BarController;
import pucrs.gcs.cdl.business.Cliente;

public class RegistrarSaida implements Initializable {
	
	@FXML
	private TextField iptCpf;
	
	@FXML
	private TableView<Cliente> tvwClientesBar;
	@FXML
	private TableColumn<Cliente, String> tclClientesNome;
	@FXML
	private TableColumn<Cliente, String> tclClientesIdade;
	@FXML
	private TableColumn<Cliente, String> tclClientesCpf;
	@FXML
	private TableColumn<Cliente, String> tclClientesGenero;
	@FXML
	private TableColumn<Cliente, String> tclClientesSocio;
	@FXML
	private TableColumn<Cliente, String> tclClientesNumSocio;
	
	
	@FXML
	private Button btnConfirmar;
	
	@FXML
	private Button btnVoltar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buildTableClientes();
	}
	
	public void buildTableClientes() {
		tclClientesCpf = new TableColumn<>("CPF");
		tclClientesCpf.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cliente,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getCpf());
			}
		});
		
		tclClientesNome = new TableColumn<>("Nome");
		tclClientesNome.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cliente,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getNome());
			}
		});
		
		tclClientesIdade = new TableColumn<>("Idade");
		tclClientesIdade.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cliente,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(String.valueOf(param.getValue().getIdade()));
			}
		});
		
		tclClientesGenero = new TableColumn<>("Gênero");
		tclClientesGenero.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cliente,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getGenero() ? "Feminino" : "Masculino");
			}
		});
		
		tclClientesSocio = new TableColumn<>("Sócio");
		tclClientesSocio.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cliente,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().isSocio() ? "Sim" : "Não");
			}
		});
		
		
		tclClientesNumSocio = new TableColumn<>("Número sócio");
		tclClientesNumSocio.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cliente,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getNumSocio());
			}
		});
		
		tvwClientesBar.getColumns().setAll(Arrays.asList(tclClientesCpf, tclClientesNome, tclClientesIdade, tclClientesGenero, tclClientesSocio, tclClientesNumSocio));
		
		ObservableList<Cliente> listClientes = FXCollections.observableArrayList();
		List<Cliente> clientes = BarController.consultaClientesNoBar();
		clientes.forEach(c -> listClientes.add(c));
		
		FilteredList<Cliente> filteredListClientes = new FilteredList<>(listClientes, c -> true);
		iptCpf.textProperty().addListener((observalue, oldValue, newValue) -> {
			filteredListClientes.setPredicate(c -> {
				if(newValue == null || newValue.isEmpty())
					return true;
				
				if(c.getCpf().startsWith(newValue))
					return true;
				
				return false;
			});
		});
		
		tvwClientesBar.setItems(filteredListClientes);	
	}

	public void handleBtnConfirmar(ActionEvent event) {
		if(tvwClientesBar.getSelectionModel().getSelectedItem()==null){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Nenhum cliente foi selecionado para sair!");
			alert.setTitle("ERRO");
			alert.showAndWait();
		}
		else {
			BarController.clienteSai(tvwClientesBar.getSelectionModel().getSelectedItem().getCpf());
			buildTableClientes();
		}

	}
	
	public void handleBtnVoltar(ActionEvent event) {
		try {
			Parent s = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));
			btnVoltar.getScene().setRoot(s);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
