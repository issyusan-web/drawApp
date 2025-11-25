-- （必要なら）同名DBを先に削除
-- DROP DATABASE IF EXISTS drawapp;

-- ①DB作成
--ENCODING = 'UTF8'は「文字列データを UTF-8 として保存・解釈する」
--TEMPLATE = template0;は「まっさらな空のデータベースをテンプレートにして、新しいDBを作る」

CREATE DATABASE drawapp
  WITH
    ENCODING = 'UTF8'
    TEMPLATE = template0;

-- ② 既存テーブルがあれば削除（外部キーもまとめて落とす）
DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS users  CASCADE;

-- ③ usersテーブル（ユーザー情報）
--   ・id         : 自動採番 PK
--   ・login_id   : ログインID（ユニーク）
--   ・password   : パスワード
--   ・name       : 名前
--   ・auth       : 権限 0=一般 / 1=管理者（デフォルト0）

CREATE TABLE users (
    id        INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    login_id  VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    name      VARCHAR(255) NOT NULL,
    auth      INTEGER NOT NULL DEFAULT 0 CHECK (auth IN (0, 1))
);

-- ④ memberテーブル（くじに参加するメンバー）
--   ・id       : 自動採番 PK
--   ・name     : メンバー名
--   ・users_id : users.id へのFK
--              親ユーザー削除時に自動で一緒に削除（ON DELETE CASCADE）

CREATE TABLE member (
    id       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     TEXT NOT NULL,
    users_id INTEGER NOT NULL,
    CONSTRAINT fk_member_users
        FOREIGN KEY (users_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

