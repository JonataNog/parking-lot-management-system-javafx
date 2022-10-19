package testes;

import modelo.servicos.VagaServicos;
import modelo.entidades.Vaga;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VagaServicosTest {

    private  VagaServicos vagaServicos = null;
    @org.junit.jupiter.api.BeforeEach
    void init(){
        vagaServicos = new VagaServicos();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown(){
        vagaServicos.getListaVagas().clear();
        vagaServicos.getListaVagasOcupadas().clear();
    }

    @org.junit.jupiter.api.Test
    void iniciaVagas() {
        List<Vaga> listaVagas;
        vagaServicos.iniciaVagas();
        listaVagas = vagaServicos.getListaVagas();
        assertEquals(48, listaVagas.size());
        for(int i = 1; i <= 48; i++){
            assertEquals(i, listaVagas.get(i-1).getNumero());
        }
    }

    @org.junit.jupiter.api.Test
    void listaEstaVazia() {
        assertTrue(vagaServicos.listaEstaVazia());
        vagaServicos.iniciaVagas();
        assertFalse(vagaServicos.listaEstaVazia());

    }


    @org.junit.jupiter.api.Test
    void ocuparVaga() {
        List<Vaga> listaVagas;
        Vaga vaga = new Vaga(1);
        vagaServicos.getListaVagas().add(vaga);
        vagaServicos.ocuparVaga(vaga);
        listaVagas = vagaServicos.getListaVagasOcupadas();
        assertFalse(vaga.isDisponivel());
        assertEquals(vaga, listaVagas.get(0));
    }

    @org.junit.jupiter.api.Test
    void ocuparVagaNULLPointerException() {
        assertThrows(NullPointerException.class, () -> {
            Vaga vaga = new Vaga(1);
            vagaServicos.ocuparVaga(vaga);
        });
    }

    @org.junit.jupiter.api.Test
    void liberarVaga() {
        List<Vaga> listaVagas;
        Vaga vaga = new Vaga(1);
        vagaServicos.getListaVagas().add(vaga);
        vagaServicos.ocuparVaga(vaga);
        listaVagas = vagaServicos.getListaVagasOcupadas();
        vagaServicos.liberarVaga(listaVagas.get(0));
        assertTrue(vaga.isDisponivel());
        assertEquals(0, listaVagas.size());

    }
    @org.junit.jupiter.api.Test
    void liberarVagaNULLPointerException() {
        assertThrows(NullPointerException.class, () -> {
            Vaga vaga = new Vaga(1);
            vagaServicos.liberarVaga(vaga);
        });
    }

    @org.junit.jupiter.api.Test
    void estaDisponivelNULLPointerException() {
        assertThrows(NullPointerException.class, () -> {
            Vaga vaga = null;
            vagaServicos.estaDisponivel(vaga);
        });
    }

    @org.junit.jupiter.api.Test
    void valorTotal() {
        Vaga vaga = new Vaga();
        vaga.setHoraEntrada("14:30");
        vaga.setHoraSaida("16:30");
        double preco = vagaServicos.valorTotal(vaga);
        assertEquals(11, preco);
    }
    @org.junit.jupiter.api.Test
    void valorTotalNULLPointerException() {
        Vaga vaga = null;
        assertThrows(NullPointerException.class, () -> vagaServicos.valorTotal(vaga));
    }

}