drop sequence adapter.user_seq;
drop sequence adapter.role_seq;
drop sequence adapter.userrole_seq;

create sequence adapter.user_seq start with 1 increment by 1 maxvalue 999999999999  nocache; 
create sequence adapter.role_seq start with 1 increment by 1 maxvalue 999999999999  nocache; 
create sequence adapter.userrole_seq start with 1 increment by 1 maxvalue 999999999999  nocache; 

drop table adapter.admin_user;
drop table adapter.admin_role;
drop table adapter.user_role;

create table adapter.admin_user
            (
              user_id number primary key,
              first_name varchar2(25) not null,
              last_name varchar2(25) not null,
              username varchar2(40) not null,
              password varchar2(75) not null,
              active_ind char(1) not null,
              new_ind char(1) not null,
              pwd_exp_dt date not null,
              created_by varchar2(25) not null,
              created_date date not null,
              updated_by varchar2(25) null,
              updated_date date null
            );
            
create table adapter.admin_role (
                    role_id number primary key,
                    role_name varchar2(30) not null,
                    role_desc varchar2(200),
                    created_by varchar2(25) not null,
                    created_date date not null,
                    updated_by varchar2(25) null,
                    updated_date date null                  
                    );
                    
create table adapter.user_role (
                    user_role_id number primary key,
                    user_id number not null constraint userseqno_fk references admin_user(user_id),
                    role_id number not null constraint roleseqno_fk references admin_role(role_id),
                    created_by varchar2(25) not null,
                    created_date date not null,
                    updated_by varchar2(25) null,
                    updated_date date  null
            );
 
--Role insertion            
INSERT INTO "ADAPTER"."ADMIN_ROLE" VALUES (userrole_seq.nextval, 'BYPASS_FILE_MANAGEMENT', 'Only access to By Pass File Management', 'Admin', Sysdate, null, null);
INSERT INTO "ADAPTER"."ADMIN_ROLE" VALUES (userrole_seq.nextval, 'PARTNER_MANAGEMENT', 'Only access to Partner Management', 'Admin', Sysdate, null, null);
INSERT INTO "ADAPTER"."ADMIN_ROLE" VALUES (userrole_seq.nextval, 'USER_ACCESS_MANAGEMENT', 'Only access to User Access Management', 'Admin', Sysdate, null, null);

--User insertion
INSERT INTO "ADAPTER"."ADMIN_USER" VALUES (user_seq.nextval, 'Admin', 'Admin', 'admin@ac.com', '$2a$10$cAHlIdQVTReEyU1uHEnDMedZTNYtHZFsIdxZ/GHol4oTSXcjErOrS', 'Y', 'Y', (sysdate+30), 'admin', sysdate, null, null);
insert into adapter.USER_ROLE values (userrole_seq.nextval, 1, 2, 'admin', sysdate, null, null);

COMMIT;
-- ===============================================================================================================================
DROP TABLE ADAPTER.FACILITIES_OPERATIONS;

CREATE TABLE ADAPTER.FACILITIES_OPERATIONS(
FACILITY_ID  NUMBER PRIMARY KEY,
IN_ALLOW_PATIENT_DICOVERY VARCHAR2(1),
IN_ALLOW_DOC_QUERY VARCHAR2(1),
IN_ALLOW_DOC_RETRIEVE VARCHAR2(1),
OUT_ALLOW_PATIENT_DICOVERY VARCHAR2(1),
OUT_ALLOW_DOC_QUERY VARCHAR2(1),
OUT_ALLOW_DOC_RETRIEVE VARCHAR2(1),
CONSTRAINT FK_FACILITIES_OPERATIONS FOREIGN KEY (FACILITY_ID) REFERENCES ADAPTER.FACILITIES(FACILITY_ID));


--Insert statements 
INSERT INTO ADAPTER.FACILITIES_OPERATIONS (facility_id)
SELECT facility_id FROM  ADAPTER.FACILITIES; 

---Alter table

