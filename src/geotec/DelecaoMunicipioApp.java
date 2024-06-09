package geotec;

import entities.Municipio;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.stream.Collectors;

public class DelecaoMunicipioApp extends Application {

    private ObservableList<Municipio> municipios;
    private Municipio municipioSelecionado;
    private ComboBox<String> cmbMunicipios;

    public DelecaoMunicipioApp(ObservableList<Municipio> municipios) {
        this.municipios = municipios;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Deletar Município");

        VBox vbox = new VBox();
        vbox.setSpacing(10);

        Label lblMunicipio = new Label("Selecionar Município:");
        cmbMunicipios = new ComboBox<>();
        cmbMunicipios.setPromptText("Selecione um município...");
        cmbMunicipios.getItems().addAll(municipios.stream().map(Municipio::getNome).collect(Collectors.toList()));
        cmbMunicipios.setOnAction(e -> {
            String selectedMunicipioName = cmbMunicipios.getSelectionModel().getSelectedItem();
            municipioSelecionado = municipios.stream().filter(m -> m.getNome().equals(selectedMunicipioName)).findFirst().orElse(null);
        });

        Button btnSim = new Button("Sim");
        btnSim.setOnAction(e -> confirmarDelecao(primaryStage));

        Button btnSair = new Button("Sair");
        btnSair.setOnAction(e -> primaryStage.close());

        vbox.getChildren().addAll(lblMunicipio, cmbMunicipios, btnSim, btnSair);

        Scene scene = new Scene(vbox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void confirmarDelecao(Stage primaryStage) {
        if (municipioSelecionado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um município para deletar.");
            alert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmar Deleção");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Tem certeza que deseja deletar o município " + municipioSelecionado.getNome() + "?");

        ButtonType buttonTypeSim = new ButtonType("Sim");
        ButtonType buttonTypeNao = new ButtonType("Não");
        confirmAlert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeSim) {
                municipios.remove(municipioSelecionado);
                primaryStage.close();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
