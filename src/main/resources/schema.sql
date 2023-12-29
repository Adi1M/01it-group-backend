CREATE TABLE IF NOT EXISTS "category"
(
    "id"        serial PRIMARY KEY,
    "name"      varchar NOT NULL,
    "parent_id" integer,
    "img_url"   varchar not null,
    FOREIGN KEY ("parent_id") REFERENCES category ("id") ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS "brand"
(
    "id"          serial PRIMARY KEY,
    "name"        varchar UNIQUE NOT NULL,
    "description" text           NOT NULL,
    "logo_url"    varchar        NOT NULL
);

CREATE TABLE IF NOT EXISTS "role"
(
    "id"   serial PRIMARY KEY,
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
    "password"     varchar        NOT NULL,
    "city"         varchar        NOT NULL,
    "address"      varchar        NOT NULL,
    "avatar"       varchar,
    "role_id"      integer,
    FOREIGN KEY ("role_id") REFERENCES "role" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "product"
(
    "id"           serial PRIMARY KEY,
    "name"         varchar NOT NULL,
    "price"        decimal NOT NULL,
    "description"  text    NOT NULL,
    "rating_total" decimal NOT NULL,
    "category_id"  integer,
    "brand_id"     integer,
    "img_url"      varchar NOT NULL,
    "quantity"     integer NOT NULL,
    FOREIGN KEY ("category_id") REFERENCES category ("id") ON DELETE CASCADE,
    FOREIGN KEY ("brand_id") REFERENCES "brand" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "tag"
(
    "id"   serial PRIMARY KEY,
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
    "id"          serial PRIMARY KEY,
    "user_id"     integer,
    "total_price" decimal,
    FOREIGN KEY ("user_id") REFERENCES "_user" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "payment_status"
(
    "id"     serial PRIMARY KEY,
    "status" varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "order"
(
    "id"         serial PRIMARY KEY,
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



CREATE TABLE IF NOT EXISTS "products_in_basket"
(
    "product_id" integer,
    "basket_id"  integer,
    "quantity"   integer,
    FOREIGN KEY ("product_id") REFERENCES "product" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("basket_id") REFERENCES "basket" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "comment"
(
    "id"         serial PRIMARY KEY,
    "product_id" integer,
    "user_id"    integer,
    "content"    text    NOT NULL,
    "rating"     decimal NOT NULL,
    "created_at" timestamp,
    "updated_at" timestamp,
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
    "id"         serial PRIMARY KEY,
    "user_id"    integer,
    "comment_id" integer,
    "content"    text NOT NULL,
    "created_at" timestamp,
    "updated_at" timestamp,
    FOREIGN KEY ("user_id") REFERENCES "_user" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("comment_id") REFERENCES "comment" ("id") ON DELETE CASCADE
);