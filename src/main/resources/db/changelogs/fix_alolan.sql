begin;

alter table pokemon rename column region to variant;

alter table pokemon add region bigint not null default(0);

UPDATE pokemon SET region=1 WHERE lucky=true AND variant!=0 AND name IN 
('Rattata', 'Raticate', 'Raichu', 'Sandshrew', 'Sandslash', 'Vulpix', 
'Ninetales', 'Diglett', 'Dugtrio', 'Meowth', 'Persian', 'Geodude', 
'Graveler', 'Golem', 'Grimer', 'Muk', 'Exeggutor', 'Marowak');

UPDATE pokemon SET region=1 WHERE lucky=false AND shiny=true AND variant!=0 AND name IN 
('Rattata', 'Raticate', 'Raichu', 'Sandshrew', 'Sandslash', 'Vulpix', 
'Ninetales', 'Diglett', 'Dugtrio', 'Meowth', 'Persian', 'Geodude', 
'Graveler', 'Golem', 'Grimer', 'Muk', 'Exeggutor', 'Marowak');

end;