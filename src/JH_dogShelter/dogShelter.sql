-- 회원테이블
DROP TABLE SHELTER;
CREATE TABLE SHELTER(
    SID     NVARCHAR2(10) PRIMARY KEY,
    SPW     NVARCHAR2(10),
    SNAME   NVARCHAR2(5),
    SADDR   NVARCHAR2(30),
    SPHONE  NVARCHAR2(15),
    SEMAIL  NVARCHAR2(30),
    SCHAR   NVARCHAR2(1),
    CMONNEY NVARCHAR2(20)
);
SELECT * FROM SHELTER;

-- 개테이블
DROP TABLE DOG;
CREATE TABLE DOG(
    DNO     NUMBER(38) PRIMARY KEY,
    DNAME   NVARCHAR2(10),
    DAGE    NUMBER(20),
    DBREED  NVARCHAR2(10),
    DGENDER NVARCHAR2(2),
    D_DATE  DATE
);
SELECT * FROM DOG;

-- 공고번호 자동생성을 위한 시퀀스 생성
CREATE SEQUENCE DOG_SEQUENCE
INCREMENT BY 1
START WITH 1
MAXVALUE 1000
MINVALUE 1
NOCYCLE
NOCACHE;

COMMIT;