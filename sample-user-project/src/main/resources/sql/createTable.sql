create table M_USER (
  LOGIN_NAME character varying(255) not null
  , NAME character varying(255) not null
  , LOGIN_PASSWORD character varying(80) not null
  , LOGIN_FAILED_COUNT integer
  , ACCOUNT_LOCK_FLAG VARCHAR(1)
  , ACCOUNT_LOCK_DATE timestamp
  , PASSWORD_LAST_UPDATED_DATE timestamp
  , SALT character varying(20)
  , constraint M_USER_PKC primary key (LOGIN_NAME)
) ;

create table M_DEVICE (
  LOGIN_NAME character varying(255) not null
  , DEVICE_ID character varying(255) not null
  , AUTH_TOKEN character varying(80) not null
  , AUTH_TOKEN_PUBLISH_DATE timestamp
  , LAST_SYNC_DATE timestamp
  , PUSH_DEVICE_TOKEN character varying(80)
  , NECESSITY_AUTH_SECRET_QUESTION character varying(80)
  , constraint M_DEVICE_PKC primary key (LOGIN_NAME, DEVICE_ID)
) ;


create sequence address_book_id_seq;
create table T_ADDRESS_BOOK (
  ADDRESS_BOOK_ID integer default nextval('address_book_id_seq'::regclass) not null
  , LOGIN_NAME character varying(255) not null
  , ADDRESS_LOGIN_NAME character varying(255) not null
  , ADDRESS_NAME character varying(255) not null
  , SORT_NUM integer
  , constraint T_ADDRESS_BOOK_PKC primary key (ADDRESS_BOOK_ID)
) ;

create sequence chat_room_id_seq;
create table T_CHAT_ROOM (
  CHAT_ROOM_ID integer  default nextval('chat_room_id_seq'::regclass) not null
  , LOGIN_NAME character varying(255) not null
  , CHAT_ROOM_NAME character varying(255) not null
  , SORT_NUM integer not null
  , CHAT_ROOM_TITLE character varying(255) not null
  , DELETE_FLAG character varying(1)
  , constraint T_CHAT_ROOM_PKC primary key (CHAT_ROOM_ID)
) ;