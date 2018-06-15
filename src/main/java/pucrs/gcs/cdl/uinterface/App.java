package pucrs.gcs.cdl.uinterface;

/*import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;*/
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
		encheBar();
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
	
	/*public static List<Cliente> csvImport() {
		List<Cliente> clientes = new ArrayList<>();
		Path csvFile = Paths.get(System.getProperty("user.dir"), "db", "nomes.csv");
		try {
			List<String> lines = Files.readAllLines(csvFile);
			for(int i = 1; i < 11; i++) {
				String[] line = lines.get(i).split(",");
				clientes.add(new Cliente(line[2], Integer.parseInt(line[3]), line[4], (line[1].equals("true"))));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return clientes;
	}*/

	public static void encheBar() {
		List<Cliente> cs = new ArrayList<>();
		cs.add(new Cliente("Carla Alves", 29, "54581431247", true, false, ""));
		cs.add(new Cliente("Ágatha Lima", 24, "99572838474", true, true, "3997"));
		cs.add(new Cliente("Gabriel Oliveira", 29, "67926837601", false, true, "6024"));
		cs.add(new Cliente("Bianca Azevedo", 68, "23035700346", true, true, "3193"));
		cs.add(new Cliente("Isabelle Carvalho", 60, "58347099073", true, false, ""));
		cs.add(new Cliente("Paulo Ferreira", 80, "63157795813", false, false, ""));
		cs.add(new Cliente("Murilo Carvalho", 65, "41702199967", false, false, ""));
		cs.add(new Cliente("Maria Pereira", 58, "21580632033", true, true, "3270"));
		cs.add(new Cliente("Tomás Cunha", 81, "95970753777", false, true, "1912"));
		cs.add(new Cliente("Murilo Castro", 39, "70558679242", false, true, "1840"));
		cs.add(new Cliente("Mariana Carvalho", 80, "17239833305", true, true, "6309"));
		cs.add(new Cliente("Brenda Pinto", 68, "17816401302", true, false, ""));
		cs.add(new Cliente("Thaís Silva", 47, "81461737648", true, true, "4491"));
		cs.add(new Cliente("Enzo Costa", 62, "90235912670", false, false, ""));
		cs.add(new Cliente("Julia Rocha", 52, "82503424457", true, false, ""));
		cs.add(new Cliente("Igor Barbosa", 62, "61571754784", false, true, "7751"));
		cs.add(new Cliente("Vitória Martins", 38, "27553825972", true, true, "7021"));
		cs.add(new Cliente("Vitoria Cavalcanti", 68, "11467597627", true, false, ""));
		cs.add(new Cliente("Evelyn Ribeiro", 25, "45077234698", true, false, ""));
		cs.add(new Cliente("Luis Ribeiro", 71, "87992286537", false, true, "9198"));
		cs.add(new Cliente("Emily Barros", 60, "49437899940", true, false, ""));
		cs.add(new Cliente("Anna Fernandes", 48, "87018321905", true, true, "9375"));
		cs.add(new Cliente("Rafael Ferreira", 67, "52018889095", false, true, "5206"));
		cs.add(new Cliente("Bianca Rocha", 61, "37437634945", true, true, "2764"));
		cs.add(new Cliente("Ana Barbosa", 67, "65507351147", true, false, ""));
		cs.add(new Cliente("Vinicius Ribeiro", 72, "13099725919", false, true, "9185"));
		cs.add(new Cliente("Clara Souza", 23, "67539507101", true, true, "2222"));
		cs.add(new Cliente("Ana Martins", 23, "34544579201", true, true, "6219"));
		cs.add(new Cliente("Pedro Santos", 49, "21930331703", false, true, "9496"));
		cs.add(new Cliente("Diogo Barbosa", 45, "14604485704", false, false, ""));
		cs.add(new Cliente("Luis Fernandes", 28, "34165586188", false, false, ""));
		cs.add(new Cliente("Matilde Ribeiro", 51, "26200765871", true, true, "6130"));
		cs.add(new Cliente("Bruno Correia", 41, "96915534652", false, true, "4570"));
		cs.add(new Cliente("Carlos Goncalves", 51, "67243286240", false, false, ""));
		cs.add(new Cliente("Gabriela Ferreira", 54, "92192100965", true, true, "9074"));
		cs.add(new Cliente("João Barros", 67, "44457269077", false, true, "2724"));
		cs.add(new Cliente("Diogo Gomes", 66, "63419073208", false, true, "4306"));
		cs.add(new Cliente("Bruno Oliveira", 75, "25289246908", false, false, ""));
		cs.add(new Cliente("Marina Rodrigues", 61, "92261063474", true, true, "8714"));
		cs.add(new Cliente("Lara Martins", 53, "84243516227", true, false, ""));
		cs.add(new Cliente("Fábio Silva", 42, "29035444760", false, true, "9980"));
		cs.add(new Cliente("Lucas Dias", 37, "54423883864", false, true, "4744"));
		cs.add(new Cliente("Yasmin Oliveira", 33, "93800311577", true, false, ""));
		cs.add(new Cliente("Kauê Azevedo", 21, "75968538112", false, true, "6810"));
		cs.add(new Cliente("Letícia Lima", 21, "63823090011", true, true, "7814"));
		cs.add(new Cliente("Erick Almeida", 31, "63738714952", false, true, "6334"));
		cs.add(new Cliente("Luís Carvalho", 72, "76274643079", false, true, "5422"));
		cs.add(new Cliente("Enzo Silva", 67, "73585036511", false, false, ""));
		cs.add(new Cliente("Eduarda Dias", 36, "84660530507", true, false, ""));
		cs.add(new Cliente("Danilo Cardoso", 53, "40265023092", false, true, "4174"));
		cs.add(new Cliente("Giovanna Rocha", 23, "66464049763", true, true, "9673"));
		cs.add(new Cliente("Enzo Ribeiro", 39, "56959697271", false, true, "8501"));
		cs.add(new Cliente("Matilde Barros", 78, "58390874164", true, true, "5396"));
		cs.add(new Cliente("Paulo Cunha", 84, "75959227711", false, false, ""));
		cs.add(new Cliente("Yasmin Rocha", 61, "47678124619", true, true, "9271"));
		cs.add(new Cliente("Alex Correia", 64, "82646914202", false, false, ""));
		cs.add(new Cliente("Antônio Souza", 44, "10199645108", false, false, ""));
		cs.add(new Cliente("Guilherme Alves", 29, "11230013679", false, false, ""));
		cs.add(new Cliente("Amanda Souza", 22, "78067725020", true, false, ""));
		cs.add(new Cliente("Yasmin Pinto", 69, "80178641405", true, false, ""));
		cs.add(new Cliente("Júlia Oliveira", 48, "14047400580", true, false, ""));
		cs.add(new Cliente("Cauã Alves", 51, "29088832951", false, false, ""));
		cs.add(new Cliente("Nicole Cunha", 36, "11093113030", true, true, "5609"));
		cs.add(new Cliente("Isabela Cavalcanti", 72, "63401190008", true, true, "3879"));
		cs.add(new Cliente("Bianca Barros", 38, "32358320501", true, false, ""));
		cs.add(new Cliente("Samuel Azevedo", 46, "98521916833", false, true, "6239"));
		cs.add(new Cliente("Luis Lima", 31, "96597703081", false, false, ""));
		cs.add(new Cliente("Isabelle Martins", 77, "28213700597", true, true, "2896"));
		cs.add(new Cliente("Gabriela Rocha", 38, "40505436566", true, true, "1873"));
		cs.add(new Cliente("Isabella Castro", 44, "43181126322", true, false, ""));
		cs.add(new Cliente("Vitor Ribeiro", 72, "27725679073", false, false, ""));
		cs.add(new Cliente("Gabriel Barros", 77, "70273215728", false, true, "6815"));
		cs.add(new Cliente("Raissa Silva", 41, "13434227601", true, false, ""));
		cs.add(new Cliente("José Silva", 65, "40476315743", false, true, "7883"));
		cs.add(new Cliente("Amanda Almeida", 41, "15138553526", true, true, "3610"));
		cs.add(new Cliente("Arthur Rodrigues", 56, "76166737327", false, false, ""));
		cs.add(new Cliente("Sarah Azevedo", 77, "88710957820", true, true, "5555"));
		cs.add(new Cliente("José Castro", 60, "77746076769", false, true, "5349"));
		cs.add(new Cliente("Vinicius Melo", 48, "41069624896", false, false, ""));
		cs.add(new Cliente("Julieta Carvalho", 57, "92971643409", true, true, "4401"));
		cs.add(new Cliente("Kauê Goncalves", 24, "86616062848", false, true, "2821"));
		cs.add(new Cliente("Nicolash Santos", 38, "11080749365", false, false, ""));
		cs.add(new Cliente("André Ferreira", 40, "65879813304", false, true, "5140"));
		cs.add(new Cliente("Ryan Pereira", 37, "36997050999", false, false, ""));
		cs.add(new Cliente("Marina Pereira", 25, "85366505674", true, true, "2002"));
		cs.add(new Cliente("Diego Gomes", 83, "82475360305", false, true, "1904"));
		cs.add(new Cliente("Mateus Alves", 46, "23762814589", false, true, "8486"));
		cs.add(new Cliente("Isabela Fernandes", 29, "70349152454", true, false, ""));
		cs.add(new Cliente("Matheus Araujo", 63, "10210633476", false, false, ""));
		cs.add(new Cliente("Larissa Santos", 83, "26341351810", true, true, "2264"));
		cs.add(new Cliente("Giovana Almeida", 57, "61632198991", true, true, "6847"));
		cs.add(new Cliente("Cauã Souza", 82, "63309095983", false, true, "4216"));
		cs.add(new Cliente("Giovanna Rodrigues", 50, "65334060944", true, true, "8622"));
		cs.add(new Cliente("Thaís Gomes", 46, "24678217819", true, false, ""));
		cs.add(new Cliente("Miguel Oliveira", 70, "75046312808", false, true, "4687"));
		cs.add(new Cliente("Julian Goncalves", 55, "77202432005", false, true, "7704"));
		cs.add(new Cliente("Vitória Rodrigues", 80, "40376349026", true, false, ""));
		cs.add(new Cliente("Luiza Dias", 82, "74046483601", true, false, ""));
		cs.add(new Cliente("João Barros", 83, "46717176628", false, true, "7220"));
		cs.add(new Cliente("Gustavo Pereira", 44, "34310561136", false, true, "5799"));
		cs.add(new Cliente("Vinicius Martins", 23, "86851322587", false, true, "1256"));
		cs.add(new Cliente("Rafael Pinto", 54, "91784562076", false, false, ""));
		cs.add(new Cliente("Arthur Almeida", 44, "37972045909", false, false, ""));
		cs.add(new Cliente("Melissa Santos", 50, "97101847331", true, true, "9720"));
		cs.add(new Cliente("Diogo Ribeiro", 45, "82868804985", false, true, "5082"));
		cs.add(new Cliente("Douglas Ribeiro", 78, "28305787087", false, false, ""));
		cs.add(new Cliente("Luís Pinto", 80, "32206836904", false, true, "6611"));
		cs.add(new Cliente("Martim Gomes", 52, "21321911742", false, true, "5090"));
		cs.add(new Cliente("Guilherme Lima", 72, "74857402530", false, true, "6083"));
		cs.add(new Cliente("Luis Santos", 84, "41960268260", false, false, ""));
		cs.add(new Cliente("Anna Goncalves", 64, "63879907340", true, true, "1490"));
		cs.add(new Cliente("Carlos Cunha", 59, "67850603460", false, true, "9147"));
		cs.add(new Cliente("Renan Carvalho", 77, "97038636553", false, true, "8058"));
		cs.add(new Cliente("Fernanda Silva", 42, "13095907966", true, true, "7538"));
		cs.add(new Cliente("Marcos Correia", 80, "31904913059", false, false, ""));
		cs.add(new Cliente("Fernanda Sousa", 74, "46792659819", true, false, ""));
		cs.add(new Cliente("Murilo Gomes", 26, "21025966724", false, false, ""));
		cs.add(new Cliente("Victor Ferreira", 62, "34650751454", false, false, ""));
		cs.add(new Cliente("Cauã Ribeiro", 59, "17751380896", false, false, ""));
		cs.add(new Cliente("Vitór Martins", 74, "17617892101", false, false, ""));
		cs.add(new Cliente("Vitória Pereira", 49, "97570337239", true, false, ""));
		cs.add(new Cliente("Gabrielly Ribeiro", 49, "84958562044", true, false, ""));
		cs.add(new Cliente("Leonor Souza", 63, "13694982841", true, false, ""));
		cs.add(new Cliente("Marisa Goncalves", 35, "59933003615", true, false, ""));
		cs.add(new Cliente("Alex Cavalcanti", 42, "57822353578", false, false, ""));
		cs.add(new Cliente("Erick Alves", 45, "20764201263", false, true, "2255"));
		cs.add(new Cliente("Ágatha Goncalves", 35, "96598540801", true, false, ""));
		cs.add(new Cliente("Paulo Barros", 43, "67844792973", false, true, "9988"));
		cs.add(new Cliente("Eduardo Barbosa", 38, "33553196947", false, false, ""));
		cs.add(new Cliente("Sofia Ribeiro", 81, "34476069088", true, false, ""));
		cs.add(new Cliente("Bianca Carvalho", 36, "42586363396", true, true, "1330"));
		cs.add(new Cliente("Yasmin Martins", 64, "82501817940", true, true, "2252"));
		cs.add(new Cliente("Igor Correia", 68, "31199305120", false, false, ""));
		cs.add(new Cliente("Tânia Costa", 23, "63313856730", true, true, "1877"));
		cs.add(new Cliente("Eduardo Sousa", 79, "60338616802", false, true, "2101"));
		cs.add(new Cliente("Clara Martins", 46, "43665174260", true, true, "1076"));
		cs.add(new Cliente("Manuela Dias", 41, "39603940550", true, false, ""));
		cs.add(new Cliente("Rebeca Martins", 19, "62385282224", true, false, ""));
		cs.add(new Cliente("Victor Rodrigues", 36, "32724149246", false, true, "2477"));
		cs.add(new Cliente("Kai Castro", 82, "85135128263", false, true, "9259"));
		cs.add(new Cliente("Brenda Pinto", 47, "70546089330", true, false, ""));
		cs.add(new Cliente("Maria Melo", 59, "85442760323", true, true, "9522"));
		cs.add(new Cliente("Emily Pinto", 50, "31366545010", true, false, ""));
		cs.add(new Cliente("Thiago Sousa", 27, "36960128234", false, false, ""));
		cs.add(new Cliente("Camila Melo", 70, "37957226590", true, false, ""));
		cs.add(new Cliente("Anna Souza", 78, "58548113162", true, true, "5644"));
		cs.add(new Cliente("Carolina Almeida", 59, "64691438220", true, true, "1831"));
		cs.add(new Cliente("Livia Azevedo", 70, "92427320790", true, false, ""));
		cs.add(new Cliente("Júlia Oliveira", 54, "91913427242", true, false, ""));
		cs.add(new Cliente("Kauê Martins", 59, "69944617180", false, true, "9540"));
		cs.add(new Cliente("Pedro Pereira", 52, "92749144663", false, true, "5137"));
		cs.add(new Cliente("Beatrice Rocha", 52, "33116900108", true, true, "9442"));
		cs.add(new Cliente("Kauã Dias", 22, "83582044262", false, true, "4026"));
		cs.add(new Cliente("Lucas Fernandes", 61, "72311591924", false, false, ""));
		cs.add(new Cliente("Maria Alves", 78, "38037390802", true, false, ""));
		cs.add(new Cliente("Cauã Sousa", 84, "42536802914", false, false, ""));
		cs.add(new Cliente("Giovana Castro", 21, "88456079332", true, true, "1085"));
		cs.add(new Cliente("Amanda Gomes", 35, "57119552503", true, true, "8711"));
		cs.add(new Cliente("Luis Rocha", 85, "53250465194", false, false, ""));
		cs.add(new Cliente("Gabrielly Costa", 45, "19613551670", true, false, ""));
		cs.add(new Cliente("Ágatha Cunha", 54, "54057270752", true, false, ""));
		cs.add(new Cliente("Bruno Barbosa", 19, "57587903461", false, true, "7439"));
		cs.add(new Cliente("Júlia Silva", 51, "88675834845", true, false, ""));
		cs.add(new Cliente("Kauê Rocha", 84, "62381206140", false, true, "3669"));
		cs.add(new Cliente("Fábio Martins", 72, "34412087735", false, false, ""));
		cs.add(new Cliente("Arthur Carvalho", 68, "24686774209", false, false, ""));
		cs.add(new Cliente("Tiago Cavalcanti", 85, "87083649820", false, false, ""));
		cs.add(new Cliente("Luís Gomes", 49, "71139111370", false, true, "3163"));
		cs.add(new Cliente("Aline Pereira", 39, "10665735065", true, true, "6076"));
		cs.add(new Cliente("Vitór Lima", 20, "12756022152", false, true, "1039"));
		cs.add(new Cliente("Vinícius Alves", 76, "38660482468", false, true, "5127"));
		cs.add(new Cliente("Brenda Azevedo", 69, "38161743967", true, true, "5763"));
		cs.add(new Cliente("Vitória Fernandes", 77, "97740524204", true, false, ""));
		cs.add(new Cliente("Vitória Almeida", 26, "50778400166", true, true, "9947"));
		cs.add(new Cliente("Thiago Almeida", 63, "57962257318", false, false, ""));
		cs.add(new Cliente("Manuela Goncalves", 51, "19609865208", true, false, ""));
		cs.add(new Cliente("Caio Rocha", 37, "68866364053", false, true, "8406"));
		cs.add(new Cliente("Matilde Barbosa", 66, "37053029816", true, false, ""));
		cs.add(new Cliente("Isabela Alves", 66, "75727100429", true, false, ""));
		cs.add(new Cliente("Estevan Barbosa", 67, "56792298972", false, true, "3454"));
		cs.add(new Cliente("Miguel Sousa", 58, "41900218143", false, false, ""));
		cs.add(new Cliente("Emilly Ferreira", 59, "40395597544", true, true, "7694"));
		cs.add(new Cliente("Vitór Ferreira", 26, "33080722973", false, false, ""));
		cs.add(new Cliente("Thaís Souza", 41, "94621370642", true, true, "3123"));
		cs.add(new Cliente("Júlio Azevedo", 57, "66116904688", false, true, "1848"));
		cs.add(new Cliente("Julieta Araujo", 85, "84816332324", true, false, ""));
		cs.add(new Cliente("Marcos Pereira", 77, "68373065296", false, false, ""));
		cs.add(new Cliente("Tomás Correia", 66, "51745147721", false, false, ""));
		cs.add(new Cliente("Yasmin Dias", 40, "67532662403", true, true, "1713"));
		cs.add(new Cliente("Nicolas Rocha", 62, "62676761293", false, false, ""));
		cs.add(new Cliente("Yasmin Cavalcanti", 34, "18978298761", true, true, "5517"));
		cs.add(new Cliente("Julieta Azevedo", 54, "17297126659", true, true, "8393"));
		cs.add(new Cliente("Larissa Melo", 36, "90257231366", true, true, "9484"));
		cs.add(new Cliente("Diego Cunha", 54, "58515389886", false, false, ""));
		cs.add(new Cliente("Eduarda Ferreira", 74, "72441782479", true, false, ""));
		cs.add(new Cliente("Vitória Souza", 85, "99380123370", true, true, "8328"));
		cs.add(new Cliente("Livia Costa", 83, "56345810525", true, false, ""));
		cs.add(new Cliente("Giovana Rodrigues", 34, "77797041148", true, false, ""));
		cs.add(new Cliente("Alice Pereira", 61, "14990404408", true, true, "6755"));
		cs.add(new Cliente("Rodrigo Oliveira", 29, "43146683180", false, true, "7791"));

		for(Cliente c : cs) {
			BarController.cadastraCliente(c);
			BarController.clienteEntra(c);
		}

	}

}
