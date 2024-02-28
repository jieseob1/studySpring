
-- h2 DB 설정 마지막에 있는 테이블, 샘플 데이터 만들기를 통해, member테이블을 미리 만들어줘야한다.

drop table member if exists cascade;
create table member (
    member_id varchar(10),
    money integer not null default 0,
    primary key (member_id)
);

insert into member(member_id, money) values ('hi1', 10000);
insert into member(member_id, money) values ('hi2', 20000);