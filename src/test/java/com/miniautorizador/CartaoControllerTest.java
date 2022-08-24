package com.miniautorizador;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniautorizador.model.Cartao;
import com.miniautorizador.service.CatraoService;

@DisplayName("CartaoControllerTest")
public class CartaoControllerTest {
	
	@MockBean
	private CatraoService cartaoService;
	
	@Autowired
    private MockMvc mockMvc;
	
	private static final String PATH = "/cartoes";
	
	@Test
    @DisplayName("Criar um novo cartão com limite de 500,00 reais.")
    public void save() throws Exception {
		Cartao cartao = Cartao.builder()
				.numeroCartao(1234L)
				.senhaCartao("1234")
				.build();

        String json = new ObjectMapper().writeValueAsString(cartao);

        mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }
	
	@Test
    @DisplayName("Retornar um cartão")
    public void getCartao() throws Exception {
		Optional<Cartao> cartao = Optional.of(Cartao.builder()
				.numeroCartao(1234L)
				.senhaCartao("1234")
				.build());
        
        when(cartaoService.getCartao(cartao.get().getNumeroCartao())).thenReturn(cartao);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String jsonEsperado = "[{\"numeroCartao\":\"1234\",\"numeroCartao\":\"1234\",\"valor\":\"500,00}]";
        Assertions.assertEquals(jsonEsperado, response.getContentAsString());
    }
	
	@Test
    @DisplayName("Retornar um cartão indicando senha inválida")
    public void getCartaoComSenhaInvalida() throws Exception {
		Optional<Cartao> cartao = Optional.of(Cartao.builder()
				.numeroCartao(1234L)
				.senhaCartao("1235")
				.build());
        
        when(cartaoService.getCartao(cartao.get().getNumeroCartao())).thenReturn(cartao);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        Assertions.assertThrows(com.miniautorizador.exception.ValidationException.class, () -> cartaoService.getCartao(cartao.get().getNumeroCartao()), "Cartão com senha inválida");
    }
	
	@Test
    @DisplayName("Realiza uma transação no cartão")
    public void transacao() throws Exception {
		Optional<Cartao> cartao = Optional.of(Cartao.builder()
				.numeroCartao(1234L)
				.senhaCartao("1234")
				.valor(new BigDecimal(100.00))
				.build());
        
        when(cartaoService.getCartao(cartao.get().getNumeroCartao())).thenReturn(cartao);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        String jsonEsperado = "[{\"numeroCartao\":\"1234\",\"numeroCartao\":\"1234\",\"valor\":\"400,00}]";
        Assertions.assertEquals(jsonEsperado, response.getContentAsString());
    }
}
