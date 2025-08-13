-- Crear base de datos
CREATE DATABASE IF NOT EXISTS ilib;
USE ilib;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- Configuración de caracteres
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- ------------------------
-- Tabla: books
-- ------------------------

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `edit` varchar(255) NOT NULL,
  `lang` varchar(255) NOT NULL,
  `image` varchar(100),
  `pages` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `ejemplares` varchar(255) NOT NULL,
  `stock` int(11) NOT NULL,
  `available` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `books` (`id`, `title`, `date`, `author`, `category`, `edit`, `lang`, `image`, `pages`, `description`, `ejemplares`, `stock`, `available`) VALUES
(1, 'Cien años de soledad', '1967', 'Gabriel García Márquez', 'Novela', 'Sudamericana', 'Español', 'cien_anios_de_soledad.jpg', '471', 'Una historia mágica en el pueblo de Macondo.', '5', 5, 1),
(2, '1984', '1949', 'George Orwell', 'Distopía', 'Secker & Warburg', 'Inglés', '1984.jpg', '328', 'Una crítica al totalitarismo con el Gran Hermano vigilando.', '3', 2, 1),
(3, 'Don Quijote de la Mancha', '1605', 'Miguel de Cervantes', 'Novela', 'Francisco de Robles', 'Español', 'don_quijote.jpg', '863', 'Las aventuras de un hidalgo que quiere ser caballero.', '4', 1, 1),
(4, 'The Hobbit', '1937', 'J.R.R. Tolkien', 'Fantasía', 'George Allen & Unwin', 'Inglés', 'the_hobbit.png', '310', 'Bilbo Bolsón se embarca en una gran aventura.', '6', 0, 0),
(5, 'Fahrenheit 451', '1953', 'Ray Bradbury', 'Ciencia ficción', 'Ballantine Books', 'Inglés', 'fahrenheit_451.jpg', '256', 'Una sociedad donde los libros están prohibidos.', '2', 2, 1),
(6, 'La sombra del viento', '2001', 'Carlos Ruiz Zafón', 'Misterio', 'Planeta', 'Español', 'la_sombra_del_viento.jpg', '565', 'Un joven descubre un libro olvidado que cambiará su vida.', '5', 5, 1),
(7, 'Crónica de una muerte anunciada', '1981', 'Gabriel García Márquez', 'Novela', 'Oveja Negra', 'Español', 'cronica_de_una_muerte_anunciada.jpg', '120', 'Una muerte conocida por todos, menos por el asesinado.', '4', 2, 1),
(8, 'El principito', '1943', 'Antoine de Saint-Exupéry', 'Fábula', 'Reynal & Hitchcock', 'Francés', 'el_principito.jpg', '96', 'Un pequeño príncipe viaja por planetas y enseña lecciones de vida.', '3', 1, 1),
(9, 'To Kill a Mockingbird', '1960', 'Harper Lee', 'Drama', 'J. B. Lippincott & Co.', 'Inglés', 'to_kill_a_mockingbird.jpg', '281', 'Una historia sobre justicia y racismo en el sur de EE.UU.', '4', 4, 1),
(10, 'Drácula', '1897', 'Bram Stoker', 'Terror', 'Archibald Constable', 'Inglés', 'dracula.jpg', '418', 'El clásico de vampiros que originó una leyenda.', '6', 3, 1),
(11, 'Don Quijote', '1989', 'Miguel de Cervantes', 'Tragedia', 'Lib', 'Español', NULL, '21', 'asdasdsaa', '32', 12, 7),
(12, 'El Caballero Carmelo', '1913', 'Abraham Valdelomar', 'Ficcion', 'La Nacion', 'Español', NULL, '100', 'sadasda', '60', 30, 28);

ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

-- ------------------------
-- Tabla: empleados
-- ------------------------

CREATE TABLE `empleados` (
  `id` int(11) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `rol` enum('SUPERVISOR','ADMINISTRADOR','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `empleados` (`id`, `usuario`, `clave`, `rol`) VALUES
(1, 'oscar', 'Oscar123&', 'ADMINISTRADOR');

ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `empleados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

-- ------------------------
-- Tabla: users
-- ------------------------

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `last_name_p` varchar(30) NOT NULL,
  `last_name_m` varchar(30) NOT NULL,
  `domicilio` varchar(250) DEFAULT NULL,
  `tel` varchar(25) DEFAULT NULL,
  `sanctions` int(11) DEFAULT 0,
  `sanc_money` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `users` (`id`, `name`, `last_name_p`, `last_name_m`, `domicilio`, `tel`, `sanctions`, `sanc_money`) VALUES
(1, 'Oscar', 'Arotinco', 'Hualpa', 'Av atalaya', '9923334556', 0, 0),
(2, 'Andres', 'Gonzales', 'Mendoza', 'Av Proceres', '994332554', 0, 0),
(3, 'Juan', 'Arotinco', 'Mendoza', 'av.dsada', '333777113', 0, 0);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

-- ------------------------
-- Tabla: lendings
-- ------------------------

CREATE TABLE `lendings` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `date_out` varchar(255) NOT NULL,
  `date_return` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `lendings` (`id`, `user_id`, `book_id`, `date_out`, `date_return`) VALUES
(9, 1, 2, '13-06-2025', '13-06-2025'),
(10, 2, 2, '04-07-2025', NULL);

ALTER TABLE `lendings`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `lendings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
