begin;
create table user_ninetyeight_pokemon (
  id bigint not null,
  userid bigint not null references users(id),
  pokemonid bigint not null references pokemon(id),
  total bigint not null,
  primary key (id)
);
create index unp_by_user on user_ninetyeight_pokemon(userid);
create unique index on user_ninetyeight_pokemon(userid, pokemonid);
end;