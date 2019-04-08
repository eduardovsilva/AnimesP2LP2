package com.animes;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class AnimeControllerTest extends DefaultTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testeTodosAnimes() throws Exception {
		String uri = "/animes";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	public void testeAnimeEncontrado() throws Exception {
		String uri = "/animes/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(302, status);

	}

	@Test
	public void testeAnimeNaoEncontrado() throws Exception {
		String uri = "/animes/20";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);

	}

	@Test
	public void testeAnimeCriado() throws Exception {
		String uri = "/animes";
	

		String inputJson = "{\"nome\":\"teste\",\"episodios\":6,\"classificacao\":\"abc\",\"generos\":[{\"id\":1},{\"id\":2}]}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	public void testeAnimeCriacaoAcessoNegado() throws Exception {
		String uri = "/animes";

		String inputJson = "{\"nome\":\"teste\",\"episodios\":6,\"classificacao\":\"abc\",\"generos\":[{\"id\":1},{\"id\":2}]}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).with((httpBasic("joao", "joao123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);
	}

	@Test
	public void testeAnimeAtualizado() throws Exception {
		String uri = "/animes/1";

		String inputJson = "{\"nome\":\"teste5\",\"episodios\":8,\"classificacao\":\"abcde\",\"generos\":[{\"id\":1},{\"id\":3}]}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void testeAnimeNaoAtualizadoNaoEncontrado() throws Exception {
		String uri = "/animes/30";

		String inputJson = "{\"nome\":\"teste5\",\"episodios\":8,\"classificacao\":\"abcde\",\"generos\":[{\"id\":1},{\"id\":3}]}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("dudu", "dudu123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}

	@Test
	public void testeAnimeNaoAtualizadoAcessoNegado() throws Exception {
		String uri = "/animes/1";

		String inputJson = "{\"nome\":\"teste5\",\"episodios\":8,\"classificacao\":\"abcde\",\"generos\":[{\"id\":1},{\"id\":3}]}";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).with((httpBasic("joao", "joao123")))
				.contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);
	}

	@Test
	public void testeAnimeApagado() throws Exception {
		String uri = "/animes/3";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("dudu", "dudu123"))))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void testeAnimeNaoApagadoNaoEncontrado() throws Exception {
		String uri = "/animes/30";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("dudu", "dudu123"))))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}

	@Test
	public void testeAnimeNaoApagadoAcessoNegado() throws Exception {
		String uri = "/animes/2";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).with((httpBasic("joao", "joao123"))))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);
	}
}
