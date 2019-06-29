package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.MainApplication;
import br.ufop.brTomaz.model.bean.Carona;
import br.ufop.brTomaz.model.dao.CaronaDAO;
import br.ufop.brTomaz.model.dao.CarroDAO;
import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLPesquisarCaronaController implements Initializable {

    @FXML
    private JFXTextField txtOrigem;
    @FXML
    private JFXTextField txtDestino;
    @FXML
    ListView<Carona> listViewCaronas;

    private JFXAutoCompletePopup<Carona> origemAutoComplete;
    private JFXAutoCompletePopup<Carona> destinoAutoComplete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAutoCompletes();
        listViewCaronas.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> {
        });
    }

    private void initAutoCompletes() {
        origemAutoComplete = new JFXAutoCompletePopup<>();
        destinoAutoComplete = new JFXAutoCompletePopup<>();
        CaronaDAO caronaDAO = new CaronaDAO();
        List<Carona> caronas = caronaDAO.findAll();

        origemAutoComplete.getSuggestions().addAll(caronas);
        destinoAutoComplete.getSuggestions().addAll(caronas);

        origemAutoComplete.setSuggestionsCellFactory(new Callback<ListView<Carona>, ListCell<Carona>>() {
            @Override
            public ListCell<Carona> call(ListView<Carona> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Carona item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null || !empty) {
                            setText(item.getOrigem());
                        } else {
                            setText("");
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        origemAutoComplete.setSelectionHandler(event -> txtOrigem.setText(event.getObject().getOrigem()));
        txtOrigem.textProperty().addListener(observable -> {
            origemAutoComplete.filter(user -> user.getOrigem().toUpperCase().startsWith(txtOrigem.getText().toUpperCase()));
            if (origemAutoComplete.getFilteredSuggestions().isEmpty())
                origemAutoComplete.hide();
            else
                origemAutoComplete.show(txtOrigem);
        });

        destinoAutoComplete.setSuggestionsCellFactory(new Callback<ListView<Carona>, ListCell<Carona>>() {
            @Override
            public ListCell<Carona> call(ListView<Carona> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Carona item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null || !empty) {
                            setText(item.getDestino());
                        } else {
                            setText("");
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        destinoAutoComplete.setSelectionHandler(event -> txtDestino.setText(event.getObject().getDestino()));
        txtDestino.textProperty().addListener(observable -> {
            destinoAutoComplete.filter(user -> user.getDestino().toUpperCase().startsWith(txtDestino.getText().toUpperCase()));
            if (destinoAutoComplete.getFilteredSuggestions().isEmpty())
                destinoAutoComplete.hide();
            else
                destinoAutoComplete.show(txtDestino);
        });

        txtDestino.focusedProperty().addListener((ob, ov, nv) -> {
            if (ov) {
                String origem = txtOrigem.getText();
                String destino = txtDestino.getText();

                CaronaDAO resultadoDA0 = new CaronaDAO();
                List<Carona> resultadoCaronas = resultadoDA0.search(origem, destino);

                if (resultadoCaronas.size() != 0) {
                    listViewCaronas.setItems(FXCollections.observableList(resultadoCaronas));
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Indisponível");
                    alert.setContentText("Não foram encontradas caronas para a sua pesquisa");
                    alert.show();
                }
            }
        });
    }

    @FXML
    private void entrar() throws IOException {
        Carona carona = listViewCaronas.getSelectionModel().getSelectedItem();

        if(carona.getQuantidade_atual() >= carona.getQuantidade_vagas())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Entrada não efetuada");
            alert.setContentText("Não foi possível cadastrar-se nesta carona. O carro está cheio.");
            alert.show();
            MainApplication.setScreen(Screen.HOME_PASSAGEIRO);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entrada efetuada com sucesso");
            alert.setContentText("Você foi registrado nesta carona.");
            alert.showAndWait();
            CaronaDAO novaCaronaDAO = new CaronaDAO();
            novaCaronaDAO.modificarQuantidadeVagasAtual(carona);
            MainApplication.setScreen(Screen.HOME_PASSAGEIRO);
        }
    }
}
