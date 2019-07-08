package br.ufop.brTomaz.util;

import br.ufop.brTomaz.MainApplication;
import br.ufop.brTomaz.controller.Screen;
import br.ufop.brTomaz.model.bean.Usuario;
import br.ufop.brTomaz.model.dao.UsuarioDAO;
import br.ufop.brTomaz.security.SegurancaSistema;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;

import static br.ufop.brTomaz.MainApplication.usuarioCorrente;

public class Operations {

    @FXML
    public static void deletar() throws IOException {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Exclusão de conta");
        dialog.setHeaderText("Esta é uma ação irreversível. Para continuar, digite sua senha");

        ButtonType loginButtonType = new ButtonType("Excluir", ButtonBar.ButtonData.OK_DONE);
        String password = panePassword(dialog, loginButtonType);

        if(password != null && SegurancaSistema.criptografarSenha(password).equals(usuarioCorrente.getSenha()))
        {
            Usuario usuario = usuarioCorrente;
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            boolean ok = usuarioDAO.delete(usuario);

            if(ok)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Exclusão");
                alert.setContentText("A sua conta foi excluída.");
                alert.showAndWait();
                MainApplication.setScreen(Screen.LOGIN);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Exclusão");
                alert.setContentText("Não foi possível excluir a sua conta.");
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("A senha digitada está incorreta");
        }
    }

    public static String panePassword(Dialog<Pair<String, String>> dialog, ButtonType loginButtonType) {
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        PasswordField password = new PasswordField();
        grid.add(new Label("Senha:"), 0, 1);
        grid.add(password, 1, 1);

        dialog.getDialogPane().setContent(grid);

        Optional<Pair<String, String>> result = dialog.showAndWait();

        return password.getText();
    }

    @FXML
    public static void sair() throws IOException {
        MainApplication.setScreen(Screen.LOGIN);
    }

    @FXML
    public static void historico() throws IOException {
        MainApplication.setScreen(Screen.HISTORICO);
    }

    @FXML
    public static void cadastro() throws IOException {
        MainApplication.setScreen(Screen.CADASTRO);
    }

    @FXML
    public static void caronas() throws IOException {
            MainApplication.setScreen(Screen.PESQUISAR_CARONA);
    }

    @FXML
    public static void home() throws IOException {
        if(usuarioCorrente.getCnh() != null)
        {
            MainApplication.setScreen(Screen.HOME_MOTORISTA);
        }
        else
        {
            MainApplication.setScreen(Screen.HOME_PASSAGEIRO);
        }
    }
}
