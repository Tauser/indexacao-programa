
CREATE TABLE wp_posts (
  ID int NOT NULL,
  post_author int NOT NULL,
  post_date datetime,
  post_date_gmt datetime ,
  post_content varchar(255),
  post_title varchar(255),
  post_excerpt varchar(255),
  post_status varchar(255),
  comment_status varchar(255),
  ping_status varchar(255),
  post_password varchar(255),
  post_name varchar(200) ,
  to_ping varchar(255),
  pinged varchar(255),
  post_modified datetime,
  post_modified_gmt datetime,
  post_content_filtered varchar(255),
  post_parent varchar(255),
  guid varchar(255),
  menu_order int,
  post_type varchar(255),
  post_mime_type varchar(100),
  comment_count int,
  PRIMARY KEY (ID)
);

CREATE TABLE wp_postmeta (
  meta_id int NOT NULL,
  post_id int NOT NULL,
  meta_key varchar(255),
  meta_value longtext,
  PRIMARY KEY (meta_id)
);

CREATE TABLE wp_term_relationships (
  object_id int NOT NULL ,
  term_taxonomy_id int NOT NULL,
  term_order int NOT NULL,
  PRIMARY KEY (object_id, term_taxonomy_id)
);

CREATE TABLE wp_term_taxonomy (
  term_taxonomy_id int NOT NULL,
  term_id int NOT NULL,
  taxonomy varchar(32) NOT NULL,
  description varchar(255) NOT NULL,
  parent int NOT NULL,
  count int NOT NULL,
  PRIMARY KEY (term_taxonomy_id)
);

CREATE TABLE wp_terms (
  term_id int NOT NULL,
  name varchar(200) NOT NULL,
  slug varchar(200) NOT NULL,
  term_group int NOT NULL,
  PRIMARY KEY (term_id)
);

