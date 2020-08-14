begin;

create table user_filters (
  id bigint not null,
  userid bigint not null references users(id),
  shiny_costumes boolean not null,
  shiny_shadows boolean not null,
  shiny_alolan boolean not null,
  shiny_other boolean not null,
  lucky_costumes boolean not null,
  lucky_alolan boolean not null,
  lucky_other boolean not null,
  hundo_costumes boolean not null,
  hundo_shadows boolean not null,
  hundo_alolan boolean not null,
  hundo_other boolean not null,
  primary key (id)
);
create index uf_by_user on user_hundo_pokemon(userid);

insert into user_filters (id, userid, shiny_costumes, shiny_shadows, shiny_alolan, shiny_other,
 lucky_costumes, lucky_alolan, lucky_other, 
 hundo_costumes, hundo_shadows, hundo_alolan, hundo_other)
select nextval('hibernate_sequence'), id, costumes, true, true, true, 
false, false, true, 
true, true, true, true
from users;

alter table users drop column costumes;

insert into pokemon (id, dexid, genid, name, shiny, costume, region, lucky, shadow) 
select nextval('pokemonids'), p.dexid, p.genid, p.name, false, p.costume, p.region, true, false 
from pokemon p where p.costume!=0 and p.shadow=false and not exists 
(select 'x' from pokemon m where 
m.dexid=p.dexid and m.costume=p.costume and m.region=p.region and 
m.lucky=true and m.shiny=false and m.shadow=false);

insert into pokemon (id, dexid, genid, name, shiny, costume, region, lucky, shadow) 
select nextval('pokemonids'), p.dexid, p.genid, p.name, false, p.costume, p.region, true, false 
from pokemon p where p.region!=0 and p.shadow=false and not exists
(select 'x' from pokemon m where 
m.dexid=p.dexid and m.costume=p.costume and m.region=p.region and 
m.lucky=true and m.shiny=false and m.shadow=false);

end;