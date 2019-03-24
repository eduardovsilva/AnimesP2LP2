insert into usuario(login, senha) values('dudu', '$2a$10$TqDgxCgPiL/A65qn7x5inuSCp8nhnhJ4DN0UDN/qeawF38wHj953G')
insert into usuario(login, senha) values('joao', '$2a$10$pkaUItexqLXj22KH1onVmuVecJVQwhAnurq7vuk1MLpiJ6Jv/fyli')

insert into papel(tipo_usuario) values('ROLE_ADMIN')
insert into papel(tipo_usuario) values('ROLE_USUARIO')

insert into usuarios_papeis(usuario_id, papel_id) values('dudu', 'ROLE_ADMIN')
insert into usuarios_papeis(usuario_id, papel_id) values('joao', 'ROLE_USUARIO')

insert into anime(id, nome, episodios, classificacao) values(1, 'teste', 12, 'pg-13')
insert into anime(id, nome, episodios, classificacao) values(2, 'teste2', 14, 'r-18')

insert into genero(nome) values('genero1')
insert into genero(nome) values('genero2')
insert into genero(nome) values('genero3')

insert into animes_generos(anime_id, genero_id) values (1, 'genero1')
insert into animes_generos(anime_id, genero_id) values (1, 'genero2')
insert into animes_generos(anime_id, genero_id) values (1, 'genero3')

insert into animes_generos(anime_id, genero_id) values (2, 'genero1')
insert into animes_generos(anime_id, genero_id) values (2, 'genero2')

insert into avaliacao(id, usuario_id, anime_id, nota, texto) values(1, 'dudu', 1, 9, 'as')
insert into avaliacao(id, usuario_id, anime_id, nota, texto) values(2, 'joao', 2, 8, 'snioa')
