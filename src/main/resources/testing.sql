INSERT INTO product (name, unit_price, qty, imported_date)
VALUES
    ('Product 1', 1000.00, 10, '2024-03-08'),
    ('Product 2', 1000.00, 10, '2024-03-08'),
    ('Product 3', 1000.00, 10, '2024-03-08'),
    ('Product 4', 1000.00, 10, '2024-03-08'),
    ('Product 5', 1000.00, 10, '2024-03-08'),
    ('Product 6', 1000.00, 10, '2024-03-08'),
    ('Product 7', 1000.00, 10, '2024-03-08'),
    ('Product 8', 1000.00, 10, '2024-03-08'),
    ('Product 9', 1000.00, 10, '2024-03-08'),
    ('Product 10', 1000.00, 10, '2024-03-08'),
    ('Product 11', 1000.00, 10, '2024-03-08'),
    ('Product 12', 1000.00, 10, '2024-03-08'),
    ('Product 13', 1000.00, 10, '2024-03-08'),
    ('Product 14', 1000.00, 10, '2024-03-08'),
    ('Product 15', 1000.00, 10, '2024-03-08');

drop table if exists product;

select * from product;

drop database if exists product_db;