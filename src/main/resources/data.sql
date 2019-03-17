insert into usuario(id, tipo_usuario, nome) values(1, 'admin', 'dudu')

insert into anime(id, nome, episodios, classificacao, admin_id) values(1, 'teste', 12, 'pg-13', 1)

insert into anime(id, nome, episodios, classificacao, admin_id) values(2, 'teste2', 14, 'r-18', 1)

insert into genero(id, nome, admin_id) values(1, 'genero1', 1)
insert into genero(id, nome, admin_id) values(2, 'genero2', 1)
insert into genero(id, nome, admin_id) values(3, 'genero3', 1)

insert into anime_genero(anime_id, genero_id) values (1, 1)
insert into anime_genero(anime_id, genero_id) values (1, 2)
insert into anime_genero(anime_id, genero_id) values (1, 3)

insert into anime_genero(anime_id, genero_id) values (2, 1)
insert into anime_genero(anime_id, genero_id) values (2, 2)

insert into av_nota(id, usuario_id, anime_id, valor) values(1, 1, 1, 9)
insert into av_nota(id, usuario_id, anime_id, valor) values(2, 1, 2, 8)

insert into av_texto(id, usuario_id, anime_id, corpo) values(1, 1, 1, 'abc')
insert into av_texto(id, usuario_id, anime_id, corpo) values(2, 1, 2, 'def')