
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 188
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 180 (class 1259 OID 16511)
-- Name: language; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE language (
    id integer NOT NULL,
    iso_name character varying(2)
);


ALTER TABLE language OWNER TO webadmin;

--
-- TOC entry 181 (class 1259 OID 16514)
-- Name: language_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE language_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE language_id_seq OWNER TO webadmin;

--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 181
-- Name: language_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE language_id_seq OWNED BY language.id;


--
-- TOC entry 182 (class 1259 OID 16516)
-- Name: rate; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE rate (
    id integer NOT NULL,
    record_id bigint NOT NULL,
    rate integer NOT NULL
);


ALTER TABLE rate OWNER TO webadmin;

--
-- TOC entry 183 (class 1259 OID 16519)
-- Name: rate_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE rate_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE rate_id_seq OWNER TO webadmin;

--
-- TOC entry 2153 (class 0 OID 0)
-- Dependencies: 183
-- Name: rate_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE rate_id_seq OWNED BY rate.id;


--
-- TOC entry 184 (class 1259 OID 16521)
-- Name: record; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE record (
    id integer NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    link character varying(255) NOT NULL,
    text_id integer NOT NULL,
    accent_id integer NOT NULL
);


ALTER TABLE record OWNER TO webadmin;

--
-- TOC entry 185 (class 1259 OID 16524)
-- Name: record_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE record_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE record_id_seq OWNER TO webadmin;

--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 185
-- Name: record_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE record_id_seq OWNED BY record.id;


--
-- TOC entry 186 (class 1259 OID 16526)
-- Name: text; Type: TABLE; Schema: public; Owner: webadmin
--

CREATE TABLE text (
    id integer NOT NULL,
    lang_id bigint NOT NULL,
    text pg_catalog.text
);


ALTER TABLE text OWNER TO webadmin;

--
-- TOC entry 187 (class 1259 OID 16532)
-- Name: text_id_seq; Type: SEQUENCE; Schema: public; Owner: webadmin
--

CREATE SEQUENCE text_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE text_id_seq OWNER TO webadmin;

--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 187
-- Name: text_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: webadmin
--

ALTER SEQUENCE text_id_seq OWNED BY text.id;


--
-- TOC entry 2001 (class 2604 OID 16534)
-- Name: id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY language ALTER COLUMN id SET DEFAULT nextval('language_id_seq'::regclass);


--
-- TOC entry 2002 (class 2604 OID 16535)
-- Name: id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY rate ALTER COLUMN id SET DEFAULT nextval('rate_id_seq'::regclass);


--
-- TOC entry 2003 (class 2604 OID 16536)
-- Name: id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY record ALTER COLUMN id SET DEFAULT nextval('record_id_seq'::regclass);


--
-- TOC entry 2005 (class 2604 OID 16537)
-- Name: id; Type: DEFAULT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY text ALTER COLUMN id SET DEFAULT nextval('text_id_seq'::regclass);


--
-- TOC entry 2136 (class 0 OID 16511)
-- Dependencies: 180
-- Data for Name: language; Type: TABLE DATA; Schema: public; Owner: webadmin
--

INSERT INTO language (id, iso_name) VALUES (2, 'ru');
INSERT INTO language (id, iso_name) VALUES (3, 'en');
INSERT INTO language (id, iso_name) VALUES (4, 'ch');
INSERT INTO language (id, iso_name) VALUES (5, 'fr');
INSERT INTO language (id, iso_name) VALUES (6, 'sp');
INSERT INTO language (id, iso_name) VALUES (7, 'it');


--
-- TOC entry 2156 (class 0 OID 0)
-- Dependencies: 181
-- Name: language_id_seq; Type: SEQUENCE SET; Schema: public; Owner: webadmin
--

SELECT pg_catalog.setval('language_id_seq', 7, true);


--
-- TOC entry 2138 (class 0 OID 16516)
-- Dependencies: 182
-- Data for Name: rate; Type: TABLE DATA; Schema: public; Owner: webadmin
--

INSERT INTO rate (id, record_id, rate) VALUES (1, 2, 5);
INSERT INTO rate (id, record_id, rate) VALUES (2, 2, 5);


