drop user jpaPlay cascade;
create user jpaPlay identified by 123; 
grant resource, connect, create view, create synonym, create trigger, create procedure, create materialized view to jpaPlay;