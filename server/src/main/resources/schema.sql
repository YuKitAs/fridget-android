CREATE TABLE IF NOT EXISTS flatshares (
    id CHAR(36) UNIQUE,
    name VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS users (
    id CHAR(36) UNIQUE,
    google_user_id VARCHAR(255),
    google_name VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS access_codes (
    id CHAR(36) UNIQUE,
    flatshare_id CHAR(36),
    content CHAR(5),
    PRIMARY KEY(id),
    FOREIGN KEY(flatshare_id)
        REFERENCES flatshares(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS memberships (
    id CHAR(36) UNIQUE,
    flatshare_id CHAR(36),
    user_id CHAR(36),
    magnet_color CHAR(6),
    PRIMARY KEY(id),
    FOREIGN KEY(flatshare_id)
        REFERENCES flatshares(id)
        ON DELETE CASCADE,
    FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cool_notes (
    id CHAR(36) UNIQUE,
    title TEXT,
    content TEXT,
    creator_membership_id CHAR(36),
    importance INT,
    position INT,
    created_at DATETIME,
    PRIMARY KEY(id),
    FOREIGN KEY(creator_membership_id)
        REFERENCES memberships(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS frozen_notes (
    id CHAR(36) UNIQUE,
    flatshare_id CHAR(36),
    title TEXT,
    content TEXT,
    position INT,
    PRIMARY KEY(id),
    FOREIGN KEY(flatshare_id)
        REFERENCES flatshares(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS read_confirmations (
    id CHAR(36) UNIQUE,
    cool_note_id CHAR(36),
    membership_id CHAR(36),
    PRIMARY KEY(id),
    FOREIGN KEY(cool_note_id)
        REFERENCES cool_notes(id)
        ON DELETE CASCADE,
    FOREIGN KEY(membership_id)
        REFERENCES memberships(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tagged_members (
    id CHAR(36) UNIQUE,
    cool_note_id CHAR(36),
    membership_id CHAR(36),
    PRIMARY KEY(id),
    FOREIGN KEY(cool_note_id)
        REFERENCES cool_notes(id)
        ON DELETE CASCADE,
    FOREIGN KEY(membership_id)
        REFERENCES memberships(id)
        ON DELETE CASCADE
);

