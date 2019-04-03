insert into usuario(login, senha) values('dudu', '$2a$10$TqDgxCgPiL/A65qn7x5inuSCp8nhnhJ4DN0UDN/qeawF38wHj953G')
insert into usuario(login, senha) values('joao', '$2a$10$pkaUItexqLXj22KH1onVmuVecJVQwhAnurq7vuk1MLpiJ6Jv/fyli')

insert into papel(tipo_usuario) values('ROLE_ADMIN')
insert into papel(tipo_usuario) values('ROLE_USUARIO')

insert into usuarios_papeis(usuario_login, papel_id) values('dudu', 'ROLE_ADMIN')
insert into usuarios_papeis(usuario_login, papel_id) values('joao', 'ROLE_USUARIO')

insert into anime(nome, episodios, classificacao) values('teste', 12, 'pg-13')
insert into anime(nome, episodios, classificacao) values('teste2', 14, 'r-18')

insert into genero(nome) values('genero1')
insert into genero(nome) values('genero2')
insert into genero(nome) values('genero3')

insert into animes_generos(anime_id, genero_id) values (1, 1)
insert into animes_generos(anime_id, genero_id) values (1, 2)
insert into animes_generos(anime_id, genero_id) values (1, 3)

insert into animes_generos(anime_id, genero_id) values (2, 1)
insert into animes_generos(anime_id, genero_id) values (2, 2)

insert into avaliacao(usuario_login, anime_id, nota, texto) values('dudu', 1, 9, 'as')
insert into avaliacao(usuario_login, anime_id, nota, texto) values('joao', 2, 8, 'snioa')

insert into anime_status(usuario_login, anime_id, status) values('dudu', 1, 'assistindo')
