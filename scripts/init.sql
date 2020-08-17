CREATE TABLE IF NOT EXISTS user(
	id int,
	user_name varchar(255),
	email varchar(255),
	access_token varchar(255)
);

INSERT INTO user (id, user_name, email, access_token)
    VALUES (1, 'Applifting', 'info@applifting.cz', '93f39e2f-80de-4033-99ee-249d92736a25');

INSERT INTO user (id, user_name, email, access_token)
    VALUES (2, 'Batman', 'batman@example.com', 'dcb20f8a-5657-4f1b-9f7f-ce65739b359e');

