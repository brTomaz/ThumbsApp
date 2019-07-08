-- sera necessario dropar o banco e cria-lo novamente (a coluna senha da tabela usuario mudou de tamanho)

-- DROP DATABASE db_carona;

CREATE SCHEMA db_carona;

USE db_carona;

CREATE TABLE `usuario` (
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(35) NOT NULL,
  `email` varchar(45) NOT NULL,
  `cnh` varchar(11) DEFAULT NULL,
  `senha` varchar(70) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `nome_usuario` varchar(25) NOT NULL,
  PRIMARY KEY (`cpf`),
  UNIQUE KEY `cpf` (`cpf`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `telefone` (`telefone`),
  UNIQUE KEY `nome_usuario` (`nome_usuario`),
  UNIQUE KEY `cnh` (`cnh`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `carro` (
  `placa` varchar(8) NOT NULL,
  `cor` varchar(15) NOT NULL,
  `modelo` varchar(80) DEFAULT NULL,
  `marca` varchar(20) NOT NULL,
  `ano` year(4) NOT NULL,
  `cpf_dono` varchar(11) NOT NULL,
  PRIMARY KEY (`placa`),
  UNIQUE KEY `placa` (`placa`),
  KEY `cpf_dono` (`cpf_dono`),
  CONSTRAINT `carro_ibfk_1` FOREIGN KEY (`cpf_dono`) REFERENCES `usuario` (`cpf`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `carona` (
  `idcarona` int(10) unsigned NOT NULL,
  `origem` varchar(50) NOT NULL,
  `destino` varchar(50) NOT NULL,
  `dia` varchar(20) NOT NULL,
  `horario` varchar(20) DEFAULT NULL,
  `valor` decimal(10,0) DEFAULT NULL,
  `quantidade_vagas` int(10) unsigned DEFAULT NULL,
  `quantidade_atual` int(10) NOT NULL,
  `placa_carro` varchar(8) NOT NULL,
  `cpf_motorista` varchar(11),
  PRIMARY KEY (`idcarona`),
  KEY `fk_carona_1_idx` (`cpf_motorista`),
  CONSTRAINT `fk_carona_1` FOREIGN KEY (`cpf_motorista`) REFERENCES `usuario` (`cpf`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `recebe_carona` (
  
	`id_carona` int(10) unsigned NOT NULL,
  
	`id_caronaPassageiro` int(10) unsigned NOT NULL,
  
	`cpf_passageiro` varchar(11) NOT NULL,

	PRIMARY KEY (`id_carona`, `cpf_passageiro`),

	UNIQUE KEY (`id_carona`,`cpf_passageiro`),
 
	CONSTRAINT `recebe_carona_ibfk_1` FOREIGN KEY (`id_carona`) REFERENCES `carona` (`idcarona`) ON DELETE CASCADE ON UPDATE CASCADE,
  
	CONSTRAINT `recebe_carona_ibfk_2` FOREIGN KEY (`cpf_passageiro`) REFERENCES `usuario` (`cpf`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




