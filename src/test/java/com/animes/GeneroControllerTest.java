package com.animes;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class GeneroControllerTest extends DefaultTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testeTodosGeneros() throws Exception {
		String uri = "/generos";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).with((httpBasic("dudu", "dudu123")))
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@Test
	public void testeTodosGenerosAcessoNegado() throws Exception {
		String uri = "/generos";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).with((httpBasic("joao", "joao123")))
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);

	}

	@Test
	public void testeGeneroCriado() throws Exception {
		String uri = "/generos";

		String inputJson = "{\"nome\":\"generoteste\"}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	public void testeGeneroNaoCriadoNomeExistente() throws Exception {
		String uri = "/generos";

		String inputJson = "{\"nome\":\"genero1\"}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(406, status);
	}
	
	@Test
	public void testeGeneroNaoCriadoAcessoNegado() throws Exception {
		String uri = "/generos";

		String inputJson = "{\"nome\":\"generoteste\"}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).with((httpBasic("joao", "joao123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);
	}
	
	@Test
	public void testeGeneroAtualizado() throws Exception {
		String uri = "/generos/1";

		String inputJson = "{\"nome\":\"generoteste1\"}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testeGeneroNaoAtualizadoNaoEncontrado() throws Exception {
		String uri = "/generos/100";

		String inputJson = "{\"nome\":\"generoteste3\"}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}
	
	@Test
	public void testeGeneroNaoAtualizadoAcessoNegado() throws Exception {
		String uri = "/generos/1";

		String inputJson = "{\"nome\":\"generoteste4\"}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("joao", "joao123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);
	}

	@Test
	public void testeGeneroApagado() throws Exception {
		String uri = "/generos/2";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("dudu", "dudu123"))))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testeGeneroNaoApagadoNaoEncontrado() throws Exception {
		String uri = "/generos/20";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("dudu", "dudu123"))))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}
	
	@Test
	public void testeGeneroNaoApagadoAcessoNegado() throws Exception {
		String uri = "/generos/2";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("joao", "joao123"))))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);
	}
}