--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 183
-- Name: rate_id_seq; Type: SEQUENCE SET; Schema: public; Owner: webadmin
--

SELECT pg_catalog.setval('rate_id_seq', 2, true);


--
-- TOC entry 2140 (class 0 OID 16521)
-- Dependencies: 184
-- Data for Name: record; Type: TABLE DATA; Schema: public; Owner: webadmin
--

INSERT INTO record (id, date, link, text_id, accent_id) VALUES (2, '2016-04-16 18:13:05.558797', 'eng_china_dekun_0_0_1.MP3', 1, 4);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (3, '2016-04-16 18:13:05.575442', 'eng_china_Tian_0_0_1.MP3', 1, 4);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (4, '2016-04-16 18:13:05.597314', 'eng_french_FranЗois_0_0_1.MP3', 1, 5);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (5, '2016-04-16 18:13:05.617987', 'eng_rus_rymar_0_0_1.MP3', 1, 2);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (6, '2016-04-16 18:13:05.649748', 'eng_span_nels_0_0_1.MP3', 1, 6);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (7, '2016-04-16 18:16:57.44697', 'french_china_Tian_0_0_1.MP3', 2, 4);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (8, '2016-04-16 18:16:57.471444', 'french_french_FranЗois_0_0_1.MP3', 1, 5);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (9, '2016-04-16 18:16:57.486451', 'rus_china_dekun_0_0_1.MP3', 5, 4);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (10, '2016-04-16 18:16:57.503213', 'rus_rus_alal_0_0_1.MP3', 5, 2);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (11, '2016-04-16 18:16:57.523578', 'rus_span_nels_0_0_1.mp3', 5, 6);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (12, '2016-04-16 18:16:57.538832', 'span_rus_alal_0_0_1.MP3', 4, 2);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (13, '2016-04-16 18:16:57.557014', 'span_span_nels_0_0_1.MP3', 4, 6);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (1, '2016-04-16 18:09:44.269283', 'china_china_dekun_0_0_1.MP3', 3, 4);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (14, '2016-04-17 04:09:56.142024', 'multipartFile', 8, 2);
INSERT INTO record (id, date, link, text_id, accent_id) VALUES (17, '2016-04-17 04:26:30.006371', 'multipartFile', 11, 2);


--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 185
-- Name: record_id_seq; Type: SEQUENCE SET; Schema: public; Owner: webadmin
--

SELECT pg_catalog.setval('record_id_seq', 17, true);


--
-- TOC entry 2142 (class 0 OID 16526)
-- Dependencies: 186
-- Data for Name: text; Type: TABLE DATA; Schema: public; Owner: webadmin
--

INSERT INTO text (id, lang_id, text) VALUES (1, 3, 'It was a good day today! In the morning we met our friends and had a long talk about different things. After dinner the friends left. We stayed alone. The weather was wonderful. We decided to take a stroll. The nature was very pleasant. Soon night fell. We wanted to go back as we had to go to sleep. Maybe we will walk a little bit more?');
INSERT INTO text (id, lang_id, text) VALUES (2, 5, 'Aujourd''hui, nous avons passé une bonne journée! Le matin, nous avons rejoint nos amis et on a parlé de tout et de rien. Après le dîner, nos amis sont partis. Nous sommes restés seuls. Le temps était superbe et nous avons donc décidé de nous promener. La nature était belle, c''était très agréable. Bientôt la nuit tomba, nous devions rentrer nous coucher. Peut-être marcherons-nous un peu plus?');
INSERT INTO text (id, lang_id, text) VALUES (3, 4, '今天是美好的一天。从早上我们和朋友聊了很多。下午朋友们都走了。就剩我们了。天气非常好,我们决定随便走走。美丽的风景环绕着我们。晚上我们准备回去,要知道很快该睡觉了。或者可能我们再散会儿步?');
INSERT INTO text (id, lang_id, text) VALUES (4, 6, 'Hoy fue un buen dia! Por la mañana nos encontramos con nuestros amigos y hablamos mucho de diferentes cosas. Despues del almuerzo nos quedamos solos. Hacia buen tiempo. Decidimos salir a caminar. La naturaleza era muy a gradable. Pronto atardecio. Quisimos volver a casa porque teniamos que ir a acostarnos. Podemos ir a andar un poco mas?');
INSERT INTO text (id, lang_id, text) VALUES (5, 2, 'Сегодня был отличный день! С утра мы встретились с друзьями и долго разговаривали о разных вещах. После обеда друзья уехали. Мы остались одни. Погода была чудесная! Мы решили немного прогуляться. Природа вокруг нас очень радовала. Скоро наступил вечер. Мы хотели вернуться, ведь скоро нужно ложиться спать. А может, еще погуляем?');
INSERT INTO text (id, lang_id, text) VALUES (6, 7, 'Oggi è stata una bella giornata! Stamattina abbiamo incontrato i nostri amici e abbiamo parlato tanto di varie cose. Dopo pranzo gli amici sono andati via. Siamo restati da soli. Il tempo era fantastico! Abbiamo deciso di andare a camminare un po''.  La natura era molto piacevole. Presto è calata la notte. Volevamo tornare perché dovevamo andare a dormire. Magari camminiamo un po'' di più?');
INSERT INTO text (id, lang_id, text) VALUES (7, 2, 'properties');
INSERT INTO text (id, lang_id, text) VALUES (8, 2, 'properties');
INSERT INTO text (id, lang_id, text) VALUES (11, 2, 'properties2');


