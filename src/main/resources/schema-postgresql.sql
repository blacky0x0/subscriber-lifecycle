CREATE TYPE status AS ENUM ('Active', 'Blocked');

CREATE TABLE subscriber (
    id              bigserial PRIMARY KEY NOT NULL,
    first_name      text NOT NULL,
    last_name       text NOT NULL,
    msisdn          text NOT NULL,
    balance         bigint NOT NULL,
    status          status NOT NULL
);
