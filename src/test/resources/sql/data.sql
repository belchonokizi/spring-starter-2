INSERT INTO company (id, company_name)
VALUES (1, 'Google'),
       (2, 'Meta'),
       (3, 'Amazon');

SELECT SETVAL('company_id_seq', (SELECT MAX(id) FROM company));

select
    c.company_name,
    c."id"
from
    company c;

INSERT INTO company_locales (company_id, lang, description)
VALUES ((SELECT id FROM company WHERE company_name = 'Google'), 'en', 'Google description'),
       ((SELECT id FROM company WHERE company_name = 'Google'), 'ru', 'Google описание'),
       ((SELECT id FROM company WHERE company_name = 'Meta'), 'en', 'Meta description'),
       ((SELECT id FROM company WHERE company_name = 'Meta'), 'ru', 'Meta описание'),
       ((SELECT id FROM company WHERE company_name = 'Amazon'), 'en', 'Amazon description'),
       ((SELECT id FROM company WHERE company_name = 'Amazon'), 'ru', 'Amazon описание');

INSERT INTO users (id, birth_date, firstname, lastname, role, username, company_id)
VALUES (1, '1990-01-10', 'Ivan', 'Ivanov', 'ADMIN', 'ivan@gmail.com', (SELECT id FROM company WHERE company_name = 'Google')),
       (2, '1995-10-19', 'Petr', 'Petrov', 'USER', 'petr@gmail.com', (SELECT id FROM company WHERE company_name = 'Google')),
       (3, '2001-12-23', 'Sveta', 'Svetikova', 'USER', 'sveta@gmail.com', (SELECT id FROM company WHERE company_name = 'Meta')),
       (4, '1984-03-14', 'Vlad', 'Vladikov', 'USER', 'vlad@gmail.com', (SELECT id FROM company WHERE company_name = 'Amazon')),
       (5, '1984-03-14', 'Kate', 'Smith', 'ADMIN', 'kate@gmail.com', (SELECT id FROM company WHERE company_name = 'Amazon'));

SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO payment (amount, receiver_id)
VALUES (100, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (300, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (500, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (250, (SELECT id FROM users WHERE username = 'petr@gmail.com')),
       (600, (SELECT id FROM users WHERE username = 'petr@gmail.com')),
       (500, (SELECT id FROM users WHERE username = 'petr@gmail.com')),
       (400, (SELECT id FROM users WHERE username = 'sveta@gmail.com')),
       (300, (SELECT id FROM users WHERE username = 'sveta@gmail.com')),
       (500, (SELECT id FROM users WHERE username = 'vlad@gmail.com')),
       (700, (SELECT id FROM users WHERE username = 'vlad@gmail.com')),
       (340, (SELECT id FROM users WHERE username = 'vlad@gmail.com')),
       (440, (SELECT id FROM users WHERE username = 'kate@gmail.com')),
       (510, (SELECT id FROM users WHERE username = 'kate@gmail.com')),
       (630, (SELECT id FROM users WHERE username = 'kate@gmail.com'));

INSERT INTO chat (chat_name)
VALUES ('dmdev'),
       ('java'),
       ('database');

INSERT INTO users_chat(user_id, chat_id)
VALUES ((SELECT id FROM users WHERE username = 'ivan@gmail.com'), (SELECT id FROM chat WHERE chat_name = 'dmdev')),
       ((SELECT id FROM users WHERE username = 'petr@gmail.com'), (SELECT id FROM chat WHERE chat_name = 'dmdev')),
       ((SELECT id FROM users WHERE username = 'sveta@gmail.com'), (SELECT id FROM chat WHERE chat_name = 'dmdev')),
       ((SELECT id FROM users WHERE username = 'petr@gmail.com'), (SELECT id FROM chat WHERE chat_name = 'java')),
       ((SELECT id FROM users WHERE username = 'sveta@gmail.com'), (SELECT id FROM chat WHERE chat_name = 'java')),
       ((SELECT id FROM users WHERE username = 'vlad@gmail.com'), (SELECT id FROM chat WHERE chat_name = 'java')),
       ((SELECT id FROM users WHERE username = 'kate@gmail.com'), (SELECT id FROM chat WHERE chat_name = 'java')),
       ((SELECT id FROM users WHERE username = 'petr@gmail.com'), (SELECT id FROM chat WHERE chat_name = 'database')),
       ((SELECT id FROM users WHERE username = 'kate@gmail.com'), (SELECT id FROM chat WHERE chat_name = 'database'));