begin;
create table user_hundo_pokemon (
  id bigint not null,
  userid bigint not null references users(id),
  pokemonid bigint not null references pokemon(id),
  total bigint not null,
  primary key (id)
);
create index uhp_by_user on user_hundo_pokemon(userid);
create unique index on user_hundo_pokemon(userid, pokemonid);
alter table pokemon add lucky boolean;
update pokemon set lucky=true where available=true;
update pokemon set lucky=false where available=false;
update pokemon set lucky=false where shiny=true;
alter table pokemon drop available;
end;