CREATE SEQUENCE T_BOOKS_ID_SEQ;

create table T_BOOKS (
    ID      bigint not null default nextval ('T_BOOKS_ID_SEQ'),
    NAME    varchar (255) not null
);