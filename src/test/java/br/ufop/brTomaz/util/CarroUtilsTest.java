package br.ufop.brTomaz.util;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

public class CarroUtilsTest extends TestCase {

    public void testMarcas() throws IOException {
        List<Marca> marcas = new CarroUtils().obterMarcas();
    }
}
