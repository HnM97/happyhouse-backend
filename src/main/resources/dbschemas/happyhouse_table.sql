
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Schema happyhouse

-- Schema happyhouse

CREATE SCHEMA IF NOT EXISTS happyhouse DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE happyhouse ;

-- Table happyhouse.members

DROP TABLE IF EXISTS happyhouse.members ;
CREATE TABLE IF NOT EXISTS happyhouse.members (
    user_id VARCHAR(16) NOT NULL,
    user_name VARCHAR(20) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    email_id VARCHAR(20) NULL DEFAULT NULL,
    email_domain VARCHAR(45) NULL DEFAULT NULL,
    join_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

alter table happyhouse.members modify user_password varchar(100);
alter table members add token varchar(1000) null default null;

commit;

-- Table happyhouse.board

DROP TABLE IF EXISTS happyhouse.board ;

CREATE TABLE IF NOT EXISTS happyhouse.board (
                                                article_no INT NOT NULL AUTO_INCREMENT,
                                                user_id VARCHAR(16) NULL DEFAULT NULL,
    subject VARCHAR(100) NULL DEFAULT NULL,
    content VARCHAR(2000) NULL DEFAULT NULL,
    hit INT NULL DEFAULT 0,
    register_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (article_no),
    INDEX board_to_members_user_id_fk (user_id ASC) VISIBLE,
    CONSTRAINT board_to_members_user_id_fk
    FOREIGN KEY (user_id)
    REFERENCES happyhouse.members (user_id))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- create table QnA
drop table if exists qna;
CREATE TABLE IF NOT EXISTS happyhouse.qna (
    user_id VARCHAR(16) NOT NULL,
    article_no INT NOT NULL AUTO_INCREMENT,
    subject VARCHAR(100) NULL DEFAULT NULL,
    content VARCHAR(2000) NULL DEFAULT NULL,
    hit INT NULL DEFAULT 0,
    register_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (article_no, user_id),
    INDEX qna_to_members_user_id_fk (user_id ASC) VISIBLE,
    CONSTRAINT qna_to_members_user_id_fk
    FOREIGN KEY (user_id)
    REFERENCES happyhouse.members (user_id))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- Table happyhousehouse.memo

DROP TABLE IF EXISTS happyhouse.memo ;

CREATE TABLE IF NOT EXISTS happyhouse.memo (
                                               memo_no INT NOT NULL AUTO_INCREMENT,
                                               user_id VARCHAR(16) NULL DEFAULT NULL,
    comment VARCHAR(500) NULL DEFAULT NULL,
    memo_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    article_no INT NULL DEFAULT NULL,
    PRIMARY KEY (memo_no),
    INDEX memo_to_qna_article_no_fk (article_no ASC) VISIBLE,
    INDEX memo_toqnaqna_member_fk_idx (user_id ASC) VISIBLE,
    CONSTRAINT memo_to_qna_article_no_fk
    FOREIGN KEY (article_no)
    REFERENCES happyhouse.qna (article_no)
    on delete cascade,
    CONSTRAINT memo_to_member_user_id_fk
    FOREIGN KEY (user_id)
    REFERENCES happyhouse.members (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


drop table interest;
CREATE TABLE IF NOT EXISTS happyhouse.interest (
    user_id VARCHAR(16) NOT NULL,
    dongcode VARCHAR(10) NOT NULL,
    PRIMARY KEY (user_id, dongcode))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


alter table houseinfo add hit int default 0;

drop table if exists bookmark;
create table bookmark (
                          user_id varchar(16),
                          aptCode  bigint,
                          dongCode varchar(10),
                          bookmark_no  INT NOT NULL AUTO_INCREMENT,
                          primary key(bookmark_no),
                          foreign key(user_id) references `members`(`user_id`),
                          foreign key(aptCode) references `houseinfo`(`aptCode`))
						  ENGINE = InnoDB
						  DEFAULT CHARACTER SET = utf8mb4
						  COLLATE = utf8mb4_0900_ai_ci;

-- view 생성
drop view bookmarkinfo;
create view bookmarkinfo as
select bm.user_id as user_id, bm.aptCode as aptCode, bm.dongcode as dongCode, hi.apartmentName as apartmentname, hi.buildyear as buildYear, hi.roadName as roadName, hi.jibun as jibun, hi.lng as lng, hi.lat as lat, min(cast(replace(hi.dealAmount, ",","") as UNSIGNED)) as minAmount, max(cast(replace(hi.dealAmount, ",","") as UNSIGNED)) as maxAmount
from bookmark as bm, house as hi
where bm.aptCode = hi.aptCode
group by bm.aptCode;


drop view house;
create view house as
select hi.aptCode, hi.dongcode, apartmentName, buildYear, dealAmount, area, dealyear, dealmonth, dealday, roadName, jibun, lng, lat, hi.hit
from houseinfo hi, housedeal hd
where hi.aptcode = hd.aptcode;

create index houseinfo_idx on houseinfo(aptCode);
create index housedeal_idx on houseinfo(aptCode);

drop table apartment;
create table apartment as
select DISTINCT aptCode, dongcode, buildYear, apartmentName, roadName, jibun, lng, lat, min(cast(replace(dealAmount, ",","") as UNSIGNED)) as minAmount, max(cast(replace(dealAmount, ",","") as UNSIGNED)) as maxAmount
from house
group by aptCode;

select count(aptCode) from apartment;

drop view sido;
create view sido as
select left(dongCode,2) dongcode, sidoName
        from dongcode
        GROUP BY left(dongcode,2);

drop view gugun;
create view gugun as
select left(dongCode,5) dongcode, gugunName
        from dongcode
        group by left(dongCode,5);

drop view dong;
create view dong as
select left(dongCode,8) dongcode, dongName
from dongcode
group by left(dongCode,8);



