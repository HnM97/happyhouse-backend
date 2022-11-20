-- insert into qna(`user_id`, `subject`,`content`,`hit`,`register_time`)
-- values("ssafy", "test", "testcontent", 0, now());

-- select * from qna;

-- insert into memo(`user_id`, `comment`,`article_no`,`memo_time`)
-- values("ssafy", "testcomment", 1, now());

-- select * from members;
-- select * from memo;

------------------------------------
alter table houseinfo add hit int default 0;


select * from members;
desc members;
desc houseinfo;
create table bookmark (
	user_id varchar(16),
    aptCode  bigint,
    dongCode varchar(10),
    primary key(user_id, aptCode),
    foreign key(user_id) references `members`(`user_id`),
    foreign key(aptCode) references `houseinfo`(`aptCode`)
);
desc bookmark;

drop view bookmarkinfo;
create view bookmarkinfo as
select bm.user_id, bm.aptCode, bm.dongcode, hi.apartmentName, hi.buildyear, hi.roadName, hi.jibun, hi.lng, hi.lat
from bookmark as bm, houseinfo as hi
where bm.aptCode = hi.aptCode;

----------------------
select * from members;
select * from houseinfo;
desc bookmarkinfo;
select * from bookmarkinfo;

insert into bookmark
values ('ssafy', '11110000000001', '11110000');
        
select * from bookmark;
delete from bookmark where user_id = 'ssafy';

---------------------------------------------------
-- 사용할 view 생성

drop view house;
create view house as
select hi.aptCode, hi.dongcode, apartmentName, buildYear, dealAmount, area, dealyear, dealmonth, dealday, roadName, jibun, lng, lat, hi.hit
from houseinfo hi, housedeal hd
where hi.aptcode = hd.aptcode;

SELECT *
from house;

drop view apartment;
create view apartment as
select DISTINCT aptCode, dongcode, buildYear, apartmentName, roadName, jibun, lng, lat
from house;

SELECT *
from apartment;
-------------------------------------------------
-- view 생성

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

select * from sido;

select * from gugun;

select * from dong;


-----------------------------------------------------------
-- 아래와 같이 dao에서 해당 쿼리문을 실행하여 컨트롤러에서 패치

-- 구군의 동코드와 구군 이름을 반환
SELECT *
from sido, gugun
where sido.dongcode = left(gugun.dongcode,2)
and sido.dongcode = 26;
-- 11을 ?로 바꾸어서 처리

-- 동의 동코드와 동 이름을 반환
SELECT dong.dongcode, dong.dongname
from gugun, dong
where gugun.dongcode = left(dong.dongcode,5)
and gugun.dongcode = 11110;
-- 11110을 ?로 바꾸어서 처리



