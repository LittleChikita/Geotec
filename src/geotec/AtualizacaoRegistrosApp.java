package geotec;

import entities.Municipio;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AtualizacaoRegistrosApp extends Application {

    private ObservableList<Municipio> municipios;
    private Municipio municipioSelecionado;

    private TextField txtCodigoIBGE;
    private TextField txtNome;
    private TextField txtPopulacao;
    private TextField txtDomicilios;
    private TextField txtPibTotal;
    private TextField txtIdh;
    private TextField txtRendaMedia;
    private TextField txtRendaNominal;
    private TextField txtPeaDia;
    private TextField txtIdhEducacao;
    private TextField txtIdhLongevidade;

    private ComboBox<String> cmbMunicipios;

    public AtualizacaoRegistrosApp(ObservableList<Municipio> municipios) {
        this.municipios = municipios;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Atualização de Registros");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblMunicipio = new Label("Selecionar Município:");
        cmbMunicipios = new ComboBox<>();
        cmbMunicipios.setPromptText("Selecione um município...");
        cmbMunicipios.getItems().addAll(municipios.stream().map(Municipio::getNome).collect(Collectors.toList()));
        cmbMunicipios.setOnAction(e -> {
            String selectedMunicipioName = cmbMunicipios.getSelectionModel().getSelectedItem();
            municipioSelecionado = municipios.stream().filter(m -> m.getNome().equals(selectedMunicipioName)).findFirst().orElse(null);
            exibirDetalhesMunicipio();
        });

        Label lblCodigoIBGE = new Label("Código IBGE:");
        txtCodigoIBGE = new TextField();
        txtCodigoIBGE.setDisable(true);

        Label lblNome = new Label("Município:");
        txtNome = new TextField();
        txtNome.setDisable(true);

        Label lblPopulacao = new Label("População:");
        txtPopulacao = new TextField();

        Label lblDomicilios = new Label("Domicílios:");
        txtDomicilios = new TextField();

        Label lblPibTotal = new Label("PIB Total (R$ mil):");
        txtPibTotal = new TextField();

        Label lblIdh = new Label("IDH:");
        txtIdh = new TextField();

        Label lblRendaMedia = new Label("Renda Média:");
        txtRendaMedia = new TextField();

        Label lblRendaNominal = new Label("Renda Nominal:");
        txtRendaNominal = new TextField();

        Label lblPeaDia = new Label("PEA Dia:");
        txtPeaDia = new TextField();

        Label lblIdhEducacao = new Label("IDH Educação:");
        txtIdhEducacao = new TextField();

        Label lblIdhLongevidade = new Label("IDH Longevidade:");
        txtIdhLongevidade = new TextField();

        Button btnAtualizar = new Button("Atualizar");
        btnAtualizar.setOnAction(e -> atualizarRegistro(txtPopulacao.getText(), txtDomicilios.getText(), txtPibTotal.getText(),
                txtIdh.getText(), txtRendaMedia.getText(), txtRendaNominal.getText(), txtPeaDia.getText(),
                txtIdhEducacao.getText(), txtIdhLongevidade.getText()));

        Button btnSalvarSair = new Button("Salvar e Sair");
        btnSalvarSair.setOnAction(e -> {
            atualizarRegistro(txtPopulacao.getText(), txtDomicilios.getText(), txtPibTotal.getText(),
                    txtIdh.getText(), txtRendaMedia.getText(), txtRendaNominal.getText(), txtPeaDia.getText(),
                    txtIdhEducacao.getText(), txtIdhLongevidade.getText());
            primaryStage.close();
        });

        HBox buttonsBox = new HBox(btnAtualizar, btnSalvarSair);
        buttonsBox.setSpacing(10);

        VBox vbox = new VBox(lblMunicipio, cmbMunicipios, lblCodigoIBGE, txtCodigoIBGE, lblNome, txtNome, lblPopulacao, txtPopulacao, lblDomicilios,
                txtDomicilios, lblPibTotal, txtPibTotal, lblIdh, txtIdh, lblRendaMedia, txtRendaMedia, lblRendaNominal,
                txtRendaNominal, lblPeaDia, txtPeaDia, lblIdhEducacao, txtIdhEducacao, lblIdhLongevidade, txtIdhLongevidade,
                buttonsBox);
        vbox.setSpacing(10);

        grid.getChildren().addAll(vbox);

        Scene scene = new Scene(grid, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void exibirDetalhesMunicipio() {
        if (municipioSelecionado != null) {
            txtCodigoIBGE.setText(municipioSelecionado.getCodigoIBGE());
            txtNome.setText(municipioSelecionado.getNome());
            txtPopulacao.setText(municipioSelecionado.getPopulacao());
            txtDomicilios.setText(municipioSelecionado.getDomicilios());
            txtPibTotal.setText(municipioSelecionado.getPibTotal());
            txtIdh.setText(municipioSelecionado.getIdh());
            txtRendaMedia.setText(municipioSelecionado.getRendaMedia());
            txtRendaNominal.setText(municipioSelecionado.getRendaNominal());
            txtPeaDia.setText(municipioSelecionado.getPeaDia());
            txtIdhEducacao.setText(municipioSelecionado.getIdhEducacao());
            txtIdhLongevidade.setText(municipioSelecionado.getIdhLongevidade());
        }
    }

    private void atualizarRegistro(String populacao, String domicilios, String pibTotal, String idh,
            String rendaMedia, String rendaNominal, String peaDia,
            String idhEducacao, String idhLongevidade) {
        if (municipioSelecionado != null) {
            municipioSelecionado.setPopulacao(populacao);
            municipioSelecionado.setDomicilios(domicilios);
            municipioSelecionado.setPibTotal(pibTotal);
            municipioSelecionado.setIdh(idh);
            municipioSelecionado.setRendaMedia(rendaMedia);
            municipioSelecionado.setRendaNominal(rendaNominal);
            municipioSelecionado.setPeaDia(peaDia);
            municipioSelecionado.setIdhEducacao(idhEducacao);
            municipioSelecionado.setIdhLongevidade(idhLongevidade);

            // Registra a data da última atualização
            LocalDateTime dataAtualizacao = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = dataAtualizacao.format(formatter);
            municipioSelecionado.setDataUltimaAtualizacao(dataFormatada);

            // Atualiza a exibição dos detalhes do município na interface
            exibirDetalhesMunicipio();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
