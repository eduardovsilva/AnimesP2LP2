package com.animes;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class AnimeStatusControllerTest extends DefaultTest {
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void testeTodosStatus() throws Exception {
		String uri = "/lista";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).with((httpBasic("dudu", "dudu123")))
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testeTodosStatusAcessoNegado() throws Exception {
		String uri = "/lista";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).with((httpBasic("joao", "joao123")))
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);
	}
	
	@Test
	public void testeStatusCriado() throws Exception {
		String uri = "/lista";
		
		String inputJson = "{\"status\":\"a\",\"anime\":{\"id\":2}}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).with((httpBasic("joao", "joao123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	public void testeStatusNaoCriadoRepetido() throws Exception {
		String uri = "/lista";
		
		String inputJson = "{\"status\":\"abc\",\"anime\":{\"id\":1}}";	
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(406, status);
	}
	
	@Test
	public void testeStatusAtualizado() throws Exception {
		String uri = "/lista/1";
		
		String inputJson = "{\"status\":\"abc\",\"anime\":{\"id\":1}}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testeStatusNaoAtualizadoAcessoNegado() throws Exception {
		String uri = "/lista/1";
		
		String inputJson = "{\"status\":\"abc\",\"anime\":{\"id\":1}}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("joao", "joao123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(401, status);
	}
	
	@Test
	public void testeStatusNaoAtualizadoNaoEncontrado() throws Exception {
		String uri = "/lista/24";
		
		String inputJson = "{\"status\":\"abc\",\"anime\":{\"id\":1}}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}
	
	@Test
	public void testeStatusApagado() throws Exception {
		String uri = "/lista/2";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("joao", "joao123"))))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testeStatusNaoApagadoAcessoNegado() throws Exception {
		String uri = "/lista/1";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("joao", "joao123"))))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(401, status);
	}
	
	@Test
	public void testeStatusNaoApagadoNaoEncontrado() throws Exception {
		String uri = "/lista/15";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("dudu", "dudu123"))))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}
}
