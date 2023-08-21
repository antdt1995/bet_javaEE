INSERT INTO public.football_team (league,manager,team_name) VALUES
	 ('EPL','Erik Ten Hag','Man Utd'),
	 ('EPL','Pep Guardiola','Man City'),
	 ('EPL','Jurgen Klopp','Liverpool'),
	 ('EPL','Frank Lampard','Chelsea'),
	 ('EPL','Mikel Arteta','Arsenal'),
	 ('La Liga','Xavi','Barcelona'),
	 ('La Liga','Carlo Ancelotti','Real Madrid'),
	 ('La Liga','Diego Simeone','Atletico'),
	 ('La Liga','Jose Luis Mendilibar','Sevilla'),
	 ('Serie A','Stefano Pioli','Milan');
INSERT INTO public.football_team (league,manager,team_name) VALUES
	 ('Serie A','Simone Inzaghi','Inter'),
	 ('Serie A','Luciano Spalletti','Napoli'),
	 ('Serie A','Massimiliano Allegri','Juventus'),
	 ('Liga Portugal Bwin','Roger Schmidt','Benfica'),
	 ('Liga Portugal Bwin','Sergio Conceicao','Porto'),
	 ('Liga Portugal Bwin','Ruben Amorim','Sporting'),
	 ('Ligue 1','Christophe Galtier','PSG'),
	 ('Ligue 1','Igor Tudor','Marseille'),
	 ('Ligue 1','Philippe Clement','Monaco'),
	 ('Bundesliga','Thomas Tuchel','Bayern Munich');
INSERT INTO public.football_team (league,manager,team_name) VALUES
	 ('Bundesliga','Edin Terzic','Dortmund');

INSERT INTO public.football_match (away_team_score,home_team_score,start_date,away_team_id,home_team_id) VALUES
	 (1,1,'2023-04-20 00:00:00.000',2,20),
	 (3,3,'2023-04-20 00:00:00.000',14,11),
	 (1,6,'2017-03-08 00:00:00.000',17,6),
	 (0,7,'2023-03-05 00:00:00.000',1,3),
	 (1,6,'2023-03-05 00:00:00.000',9,8),
	 (0,2,'2023-03-08 00:00:00.000',21,4),
	 (0,2,'2023-03-09 00:00:00.000',17,20),
	 (0,0,'2023-03-15 00:00:00.000',11,15),
	 (0,1,'2023-03-16 00:00:00.000',3,7),
	 (1,1,'2023-04-05 00:00:00.000',11,13);
	
	INSERT INTO public.customer (bank_account,bank_name,first_name,last_name,phone) VALUES
	 ('2924914841','Digitube','Elijah','Grigorian','679-124-1783'),
	 ('2822294917','Topdrive','Amandie','Dyzart','689-594-1432'),
	 ('9324268627','Zoomdog','Thomas','Gibling','304-723-6878'),
	 ('3862980782','Blogtag','Dukey','Bannon','788-145-8763'),
	 ('7884501953','Kwideo','Karlis','Gillyett','554-618-2864'),
	 ('8460608182','Twitterbeat','Jami','Vane','735-933-7437'),
	 ('0526983191','Mita','Adel','Wardington','659-337-4965'),
	 ('9285215203','Leexo','Noellyn','Gowlett','973-375-8790'),
	 ('1674574843','Teklist','Cathleen','Emm','247-764-0452'),
	 ('3454388368','Mybuzz','Karlie','Fante','436-117-3907');
INSERT INTO public.customer (bank_account,bank_name,first_name,last_name,phone) VALUES
	 ('6383464310','InnoZ','Lindsay','Wedlock','575-277-9918'),
	 ('3332157205','Chatterpoint','Pavia','Conen','282-585-8478'),
	 ('7997816895','Wordpedia','Brok','Arnaut','665-305-9745'),
	 ('4578313069','Feedbug','Carlie','Windrum','883-542-5503'),
	 ('6731262706','Oyoyo','Winnie','Hayers','422-677-4228'),
	 ('4312598045','Skinte','Bradly','Polfer','670-955-4280'),
	 ('1435168054','Twitterlist','Samuel','Langeley','354-519-6006'),
	 ('3879599084','Wikivu','Antoni','Hallagan','275-626-1671'),
	 ('4603979290','Skilith','Emilee','Francescuccio','426-443-7142'),
	 ('2907689959','Trunyx','Dollie','Goldston','985-910-4611');
