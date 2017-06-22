/*
Navicat PGSQL Data Transfer

Source Server         : postgres
Source Server Version : 90309
Source Host           : localhost:5432
Source Database       : mybatis
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90309
File Encoding         : 65001

Date: 2017-06-22 20:05:03
*/


-- ----------------------------
-- Sequence structure for role_id_seq
-- ----------------------------
DROP SEQUENCE "public"."role_id_seq";
CREATE SEQUENCE "public"."role_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;
SELECT setval('"public"."role_id_seq"', 3, true);

-- ----------------------------
-- Sequence structure for users_id_seq
-- ----------------------------
DROP SEQUENCE "public"."users_id_seq";
CREATE SEQUENCE "public"."users_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 21
 CACHE 1;
SELECT setval('"public"."users_id_seq"', 21, true);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS "public"."role";
CREATE TABLE "public"."role" (
"id" int4 DEFAULT nextval('role_id_seq'::regclass) NOT NULL,
"role_name" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO "public"."role" VALUES ('1', 'admin');
INSERT INTO "public"."role" VALUES ('2', 'user');
INSERT INTO "public"."role" VALUES ('3', 'moderator');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
"id" int4 DEFAULT nextval('users_id_seq'::regclass) NOT NULL,
"user_name" varchar(100) COLLATE "default" NOT NULL,
"role_id" int4 DEFAULT 2 NOT NULL,
"email" varchar(100) COLLATE "default" NOT NULL,
"gender" varchar(1) COLLATE "default" NOT NULL,
"phone_number" varchar(30) COLLATE "default" NOT NULL,
"status" bool DEFAULT true NOT NULL,
"user_hash" varchar(100) COLLATE "default" NOT NULL,
"password" varchar(40) COLLATE "default" NOT NULL,
"profile_url" varchar(1000) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO "public"."users" VALUES ('1', 'Theara Seng', '1', 'seng.theara.kh@gmail.com', 'M', '070734166', 't', 'ajlfkjkasfjklsadjflk', '123', 'https://scontent.fpnh4-1.fna.fbcdn.net/v/t1.0-9/17991902_702597506612365_8361224158021214888_n.jpg?oh=bb1acb80357caefda92ba5c152f8c8cb&oe=59E9BC61');
INSERT INTO "public"."users" VALUES ('2', 'Bunthai Deng', '2', 'bunthai.pink@gmaill.com', 'M', '010747583', 't', 'lkjflksadjkfjlskadf', '123', 'https://scontent.fpnh4-1.fna.fbcdn.net/v/t1.0-1/p160x160/16265977_1323890707672069_1454257451006852506_n.jpg?oh=278904a72fcdc0cffc4213fb0f878645&oe=59EA4A8F');
INSERT INTO "public"."users" VALUES ('21', 'asdf', '1', 'asdfasdf', 'M', 'false', 't', 'fasdfsadfsdafsadfsdafsdafflksjdflksajdlkfjsdalkf', '111111111111111111111111', 'asdf');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."role_id_seq" OWNED BY "role"."id";
ALTER SEQUENCE "public"."users_id_seq" OWNED BY "users"."id";

-- ----------------------------
-- Primary Key structure for table role
-- ----------------------------
ALTER TABLE "public"."role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."users"
-- ----------------------------
ALTER TABLE "public"."users" ADD FOREIGN KEY ("role_id") REFERENCES "public"."role" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
