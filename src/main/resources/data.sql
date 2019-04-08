insert into usuario(id, username, password) values(1, 'dudu', '$2a$10$TqDgxCgPiL/A65qn7x5inuSCp8nhnhJ4DN0UDN/qeawF38wHj953G')
insert into usuario(id, username, password) values(2, 'joao', '$2a$10$pkaUItexqLXj22KH1onVmuVecJVQwhAnurq7vuk1MLpiJ6Jv/fyli')
insert into usuario(id, username, password) values(3, 'teste', '$2a$10$PJCil1kq6oDP3BOfc58BueWbcX2yjut8hpGVQ5Yf6jcI1sII9XhhO')


insert into papel(tipo_usuario) values('ROLE_ADMIN')
insert into papel(tipo_usuario) values('ROLE_USUARIO')

insert into usuarios_papeis(usuario_id, papel_id) values(1, 'ROLE_ADMIN')
insert into usuarios_papeis(usuario_id, papel_id) values(2, 'ROLE_USUARIO')
insert into usuarios_papeis(usuario_id, papel_id) values(3, 'ROLE_USUARIO')

insert into anime(id, nome, episodios, classificacao) values(1, 'teste', 12, 'pg-13')
insert into anime(id, nome, episodios, classificacao) values(2, 'teste2', 14, 'r-18')
insert into anime(id, nome, episodios, classificacao) values(3, 'teste3', 13, 'r')

insert into genero(id, nome) values(1, 'genero1')
insert into genero(id, nome) values(2, 'genero2')
insert into genero(id, nome) values(3, 'genero3')

insert into animes_generos(anime_id, genero_id) values (1, 1)
insert into animes_generos(anime_id, genero_id) values (1, 2)
insert into animes_generos(anime_id, genero_id) values (1, 3)

insert into animes_generos(anime_id, genero_id) values (2, 1)
insert into animes_generos(anime_id, genero_id) values (2, 2)

insert into animes_generos(anime_id, genero_id) values (3, 1)

insert into avaliacao(id, usuario_id, anime_id, nota, texto) values(1, 1, 1, 9, 'as')
insert into avaliacao(id, usuario_id, anime_id, nota, texto) values(2, 1, 2, 9, 'adfs')
insert into avaliacao(id, usuario_id, anime_id, nota, texto) values(3, 2, 1, 8, 'snioa')
insert into avaliacao(id, usuario_id, anime_id, nota, texto) values(4, 3, 1, 5, 'dusah')

insert into anime_status(id, usuario_id, anime_id, status) values(1, 1, 1, 'assistindo')
insert into anime_status(id, usuario_id, anime_id, status) values(2, 2, 1, 'assistindo')
