--Users
INSERT INTO users(username,password) VALUES('user1','$2a$10$cwslzAkqoZgLsc0h5pExKee4cVZT56CfOloXW8SRcrXIMnx19O9u6');
INSERT INTO users(username,password) VALUES('user2','$2a$10$cwslzAkqoZgLsc0h5pExKee4cVZT56CfOloXW8SRcrXIMnx19O9u6');
INSERT INTO users(username,password) VALUES('user3','$2a$10$cwslzAkqoZgLsc0h5pExKee4cVZT56CfOloXW8SRcrXIMnx19O9u6');
INSERT INTO users(username,password) VALUES('user4','$2a$10$cwslzAkqoZgLsc0h5pExKee4cVZT56CfOloXW8SRcrXIMnx19O9u6');
INSERT INTO users(username,password) VALUES('user5','$2a$10$cwslzAkqoZgLsc0h5pExKee4cVZT56CfOloXW8SRcrXIMnx19O9u6');

--Game
INSERT INTO games(name,creator_username,max_players,turn) VALUES ('prueba','user1',3,-1);

--Players
INSERT INTO players(user_id,game_id,coins,wounds,character_name) VALUES (1,1,0,0,0);
INSERT INTO players(user_id,game_id,coins,wounds,character_name) VALUES (2,1,0,0,0);
INSERT INTO players(user_id,game_id,coins,wounds,character_name) VALUES (3,1,0,0,0);

