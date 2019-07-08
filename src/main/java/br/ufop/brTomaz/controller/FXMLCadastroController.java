package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.MainApplication;
import br.ufop.brTomaz.model.bean.Usuario;
import br.ufop.brTomaz.model.dao.UsuarioDAO;
import br.ufop.brTomaz.util.MaskFieldUtil;
import br.ufop.brTomaz.util.Operations;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static br.ufop.brTomaz.MainApplication.usuarioCorrente;

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
        MaskFieldUtil.cpfField(txtCPF);
        MaskFieldUtil.foneField(txtTelefone);

        BooleanProperty sizeCpf = new SimpleBooleanProperty(true);
        BooleanProperty sizeFone = new SimpleBooleanProperty(true);

        txtCPF.textProperty().addListener((ob, ov, nv) -> {
            sizeCpf.setValue(nv.length() < 14);
        });
        txtTelefone.textProperty().addListener((ob, ov, nv) -> {
            sizeFone.setValue(nv.length() < 14);
        });


        btnEnviar.disableProperty().bind(
                txtNome.textProperty().isEmpty()
                        .or(txtCPF.textProperty().isEmpty()
                                .or(txtNome.textProperty().isEmpty()
                                        .or(txtSenha.textProperty().isEmpty()
                                                .or(txtEmail.textProperty().isEmpty()
                                                        .or(txtTelefone.textProperty().isEmpty())
                                                        .or(sizeCpf)
                                                        .or(sizeFone)
                                                )
                                        )
                                )
                        )
        );
    }

    @FXML
    private void cadastrarMotorista() throws IOException {

        String nome = txtNome.getText();
        String nomeUsuario = txtNomeUsuario.getText();

        String telefone = MaskFieldUtil.onlyDigitsValue(txtTelefone);
        String CPF = MaskFieldUtil.onlyDigitsValue(txtCPF);

        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        final StringProperty cnh = new SimpleStringProperty("");
        cnh.addListener((ob, ov, nv) -> {
            nv = nv.length() > 11 ? nv.substring(0, 11) : nv.strip();
            cnh.setValue(nv);
        });

        TextInputDialog cnhDialog = new TextInputDialog();

        cnhDialog.setTitle("Entrada de CNH");
        cnhDialog.setHeaderText("Digite o número da sua CNH");
        cnhDialog.setContentText("CNH: ");
        cnhDialog.showAndWait().ifPresent(cnh::set);

        Usuario usuario = new Usuario(CPF, nome, email, cnh.getValue(), senha, telefone, nomeUsuario);

        Boolean ok = new UsuarioDAO().save(usuario);

        if (ok) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioCorrente = usuarioDAO.retrieveUser(email);

            MainApplication.setScreen(Screen.HOME_MOTORISTA);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }
    }

    @FXML
    private void cadastrarPassageiro() throws IOException {
        String nome = txtNome.getText();
        String nomeUsuario = txtNomeUsuario.getText();
        String telefone = MaskFieldUtil.onlyDigitsValue(txtTelefone);
        String CPF = MaskFieldUtil.onlyDigitsValue(txtCPF);
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        Usuario usuario = new Usuario(CPF, nome, email, null, senha, telefone, nomeUsuario);

        Boolean ok = new UsuarioDAO().save(usuario);

        if (ok) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioCorrente = usuarioDAO.retrieveUser(email);

            MainApplication.setScreen(Screen.HOME_PASSAGEIRO);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Não foi possível realizar o cadastro.");
            alert.show();
        }
    }

    @FXML
    private void home() throws IOException {
        Operations.home();
    }

    @FXML
    private void caronas() throws IOException {
        Operations.caronas();
    }

    @FXML
    private void cadastro() throws IOException {
        Operations.cadastro();
    }

    @FXML
    private void historico() throws IOException {
        Operations.historico();
    }

    @FXML
    private void deletar() throws IOException, NoSuchAlgorithmException {
        Operations.deletar();
    }


    @FXML
    private void sair() throws IOException {
        Operations.sair();
    }
}
