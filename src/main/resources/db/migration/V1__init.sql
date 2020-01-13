/**
 * Author:  flavio
 * Created: 13/01/2020
*/

CREATE TABLE product ( 
    id bigint PRIMARY KEY NOT NULL, 
    name varchar(100) UNIQUE NOT NULL, 
    quantity decimal NOT NULL, 
    value decimal NOT NULL
);

