CREATE TABLE IF NOT EXISTS "Category"
(
    "id"   integer PRIMARY KEY,
    "name" varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "Parent_category"
(
    "child_id"  integer,
    "parent_id" integer,
    FOREIGN KEY ("child_id") REFERENCES "Category" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("parent_id") REFERENCES "Category" ("id") ON DELETE CASCADE
);

CREATE TABLE IF N   OT EXISTS "Brand"
(
    "id"          integer PRIMARY KEY,
    "name"        varchar UNIQUE NOT NULL,
    "description" text           NOT NULL,
    "logo_url"    varchar        NOT NULL
);

CREATE TABLE IF NOT EXISTS "Role"
(
    "id"   integer PRIMARY KEY,
    "name" varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "User"
(
    "id"           integer PRIMARY KEY,
    "first_name"   varchar        NOT NULL,
    "last_name"    varchar        NOT NULL,
    "phone_number" varchar UNIQUE NOT NULL,
    "email"        varchar UNIQUE NOT NULL,
    "gender"       varchar,
    "birth_day"    date,
    "role_id"      integer,
    FOREIGN KEY ("role_id") REFERENCES "Role" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Product"
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
    FOREIGN KEY ("category_id") REFERENCES "Category" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("brand_id") REFERENCES "Brand" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Tag"
(
    "id"   integer PRIMARY KEY,
    "name" varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "Product_tags"
(
    "product_id" integer,
    "tag_id"     integer,
    FOREIGN KEY ("product_id") REFERENCES "Product" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("tag_id") REFERENCES "Tag" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Basket"
(
    "id"          integer PRIMARY KEY,
    "user_id"     integer,
    "total_price" decimal,
    FOREIGN KEY ("user_id") REFERENCES "User" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Payment_status"
(
    "id"     integer PRIMARY KEY,
    "status" varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "Order"
(
    "id"         integer PRIMARY KEY,
    "user_id"    integer,
    "status_id"  integer,
    "price"      decimal,
    "created_at" timestamp,
    "updated_at" timestamp,
    FOREIGN KEY ("user_id") REFERENCES "User" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("status_id") REFERENCES "Payment_status" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Ordered_products"
(
    "product_id" integer,
    "order_id"   integer,
    FOREIGN KEY ("product_id") REFERENCES "Product" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("order_id") REFERENCES "Order" ("id") ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS "Products_in_Basket"
(
    "product_id" integer,
    "basket_id"  integer,
    "quantity"   integer,
    FOREIGN KEY ("product_id") REFERENCES "Product" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("basket_id") REFERENCES "Basket" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Comment"
(
    "id"         integer PRIMARY KEY,
    "product_id" integer,
    "user_id"    integer,
    "content"    text    NOT NULL,
    "rating"     integer NOT NULL,
    "created_by" timestamp,
    FOREIGN KEY ("product_id") REFERENCES "Product" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("user_id") REFERENCES "User" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Favorites"
(
    "product_id" integer,
    "user_id"    integer,
    FOREIGN KEY ("product_id") REFERENCES "Product" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("user_id") REFERENCES "User" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Reply"
(
    "id"         integer PRIMARY KEY,
    "user_id"    integer,
    "comment_id" integer,
    "content"    text NOT NULL,
    "created_by" timestamp,
    FOREIGN KEY ("user_id") REFERENCES "User" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("comment_id") REFERENCES "Comment" ("id") ON DELETE CASCADE
);
