package geotec;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginApp extends Application {

    private static final String USUARIO_CORRETO = "IBGE550234";
    private static final String SENHA_CORRETA = "IBGE1234";
    private int tentativasRestantes = 3;

    private Geotec mainApp;

    public LoginApp(Geotec mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void start(Stage loginStage) {
        loginStage.setTitle("Login");

        VBox vbox = new VBox();
        vbox.setSpacing(10);

        TextField txtUsuario = new TextField();
        txtUsuario.setPromptText("Usuário");

        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Senha");

        Label lblMensagem = new Label();

        Button btnEntrar = new Button("Entrar");
        btnEntrar.setOnAction(e -> {
            String usuario = txtUsuario.getText();
            String senha = txtSenha.getText();

            if (USUARIO_CORRETO.equals(usuario) && SENHA_CORRETA.equals(senha)) {
                loginStage.close();
                mainApp.iniciarTelaPrincipal(new Stage());
            } else {
                tentativasRestantes--;
                if (tentativasRestantes > 0) {
                    lblMensagem.setText("Usuário ou senha incorretos. Tentativas restantes: " + tentativasRestantes);
                } else {
                    lblMensagem.setText("Número de tentativas excedido. O programa será fechado.");
                    loginStage.close();
                    System.exit(0);
                }
            }
        });

        vbox.getChildren().addAll(txtUsuario, txtSenha, btnEntrar, lblMensagem);

        Scene scene = new Scene(vbox, 300, 200);
        loginStage.setScene(scene);
        loginStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
