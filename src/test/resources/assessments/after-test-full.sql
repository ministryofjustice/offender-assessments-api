DELETE FROM OFFENDER where OFFENDER_PK = 1234;

DELETE FROM OASYS_ANSWER where OASYS_QUESTION_PK in (SELECT OASYS_QUESTION_PK from OASYS_QUESTION where OASYS_SECTION_PK in (SELECT OASYS_SECTION_PK from OASYS_SECTION where OASYS_SET_PK = 5433) );

DELETE FROM OASYS_QUESTION where OASYS_SECTION_PK in (SELECT OASYS_SECTION_PK from OASYS_SECTION where OASYS_SET_PK = 5433);

DELETE FROM OASYS_SECTION where OASYS_SET_PK = 5433;

DELETE FROM OASYS_SET where OASYS_SET_PK = 5430;
DELETE FROM OASYS_SET where OASYS_SET_PK = 5431;
DELETE FROM OASYS_SET where OASYS_SET_PK = 5432;
DELETE FROM OASYS_SET where OASYS_SET_PK = 5433;
DELETE FROM OASYS_SET where OASYS_SET_PK = 5434;

DELETE FROM OASYS_ASSESSMENT_GROUP where OASYS_ASSESSMENT_GROUP_PK = 6542;
DELETE FROM OASYS_ASSESSMENT_GROUP where OASYS_ASSESSMENT_GROUP_PK = 6543;