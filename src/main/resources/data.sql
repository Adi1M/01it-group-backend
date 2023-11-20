DELETE FROM "Reply";
DELETE FROM "Favorites";
DELETE FROM "Comment";
DELETE FROM "Products_in_Basket";
DELETE FROM "Payment_status";
DELETE FROM "Order";
DELETE FROM "Ordered_products";
DELETE FROM "Basket";
DELETE FROM "Product";
DELETE FROM "User";
DELETE FROM "Role";
DELETE FROM "Brand";
DELETE FROM "Parent_category";
DELETE FROM "Category";

-- Insert data into "Role"
INSERT INTO "Role" ("id", "name") VALUES
                                      (1, 'Admin'),
                                      (2, 'User');

-- Insert data into "Category"
INSERT INTO "Category" ("id", "name") VALUES
                                          (1, 'Electronics'),
                                          (2, 'Clothing'),
                                          (3, 'Home and Kitchen');

-- Insert data into "Brand"
INSERT INTO "Brand" ("id", "name", "description", "logo_url") VALUES
                                                                  (1, 'Sony', 'Electronics brand', 'sony_logo.png'),
                                                                  (2, 'Nike', 'Sportswear brand', 'nike_logo.png');

-- Insert data into "User"
INSERT INTO "User" ("id", "first_name", "last_name", "phone_number", "email", "gender", "birth_day", "role_id") VALUES
                                                                                                                    (1, 'John', 'Doe', '1234567890', 'john@example.com', 'Male', '1990-01-01', 1),
                                                                                                                    (2, 'Jane', 'Smith', '9876543210', 'jane@example.com', 'Female', '1992-03-15', 2);

-- Insert data into "Product"
INSERT INTO "Product" ("id", "name", "price", "description", "rating_total", "category_id", "brand_id", "img_url", "quantity") VALUES
                                                                                                                                   (1, 'Smartphone', 599.99, 'High-end smartphone', 4.5, 1, 1, 'phone_image.jpg', 50),
                                                                                                                                   (2, 'Running Shoes', 89.99, 'Comfortable running shoes', 4.2, 2, 2, 'shoes_image.jpg', 100);

-- Insert data into "Basket"
INSERT INTO "Basket" ("id", "user_id", "total_price") VALUES
                                                          (1, 1, 0),
                                                          (2, 2, 0);

-- Insert data into "Payment_status"
INSERT INTO "Payment_status" ("id", "status") VALUES
                                                  (1, 'Paid'),
                                                  (2, 'Pending');

-- Insert data into "Order"
INSERT INTO "Order" ("id", "user_id", "status_id", "price", "created_at", "updated_at") VALUES
                                                                                            (1, 1, 1, 599.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                            (2, 2, 2, 89.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- Insert data into "Ordered_products"
INSERT INTO "Ordered_products" ("product_id", "order_id") VALUES
                                                              (1, 1),
                                                              (2, 2);

-- Insert data into "Products_in_Basket"
INSERT INTO "Products_in_Basket" ("product_id", "basket_id", "quantity") VALUES
                                                                             (1, 1, 1),
                                                                             (2, 2, 2);

-- Insert data into "Comment"
INSERT INTO "Comment" ("id", "product_id", "user_id", "content", "rating", "created_by") VALUES
                                                                                             (1, 1, 1, 'Great smartphone!', 5, CURRENT_TIMESTAMP),
                                                                                             (2, 2, 2, 'Comfortable shoes for running', 4, CURRENT_TIMESTAMP);

-- Insert data into "Favorites"
INSERT INTO "Favorites" ("product_id", "user_id") VALUES
                                                      (1, 1),
                                                      (2, 2);

-- Insert data into "Reply"
INSERT INTO "Reply" ("id", "user_id", "comment_id", "content", "created_by") VALUES
                                                                                 (1, 1, 1, 'Thank you!', CURRENT_TIMESTAMP),
                                                                                 (2, 2, 2, 'Glad you like them!', CURRENT_TIMESTAMP);
