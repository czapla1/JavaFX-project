create database TimeCounter;
use TimeCounter;

create table logins (
	id_login int not null auto_increment,
    id_employee int not null,
    login varchar(45) not null unique,
    pass varchar(32) not null,
    access varchar(15),
    primary key(id_login),
    foreign key(id_employee) references employees (id_employee) 
    ON DELETE CASCADE
 );
 
 
insert into logins (id_login,id_employee, login, pass, access) values  
(1,1, 'a', 's','employer'),
(2,2, 'log2', 'pass2', 'employee'),
(3,3, 'log3', 'pass3','employee'),
(4,4, 'log4', 'pass4', 'employee');



 create table employees(
  id_employee int not null auto_increment,
  firstname varchar(30) not null,
  lastname varchar(40) not null,
  primary key (id_employee)
);


insert into employees (id_employee, firstname,lastname) values
(1,'Jan','Kowalski'),
(2,'Tomasz','Nowak'),
(3,'Anna','Kot'),
(4,'Agata','Przybysz');



create table payments (

	id_payment int not null auto_increment,
	id_employee int not null,
	month_name int not null,
    total_brutto double,
    primary key(id_payment),
    foreign key(id_employee) references employees (id_employee) 
    ON DELETE CASCADE
 );

insert into payments (id_payment, id_employee, month_name, total_brutto) values
(1, 1,1, 0),
(2, 2,1, 2300.00),
(3, 3,1, 3400.00),
(4, 4,1, 4500.00),
(5, 1,2, 3700.00),
(6, 2,2, 1700.00),
(7, 3,2, 2700.00),
(8, 4,2, 900.00);


create table rates (

	id_rate int not null auto_increment,
	id_employee int not null,
	rateX double not null default 0,
    rateY double not null default 0,
    rateZ double not null default 0,
    primary key(id_rate),
    foreign key(id_employee) references employees (id_employee) 
    ON DELETE CASCADE
 );

 
insert into rates (id_rate, id_employee, rateX, rateY, rateZ) values
(1, 1,0, 0,0),
(2, 2,25, 50,60),
(3, 3, 20, 46,75),
(4, 4,29,44,67);


