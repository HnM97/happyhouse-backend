
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
  token VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (user_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into happyhouse.members (user_id, user_name, user_password, email_id, email_domain, join_date, token)
values     ('ssafy', '김싸피', '1234', 'ssafy', 'ssafy.com', now(), null), 
        ('admin', '관리자', '1234', 'admin', 'google.com', now(), null);

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


DROP TABLE IF EXISTS interest;
CREATE TABLE IF NOT EXISTS happyhouse.interest (
  user_id VARCHAR(16) NOT NULL,
  dongcode VARCHAR(10) NOT NULL,
  PRIMARY KEY (user_id, dongcode))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


alter table houseinfo add hit int default 0;

DROP TABLE IF EXISTS bookmark;
create table bookmark (
	user_id varchar(16),
    aptCode  bigint,
    dongCode varchar(10),
    primary key(user_id, aptCode),
    foreign key(user_id) references `members`(`user_id`),
    foreign key(aptCode) references `houseinfo`(`aptCode`)
);


-- view 생성
drop view bookmarkinfo;
create view bookmarkinfo as
select bm.user_id, bm.aptCode, bm.dongcode, hi.apartmentName, hi.buildyear, hi.roadName, hi.jibun, hi.lng, hi.lat
from bookmark as bm, houseinfo as hi
where bm.aptCode = hi.aptCode;


drop view house;
create view house as
select hi.aptCode, hi.dongcode, apartmentName, buildYear, dealAmount, area, dealyear, dealmonth, dealday, roadName, jibun, lng, lat, hi.hit
from houseinfo hi, housedeal hd
where hi.aptcode = hd.aptcode;

drop view apartment;
create view apartment as
select DISTINCT aptCode, dongcode, buildYear, apartmentName, roadName, jibun, lng, lat
from house;


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
