
CREATE TABLE BBS(
	seq int auto_increment primary key,
	id varchar(50) not null,
	
	ref decimal(8) not null,
	step decimal(8) not null,
	depth decimal(8) not null,
	
	title varchar(200) not null,
	content varchar(4000) not null,
	wdate timestamp not null,
	
	del decimal(1) not null,
	readcount decimal(8) not null
);

alter table bbs
add foreign key(id) references member(id);

INSERT INTO BBS(ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT)
VALUES('ID', (SELECT IFNULL(MAX(REF), 0)+1 FROM BBS B), 0, 0, 'TITLE', 'CONTENT', NOW(), 0, 0));

select seq, id, ref, step, depth, title, content, wdate, del, readcount
from
(select row_number()over(order by ref desc, step asc) as rnum,
seq, id, ref, step, depth, title, content, wdate, del, readcount
from bbs
-- 검색
order by ref desc, step asc) a
where rnum between 1 and 10;

select seq, id, ref, step, depth, title, content, wdate, del, readcount from (select row_number()over(order by ref desc, step asc) as rnum,seq, id, ref, step, depth, title, content, wdate, del, readcount from bbs order by ref desc, step asc) a where rnum between ? and ? WHERE TITLE LIKE '%230억%' 
select seq, id, ref, step, depth, title, content, wdate, del, readcount from (select row_number()over(order by ref desc, step asc) as rnum,seq, id, ref, step, depth, title, content, wdate, del, readcount from bbs  WHERE TITLE LIKE '%120억%' order by ref desc, step asc) a where rnum between ? and ?

-- 답글 쿼리
UPDATE BBS
SET STEP=STEP+1
WHERE REF=(SELECT REF FROM BBS WHERE SEQ = ?)
AND STEP > (SELECT STEP FROM BBS WHERE SEQ = ?);

INSERT INTO BBS(SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT)
VALUES(?
			, (SELECT REF FROM BBS WHERE SEQ = ?)
			, (SELECT STEP FROM BBS WHERE SEQ = ?) +1
			, (SELECT DEPTH FROM BBS WHERE SEQ = ?) +1
			, ?
			, ?
			, NOW()
			, 0
			, 0
			);
			
select * from bbs;