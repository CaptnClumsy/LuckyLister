begin;
create table user_shiny_pokemon (
  id bigint not null,
  userid bigint not null references users(id),
  pokemonid bigint not null references pokemon(id),
  primary key (id)
);
create index usp_by_user on user_shiny_pokemon(userid);
create unique index on user_shiny_pokemon(userid, pokemonid);
alter table pokemon add shiny boolean;
alter table pokemon add costume bigint;
alter table pokemon add region bigint;
update pokemon set shiny=false, costume=0, region=0;

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,1,0,0,'Bulbasaur',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,2,0,0,'Ivysaur',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,3,0,0,'Venusaur',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,4,0,0,'Charmander',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,5,0,0,'Charmeleon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,6,0,0,'Charizard',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,7,0,0,'Squirtle',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,8,0,0,'Wartortle',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,9,0,0,'Blastoise',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,7,5,0,'Squirtle',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,8,5,0,'Wartortle',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,9,5,0,'Blastoise',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,10,0,0,'Caterpie',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,11,0,0,'Metapod',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,12,0,0,'Butterfree',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,16,0,0,'Pidgey',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,17,0,0,'Pidgeotto',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,18,0,0,'Pidgeot',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,19,0,0,'Rattata',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,20,0,0,'Raticate',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,19,0,61,'Rattata',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,20,0,61,'Raticate',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,23,0,0,'Ekans',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,24,0,0,'Arbok',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,25,0,0,'Pikachu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,26,0,0,'Raichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,25,2,0,'Pikachu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,26,2,0,'Raichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,25,3,0,'Pikachu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,26,3,0,'Raichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,25,6,0,'Pikachu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,26,6,0,'Raichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,25,4,0,'Pikachu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,26,4,0,'Raichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,25,1,0,'Pikachu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,26,1,0,'Raichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,25,7,0,'Pikachu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,26,7,0,'Raichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,26,0,61,'Raichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,27,0,0,'Sandshrew',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,28,0,0,'Sandslash',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,27,0,61,'Sandshrew',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,28,0,61,'Sandslash',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,29,0,0,'Nidoran (F)',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,30,0,0,'Nidorina (F)',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,31,0,0,'Nidoqueen (F)',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,32,0,0,'Nidoran (M)',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,33,0,0,'Nidorino (M)',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,34,0,0,'Nidoking (M)',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,35,0,0,'Clefairy',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,36,0,0,'Clefable',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,37,0,61,'Vulpix',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,38,0,61,'Ninetales',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,39,0,0,'Jigglypuff',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,40,0,0,'Wigglytuff',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,41,0,0,'Zubat',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,42,0,0,'Golbat',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,50,0,0,'Diglett',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,51,0,0,'Dugtrio',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,50,0,61,'Diglett',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,51,0,61,'Dugtrio',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,52,0,61,'Meowth',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,53,0,61,'Persian',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,54,0,0,'Psyduck',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,55,0,0,'Golduck',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,56,0,0,'Mankey',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,57,0,0,'Primeape',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,58,0,0,'Growlithe',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,59,0,0,'Arcanine',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,66,0,0,'Machop',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,67,0,0,'Machoke',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,68,0,0,'Machamp',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,74,0,0,'Geodude',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,75,0,0,'Graveler',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,76,0,0,'Golem',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,74,0,61,'Geodude',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,75,0,61,'Graveler',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,76,0,61,'Golem',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,77,0,0,'Ponyta',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,78,0,0,'Rapidash',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,81,0,0,'Magnemite',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,82,0,0,'Magneton',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,86,0,0,'Seel',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,87,0,0,'Dewgong',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,88,0,0,'Grimer',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,89,0,0,'Muk',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,88,0,61,'Grimer',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,89,0,61,'Muk',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,90,0,0,'Shellder',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,91,0,0,'Cloyster',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,92,0,0,'Gastly',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,93,0,0,'Haunter',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,94,0,0,'Gengar',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,95,0,0,'Onix',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,96,0,0,'Drowzee',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,97,0,0,'Hypno',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,98,0,0,'Krabby',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,99,0,0,'Kingler',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,103,0,61,'Exeggutor',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,104,0,0,'Cubone',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,105,0,0,'Marowak',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,105,0,61,'Marowak',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,109,0,0,'Koffing',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,110,0,0,'Weezing',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,116,0,0,'Horsea',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,117,0,0,'Seadra',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,123,0,0,'Scyther',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,124,0,0,'Jynx',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,125,0,0,'Electabuzz',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,126,0,0,'Magmar',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,127,0,0,'Pinsir',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,129,0,0,'Magikarp',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,130,0,0,'Gyarados',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,131,0,0,'Lapras',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,133,0,0,'Eevee',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,133,7,0,'Eevee',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,134,0,0,'Vaporeon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,134,7,0,'Vaporeon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,135,0,0,'Jolteon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,135,7,0,'Jolteon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,136,0,0,'Flareon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,136,7,0,'Flareon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,138,0,0,'Omanyte',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,139,0,0,'Omastar',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,140,0,0,'Kabuto',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,141,0,0,'Kabutops',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,142,0,0,'Aerodactyl',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,144,0,0,'Articuno',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,145,0,0,'Zapdos',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,146,0,0,'Moltres',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,147,0,0,'Dratini',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,148,0,0,'Dragonair',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,149,0,0,'Dragonite',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,152,0,0,'Chikorita',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,153,0,0,'Bayleef',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,154,0,0,'Meganium',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,155,0,0,'Cyndaquil',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,156,0,0,'Quilava',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,157,0,0,'Typhlosion',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,158,0,0,'Totodile',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,159,0,0,'Croconaw',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,160,0,0,'Feraligatr',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,169,0,0,'Crobat',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,172,0,0,'Pichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,172,2,0,'Pichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,172,4,0,'Pichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,172,1,0,'Pichu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,173,0,0,'Cleffa',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,174,0,0,'Igglybuff',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,175,0,0,'Togepi',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,176,0,0,'Togetic',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,177,0,0,'Natu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,178,0,0,'Xatu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,179,0,0,'Mareep',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,180,0,0,'Flaaffy',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,181,0,0,'Ampharos',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,183,0,0,'Marill',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,184,0,0,'Azumarill',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,190,0,0,'Aipom',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,191,0,0,'Sunkern',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,192,0,0,'Sunflora',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,196,0,0,'Espeon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,196,7,0,'Espeon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,197,0,0,'Umbreon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,197,7,0,'Umbreon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,198,0,0,'Murkrow',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,200,0,0,'Misdreavus',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,202,0,0,'Wobbuffet',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,204,0,0,'Pineco',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,205,0,0,'Forretress',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,208,0,0,'Steelix',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,209,0,0,'Snubbull',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,210,0,0,'Granbull',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,212,0,0,'Scizor',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,213,0,0,'Shuckle',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,215,0,0,'Sneasel',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,220,0,0,'Swinub',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,221,0,0,'Piloswine',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,225,0,0,'Delibird',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,228,0,0,'Houndour',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,229,0,0,'Houndoom',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,230,0,0,'Kingdra',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,238,0,0,'Smoochum',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,239,0,0,'Elekid',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,240,0,0,'Magby',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,243,0,0,'Raikou',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,244,0,0,'Entei',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,246,0,0,'Larvitar',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,247,0,0,'Pupitar',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,248,0,0,'Tyranitar',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,249,0,0,'Lugia',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,250,0,0,'Ho-Oh',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,252,0,0,'Treecko',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,253,0,0,'Grovyle',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,254,0,0,'Sceptile',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,255,0,0,'Torchic',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,256,0,0,'Combusken',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,257,0,0,'Blaziken',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,258,0,0,'Mudkip',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,259,0,0,'Marshtomp',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,260,0,0,'Swampert',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,261,0,0,'Poochyena',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,262,0,0,'Mightyena',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,263,0,0,'Zigzagoon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,264,0,0,'Linoone',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,270,0,0,'Lotad',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,271,0,0,'Lombre',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,272,0,0,'Ludicolo',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,276,0,0,'Taillow',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,277,0,0,'Swellow',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,278,0,0,'Wingull',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,279,0,0,'Pelipper',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,287,0,0,'Slakoth',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,288,0,0,'Vigoroth',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,289,0,0,'Slaking',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,296,0,0,'Makuhita',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,297,0,0,'Hariyama',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,298,0,0,'Azurill',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,302,0,0,'Sableye',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,303,0,0,'Mawile',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,304,0,0,'Aron',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,305,0,0,'Lairon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,306,0,0,'Aggron',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,307,0,0,'Meditite',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,308,0,0,'Medicham',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,311,0,0,'Plusle',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,312,0,0,'Minun',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,315,0,0,'Roselia',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,320,0,0,'Wailmer',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,321,0,0,'Wailord',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,325,0,0,'Spoink',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,326,0,0,'Grumpig',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,327,0,18,'Spinda',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,333,0,0,'Swablu',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,334,0,0,'Altaria',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,337,0,0,'Lunatone',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,338,0,0,'Solrock',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,345,0,0,'Lileep',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,346,0,0,'Cradily',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,347,0,0,'Anorith',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,348,0,0,'Armaldo',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,349,0,0,'Feebas',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,350,0,0,'Milotic',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,351,0,0,'Castform',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,353,0,0,'Shuppet',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,354,0,0,'Banette',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,355,0,0,'Duskull',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,356,0,0,'Dusclops',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,359,0,0,'Absol',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,360,0,0,'Wynaut',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,361,0,0,'Snorunt',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,362,0,0,'Glalie',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,366,0,0,'Clamperl',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,367,0,0,'Huntail',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,368,0,0,'Gorebyss',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,370,0,0,'Luvdisc',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,371,0,0,'Bagon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,372,0,0,'Shelgon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,373,0,0,'Salamence',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,374,0,0,'Beldum',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,375,0,0,'Metang',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,376,0,0,'Metagross',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,380,0,0,'Latias',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,381,0,0,'Latios',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,382,0,0,'Kyogre',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,383,0,0,'Groudon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,403,0,0,'Shinx',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,404,0,0,'Luxio',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,405,0,0,'Luxray',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,406,0,0,'Budew',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,407,0,0,'Roserade',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,424,0,0,'Ambipom',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,425,0,0,'Drifloon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,426,0,0,'Drifblim',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,427,0,0,'Buneary',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,428,0,0,'Lopunny',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,429,0,0,'Mismagius',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,430,0,0,'Honchkrow',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,436,0,0,'Bronzor',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,437,0,0,'Bronzong',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,461,0,0,'Weavile',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,462,0,0,'Magnezone',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,466,0,0,'Electivire',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,467,0,0,'Magmortar',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,468,0,0,'Togekiss',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,470,0,0,'Leafeon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,470,7,0,'Leafeon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,471,0,0,'Glaceon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,471,7,0,'Glaceon',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,473,0,0,'Mamoswine',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,477,0,0,'Dusknoir',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,478,0,0,'Froslass',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,488,0,0,'Cresselia',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,808,0,0,'Meltan',true,true);

insert into pokemon (id,genid,dexid,costume,region, name, available, shiny) values (nextval('pokemonids'),1,809,0,0,'Melmetal',true,true);


end;