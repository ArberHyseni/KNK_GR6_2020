drop database if exists GameDB;
create database GameDB;
use GameDB;

create table users (
idusers integer auto_increment, 
firstName varchar(25) not null,
lastName varchar(25) not null, 
email varchar(50) not null unique, 
password varchar(255) not null,
primary key (idusers));


create table games_played (
gpId integer auto_increment, 
playerId integer, 
score integer,
primary key (gpId),
foreign key (playerId) references users(idusers) on delete cascade on update cascade);


create table player_statistics (
psId integer auto_increment,
userId integer not null, 
totalScore integer default 0, 
ones integer default 0,
twos integer default 0,
threes integer default 0,
fours integer default 0,
fives integer default 0,
sixes integer default 0,
noOfGames integer default 0,
primary key (psId),
foreign key (userId) references users(idusers) on delete cascade on update cascade);