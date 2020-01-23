CREATE TABLE "user" ( 
    id bigserial PRIMARY KEY NOT NULL, 
    name varchar(100) NOT NULL,
    username varchar(100) UNIQUE NOT NULL, 
    email varchar(100) UNIQUE NOT NULL,
    password varchar(100) NOT NULL,  
    profile varchar(100) NOT NULL
);

CREATE TABLE product ( 
    id bigserial PRIMARY KEY NOT NULL, 
    name varchar(100) UNIQUE NOT NULL, 
    quantity bigint NOT NULL, 
    value numeric NOT NULL
);