package br.ufop.brTomaz.util;

import br.ufop.brTomaz.MainApplication;
import br.ufop.brTomaz.controller.Screen;
import br.ufop.brTomaz.model.bean.Usuario;
import br.ufop.brTomaz.model.dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;

import static br.ufop.brTomaz.MainApplication.usuarioCorrente;

public class Operations {

    @FXML
    public static void deletar() throws IOException {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Deseja realmente excluir a sua conta?");
        textInputDialog.setContentText("Esta é uma ação irreversível. Digite a sua senha para continuar:");
        textInputDialog.showAndWait();
        String senha = textInputDialog.getResult();

        if(senha.equals(usuarioCorrente.getSenha()))
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
        if(usuarioCorrente.getCnh() != null)
        {
            MainApplication.setScreen(Screen.OFERECER_CARONA);
        }
        else
        {
            MainApplication.setScreen(Screen.PESQUISAR_CARONA);
        }
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
