CREATE TABLE IF NOT EXISTS payment_types(
   payment_type_id BIGSERIAL PRIMARY KEY,
   type_name varchar(20),
    version int,
    created_at bigint,
    updated_at bigint
);

CREATE TABLE IF NOT EXISTS payments(
    payment_id BIGSERIAL PRIMARY KEY,
    amount decimal,
    payment_type_id BIGINT,
    "date" bigint,
    customer_id bigint,
    version int,
    created_at bigint,
    updated_at bigint,
    constraint fk_payment_payment_type foreign key(payment_type_id) references payment_types(payment_type_id)
);

CREATE TABLE IF NOT EXISTS inventories(
    item_id BIGSERIAL PRIMARY KEY,
    item_name varchar(255),
    quantity int,
    price decimal,
    version int,
    created_at bigint,
    updated_at bigint
);