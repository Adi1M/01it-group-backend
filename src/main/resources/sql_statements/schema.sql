CREATE TABLE "Product"
(
    "id"           integer PRIMARY KEY,
    "name"         varchar NOT NULL,
    "price"        decimal NOT NULL,
    "description"  text    NOT NULL,
    "rating_total" decimal NOT NULL,
    "category_id"  integer,
    "brand_id"     integer,
    "img_url"      varchar NOT NULL,
    "quantity"     integer NOT NULL
);

CREATE TABLE "Category"
(
    "id"   integer PRIMARY KEY,
    "name" varchar NOT NULL
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
    "id"         integer PRIMARY KEY,
    "user_id"    integer,
    "status_id"  integer,
    "price"      decimal,
    "created_at" timestamp,
    "updated_at" timestamp
);

CREATE TABLE "Payment_status"
(
    "id"     integer PRIMARY KEY,
    "status" varchar NOT NULL
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
    "name"        varchar UNIQUE NOT NULL,
    "description" text           NOT NULL,
    "logo_url"    varchar        NOT NULL
);

CREATE TABLE "Comment"
(
    "id"         integer PRIMARY KEY,
    "product_id" integer,
    "user_id"    integer,
    "content"    text    NOT NULL,
    "rating"     integer NOT NULL,
    "created_by" timestamp
);

CREATE TABLE "Role"
(
    "id"   integer PRIMARY KEY,
    "name" varchar NOT NULL
);

CREATE TABLE "User"
(
    "id"           integer PRIMARY KEY,
    "first_name"   varchar        NOT NULL,
    "last_name"    varchar        NOT NULL,
    "phone_number" varchar UNIQUE NOT NULL,
    "email"        varchar UNIQUE NOT NULL,
    "gender"       varchar,
    "birth_day"    date,
    "role_id"      integer
);

CREATE TABLE "Basket"
(
    "id"          integer PRIMARY KEY,
    "user_id"     integer,
    "total_price" decimal
);

CREATE TABLE "Favorites"
(
    "product_id" integer,
    "user_id"    integer
);

CREATE TABLE "Reply"
(
    "id"         integer PRIMARY KEY,
    "user_id"    integer,
    "comment_id" integer,
    "content"    text NOT NULL,
    "created_by" timestamp
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
    ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

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

ALTER TABLE "User"
    ADD FOREIGN KEY ("role_id") REFERENCES "Role" ("id");

ALTER TABLE "Basket"
    ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "Favorites"
    ADD FOREIGN KEY ("product_id") REFERENCES "Product" ("id");

ALTER TABLE "Favorites"
    ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "Reply"
    ADD FOREIGN KEY ("user_id") REFERENCES "User" ("id");

ALTER TABLE "Reply"
    ADD FOREIGN KEY ("comment_id") REFERENCES "Comment" ("id");
