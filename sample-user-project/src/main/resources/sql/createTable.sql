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