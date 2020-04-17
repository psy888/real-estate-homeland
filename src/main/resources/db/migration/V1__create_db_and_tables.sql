-- CREATE DATABASE real_estate;
CREATE SCHEMA sc;

-- users and roles
CREATE TABLE sc.app_role
(
    role_id   bigint                NOT NULL,
    role_name character varying(30) NOT NULL,
    CONSTRAINT app_role_pkey PRIMARY KEY (role_id),
    CONSTRAINT app_role_uk UNIQUE (role_name)
);

CREATE TABLE sc.user_role
(
    id      bigint                 NOT NULL,
    role_id bigint                 NOT NULL,
    user_id character varying(255) NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (id),
    CONSTRAINT user_role_uk UNIQUE (user_id, role_id)
);

CREATE TABLE sc."user"
(
    id                 character varying(255) NOT NULL,
    email              character varying(255) NOT NULL,
    encrypted_password character varying(255) NOT NULL,
    first_name         character varying(255) NOT NULL,
    enabled            boolean,
    last_name          character varying(255),
    phone_number       character varying(15)  NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_email_uk UNIQUE (email)
);

-- model
CREATE TABLE sc.property
(
    id              character varying(255) NOT NULL,
    address         character varying(255),
    area            integer,
    bathroom_number integer,
    bedroom_number  integer,
    city            character varying(255),
    currency_code   character varying(3),
    description     character varying(255),
    featured        boolean,
    promoted        boolean,
    price           double precision,
    action_type     character varying(25),
    property_type   character varying(25),
    agent_id        character varying(255),
    CONSTRAINT property_pkey PRIMARY KEY (id)
);

CREATE TABLE sc.photo
(
    filename character varying(255) NOT NULL,
    ext      character varying(5),
    is_main  boolean,
    CONSTRAINT photo_pkey PRIMARY KEY (filename)
);


CREATE TABLE sc.images
(
    property_id         character varying(255) NOT NULL,
    main_image_filename character varying(255) NOT NULL
);


--security
CREATE TABLE Persistent_Logins
(
    username  varchar(64) not null,
    series    varchar(64) not null,
    token     varchar(64) not null,
    last_used timestamp   not null,
    PRIMARY KEY (series)
);

