begin;
create table user_shadow_pokemon (
  id bigint not null,
  userid bigint not null references users(id),
  pokemonid bigint not null references pokemon(id),
  primary key (id)
);
create index ushp_by_user on user_shadow_pokemon(userid);
create unique index on user_shadow_pokemon(userid, pokemonid);
alter table pokemon add shadow boolean;
update pokemon set shadow=false;

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,1,0,0,'Bulbasaur',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,2,0,0,'Ivysaur',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,3,0,0,'Venusaur',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,4,0,0,'Charmander',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,5,0,0,'Charmeleon',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,6,0,0,'Charizard',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,7,0,0,'Squirtle',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,8,0,0,'Wartortle',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,9,0,0,'Blastoise',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,19,0,0,'Rattata',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,20,0,0,'Raticate',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,41,0,0,'Zubat',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,42,0,0,'Golbat',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),2,169,0,0, 'Crobat',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,52,0,0,'Meowth',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'),1,53,0,0,'Persian',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 43,0,0, 'Oddish',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 44,0,0, 'Gloom',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 45,0,0, 'Vileplume',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 48,0,0, 'Venonat',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 49,0,0, 'Venomoth',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 54,0,0, 'Psyduck',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 55,0,0, 'Golduck',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 58,0,0, 'Growlithe',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 59,0,0, 'Arcanine',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 60, 0,0, 'Poliwag',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 61, 0,0, 'Poliwhirl',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 62, 0,0, 'Poliwrath',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 186,0,0, 'Politoed',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 69, 0,0, 'Bellsprout',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 70, 0,0, 'Weepinbell',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 71, 0,0, 'Victreebel',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 88, 0,0, 'Grimer',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 89, 0,0, 'Muk',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 96, 0,0, 'Drowzee',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 97, 0,0, 'Hypno',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 104, 0,0, 'Cubone',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 105, 0,0, 'Marowak',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 107, 0,0, 'Hitmonchan',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 123, 0,0, 'Scyther',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 212, 0,0, 'Scizor',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 125, 0,0, 'Electabuzz',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 4, 466, 0,0, 'Electivire',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 126, 0,0, 'Magmar',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 4, 467, 0,0, 'Magmortar',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 129, 0,0, 'Magikarp',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 130, 0,0, 'Gyarados',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 131, 0,0, 'Lapras',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 137, 0,0, 'Porygon',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 233, 0,0, 'Porygon2',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 4, 474, 0,0, 'Porygon-Z',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 143, 0,0, 'Snorlax',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 147, 0,0, 'Dratini',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 148, 0,0, 'Dragonair',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 1, 149, 0,0, 'Dragonite',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 179, 0,0, 'Mareep',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 180, 0,0, 'Flaaffy',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 181, 0,0, 'Ampharos',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 215, 0,0, 'Sneasel',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 4, 461, 0,0, 'Weavile',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 228, 0,0, 'Houndour',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 229, 0,0, 'Houndoom',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 246, 0,0, 'Larvitar',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 247, 0,0, 'Pupitar',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 2, 248, 0,0, 'Tyranitar',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 258, 0,0, 'Mudkip',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 259, 0,0, 'Marshtomp',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 260, 0,0, 'Swampert',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 273, 0,0, 'Seedot',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 274, 0,0, 'Nuzleaf',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 275, 0,0, 'Shiftry',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 280,0,0, 'Ralts',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 281,0,0, 'Kirlia',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 282,0,0, 'Gardevoir',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 4, 475,0,0,'Gallade',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 328,0,0, 'Trapinch',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 329,0,0, 'Vibrava',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 330,0,0, 'Flygon',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 331,0,0, 'Cacnea',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 332,0,0, 'Cacturne',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 353,0,0, 'Shuppet',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 354,0,0, 'Banette',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 355,0,0, 'Duskull',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 3, 356,0,0, 'Dusclops',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 4, 477,0,0, 'Dusknoir',false,false,true);

insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 4, 387,0,0, 'Turtwig',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 4, 388,0,0, 'Grotle',false,false,true);
insert into pokemon (id,genid,dexid,costume,region, name, lucky,shiny,shadow) values (nextval('pokemonids'), 4, 389,0,0, 'Torterra',false,false,true);

end;