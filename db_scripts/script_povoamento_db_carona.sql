
USE db_carona; 

-- inserindo na tabela usuario

INSERT INTO `usuario` (`cpf`, `nome`, `email`, `cnh`, `senha`, `telefone`, `nome_usuario`) VALUES ('01122461920', 'Lucas Rocha', 'rocha@gmail.com', NULL, '8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92', '31962123022', 'LucasR'); -- senha = 123456
INSERT INTO `usuario` (`cpf`, `nome`, `email`, `cnh`, `senha`, `telefone`, `nome_usuario`) VALUES ('08225024613', 'Victor Huggo Duarte', 'victor@gmail.com', '09165412315', 'BEF57EC7F53A6D40BEB640A780A639C83BC29AC8A9816F1FC6C5C6DCD93C4721', '31995196319', 'Victor'); -- senha = abcdef
INSERT INTO `usuario` (`cpf`, `nome`, `email`, `cnh`, `senha`, `telefone`, `nome_usuario`) VALUES ('12333333666', 'Reinaldo Antonio', 'reinaldo@gmail.com', '12220033662', '87F633634CC4B02F628685651F0A29B7BFA22A0BD841F725C6772DD00A58D489', '31995884111', 'Reinaldo'); -- senha = oi
INSERT INTO `usuario` (`cpf`, `nome`, `email`, `cnh`, `senha`, `telefone`, `nome_usuario`) VALUES ('12345112200', 'Bruno Rabello', 'brunor@gmail.com', NULL, '74E724C91D2A99DC01E3294A592D4810D807B3E25D8CD6BA8AFBA7CEE89352C6', '31995451278', 'brunorabello'); -- senha = banco
INSERT INTO `usuario` (`cpf`, `nome`, `email`, `cnh`, `senha`, `telefone`, `nome_usuario`) VALUES ('15151515151', 'Severino Pereira', 'severino@gmail.com', NULL, 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', '31998714367', 'Severino'); -- senha = 123
INSERT INTO `usuario` (`cpf`, `nome`, `email`, `cnh`, `senha`, `telefone`, `nome_usuario`) VALUES ('22255588894', 'Nathalia Mota', 'nathalia@gmail.com', '15985245612', 'D919A100CE6B45524D415D52D088D5817587C6DD8C3691B03B8063C44D043523', '31978451200', 'Nathalia'); -- senha = nat
INSERT INTO `usuario` (`cpf`, `nome`, `email`, `cnh`, `senha`, `telefone`, `nome_usuario`) VALUES ('43215689522', 'Dayane Ponciano', 'dayane@gmail.com', '11133311102', '55A9F4F8994B1BBF2058EA38C8EFB6C459000814D5F39C087002571639E6230E', '31985126212', 'Dayane'); -- senha = ola
INSERT INTO `usuario` (`cpf`, `nome`, `email`, `cnh`, `senha`, `telefone`, `nome_usuario`) VALUES ('74185296312', 'Lucas Henrique', 'lucas@gmail.com', '22255500011', 'B7E94BE513E96E8C45CD23D162275E5A12EBDE9100A425C4EBCDD7FA4DCD897C', '31954512022', 'Lucas'); -- senha = senha

-- inserindo na tabela carro

INSERT INTO `carro` VALUES ('fff2000','Prata','Corolla ALTIS 2.0 Flex 16V Aut.','TOYOTA',2011,'74185296312');
INSERT INTO `carro` VALUES ('hbc7584','Marrom','Fusca','VOLKSWAGEN',1980,'08225024613');
INSERT INTO `carro` VALUES ('mmg1500','Bege','Partner Furgão 1.8 3p','PEUGEOT',1989,'01122461920');
INSERT INTO `carro` VALUES ('qwe1122','Preto','Classe A 160 Classic Semi-Aut.','MERCEDES-BENZ',2019,'43215689522');
INSERT INTO `carro` VALUES ('rte1234','Rosa','Fusca','VOLKSWAGEN',1980,'12333333666');
INSERT INTO `carro` VALUES ('tto1542','Branco','T6 2.0 JET Flex 5p Mec.','JAC',2015,'22255588894');

-- inserindo na tabela carona

INSERT INTO `carona` VALUES (2,'João Monlevade','Belo Horizonte','2019-08-01','10:30',40,3,0,'hbc7584','08225024613');
INSERT INTO `carona` VALUES (3,'João Monlevade','São Paulo','2019-07-22','10:00',90,3,1,'fff2000','74185296312');
INSERT INTO `carona` VALUES (4,'João Monlevade','Barbacena','2019-08-23','10:00',50,3,1,'tto1542','22255588894');
INSERT INTO `carona` VALUES (5,'João Monlevade','Belo Horizonte','2019-07-14','20:00',35,4,0,'qwe1122','43215689522');
INSERT INTO `carona` VALUES (6,'João Monlevade','São Paulo','2019-07-22','23:00',80,4,1,'mmg1500','01122461920');

-- inserindo na tabela recebe_carona

INSERT INTO `recebe_carona` VALUES (4,'15151515151');