--Traps
INSERT INTO rooms(name,room_type,value_two,value_three,value_four,value_five,target,damage_type) VALUES('ATRAPAMONEDAS','TRAP',-1,1,2,2,1,1);
INSERT INTO rooms(name,room_type,value_two,value_three,value_four,value_five,target,damage_type) VALUES('CALDERO DE LAVA','TRAP',0,1,2,3,1,1);
INSERT INTO rooms(name,room_type,value_two,value_three,value_four,value_five,target,damage_type) VALUES('TRAMPA DE PINCHOS','TRAP',1,1,1,2,0,0);
INSERT INTO rooms(name,room_type,value_two,value_three,value_four,value_five,target,damage_type) VALUES('ROCA GIGANTE','TRAP',0,1,2,2,0,0);
--Enemies
INSERT INTO rooms(name,room_type,health_three,health_four,health_five,damage) VALUES('DRAG&Oacute;N','ENEMY',14,18,23,3);
INSERT INTO rooms(name,room_type,health_three,health_four,health_five,damage) VALUES('DRAG&Oacute;N','ENEMY',14,18,23,3);
INSERT INTO rooms(name,room_type,health_three,health_four,health_five,damage) VALUES('TROLL','ENEMY',14,18,23,2);
INSERT INTO rooms(name,room_type,health_three,health_four,health_five,damage) VALUES('TROLL','ENEMY',14,18,23,2);
INSERT INTO rooms(name,room_type,health_three,health_four,health_five,damage) VALUES('LA COSA','ENEMY',14,18,23,1);
INSERT INTO rooms(name,room_type,health_three,health_four,health_five,damage) VALUES('GOBLIN','ENEMY',11,14,18,2);
INSERT INTO rooms(name,room_type,health_three,health_four,health_five,damage) VALUES('GOBLIN','ENEMY',11,14,18,2);
INSERT INTO rooms(name,room_type,health_three,health_four,health_five,damage) VALUES('SERPIENTE','ENEMY',8,10,13,3);
INSERT INTO rooms(name,room_type,health_three,health_four,health_five,damage) VALUES('ESQUELETO','ENEMY',11,14,18,3);
INSERT INTO rooms(name,room_type,health_three,health_four,health_five,damage) VALUES('ZOMBI','ENEMY',11,14,18,1);
INSERT INTO rooms(name,room_type,health_three,health_four,health_five,damage) VALUES('ZOMBI','ENEMY',11,14,18,1);
--Shop
INSERT INTO rooms(name,room_type,first_item,second_item,third_item,fourth_item,fifth_item) VALUES('C&Aacute;MARA','SHOP',2,3,0,4,1);
INSERT INTO rooms(name,room_type,first_item,second_item,third_item,fourth_item,fifth_item) VALUES('C&Aacute;MARA','SHOP',2,3,0,4,1);
INSERT INTO rooms(name,room_type,first_item,second_item,third_item,fourth_item,fifth_item) VALUES('C&Aacute;MARA','SHOP',5,6,7,8,0);
INSERT INTO rooms(name,room_type,first_item,second_item,third_item,fourth_item,fifth_item) VALUES('C&Aacute;MARA','SHOP',5,6,7,8,0);
INSERT INTO rooms(name,room_type,first_item,second_item,third_item,fourth_item,fifth_item) VALUES('C&Aacute;MARA','SHOP',5,6,7,8,0);
--Treasure
INSERT INTO rooms(name,room_type,first_chest_coins,second_chest_coins) VALUES('TESORO','TREASURE',4,2);
INSERT INTO rooms(name,room_type,first_chest_coins,second_chest_coins) VALUES('TESORO','TREASURE',4,2);
INSERT INTO rooms(name,room_type,first_chest_coins,second_chest_coins) VALUES('TESORO','TREASURE',4,0);
INSERT INTO rooms(name,room_type,first_chest_coins,second_chest_coins) VALUES('TESORO','TREASURE',3,2);
INSERT INTO rooms(name,room_type,first_chest_coins,second_chest_coins) VALUES('TESORO','TREASURE',3,0);
INSERT INTO rooms(name,room_type,first_chest_coins,second_chest_coins) VALUES('TESORO','TREASURE',2,1);
INSERT INTO rooms(name,room_type,first_chest_coins,second_chest_coins) VALUES('TESORO','TREASURE',2,1);
INSERT INTO rooms(name,room_type,first_chest_coins,second_chest_coins) VALUES('TESORO','TREASURE',2,0);
INSERT INTO rooms(name,room_type,first_chest_coins,second_chest_coins) VALUES('TESORO','TREASURE',1,0);
--Bosses
INSERT INTO rooms(name,room_type,damage_type,damage_amount,escape_card,health_three,health_four,health_five,top_card_coin_reward) VALUES('MINOTAURO','FINAL_BOSS',0,1,null,14,18,23,1);
INSERT INTO rooms(name,room_type,damage_type,damage_amount,escape_card,health_three,health_four,health_five,top_card_coin_reward) VALUES('MEGADRAG&Oacute;N','FINAL_BOSS',0,3,7,99,99,99,0);
INSERT INTO rooms(name,room_type,damage_type,damage_amount,escape_card,health_three,health_four,health_five,top_card_coin_reward) VALUES('NIGROMANTE','FINAL_BOSS',0,2,6,14,18,23,0);
INSERT INTO rooms(name,room_type,damage_type,damage_amount,escape_card,health_three,health_four,health_five,top_card_coin_reward) VALUES('MEDUSA','FINAL_BOSS',0,99,null,11,14,16,0);
INSERT INTO rooms(name,room_type,damage_type,damage_amount,escape_card,health_three,health_four,health_five,top_card_coin_reward) VALUES('JAUR&acute;A DE LOBOS','FINAL_BOSS',0,2,5,11,14,16,0);
INSERT INTO rooms(name,room_type,damage_type,damage_amount,escape_card,health_three,health_four,health_five,top_card_coin_reward) VALUES('VAMPIRO','FINAL_BOSS',2,2,null,14,18,23,0);
INSERT INTO rooms(name,room_type,damage_type,damage_amount,escape_card,health_three,health_four,health_five,top_card_coin_reward) VALUES('RECAUDADOR','FINAL_BOSS',1,3,null,11,14,16,0);
INSERT INTO rooms(name,room_type,damage_type,damage_amount,escape_card,health_three,health_four,health_five,top_card_coin_reward) VALUES('ESFINGE','FINAL_BOSS',0,2,null,11,14,16,2);