-- notícias
INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (1, 1, PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste edicao_programa_radi 1</p>','Titulo teste 1','Teste 1','publish','open','closed','','Teste 1','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste1',0,'edicao_programa_radi','',0);

INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (2, 1, PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste edicao_programa_radi 2</p>','Titulo teste 2','Teste 2','publish','open','closed','','Teste 2','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste2',0,'edicao_programa_radi','',0);

INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (3, 1, PARSEDATETIME('17/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste edicao_programa_radi 3</p>','Titulo teste 3','Teste 3','publish','open','closed','','Teste 3','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste3',0,'edicao_programa_radi','',0);

INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (4, 1, PARSEDATETIME('17/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste edicao_programa_tv 3</p>','Titulo teste 4','Teste 4','publish','open','closed','','Teste 3','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste3',0,'edicao_programa_tv','',0);

INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (5, 1, PARSEDATETIME('17/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste edicao_programa_tv 3</p>','Titulo teste 5','Teste 5','publish','open','closed','','Teste 3','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste3',0,'edicao_programa_tv','',0);

INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (6, 1, PARSEDATETIME('17/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste edicao_programa_tv 6</p>','Titulo teste 6','Teste 6','publish','open','closed','','Teste 3','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste3',0,'edicao_programa_tv','',0);

INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (7, 1, PARSEDATETIME('17/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste programa radio 7</p>','Câmara Aberta','Teste 7','publish','open','closed','','Teste 3','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste7',0,'programa_radio','',0);

INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (8, 1, PARSEDATETIME('17/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste programa radio 8</p>','Jogo Rápido','Teste 8','publish','open','closed','','Teste 3','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste8',0,'programa_radio','',0);

INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (9, 1, PARSEDATETIME('17/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste programa radio 9</p>','A Voz do Brasil','teste 9','publish','open','closed','','Teste 9','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste9',0,'programa_radio','',0);

INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (10, 1, PARSEDATETIME('17/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste programa tv 10</p>','Câmara Agora','teste 10','publish','open','closed','','Teste 10','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste10',0,'programa_tv','',0);

INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (11, 1, PARSEDATETIME('17/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste programa tv 11</p>','Mulheres no Parlamento','Teste 11','publish','open','closed','','Teste 11','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste11',0,'programa_tv','',0);

INSERT INTO wp_posts(ID,post_author,post_date,post_date_gmt,post_content,post_title,post_excerpt,post_status,comment_status,ping_status,post_password,post_name,
to_ping,pinged,post_modified,post_modified_gmt,post_content_filtered,post_parent,guid,menu_order,post_type,post_mime_type,comment_count)
	VALUES (12, 1, PARSEDATETIME('17/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
'<p>Conteudo teste programa tv 12</p>','Memória Política','Teste 12','publish','open','closed','','Teste 12','','',	PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'), 
PARSEDATETIME('16/05/2019 10:00:36', 'dd/MM/yyyy HH:mm:ss'),'',	'0','/teste12',0,'programa_tv','',0);


 -- Post Meta
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (1, 1, 'cd_rodape', 'Materia 1 Rodape');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (2, 1, 'cd_deputados', '73653');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (3, 1, 'cd_relacionadas', '5');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (4, 1, 'cd_relacionadas', '6');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (5, 1, 'cd_programaPrincipal', '7');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (6, 1, 'cd_postAudio', '60');

INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (10, 2, 'cd_rodape', 'Materia 2 Rodape');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (11, 2, 'cd_deputados', '74047');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (12, 2, 'cd_deputados', '141436');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (13, 2, 'cd_relacionadas', '6');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (14, 2, 'cd_programaPrincipal', '8');

INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (21, 3, 'cd_rodape', 'Materia 3 Rodape');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (22, 3, 'cd_deputados', '73938');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (23, 3, 'cd_relacionadas', '7');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (24, 3, 'cd_programaPrincipal', '9');

INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (35, 4, 'cd_rodape', 'Materia 4 Rodape');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (36, 4, 'cd_deputados', '141436');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (37, 4, 'cd_programaPrincipal', '10');

INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (41, 5, 'cd_rodape', 'Materia 4 Rodape');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (42, 5, 'cd_programaPrincipal', '11');

INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (50, 6, 'cd_rodape', 'Materia 6 Rodape');
INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (51, 6, 'cd_programaPrincipal', '12');

INSERT INTO wp_postmeta(meta_id, post_id, meta_key, meta_value) VALUES (60, 60, '_wp_attachment_metadata', 'a:22:{s:10:"dataformat";s:3:"mp3";s:8:"channels";i:2;s:11:"sample_rate";i:48000;s:7:"bitrate";d:163226.70375521557;s:11:"channelmode";s:6:"stereo";s:12:"bitrate_mode";s:3:"vbr";s:8:"lossless";b:0;s:15:"encoder_options";s:3:"VBR";s:17:"compression_ratio";d:0.1062673852573018;s:10:"fileformat";s:3:"mp3";s:8:"filesize";i:720056;s:9:"mime_type";s:10:"audio/mpeg";s:6:"length";i:35;s:16:"length_formatted";s:4:"0:35";s:4:"text";s:43:"https://www.youtube.com/watch?v=NImeDE7ZM0E";s:6:"artist";s:21:"Videos Engraados 2016";s:5:"title";s:19:"Vdeo curto Engraado";s:14:"recording_time";s:8:"20170703";s:16:"encoder_settings";s:13:"Lavf57.56.101";s:4:"year";s:4:"2017";s:5:"image";a:3:{s:4:"mime";s:10:"image/jpeg";s:5:"width";i:480;s:6:"height";i:360;}s:5:"album";s:0:"";}');

-- wp_term_relationships
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (1, 1, 0);
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (1, 2, 0);
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (2, 2, 0);
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (3, 3, 0);
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (4, 1, 0);
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (4, 3, 0);
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (6, 3, 0);

INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (1, 4, 0);
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (2, 5, 0);
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (3, 4, 0);
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (3, 5, 0);
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (4, 6, 0);
INSERT INTO wp_term_relationships (object_id, term_taxonomy_id, term_order) VALUES (5, 4, 0);


-- wp_term_taxonomy
INSERT INTO wp_term_taxonomy (term_taxonomy_id, term_id, taxonomy, description, parent, count) VALUES (1, 1, 'post_tag', '', 0, 0);
INSERT INTO wp_term_taxonomy (term_taxonomy_id, term_id, taxonomy, description, parent, count) VALUES (2, 2, 'post_tag', '', 0, 0);
INSERT INTO wp_term_taxonomy (term_taxonomy_id, term_id, taxonomy, description, parent, count) VALUES (3, 3, 'post_tag', '', 0, 0);
INSERT INTO wp_term_taxonomy (term_taxonomy_id, term_id, taxonomy, description, parent, count) VALUES (4, 4, 'tema', '', 0, 0);
INSERT INTO wp_term_taxonomy (term_taxonomy_id, term_id, taxonomy, description, parent, count) VALUES (5, 5, 'tema', '', 0, 0);
INSERT INTO wp_term_taxonomy (term_taxonomy_id, term_id, taxonomy, description, parent, count) VALUES (6, 6, 'tema', '', 0, 0);

-- wp_terms
INSERT INTO wp_terms (term_id, name, slug, term_group) VALUES (1, 'Meio ambiente', 'meio-ambiente', 0);
INSERT INTO wp_terms (term_id, name, slug, term_group) VALUES (2, 'Saiba mais', 'saiba-mais', 0);
INSERT INTO wp_terms (term_id, name, slug, term_group) VALUES (3, 'quebra de decoro', 'quebra-de-decoro', 0);
-- Retrancas
INSERT INTO wp_terms (term_id, name, slug, term_group) VALUES (4, 'Economia', 'economia', 0);
INSERT INTO wp_terms (term_id, name, slug, term_group) VALUES (5, 'Cidades', 'cidades', 0);
INSERT INTO wp_terms (term_id, name, slug, term_group) VALUES (6, 'Trabalho', 'Trabalho', 0);


