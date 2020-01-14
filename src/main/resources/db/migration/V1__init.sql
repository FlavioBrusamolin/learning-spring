/**
 * Author:  flavio
 * Created: 13/01/2020
*/

CREATE TABLE product ( 
    id bigserial PRIMARY KEY NOT NULL, 
    name varchar(100) UNIQUE NOT NULL, 
    quantity bigint NOT NULL, 
    value numeric NOT NULL
);

