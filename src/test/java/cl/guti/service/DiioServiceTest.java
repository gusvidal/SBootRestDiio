package cl.guti.service;

import cl.guti.model.Diio;
import cl.guti.repository.DiioDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.internal.util.Assert;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class DiioServiceTest {

    @Mock
    private DiioDao diioRepository;
    @InjectMocks
    private DiioService diioService;

    private Diio diio;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        diio = new Diio();
        diio.setId(1);
        diio.setNroDiio("02.235.6985");
        diio.setDesc("Novillo angus negro");
        diio.setFechaInstall("2023/02/03");
        diio.setFechaNacimiento("202/10/12");
    }

    @Test
    void testListar() {
        Mockito.when(diioRepository.findAll()).thenReturn(Arrays.asList(diio));
        Assert.notNull(diioService.listar());
    }
}