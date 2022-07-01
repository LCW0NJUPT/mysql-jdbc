-- 测试的sql语句
create database db1;
create database db2 charset utf8;

alter database db2 charset utf8mb4;

show create database hr;
drop database db1;
drop database if exists db2;

create database db1 charset utf8;
drop table tb1;
use db1;
create table tb1(
    name varchar(128),
    gender char(2)
)charset=utf8 engine innodb;

show tables;

desc tb1;

show create table tb1;

-- 改表名
rename table tb1 to user;

alter table user engine=myisam charset=GBK;
desc user;

-- 添加字段
alter table user add id int first;
desc user;

alter table user add email varchar(100) after name;
desc user;

-- 总共三位，小数位两位
alter table user add(
    age int,
    height decimal(3,2)
    );
desc user;

-- 修改字段名
alter table user change gender sex char(1);
desc user;

-- 修改字段类型
alter table user modify sex varchar(2);
desc user;


-- 修改字段顺序
alter table user
    modify email varchar(100) after id;

alter table user
    modify age int after name;

desc user;

-- 删除表
-- 如果存在，删除
drop table if exists user;
show tables;

-- 截断表、重建表
-- 先删除表，再重新创建
create table user(
    id int,
    name varchar(20)
);

insert into user values(1,'张三');
desc user;

truncate table user;
desc user;


-- -- 插入数据 -- --
drop table if exists user;

create table user(
                     id int,
                     name varchar(20)
)engine=innodb charset=utf8;
desc user;
-- 向 user 表插入数据
insert into user values(1, '张三');

insert into user values
                     (2, '李四'), (3, '王五'),
                     (4, '赵六'), (7, '钱七');
desc user;

-- -- 查询 -- --
select * from user;

-- 修改数据 update
update user set id=999, name='张三三' where id=1;
select * from user;

-- 删除数据
delete from user
    where id=4;
select * from user;

-- timestamp测试
drop table if exists tb3;
create table tb3(
                    a int,
                    b timestamp,
                    c timestamp
)engine=innodb charset=utf8;

insert into tb3(a) values(1);
select * from tb3;

update tb3 set a=2;
select * from tb3;

update tb3 set c='2038-1-1'; -- 可以修改
-- update tb3 set c='2039-1-1'; -- 超出范围
select * from tb3;



use db1;
create table tb4(
id int primary key,
name varchar(20)
)engine=innodb charset=utf8;
desc tb4;

insert into tb4 values(1,'张三');

create table tb5(
id int primary key auto_increment,
name varchar(20)
)engine=innodb charset=utf8;

insert into tb5(name) values('a'),('b'),('c');

select * from tb5;

show create table tb5;

-- 插入一个指定的值
insert into tb5 values(1000, 'e');
select * from tb5;

alter table tb5 auto_increment=10000;
insert into tb5(name) values('g'),('h'),('i');

select * from tb5;

select last_insert_id();
desc tb5;


-- 班级表，主表、父表
drop table if exists banji;

create table banji(
  id int primary key auto_increment,
  name varchar(20)
)engine=innodb charset=utf8;

-- 学生表，子表
drop table if exists xuesheng;

create table xuesheng(
     id int primary key auto_increment,
     num int,
     name varchar(20),
     ban_id int,
     foreign key(ban_id)
         references banji(id)
)engine=innodb charset=utf8;

insert into banji(name) values('A'),('B');

insert into xuesheng(num,name,ban_id) values
                                          (6433, 'a', 1),
                                          (6434, 'b', 2),
                                          (6435, 'c', 1),
                                          (6436, 'd', 2),
                                          (6437, 'e', null);

select * from xuesheng;
select * from banji;

desc xuesheng;
show create table xuesheng;

Alter table banji
modify name varchar(20) unique not null;

desc banji;
-- insert  into banji(name) values('A');
-- insert into banji(name) values(NULL);




select version();
# create table tb6(
#                 age int,
#                 gender char(1),
#                 check(age>=7 and age<=60),
#                 check(gender in('M', 'F'))
#
# );
#
#
# insert into tb6 values(7,'M');
#
# insert into tb6 values(100,'F');

use db1;
-- 创建备份表
drop table banji_bak;
create table banji_bak(
                          id int,
                          name varchar(20)
);
insert into banji_bak (id, name)
select id,name from banji;
select * from banji_bak;

-- 查询结果直接创建成新表
create table banji_bak2
as
select id,name from banji;
select * from banji_bak;


update xuesheng set num=num+10000
order by rand() limit 2;

delete from xuesheng order by num limit 2;


use hr;
--
select employee_id,first_name,salary
from employees
where employee_id=(
    select manager_id
    from employees
    where manager_id is not null
    group by manager_id
    having count(*)=(
        select max(c) from (
               select manager_id,count(*) c
               from employees
               where manager_id is not null
               group by manager_id) t));