INSERT INTO public.customer (bank_account,bank_name,first_name,last_name,phone) VALUES
	 ('56416516146','LukaKu','rick','supper','563-4674-4655'),
	 ('546514654','asdasd','adasd','adsadasd','54665146'),
	 ('651563156','adsasdasd','asdasd','dasdasd','3513210651'),
	 ('651563156','adsasdasd','asdasd','dasdasd','3513210651'),
	 (NULL,NULL,'Binladen','osama','0911911991'),
	 ('12312512512','asdadaxd1','huy','cute','54665146'),
	 ('0919111511','Bank Of Messi','huy','cute','54665146');
	
	
	INSERT INTO public.account (active,email,"password",total_balance,user_name,customer_id) VALUES
	 (false,'bbode4@amazon.co.uk','$2y$10$otjpzdcqv0srodvjcstfm.kp0xxJcCZsZljk5QB78cirkmvm6EKRi',9718.0,'csole4',5),
	 (false,'jsoff5@shop-pro.jp','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',6354.0,'kwaddams5',6),
	 (false,'dfolcarelli6@tripod.com','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',1330.0,'fcornner6',7),
	 (false,'nchalloner7@upenn.edu','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',8796.0,'lmertsching7',8),
	 (false,'strew1@nhs.uk','$2y$10$/wp8avzumg0rshd.otrcloewxl7tE5HvoC1wrhI7zJxY5R5/1obFK',9225.0,'kmcgoogan1',2),
	 (false,'waylett2@dailymail.co.uk','$2y$10$yYbBeL31zz25K3xF4AkqVuye7T74qOj3nzy1i/OxZ0HAnOIoo9Gfu',5933.0,'mlascelles2',3),
	 (false,'mpuffett3@friendfeed.com','$2y$10$81N/WEN4af4d4FNL/j9O6e3orNGHMIkEp9kUHmg/kv3foUMejcO4W',7390.0,'nleuty3',4),
	 (false,'stether8@prnewswire.com','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',9478.0,'kmenhenitt8',9),
	 (false,'fbysaker9@cdc.gov','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',5584.0,'emoth9',10),
	 (false,'lsaipya@twitpic.com','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',4186.0,'tjewesa',11);
INSERT INTO public.account (active,email,"password",total_balance,user_name,customer_id) VALUES
	 (false,'spersentb@netlog.com','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',6566.0,'pmccormackb',12),
	 (false,'mphilpsc@1688.com','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',8880.0,'tdearnaleyc',13),
	 (false,'cstrangmand@printfriendly.com','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',1509.0,'nbutchersd',14),
	 (false,'goheffernane@google.es','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',1924.0,'pburchfielde',15),
	 (false,'ganersenf@state.tx.us','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',7517.0,'dcowoppef',16),
	 (false,'mfladeg@csmonitor.com','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',8696.0,'curieng',17),
	 (false,'afayterh@flavors.me','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',1774.0,'lpetrashkovh',18),
	 (false,'jbeldeni@liveinternet.ru','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',2273.0,'cbrumbyi',19),
	 (false,'cdunleaj@slideshare.net','$2y$10$oxgfwg5hflvztm/ysfn6zercfa4w.mqr27/tqzpnzob.lybdgozqu',6100.0,'fbowditchj',20),
	 (true,'asdasd@gmail.com','$2a$10$tbQgu9DdBlu9QEMd9VP.L.iY/RENPehljteFLvwtm36YHFPECQM2i',NULL,'abc123',24);


