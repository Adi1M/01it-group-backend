CREATE TABLE IF NOT EXISTS "category"
(
    "id"   integer PRIMARY KEY,
    "name" varchar NOT NULL,
    "parent_id" integer,
    FOREIGN KEY ("parent_id") REFERENCES category ("id") ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS "Brand"
(
    "id"          integer PRIMARY KEY,
    "name"        varchar UNIQUE NOT NULL,
    "description" text           NOT NULL,
    "logo_url"    varchar        NOT NULL
);

CREATE TABLE IF NOT EXISTS "role"
(
    "id"   integer PRIMARY KEY,
    "name" varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "_user"
(
    "id"           serial PRIMARY KEY,
    "first_name"   varchar        NOT NULL,
    "last_name"    varchar        NOT NULL,
    "phone_number" varchar UNIQUE NOT NULL,
    "email"        varchar UNIQUE NOT NULL,
    "gender"       varchar,
    "birth_day"    date,
    "role_id"      integer,
--     "password"     varchar NOT NULL,
    FOREIGN KEY ("role_id") REFERENCES "role" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "product"
(
    "id"           integer PRIMARY KEY,
    "name"         varchar NOT NULL,
    "price"        decimal NOT NULL,
    "description"  text    NOT NULL,
    "rating_total" decimal NOT NULL,
    "category_id"  integer,
    "brand_id"     integer,
    "img_url"      varchar NOT NULL,
    "quantity"     integer NOT NULL,
    FOREIGN KEY ("category_id") REFERENCES category ("id") ON DELETE CASCADE,
    FOREIGN KEY ("brand_id") REFERENCES "Brand" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "tag"
(
    "id"   integer PRIMARY KEY,
    "name" varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "product_tags"
(
    "product_id" integer,
    "tag_id"     integer,
    FOREIGN KEY ("product_id") REFERENCES "product" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("tag_id") REFERENCES "tag" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "basket"
(
    "id"          integer PRIMARY KEY,
    "user_id"     integer,
    "total_price" decimal,
    FOREIGN KEY ("user_id") REFERENCES "_user" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "payment_status"
(
    "id"     integer PRIMARY KEY,
    "status" varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "order"
(
    "id"         integer PRIMARY KEY,
    "user_id"    integer,
    "status_id"  integer,
    "price"      decimal,
    "created_at" timestamp,
    "updated_at" timestamp,
    FOREIGN KEY ("user_id") REFERENCES "_user" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("status_id") REFERENCES "payment_status" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "ordered_products"
(
    "product_id" integer,
    "order_id"   integer,
    FOREIGN KEY ("product_id") REFERENCES "product" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("order_id") REFERENCES "order" ("id") ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS "products_in_Basket"
(
    "product_id" integer,
    "basket_id"  integer,
    "quantity"   integer,
    FOREIGN KEY ("product_id") REFERENCES "product" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("basket_id") REFERENCES "basket" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "comment"
(
    "id"         integer PRIMARY KEY,
    "product_id" integer,
    "user_id"    integer,
    "content"    text    NOT NULL,
    "rating"     integer NOT NULL,
    "created_by" timestamp,
    FOREIGN KEY ("product_id") REFERENCES "product" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("user_id") REFERENCES "_user" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "favorites"
(
    "product_id" integer,
    "user_id"    integer,
    FOREIGN KEY ("product_id") REFERENCES "product" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("user_id") REFERENCES "_user" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "reply"
(
    "id"         integer PRIMARY KEY,
    "user_id"    integer,
    "comment_id" integer,
    "content"    text NOT NULL,
    "created_by" timestamp,
    FOREIGN KEY ("user_id") REFERENCES "_user" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("comment_id") REFERENCES "comment" ("id") ON DELETE CASCADE
);

ALTER TABLE "_user" add column password varchar(255);
-- CREATE TABLE IF NOT EXISTS "Parent_category"
-- (
--     "child_id"  integer,
--     "parent_id" integer,
--     FOREIGN KEY ("child_id") REFERENCES "Category" ("id") ON DELETE CASCADE,
--     FOREIGN KEY ("parent_id") REFERENCES "Category" ("id") ON DELETE CASCADE
-- );
