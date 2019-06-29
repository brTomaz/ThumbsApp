package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.MainApplication;
import br.ufop.brTomaz.model.bean.Carona;
import br.ufop.brTomaz.model.bean.Carro;
import br.ufop.brTomaz.model.dao.CaronaDAO;
import br.ufop.brTomaz.model.dao.CarroDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import jfxtras.scene.control.LocalTimeTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static br.ufop.brTomaz.MainApplication.idCarona;
import static br.ufop.brTomaz.MainApplication.usuarioCorrente;

public class FXMLOferecerCaronaController implements Initializable {
    @FXML
    JFXTextField txtOrigem;
    @FXML
    JFXTextField txtDestino;
    @FXML
    JFXTextField txtValor;
    @FXML
    JFXComboBox <Integer>cmbQuantidade;
    @FXML
    AnchorPane oferecerPane;
    @FXML
    Pane carrosPane;
    @FXML
    JFXButton btnEscolherCarro;
    @FXML
    LocalTimeTextField localTimeHorario;
    @FXML
    DatePicker datePicker;
    @FXML JFXListView <Carro>listViewCarros;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BooleanProperty sizeValor = new SimpleBooleanProperty(true);

        txtValor.textProperty().addListener((ob, ov, nv) -> {
            sizeValor.setValue(nv.length() < 5);
        });

        btnEscolherCarro.disableProperty().bind(
                (txtValor.textProperty().isEmpty()
                        .or(txtOrigem.textProperty().isEmpty()
                                .or(txtDestino.textProperty().isEmpty()
                                        .or(datePicker.valueProperty().isNull())
                                )
                        )
                )
        );
        carregarQuantidade();

        listViewCarros.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) ->{

        });
    }


    private void carregarQuantidade() {
        List<Integer> quantidades = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            quantidades.add(i);
        }

        cmbQuantidade.setItems(FXCollections.observableList(quantidades));
    }

    @FXML
    private void escolherCarros() throws IOException {

        oferecerPane.setDisable(true);
        oferecerPane.setOpacity(0.0);
        carrosPane.setDisable(false);
        carrosPane.setOpacity(1.0);
        carregarCarrosUsuario();
    }

    @FXML
    private void carregarCarrosUsuario()
    {
        List<Carro> carros = new ArrayList<>();
        CarroDAO carrosUsuario = new CarroDAO();
        carros = carrosUsuario.search(usuarioCorrente.getCpf());

        listViewCarros.setItems(FXCollections.observableList(carros));
    }

    @FXML
    private void oferecerCarona() throws IOException {

        String origem = txtOrigem.getText();
        String destino = txtDestino.getText();
        String dia = datePicker.valueProperty().get() + "";
        String horaString = localTimeHorario.localTimeProperty().get() + "";
        String hora = horaString.substring(0, 5);
        double valor = Double.parseDouble(txtValor.getText());
        int vagas = cmbQuantidade.getSelectionModel().getSelectedItem();
        String placa_carro = listViewCarros.getSelectionModel().getSelectedItem().getPlaca();

        Carona carona = new Carona(idCarona, origem, destino, dia, hora, valor, vagas, 0, placa_carro, usuarioCorrente.getCpf());

        Boolean ok = new CaronaDAO().save(carona);

        if (ok) {
            idCarona++;
            MainApplication.setScreen(Screen.HOME_MOTORISTA);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }
    }

}