alter table ADAPTER.FACILITIES_OPERATIONS add constraint IN_CHECK_PATIENT_DICOVERY CHECK (IN_ALLOW_PATIENT_DICOVERY IN ('Y','N'));
alter table ADAPTER.FACILITIES_OPERATIONS add constraint IN_CHECK_DOC_QUERY CHECK (IN_ALLOW_DOC_QUERY IN ('Y','N') );
alter table ADAPTER.FACILITIES_OPERATIONS add constraint IN_CHECK_DOC_RETRIEVE CHECK (IN_ALLOW_DOC_RETRIEVE IN ('Y','N'));  


alter table ADAPTER.FACILITIES_OPERATIONS add constraint OUT_CHECK_PATIENT_DICOVERY CHECK (OUT_ALLOW_PATIENT_DICOVERY IN ('Y','N'));
alter table ADAPTER.FACILITIES_OPERATIONS add constraint OUT_CHECK_DOC_QUERY CHECK (OUT_ALLOW_DOC_QUERY IN ('Y','N') );
alter table ADAPTER.FACILITIES_OPERATIONS add constraint OUT_CHECK_DOC_RETRIEVE CHECK (OUT_ALLOW_DOC_RETRIEVE IN ('Y','N'));  

--Update table

update ADAPTER.FACILITIES_OPERATIONS set in_allow_doc_query = 'Y', in_allow_doc_retrieve = 'Y', in_allow_patient_dicovery ='Y',
out_allow_doc_query = 'Y',out_allow_doc_retrieve ='Y',out_allow_patient_dicovery = 'Y';

-- ALLOWED_ORG - This is just a temporary table, once we get VAP access we do not need this table.

CREATE TABLE ADAPTER.ALLOWED_ORG (
ORG_ID number primary key,
ORG_NUMBER varchar2(255),
ORG_OID varchar2(255),
ORG_NAME varchar2(255),
ORG_DOMAIN varchar2(255),
ORG_COMMUNITY_ID_PREFIX varchar2(255),
ORG_CONTACT varchar2(255),
ORG_PHONE_NUMBER varchar2(255),
ACTIVE varchar2(1),
ORG_CONSUMER_ONLY varchar2(1)
);
-- =====================================FROM SHEETAL =====================================================
CREATE TABLE "ADAPTER.FILE_UPDATE_INFO"
  (
    "ID"                    NUMBER PRIMARY KEY,
    "FILE_NAME"             VARCHAR2(200 BYTE),
    "FILE_TYPE"             VARCHAR2(40 BYTE),
    "LAST_FILE_UPDATE_TIME" TIMESTAMP,
    "ADDED_BY"              VARCHAR2(40 BYTE),
    "ADDED_DT"              TIMESTAMP DEFAULT systimestamp,
    "UPDATED_BY"            VARCHAR2(40 BYTE),
    "UPDATED_DT"            TIMESTAMP DEFAULT systimestamp,
    "SERVER"                VARCHAR2(50 BYTE)
  ) ;

CREATE SEQUENCE FILE_UPDATE_INFO_SEQ MINVALUE 1 MAXVALUE 999999999999999999999999999 START WITH 1 INCREMENT BY 1;
--  facilities
CREATE TABLE "ADAPTER"."FACILITIES" 
   (     "FACILITY_ID" NUMBER, 
          "FACILITY_NUMBER" VARCHAR2(10 BYTE) CONSTRAINT "FACILITIES_NN1" NOT NULL ENABLE, 
          "HOME_COMMUNITY_ID" VARCHAR2(255 BYTE) CONSTRAINT "FACILITIES_NN2" NOT NULL ENABLE, 
          "FACILITY_NAME" VARCHAR2(255 BYTE) CONSTRAINT "FACILITIES_NN3" NOT NULL ENABLE, 
          "FULL_HOME_COMMUNITY_ID" VARCHAR2(255 BYTE), 
          "ACP_CHECK" CHAR(1 BYTE), 
          "USE_SPEC_VERSION" VARCHAR2(5 BYTE), 
          "DOC_SPEC_ID" NUMBER, 
          "PREF_SPEC" VARCHAR2(1 BYTE), 
           CONSTRAINT "FACILITIES_PK" PRIMARY KEY ("FACILITY_ID")
  
           CONSTRAINT "FACILITIES_DOC_SPEC_TYPES_FK1" FOREIGN KEY ("DOC_SPEC_ID")
            REFERENCES "ADAPTER"."DOC_SPEC_TYPES" ("DOC_SPEC_ID") ENABLE
   )


            
