
CREATE TABLE customer(
    id          SERIAL,
    name        VARCHAR(50),
    email       VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE p_order(
    id          SERIAL, 
    cust_id     INT, 
    o_date      DATE,
    total_cost  FLOAT(2),
    PRIMARY KEY (id),
    FOREIGN KEY (cust_id) 
        REFERENCES customer(id)
        ON DELETE RESTRICT
);

CREATE TABLE product(
    id          SERIAL,
    name        VARCHAR(50),
    unit_cost   FLOAT(2),
    PRIMARY KEY (id)
);

CREATE TABLE line_item(
    po_id       INT,
    line_no     INT,
    prod_id     INT,
    quantity    INT,
    PRIMARY KEY (po_id, line_no),
    FOREIGN KEY (po_id) 
        REFERENCES p_order(id)
        ON DELETE CASCADE,
    FOREIGN KEY (prod_id) 
        REFERENCES product(id)
        ON DELETE RESTRICT
);