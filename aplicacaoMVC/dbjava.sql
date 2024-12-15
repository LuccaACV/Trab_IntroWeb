-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 31/10/2024 às 13:41
-- Versão do servidor: 5.7.31
-- Versão do PHP: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `dbjava`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `administrador`
--

DROP TABLE IF EXISTS `administrador`;
CREATE TABLE IF NOT EXISTS `administrador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(8) NOT NULL,
  `endereco` varchar(40) NOT NULL,
  `aprovado` varchar(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

--
-- Despejando dados para a tabela `administrador`
--

INSERT INTO `administrador` (`id`, `nome`, `cpf`, `senha`, `endereco`, `aprovado`) VALUES
(1, 'Leo', '249.252.810-38', '123', 'Rua Nilo Peçanha 1', 'S'),
(2, 'Carlos', '081.599.500-80', '111', 'Rua Carlo I n. 2', 'N'),
(5, 'admina', '167.740.300-41', '1111', 'Rua Nilo Pecanha 131', 'S'),
(6, 'Leo1', '249.252.810-38', '1111', 'Rua Nilo Pecanha 131', 'S');

-- --------------------------------------------------------

--
-- Estrutura para tabela `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

--
-- Despejando dados para a tabela `categorias`
--

INSERT INTO `categorias` (`id`, `descricao`) VALUES
(1, 'Artes'),
(2, 'Esportes'),
(3, 'Saúde'),
(4, 'Celebridades'),
(5, 'Turismo'),
(6, 'Política'),
(7, 'Entretenimento');

-- --------------------------------------------------------

--
-- Estrutura para tabela `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
CREATE TABLE IF NOT EXISTS `comentarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comentario` varchar(255) CHARACTER SET utf8 NOT NULL,
  `data` date DEFAULT NULL,
  `idcategoria` int(11) NOT NULL,
  `idadministrador` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_categorias` (`idcategoria`),
  KEY `fk_adminstrador` (`idadministrador`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

--
-- Despejando dados para a tabela `comentarios`
--

INSERT INTO `comentarios` (`id`, `comentario`, `data`, `idcategoria`, `idadministrador`) VALUES
(1, 'comentário 1', '2022-07-22', 1, 1),
(2, 'comentário 2', '2022-07-22', 2, 1),
(3, 'comentário 3', '2022-07-22', 3, 1),
(4, 'comentário 4', '2022-07-22', 4, 2),
(6, 'teste 6', '2022-07-27', 3, 1),
(7, 'teste 7', '2022-07-27', 5, 2),
(8, 'lala', '2022-07-27', 7, 5),
(9, 'teste 9', '2022-07-27', 6, 1),
(10, 'vai', '2024-10-17', 1, 1),
(11, 'a', '2024-10-17', 6, 1),
(12, 'aa', '2024-10-17', 2, 1);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `comentarios_FK` FOREIGN KEY (`idadministrador`) REFERENCES `administrador` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comentarios_FK_1` FOREIGN KEY (`idcategoria`) REFERENCES `categorias` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
