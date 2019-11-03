CREATE TYPE status AS ENUM ('Active', 'Blocked');

CREATE TABLE subscriber (
    id         bigserial PRIMARY KEY              NOT NULL,
    first_name text                               NOT NULL,
    last_name  text                               NOT NULL,
    msisdn     text      UNIQUE                   NOT NULL,
    balance    bigint    DEFAULT 0                NOT NULL,
    status     status    DEFAULT 'Active'::status NOT NULL,
    created    timestamp DEFAULT now()            NOT NULL,
    updated    timestamp DEFAULT now()            NOT NULL
);

CREATE TABLE call (
    id            bigserial PRIMARY KEY   NOT NULL,
    subscriber_id bigserial REFERENCES subscriber (id) ON DELETE CASCADE,
    created       timestamp DEFAULT now() NOT NULL,
    updated       timestamp DEFAULT now() NOT NULL
);