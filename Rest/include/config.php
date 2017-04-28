<?php

/*
use android_api /** Selecting Database **/
/* 
create table users(
   id int(11) primary key auto_increment,
   unique_id varchar(23) not null unique,
   name varchar(50) not null,
   email varchar(100) not null unique,
   encrypted_password varchar(80) not null,
   salt varchar(10) not null,
   created_at datetime,
   updated_at datetime null
); 
*/

/*
use android_api /** Selecting Database **/
/* 
create table users(
   id int(11) primary key auto_increment,
   name varchar(50) not null,
   email varchar(100) not null unique,
   password varchar(80) not null
   ); 
*/
/*
use testrest;
create table reclamation(
   _id int(11) primary key auto_increment,
   titre varchar(50),
   refRec varchar(50),
   _client varchar(50),
   adresse varchar(50),
   contactTel varchar(50),
   dateRec varchar(50),
   RG_Rec varchar(50),
   SR_Rec varchar(50),
   tete_TRec varchar(4),
   amorce_TRec varchar(4),
   couleur_TRec varchar(4),
   tete_DRec varchar(4),
   amorce_DRec varchar(4),
   couleur_DRec varchar(4),
   sgnlPar varchar(50),
   observInit varchar(50),
   observeTech varchar(50),
   description varchar(50),
   etat varchar(50)
   ); 
*/

/*
INSERT INTO `users` ( `name`, `email`, `password`) VALUES
('Jim', 'Engineer@gmail.com', 'azerty'),
('Michel', 'inginieur@gmailcom', 'azerty'),
('Ramsis', 'xman@gmail.com', 'azerty'),
('Karry', 'uman@gmail.com', 'azerty'),
('Adolf', 'james@gmail.com', 'azerty'),
('Spprano', 'salt@gmail.com', 'azerty');

*/

/*
INSERT INTO `reclamation` (`titre`, `refRec`, `_client`, `adresse`, `contactTel`,
 `dateRec`, `RG_Rec`, `SR_Rec`, `tete_TRec`, `amorce_TRec`, `couleur_TRec` , `tete_DRec`, `amorce_DRec`, `couleur_DRec`, `sgnlPar`, `observInit`, 
 `observeTech`, `description`, `etat`) 
VALUES 
('Panne 1', 'IMR7845', 'Client A','Adresse 1', 'contactTel 1','18/05/2016', 'RG_Rec', 'SR_Rec', '0', '0', '0', '0', '0', '0', 'web', 'panne adsl', '', '', 'En attente'),
('Panne 2', 'IMR7846', 'Client B','Adresse 2', 'contactTel 2','18/05/2016', 'RG_Rec', 'SR_Rec', '0', '0', '0', '0', '0', '0', 'web', 'panne adsl', '', '', 'En attente'),
('Panne 3', 'IMR7847', 'Client C','Adresse 3', 'contactTel 3','18/05/2016', 'RG_Rec', 'SR_Rec', '0', '0', '0', '0', '0', '0', 'web', 'panne adsl', '', '', 'En attente'),
('Panne 4', 'IMR7848', 'Client D','Adresse 4', 'contactTel 4','18/05/2016', 'RG_Rec', 'SR_Rec', '0', '0', '0', '0', '0', '0', 'web', 'panne adsl', '', '', 'En attente'),
('Panne 5', 'IMR7850', 'Client E','Adresse 5', 'contactTel 5','18/05/2016', 'RG_Rec', 'SR_Rec', '0', '0', '0', '0', '0', '0', 'web', 'panne adsl', '', '', 'En attente');
*/
//GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '' WITH GRANT OPTION;
//FLUSH PRIVILEGES;
/** Creating Users Table **/

define("DB_HOST", "192.168.1.102");
define("DB_USER","root");
define("DB_PASSWORD","");
define("DB_DATABASE","testrest");

?>