--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 187
-- Name: text_id_seq; Type: SEQUENCE SET; Schema: public; Owner: webadmin
--

SELECT pg_catalog.setval('text_id_seq', 11, true);


--
-- TOC entry 2008 (class 2606 OID 16539)
-- Name: language_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY language
ADD CONSTRAINT language_pkey PRIMARY KEY (id);


--
-- TOC entry 2011 (class 2606 OID 16541)
-- Name: rate_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY rate
ADD CONSTRAINT rate_pkey PRIMARY KEY (id);


--
-- TOC entry 2014 (class 2606 OID 16543)
-- Name: record_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY record
ADD CONSTRAINT record_pkey PRIMARY KEY (id);


--
-- TOC entry 2017 (class 2606 OID 16545)
-- Name: text_pkey; Type: CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY text
ADD CONSTRAINT text_pkey PRIMARY KEY (id);


--
-- TOC entry 2006 (class 1259 OID 16546)
-- Name: language_id_uindex; Type: INDEX; Schema: public; Owner: webadmin
--

CREATE UNIQUE INDEX language_id_uindex ON language USING btree (id);


--
-- TOC entry 2009 (class 1259 OID 16547)
-- Name: rate_id_uindex; Type: INDEX; Schema: public; Owner: webadmin
--

CREATE UNIQUE INDEX rate_id_uindex ON rate USING btree (id);


--
-- TOC entry 2012 (class 1259 OID 16548)
-- Name: record_id_uindex; Type: INDEX; Schema: public; Owner: webadmin
--

CREATE UNIQUE INDEX record_id_uindex ON record USING btree (id);


--
-- TOC entry 2015 (class 1259 OID 16549)
-- Name: text_id_uindex; Type: INDEX; Schema: public; Owner: webadmin
--

CREATE UNIQUE INDEX text_id_uindex ON text USING btree (id);


--
-- TOC entry 2018 (class 2606 OID 16550)
-- Name: rate_record_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY rate
ADD CONSTRAINT rate_record_id_fk FOREIGN KEY (record_id) REFERENCES record(id);


--
-- TOC entry 2019 (class 2606 OID 16555)
-- Name: record_language_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY record
ADD CONSTRAINT record_language_id_fk FOREIGN KEY (accent_id) REFERENCES language(id);


--
-- TOC entry 2020 (class 2606 OID 16560)
-- Name: record_text_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY record
ADD CONSTRAINT record_text_id_fk FOREIGN KEY (text_id) REFERENCES text(id);


--
-- TOC entry 2021 (class 2606 OID 16565)
-- Name: text_language_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: webadmin
--

ALTER TABLE ONLY text
ADD CONSTRAINT text_language_id_fk FOREIGN KEY (lang_id) REFERENCES language(id);


--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: nicaraguanec
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM webadmin;
GRANT ALL ON SCHEMA public TO webadmin;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-04-17 05:26:15

--
-- PostgreSQL database dump complete
--

