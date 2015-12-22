drop user cacheCordination cascade;
create user cacheCordination identified by 123; 
grant resource, connect, create view, create synonym, create trigger, create procedure, create materialized view to cacheCordination;
grant change notification to cacheCordination;