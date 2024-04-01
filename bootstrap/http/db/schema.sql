CREATE TABLE `user`
(
    id             BINARY(16)   NOT NULL,
    username       VARCHAR(255) NOT NULL,
    email          VARCHAR(255) NOT NULL,
    status_message VARCHAR(255),
    profile_image  VARCHAR(255),
    created_at     DATETIME     NOT NULL,
    updated_at     DATETIME     NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_social_login_info
(
    id         BINARY(16)   NOT NULL,
    email      VARCHAR(255) NOT NULL,
    user_id    BINARY(16)   NOT NULL,
    provider   VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL,
    PRIMARY KEY (id)
);
