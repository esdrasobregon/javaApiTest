CREATE DATABASE `prueba` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `persona` (
  `idpersona` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  PRIMARY KEY (`idpersona`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
-----
CREATE TABLE `tipo_unidades` (
  `idtipo_unidades` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `puertas` int NOT NULL,
  PRIMARY KEY (`idtipo_unidades`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
----
CREATE TABLE `tipoaccion` (
  `idtipoAccion` int NOT NULL,
  `descripcion` varchar(12) NOT NULL,
  PRIMARY KEY (`idtipoAccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
------
CREATE TABLE `unidades` (
  `idBus` int NOT NULL AUTO_INCREMENT,
  `fecha_ingreso` datetime NOT NULL,
  `marca` varchar(56) NOT NULL,
  `modelo` int NOT NULL,
  `placa` varchar(45) NOT NULL,
  `activo` bit(1) NOT NULL,
  `tipo` int NOT NULL,
  PRIMARY KEY (`idBus`,`tipo`),
  UNIQUE KEY `placa_UNIQUE` (`placa`),
  KEY `fk_unidades_tipo_unidades1_idx` (`tipo`),
  CONSTRAINT `fk_unidades_tipo_unidades1` FOREIGN KEY (`tipo`) REFERENCES `tipo_unidades` (`idtipo_unidades`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
-------------
CREATE TABLE `unidades_auditoria` (
  `idBus` int NOT NULL,
  `fecha_ingreso` datetime NOT NULL,
  `nueva_fecha_ingreso` datetime DEFAULT NULL,
  `marca` varchar(56) NOT NULL,
  `nueva_marca` varchar(56) DEFAULT NULL,
  `modelo` int NOT NULL,
  `nuevo_modelo` int DEFAULT NULL,
  `placa` varchar(45) NOT NULL,
  `nueva_placa` varchar(45) DEFAULT NULL,
  `tipo` int NOT NULL,
  `nuevo_tipo` varchar(45) DEFAULT NULL,
  `activo` bit(1) NOT NULL,
  `nuevo_activo` bit(1) DEFAULT NULL,
  `fedcha_accion` datetime NOT NULL,
  `tipoAccion_idtipoAccion` int NOT NULL,
  `usuario_idusuario` int NOT NULL,
  `usuario_persona_idpersona` int NOT NULL,
  PRIMARY KEY (`tipoAccion_idtipoAccion`,`usuario_idusuario`,`usuario_persona_idpersona`),
  UNIQUE KEY `placa_UNIQUE` (`placa`),
  KEY `fk_unidades_auditoria_usuario1_idx` (`usuario_idusuario`,`usuario_persona_idpersona`),
  CONSTRAINT `fk_unidades_auditoria_tipoAccion1` FOREIGN KEY (`tipoAccion_idtipoAccion`) REFERENCES `tipoaccion` (`idtipoAccion`),
  CONSTRAINT `fk_unidades_auditoria_usuario1` FOREIGN KEY (`usuario_idusuario`, `usuario_persona_idpersona`) REFERENCES `usuario` (`idusuario`, `persona_idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-----------
CREATE TABLE `usuario` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(12) NOT NULL,
  `contrasenya` varchar(45) NOT NULL,
  `persona_idpersona` int NOT NULL,
  PRIMARY KEY (`idusuario`,`persona_idpersona`),
  UNIQUE KEY `nombreUsuario_UNIQUE` (`nombreUsuario`),
  KEY `fk_usuario_persona_idx` (`persona_idpersona`),
  CONSTRAINT `fk_usuario_persona` FOREIGN KEY (`persona_idpersona`) REFERENCES `persona` (`idpersona`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

