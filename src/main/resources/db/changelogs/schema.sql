create sequence hibernate_sequence START WITH 1 INCREMENT BY 1;
create sequence pokemonids START WITH 1 INCREMENT BY 1;

create table users (
  id bigint not null,
  name varchar(50) not null,
  displayname varchar(200) not null,
  admin boolean not null,
  primary key (id)
);

create table pokemon (
  id bigint not null,
  dexid bigint not null,
  genid bigint not null,
  name varchar(100) not null,
  available boolean,
  primary key (id)
);

create table user_lucky_pokemon (
  id bigint not null,
  userid bigint not null references users(id),
  pokemonid bigint not null references pokemon(id),
  primary key (id)
);

create index ulp_by_user on user_lucky_pokemon(userid);
create unique index on user_lucky_pokemon(userid, pokemonid);

create table friends (
  id bigint not null,
  userid bigint not null references users(id),
  friendid bigint not null references users(id),
  primary key (id)
);

create index friends_by_user on friends(userid);
