begin;
update pokemon set available=true;
update pokemon set available=false where name in ('Mew', 'Celebi', 'Kecleon', 'Jirachi', 'Deoxys', 'Mime Jr.', 'Rotom', 'Regigigas', 'Phione', 'Manaphy', 'Darkrai', 'Shaymin', 'Arceus');
end;