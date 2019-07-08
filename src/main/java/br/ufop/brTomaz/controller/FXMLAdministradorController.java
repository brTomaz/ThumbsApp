package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.MainApplication;
import br.ufop.brTomaz.model.bean.Usuario;
import br.ufop.brTomaz.model.dao.CaronaDAO;
import br.ufop.brTomaz.model.dao.CarroDAO;
import br.ufop.brTomaz.model.dao.UsuarioDAO;
import br.ufop.brTomaz.model.interfaces.Deletavel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLAdministradorController implements Initializable {
    @FXML
    private JFXListView <Deletavel> listViewItens;

    @FXML
    private JFXButton delete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        delete.disableProperty().bind(listViewItens.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    private void sair() throws IOException {
        MainApplication.setScreen(Screen.LOGIN);
    }

    @FXML
    private void deletar() throws IOException {
        listViewItens.getSelectionModel().getSelectedItem().deletar();
        Deletavel deletavel = listViewItens.getSelectionModel().getSelectedItem();
        alertDelete();
        listViewItens.getItems().remove(deletavel);
    }

    private void alertDelete()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exclusão");
        alert.setHeaderText("Exclusão de item");
        alert.setContentText("O item selecionado foi excluído do sistema");
        alert.showAndWait();
    }

    @FXML
    private void carros() {
        CarroDAO carrosSistema = new CarroDAO();
        List<Deletavel> carros = new ArrayList<>(carrosSistema.findAll());
        listViewItens.setItems(FXCollections.observableList(carros));
    }

    @FXML
    private void caronas() {
        CaronaDAO caronasSistema = new CaronaDAO();
        List<Deletavel> caronas = new ArrayList<>(caronasSistema.findAll());
        listViewItens.setItems(FXCollections.observableList(caronas));
    }

    private List<Usuario> usuarios(String tipoUsuario) {
        UsuarioDAO usuariosSistema = new UsuarioDAO();
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = usuariosSistema.findAll();

        List<Usuario> motoristasAux = new ArrayList<>();
        List<Usuario> passageirosAux = new ArrayList<>();

        for(Usuario usuario : usuarios)
        {
            if(usuario.getCnh() != null)
            {
                motoristasAux.add(usuario);
            }
            else
            {
                passageirosAux.add(usuario);
            }
        }

        if(tipoUsuario.equals("Motorista"))
        {
            return motoristasAux;
        }
        else
        {
            return passageirosAux;
        }
    }

    @FXML
    private void passageiros()
    {
        List<Deletavel> passageiros = new ArrayList<>(usuarios("Passageiro"));
        listViewItens.setItems(FXCollections.observableList(passageiros));
    }

    @FXML
    private void motoristas()
    {
        List<Deletavel> motoristas = new ArrayList<>(usuarios("Motorista"));
        listViewItens.setItems(FXCollections.observableList(motoristas));
    }
}
