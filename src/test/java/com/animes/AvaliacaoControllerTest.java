package com.animes;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class AvaliacaoControllerTest extends DefaultTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void testeTodasAvaliacoes() throws Exception {
		String uri = "/avaliacoes";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).with((httpBasic("dudu", "dudu123")))
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testeTodasAvaliacoesAcessoNegado() throws Exception {
		String uri = "/avaliacoes";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).with((httpBasic("joao", "joao123")))
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);
	}
	
	@Test
	public void testeAvaliacaoCriada() throws Exception {
		String uri = "/avaliacoes";
		
		String inputJson = "{\"nota\":0,\"texto\":\"abc\",\"anime\":{\"id\":2}}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).with((httpBasic("joao", "joao123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	public void testeAvaliacaoNaoCriadaRepetida() throws Exception {
		String uri = "/avaliacoes";
		
		String inputJson = "{\"nota\":0,\"texto\":\"abc\",\"anime\":{\"id\":1}}";	
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(406, status);
	}
	
	@Test
	public void testeAvaliacaoAtualizada() throws Exception {
		String uri = "/avaliacoes/1";
		
		String inputJson = "{\"nota\":0,\"texto\":\"abc\",\"anime\":{\"id\":1}}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testeAvaliacaoNaoAtualizadaAcessoNegado() throws Exception {
		String uri = "/avaliacoes/3";
		
		String inputJson = "{\"nota\":0,\"texto\":\"abc\",\"anime\":{\"id\":1}}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(401, status);
	}
	
	@Test
	public void testeAvaliacaoNaoAtualizadaNaoEncontrada() throws Exception {
		String uri = "/avaliacoes/24";
		
		String inputJson = "{\"nota\":0,\"texto\":\"abc\",\"anime\":{\"id\":1}}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}
	
	@Test
	public void testeAvaliacaoApagada() throws Exception {
		String uri = "/avaliacoes/2";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("dudu", "dudu123"))))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testeAvaliacaoNaoApagadaAcessoNegado() throws Exception {
		String uri = "/avaliacoes/1";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("joao", "joao123"))))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(401, status);
	}
	
	@Test
	public void testeAvaliacaoNaoApagadaNaoEncontrada() throws Exception {
		String uri = "/avaliacoes/15";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("dudu", "dudu123"))))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}
}
