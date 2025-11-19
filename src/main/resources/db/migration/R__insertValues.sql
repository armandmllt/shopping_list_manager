INSERT INTO users VALUES (1, 'admin','admin@gmail.com','admin'),
                        (2, 'user', 'user@gmail.com', 'user');

INSERT INTO ingredients VALUES (1, 'Lentilles corail'),
        (2, 'Pates'),
        (3, 'Beurre'),
        (4, 'Oeuf'),
        (5, 'Farine'),
        (6, 'Epices');

INSERT INTO recipes VALUES (1, 
                            'Pâtes au beurre', 
                            'Faire cuire les pates selon le temps de cuisson indiqué.\n
                            Une fois les pâtes cuites et égouttées, assaisonner, ajouter le beurre et servir.'),
                            (2, 
                            'Galettes de lentilles', 
                            'Faire cuire les lentilles corail dans un fond deau.\n
                            Une fois cuite, les mélanger avec un oeuf, la farine et des épices au choix.\n
                            Faire cuire au four à 180°C pendant environ 20 minutes.');
                            
-- Admin's shopping list
INSERT INTO shopping_lists VALUES (1, 1);
-- User's shopping list
INSERT INTO shopping_lists VALUES (2, 2);

-- Recipes and servings for Admin
INSERT INTO shopping_list_recipes VALUES (1, 1, 1, 2),
                                        (2, 1, 2, 4);
-- Recipes and servings for User
INSERT INTO shopping_list_recipes VALUES (3, 2, 1, 1);



-- Ingredients for pastas with butter
INSERT INTO recipe_ingredients VALUES (1, 1, 2, 100, 'g'),
                                    (2, 1, 3, 15, 'g');
-- Ingredients for lentils patty
INSERT INTO recipe_ingredients VALUES (3, 2, 1, 70, 'g'),
                                    (4, 2, 4, 1, NULL), 
                                    (5, 2, 5, 15, 'g'),
                                    (6, 2, 6, NULL, NULL);


