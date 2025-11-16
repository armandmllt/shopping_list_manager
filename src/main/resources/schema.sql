CREATE TABLE `users` (
  `id_user` int PRIMARY KEY,
  `name` varchar(50),
  `email` varchar(255),
  `password` varchar(255)
);

CREATE TABLE `shopping_lists` (
  `id_list` int PRIMARY KEY,
  `fk_user` int
);

CREATE TABLE `shopping_list_recipe` (
  `id_shopping_list_recipe` int PRIMARY KEY,
  `fk_shopping_list` int,
  `fk_recipe` int,
  `servings` int
);

CREATE TABLE `recipes` (
  `id_recipe` int PRIMARY KEY,
  `name` varchar(255),
  `instructions` text
);

CREATE TABLE `recipes_ingredients` (
  `id_recipe_ingredient` int PRIMARY KEY,
  `fk_recipe` int,
  `fk_ingredient` int,
  `quantity` int,
  `unit` varchar(10)
);

CREATE TABLE `ingredients` (
  `id_ingredient` int PRIMARY KEY,
  `name` varchar(255)
);

ALTER TABLE `users` ADD FOREIGN KEY (`id_user`) REFERENCES `shopping_lists` (`fk_user`);

ALTER TABLE `recipes_ingredients` ADD FOREIGN KEY (`fk_ingredient`) REFERENCES `ingredients` (`id_ingredient`);

ALTER TABLE `recipes_ingredients` ADD FOREIGN KEY (`fk_recipe`) REFERENCES `recipes` (`id_recipe`);

ALTER TABLE `shopping_list_recipe` ADD FOREIGN KEY (`fk_shopping_list`) REFERENCES `shopping_lists` (`id_list`);

ALTER TABLE `shopping_list_recipe` ADD FOREIGN KEY (`fk_recipe`) REFERENCES `recipes` (`id_recipe`);
