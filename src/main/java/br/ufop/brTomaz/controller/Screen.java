package br.ufop.brTomaz.controller;

public enum Screen {
    ALTERAR_DADOS("FXMLAlterarDados.fxml"),
    CADASTRO("FXMLCadastro.fxml"),
    HISTORICO("FXMLHistorico.fxml"),
    HOME_MOTORISTA("FXMLHomeMotorista.fxml"),
    HOME_PASSAGEIRO("FXMLHomePassageiro.fxml"),
    LOGIN("FXMLLogin.fxml"),
    CADASTRO_CARRO("FXMLCadastroCarro.fxml"),
    OFERECER_CARONA("FXMLOferecerCarona.fxml"),
    OPCAO_CADASTRO("FXMLOpcaoCadastro.fxml"),
    PESQUISAR_CARONA("FXMLPesquisarCarona.fxml");

    private final String fxmlName;

    Screen(String fxmlName)
    {
        this.fxmlName = fxmlName;
    }

    public String getFXMLPath()
    {
        String separator = System.getProperty("file.separator");
        return separator
                .concat("br")
                .concat(separator)
                .concat("ufop")
                .concat(separator)
                .concat("brTomaz")
                .concat(separator)
                .concat("fxml")
                .concat(separator)
                .concat(fxmlName);
    }
}
