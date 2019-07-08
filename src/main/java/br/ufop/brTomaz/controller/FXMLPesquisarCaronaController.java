package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.model.bean.Carona;
import br.ufop.brTomaz.model.bean.Recebe_Carona;
import br.ufop.brTomaz.model.bean.Usuario;
import br.ufop.brTomaz.model.dao.CaronaDAO;
import br.ufop.brTomaz.model.dao.Recebe_CaronaDAO;
import br.ufop.brTomaz.model.dao.UsuarioDAO;
import br.ufop.brTomaz.util.Operations;
import com.jfoenix.controls.JFXAutoCompletePopup;
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
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

import static br.ufop.brTomaz.MainApplication.setScreen;
import static br.ufop.brTomaz.MainApplication.usuarioCorrente;

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
                    alert.setHeaderText("Caronas indiponíveis");
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
            alert.setTitle("Erro");
            alert.setHeaderText("Entrada não efetuada");
            alert.setContentText("Não foi possível fazer o seu cadastro nesta carona. O carro está cheio.");
            alert.show();
            setScreen(Screen.HOME_PASSAGEIRO);
        }
        else
        {
            int idCarona = listViewCaronas.getSelectionModel().getSelectedItem().getIdcarona();

            Recebe_Carona recebeCarona = new Recebe_Carona(idCarona, usuarioCorrente.getCpf());
            Recebe_CaronaDAO salvarCaronaDAO = new Recebe_CaronaDAO();
            boolean ok = salvarCaronaDAO.save(recebeCarona);
            if(ok)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Carona");
                alert.setHeaderText("Entrada efetuada com sucesso");
                alert.setContentText("Você foi registrado nesta carona.");
                alert.showAndWait();

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario motorista = usuarioDAO.buscaMotorista(recebeCarona);

                Alert contato = new Alert(Alert.AlertType.CONFIRMATION);
                contato.setTitle("Por favor, entre em contato:");
                String telefone = "(" + motorista.getTelefone().substring(1, 3) + ") " + motorista.getTelefone().substring(3, 7) + "-" + motorista.getTelefone().substring(7);
                contato.setContentText("Nome: " + motorista.getNome() + "\n"+"Telefone: " + telefone);
                contato.showAndWait();

                CaronaDAO novaCaronaDAO = new CaronaDAO();
                novaCaronaDAO.modificarQuantidadeVagasAtual(carona);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("A entrada não foi efetuada com sucesso");
                alert.setContentText("Não foi possível efetuar o seu registro nesta carona.");
                alert.showAndWait();
            }
            setScreen(Screen.HOME_PASSAGEIRO);
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
