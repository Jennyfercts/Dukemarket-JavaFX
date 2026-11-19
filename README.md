# DukeMarket - JavaFX #

Projeto realizado para praticar a conxão com o banco de dados, incluindo também interface gráfica utilizando o recurso JavaFX.
Esse sistema realiza o cadastro, alteração e exclusão de produtos e clientes, considerando o contexto de uma loja/supermercado.


## SQL para a criação do banco de dados utilizado: ##

```sql

create database jennyfer_estoque;
use jennyfer_estoque;

create table produto (
  id INT auto_increment primary key,
  codBarras varchar(15),
  descricao varchar(30) NOT NULL,
  qtd decimal(10,2) default 0,
  valorCompra decimal(10,2) default 0,
  valorVenda decimal(10,2) default 0,
  dataCadastro datetime default current_timestamp);

insert into produto(codBarras, descricao, qtd, valorCompra, valorVenda) 
values ("7891112223332", "Computador DELL OptiPlex 3080", 5.00, 3480.00, 5000.00);

create table cliente (
  id INT auto_increment primary key,
  nome varchar(50),
  cpf varchar(11),
  endereço varchar(60),
  cidade varchar(50),
  uf varchar(3),
  dataCadastro datetime default current_timestamp);
