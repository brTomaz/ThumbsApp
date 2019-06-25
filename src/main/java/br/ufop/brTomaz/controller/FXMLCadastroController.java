package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.MainApplication;
import br.ufop.brTomaz.model.bean.Usuario;
import br.ufop.brTomaz.model.dao.UsuarioDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLCadastroController implements Initializable {

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtNomeUsuario;

    @FXML
    private JFXTextField txtTelefone;

    @FXML
    private JFXTextField txtCPF;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXTextField txtData;

    @FXML
    private JFXButton btnEnviar;

    @FXML
    private Pane optionPane;

    @FXML
    private AnchorPane dataPane;

    @FXML
    private JFXButton btnPassageiro;

    @FXML
    private JFXButton btnMotorista;

    @FXML
    private void enviar() throws IOException {


        dataPane.setDisable(true);
        dataPane.setOpacity(0.0);
        optionPane.setDisable(false);
        optionPane.setOpacity(1.0);

        //transition(dataPane,optionPane);
        //MainApplication.setScreen(Screen.OPCAO_CADASTRO);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnEnviar.disableProperty().bind(
                txtNome.textProperty().isEmpty()
                .or(txtCPF.textProperty().isEmpty()
                    .or(txtData.textProperty().isEmpty()
                        .or(txtNome.textProperty().isEmpty()
                            .or(txtSenha.textProperty().isEmpty()
                                .or(txtEmail.textProperty().isEmpty()
                                    .or(txtTelefone.textProperty().isEmpty())
                                )
                            )
                        )
                    )
                )
        );
    }

    @FXML
    private void cadastrarMotorista() throws IOException {
        //TODO Pesquisar como recuperar um result de um dialog

        MainApplication.setScreen(Screen.HOME_MOTORISTA);

    }

    @FXML
    private void cadastrarPassageiro() throws IOException {
        String nome = txtNome.getText();
        String nomeUsuario = txtNomeUsuario.getText();
        String telefone = txtTelefone.getText();
        String CPF = txtCPF.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        Usuario usuario = new Usuario(CPF, nome, email, null, senha, telefone, nomeUsuario);

        Boolean ok = new UsuarioDAO().save(usuario);

        if(ok){
            MainApplication.setScreen(Screen.HOME_PASSAGEIRO);
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
        }
    }

    private void transition(Pane show, Pane hide){
        FadeTransition transition = new FadeTransition();
        transition.setNode(show);
        transition.setDuration(Duration.seconds(1));
        transition.setFromValue(1.0);
        transition.setToValue(0.0);
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(1),hide);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            }
        });
        transition.play();
        hide.toFront();

    }


}
