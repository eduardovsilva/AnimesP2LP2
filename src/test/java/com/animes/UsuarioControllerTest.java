package com.animes;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class UsuarioControllerTest extends DefaultTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testeTodosUsuarios() throws Exception {
		String uri = "/usuarios";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(uri).with((httpBasic("dudu", "dudu123"))).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	public void testeTodosUsuariosAcessoNegado() throws Exception {
		String uri = "/usuarios";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(uri).with((httpBasic("joao", "joao123"))).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);

	}

	@Test
	public void testeUsuarioEncontrado() throws Exception {
		String uri = "/usuarios/dudu";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(302, status);

	}

	@Test
	public void testeUsuarioNaoEncontrado() throws Exception {
		String uri = "/usuarios/dudu2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);

	}

	@Test
	public void testeUsuarioCriado() throws Exception {
		String uri = "/usuarios";

		String inputJson = "{\"username\":\"teste2\",\"password\":\"123\"}";
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	public void testeUsuarioUsernameExistente() throws Exception {
		String uri = "/usuarios";

		String inputJson = "{\"username\":\"dudu\",\"password\":\"123\"}";
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(406, status);
	}

	@Test
	public void testeUsuarioAtualizado() throws Exception {
		String uri = "/usuarios/2";

		String inputJson = "{\"username\":\"joao\",\"password\":\"joao123\"}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("joao", "joao123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testeUsuarioAtualizadoUsuarioNaoExiste() throws Exception {
		String uri = "/usuarios/2";

		String inputJson = "{\"username\":\"joao\",\"password\":\"joao123\"}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("abc", "123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(401, status);
	}

	@Test
	public void testeUsuarioNaoAtualizadoAcessoNegado() throws Exception {
		String uri = "/usuarios/1";

		String inputJson = "{\"username\":\"joao\",\"password\":\"joao123\"}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("joao", "joao123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(401, status);
	}

	@Test
	public void testeUsuarioNaoAtualizadoNaoEncontrado() throws Exception {
		String uri = "/usuarios/4";

		String inputJson = "{\"username\":\"joao\",\"password\":\"joao123\"}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("joao", "joao123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}

	@Test
	public void testeUsuarioApagado() throws Exception {
		String uri = "/usuarios/3";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("teste", "teste123"))))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void testeUsuarioNaoApagadoAcessoNegado() throws Exception {
		String uri = "/usuarios/3";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("joao", "joao123"))))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(401, status);
	}

	@Test
	public void testeUsuarioNaoApagadoNaoEncontrado() throws Exception {
		String uri = "/usuarios/4";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("joao", "joao123"))))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}
}
