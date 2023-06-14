# provaJuliana

<BR>

## Script sql
```create database juliana;
use juliana;

create table if not exists alunos(
id_aluno int not null primary key auto_increment,
nome varchar(50) not null,
cpf int(20) not null,
altura float(10,2) not null,
peso float(10,2) not null,
dataNasc date not null,
imc float(10,2) not null
);
