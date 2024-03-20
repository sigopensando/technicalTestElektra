create database if not exists property_db;
use property_db;

create table if not exists property_table
(
    id          bigint primary key,
    title       varchar(255) not null,
    description varchar(255),
    owner_name  varchar(255),
    owner_email varchar(255) not null,
    price       double,
    address     varchar(255),
    created_at  datetime,
    updated_at  datetime
);

create procedure if not exists SelectAllProperties()
begin
    select * from property_table;
end;

create procedure if not exists UpdatePrice(In Price double, In PropertyId long)
begin
    update property_table set price = Price where id = PropertyId;
end;
