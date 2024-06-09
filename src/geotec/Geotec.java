package geotec;

import entities.CSVUtils;
import entities.Municipio;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;

public class Geotec extends Application {

    private TableView<Municipio> tableView;
    private ObservableList<Municipio> municipios;
    private TextField txtBusca;

    @Override
    public void start(Stage primaryStage) {
        LoginApp loginApp = new LoginApp(this);
        loginApp.start(new Stage());
    }

    public void iniciarTelaPrincipal(Stage primaryStage) {
        primaryStage.setTitle("Gestão de Municípios");

        tableView = new TableView<>();
        municipios = FXCollections.observableArrayList();

        TableColumn<Municipio, String> colCodigoIBGE = new TableColumn<>("Código IBGE");
        colCodigoIBGE.setCellValueFactory(new PropertyValueFactory<>("codigoIBGE"));

        TableColumn<Municipio, String> colNome = new TableColumn<>("Município");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Municipio, String> colMicrorregiao = new TableColumn<>("Microrregião");
        colMicrorregiao.setCellValueFactory(new PropertyValueFactory<>("microRegiao"));

        TableColumn<Municipio, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        TableColumn<Municipio, String> colRegiaoGeografica = new TableColumn<>("Região Geográfica");
        colRegiaoGeografica.setCellValueFactory(new PropertyValueFactory<>("regiaoGeografica"));

        TableColumn<Municipio, String> colAreaKm2 = new TableColumn<>("Área km²");
        colAreaKm2.setCellValueFactory(new PropertyValueFactory<>("areaKm"));

        TableColumn<Municipio, String> colPopulacao = new TableColumn<>("População");
        colPopulacao.setCellValueFactory(new PropertyValueFactory<>("populacao"));

        TableColumn<Municipio, String> colDomicilios = new TableColumn<>("Domicílios");
        colDomicilios.setCellValueFactory(new PropertyValueFactory<>("domicilios"));

        TableColumn<Municipio, String> colPibTotal = new TableColumn<>("PIB Total (R$ mil)");
        colPibTotal.setCellValueFactory(new PropertyValueFactory<>("pibTotal"));

        TableColumn<Municipio, String> colIdh = new TableColumn<>("IDH");
        colIdh.setCellValueFactory(new PropertyValueFactory<>("idh"));

        TableColumn<Municipio, String> colRendaMedia = new TableColumn<>("Renda Média");
        colRendaMedia.setCellValueFactory(new PropertyValueFactory<>("rendaMedia"));

        TableColumn<Municipio, String> colRendaNominal = new TableColumn<>("Renda Nominal");
        colRendaNominal.setCellValueFactory(new PropertyValueFactory<>("rendaNominal"));

        TableColumn<Municipio, String> colPeaDia = new TableColumn<>("PEA Dia");
        colPeaDia.setCellValueFactory(new PropertyValueFactory<>("peaDia"));

        TableColumn<Municipio, String> colIdhEducacao = new TableColumn<>("IDH Educação");
        colIdhEducacao.setCellValueFactory(new PropertyValueFactory<>("idhEducacao"));

        TableColumn<Municipio, String> colIdhLongevidade = new TableColumn<>("IDH Longevidade");
        colIdhLongevidade.setCellValueFactory(new PropertyValueFactory<>("idhLongevidade"));

        tableView.getColumns().addAll(colCodigoIBGE, colNome, colMicrorregiao, colEstado, colRegiaoGeografica, colAreaKm2, colPopulacao, colDomicilios, colPibTotal, colIdh, colRendaMedia, colRendaNominal, colPeaDia, colIdhEducacao, colIdhLongevidade);

        Button btnCarregar = new Button("Carregar Dados");
        btnCarregar.setOnAction(e -> carregarDados());

        txtBusca = new TextField();
        txtBusca.setPromptText("Digite o nome ou código IBGE do município");

        txtBusca.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.contains("#")) {
                    txtBusca.setText(oldValue);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Entrada Inválida");
                    alert.setHeaderText(null);
                    alert.setContentText("O caractere '#' não é permitido.");
                    alert.showAndWait();
                }
            }
        });

        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> buscarMunicipio());

        Button btnAtualizarTabela = new Button("Atualizar");
        btnAtualizarTabela.setOnAction(e -> carregarDados()); // Chama o método carregarDados para atualizar a tabela

        Button btnAtualizar = new Button("Atualizar Registro");
        btnAtualizar.setOnAction(e -> abrirInterfaceAtualizacao(primaryStage));

        Button btnDeletar = new Button("Deletar");
        btnDeletar.setOnAction(e -> abrirInterfaceDelecao(primaryStage));

        HBox hBoxBusca = new HBox(txtBusca, btnBuscar);
        hBoxBusca.setSpacing(10);

        VBox vbox = new VBox(btnCarregar, hBoxBusca, tableView, btnAtualizarTabela, btnAtualizar, btnDeletar); // Adiciona o botão Deletar na interface
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void carregarDados() {
        List<Municipio> lista = CSVUtils.lerCSV("C:\\Users\\Chê Chikita\\Desktop\\inCSV\\arquivo.csv");
        municipios.setAll(lista);
        tableView.setItems(municipios);
        CSVUtils.escreverCSV(lista, "C:\\ProjetoIntegrador\\Saida\\ArquivoTatt.csv");
    }

    private void buscarMunicipio() {
        String busca = txtBusca.getText().toLowerCase();
        ObservableList<Municipio> resultadoBusca = FXCollections.observableArrayList();

        for (Municipio municipio : municipios) {
            if (municipio.getNome().toLowerCase().contains(busca) || municipio.getCodigoIBGE().toLowerCase().contains(busca)) {
                resultadoBusca.add(municipio);
            }
        }

        tableView.setItems(resultadoBusca);
    }

    private void abrirInterfaceAtualizacao(Stage primaryStage) {
        Stage atualizacaoStage = new Stage();
        AtualizacaoRegistrosApp atualizacaoApp = new AtualizacaoRegistrosApp(municipios);
        atualizacaoApp.start(atualizacaoStage);
        atualizacaoStage.show();
    }

    private void abrirInterfaceDelecao(Stage primaryStage) {
        Stage delecaoStage = new Stage();
        DelecaoMunicipioApp delecaoApp = new DelecaoMunicipioApp(municipios);
        delecaoApp.start(delecaoStage);
        delecaoStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
