INSERT INTO address(id, street, zipcode, city)
VALUES (1, 'Rue Italie', 1000, 'Bruxelles'),
       (2, 'Rue Espagne', 1060, 'Saint-Gilles'),
       (3, 'Grand Place', 1000, 'Bruxelles'),
       (4, 'Boulevard de l''Atomium', 1020, 'Bruxelles'),
       (5, 'Rue Washington', 1050, 'Ixelles');

INSERT INTO employee(id, name, firstname, service, floor, address_id)
VALUES (1, 'Coq', 'Fernand', 'Helpdesk', 1,1),
       (2, 'Dupont', 'Julien', 'Reception', 0,2),
       (3, 'Habiba', 'Selma', 'RH', 6,4),
       (4, 'Zéribi', 'Karim', 'Director', 7,3),
       (5, 'Abdoulay', 'Issa', 'Developer', 5,5);