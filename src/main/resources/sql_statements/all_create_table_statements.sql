CREATE TABLE "Product"
(
    "id"           integer PRIMARY KEY,
    "name"         varchar,
    "price"        integer,
    "description"  varchar,
    "rating_total" double precision,
    "category_id"  integer,
    "brand_id"     integer,
    "img_url"      varchar,
    "quantity"     integer
);

CREATE TABLE "Category"
(
    "id"   integer PRIMARY KEY,
    "name" varchar
);

CREATE TABLE "Parent_category"
(
    "child_id"  integer,
    "parent_id" integer
);

CREATE TABLE "Ordered_products"
(
    "product_id" integer,
    "order_id"   integer
);

CREATE TABLE "Order"
(
    "id"        integer PRIMARY KEY,
    "user_id"   integer,
    "status_id" integer,
    "price"     integer
);

CREATE TABLE "Payment_status"
(
    "id"     integer PRIMARY KEY,
    "status" varchar
);

CREATE TABLE "Products_in_Basket"
(
    "product_id" integer,
    "basket_id"  integer,
    "quantity"   integer
);

CREATE TABLE "Brand"
(
    "id"          integer PRIMARY KEY,
    "name"        varchar,
    "description" varchar,
    "logo_url"    varchar
);

CREATE TABLE "Comment"
(
    "id"         integer PRIMARY KEY,
    "product_id" integer,
    "user_id"    integer,
    "content"    varchar,
    "rating"     integer,
    "date"       timestamp
);

CREATE TABLE "User"
(
    "id"           integer PRIMARY KEY,
    "name"         varchar,
    "phone_number" varchar,
    "role"         varchar
);

CREATE TABLE "Basket"
(
    "id"          integer PRIMARY KEY,
    "user_id"     integer,
    "total_price" integer
);

CREATE TABLE "Favorites"
(
    "id"      integer PRIMARY KEY,
    "user_id" integer
);

CREATE TABLE "Products_in_Favorites"
(
    "product_id"   integer,
    "favorites_id" integer
);

CREATE TABLE "Reply"
(
    "id"         integer PRIMARY KEY,
    "comment_id" integer,
    "user_id"    integer,
    "content"    varchar,
    "date"       timestamp
);

ALTER TABLE "Product"
    ADD FOREIGN KEY ("category_id") REFERENCES "Category" ("id");

ALTER TABLE "Product"
    ADD FOREIGN KEY ("brand_id") REFERENCES "Brand" ("id");

ALTER TABLE "Parent_category"
    ADD FOREIGN KEY ("child_id") REFERENCES "Category" ("id");

ALTER TABLE "Parent_category"
    ADD FOREIGN KEY ("parent_id") REFERENCES "Category" ("id");

ALTER TABLE "Ordered_products"
    ADD FOREIGN KEY ("product_id") REFERENCES "Product" ("id");

ALTER TABLE "Ordered_products"
    ADD FOREIGN KEY ("order_id") REFERENCES "Order" ("id");

ALTER TABLE "Order"
    ADD FOREIGN KEY ("status_id") REFERENCES "Payment_status" ("id");

ALTER TABLE "Products_in_Basket"
    ADD FOREIGN KEY ("product_id") REFERENCES "Product" ("id");

ALTER TABLE "Products_in_Basket"
    ADD FOREIGN KEY ("basket_id") REFERENCES "Basket" ("id");

ALTER TABLE "Comment"
    ADD FOREIGN KEY ("product_id") REFERENCES "Product" ("id");

ALTER TABLE "Comment"
    ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "Basket"
    ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "Favorites"
    ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "Products_in_Favorites"
    ADD FOREIGN KEY ("product_id") REFERENCES "Product" ("id");

ALTER TABLE "Products_in_Favorites"
    ADD FOREIGN KEY ("favorites_id") REFERENCES "Favorites" ("id");

ALTER TABLE "Reply"
    ADD FOREIGN KEY ("comment_id") REFERENCES "Comment" ("id");

ALTER TABLE "Reply"
    ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");
