package br.ufop.brTomaz.controller;

import br.ufop.brTomaz.model.bean.Carro;
import br.ufop.brTomaz.model.dao.CarroDAO;
import br.ufop.brTomaz.util.CarroUtils;
import br.ufop.brTomaz.util.Marca;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static br.ufop.brTomaz.MainApplication.usuarioCorrente;

public class FXMLCadastroCarroController implements Initializable {
    @FXML JFXComboBox<Marca> cmbMarca;
    @FXML JFXComboBox<Marca> cmbModelo;
    @FXML JFXComboBox<Integer> cmbAno;
    @FXML JFXComboBox<String> cmbCor;
    @FXML JFXTextField txtPlaca;
    @FXML JFXListView <Carro>listViewCarros;
    @FXML JFXButton btnRemover;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Callback<ListView<Marca>, ListCell<Marca>> cellFactory = new Callback<ListView<Marca>, ListCell<Marca>>() {
            @Override
            public ListCell<Marca> call(ListView<Marca> param) {
                return new ListCell<>() {
                    Label label = new Label("");
                    @Override
                    protected void updateItem(Marca item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        };

        btnRemover.disableProperty().bind(listViewCarros.getSelectionModel().selectedItemProperty().isNull());

        cmbMarca.setCellFactory(cellFactory);
        List<Marca> marcas = null;
        try {
            marcas = new CarroUtils().obterMarcas();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cmbMarca.setItems(FXCollections.observableList(marcas));

        cmbModelo.setCellFactory(cellFactory);
        cmbMarca.getSelectionModel()
                .selectedItemProperty()
                .addListener((ob, ov, nv)->{
            if(nv != null) {
                carregarModelos(nv);
            }
        });
        carregarCores();
        carregarAno();
        carregarCarrosUsuario();

        listViewCarros.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> {
            if(nv != null)
            {
                txtPlaca.textProperty().bind(nv.placaProperty());
                for (Marca m : cmbMarca.getItems()){
                    if(m.getName().equals(nv.getMarca())){
                        cmbMarca.getSelectionModel().select(m);
                        break;
                    }
                }

                for (Marca m : cmbModelo.getItems()){
                    if(m.getName().equals(nv.getModelo())){
                        cmbModelo.getSelectionModel().select(m);
                        break;
                    }
                }
                for(Integer i : cmbAno.getItems()) {
                    if(i == nv.getAno())
                    {
                        cmbAno.getSelectionModel().select(i);
                        break;
                    }
                }
                cmbCor.getSelectionModel().select(nv.getCor());
            }
        });
    }

    @FXML
    private void incluir() throws IOException {
        String marca = cmbMarca.getSelectionModel().getSelectedItem().getName();
        String modelo = cmbModelo.getSelectionModel().getSelectedItem().getName();
        int ano = cmbAno.getSelectionModel().getSelectedItem();
        String cor = cmbCor.getSelectionModel().getSelectedItem();
        String placa = obterPlaca();

        Carro carro = new Carro(placa, cor, modelo, marca, ano, usuarioCorrente.getCpf());

        Boolean ok = new CarroDAO().save(carro);

        if (ok) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro realizado");
            alert.setContentText("O cadastro foi realizado com sucesso!");
            alert.show();
            clear();
            carregarCarrosUsuario();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Não foi possível realizar o cadastro");
            alert.show();
            clear();
        }
    }

    @FXML
    private void carregarCarrosUsuario()
    {
        List<Carro> carros = new ArrayList<>();
        CarroDAO carrosUsuario = new CarroDAO();
        carros = carrosUsuario.search(usuarioCorrente.getCpf());

        listViewCarros.setItems(FXCollections.observableList(carros));
        Carro carro = listViewCarros.getSelectionModel().getSelectedItem();
    }

    private void carregarModelos(Marca marca){
        try {
            List<Marca> modelos = new CarroUtils().obterModelos(marca);
            cmbModelo.setItems(FXCollections.observableList(modelos));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarAno()
    {
        List<Integer> anos = new ArrayList<>();
        Calendar cal = GregorianCalendar.getInstance();
        int anoAtual = cal.get(Calendar.YEAR);

        for(int i = 1980; i <= anoAtual; i++)
        {
            anos.add(i);
        }

        cmbAno.setItems(FXCollections.observableList(anos));
    }

    private void carregarCores()
    {
        String[] cor = {"Branco", "Preto", "Prata", "Cinza", "Vermelho", "Amarelo", "Azul", "Rosa", "Bege", "Verde", "Laranja", "Vinho", "Marrom"};
        List<String> cores = Arrays.asList(cor);
        cmbCor.setItems(FXCollections.observableList(cores));
    }

    private String obterPlaca()
    {
        String placa = txtPlaca.getText();
        return placa;
    }

    @FXML
    private void remover()
    {
        Carro carro = listViewCarros.getSelectionModel().getSelectedItem();
        CarroDAO carroDAO = new CarroDAO();
        carroDAO.delete(carro);
        clear();
        listViewCarros.getSelectionModel().clearSelection();
        listViewCarros.getItems().remove(carro);
    }

    @FXML private void clear()
    {
        txtPlaca.textProperty().unbind();
        txtPlaca.clear();
        cmbAno.getSelectionModel().clearSelection();;
        cmbCor.getSelectionModel().clearSelection();
        cmbModelo.getSelectionModel().clearSelection();
        cmbMarca.getSelectionModel().clearSelection();
    }
}
