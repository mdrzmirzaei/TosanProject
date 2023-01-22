--create user cb identified by corebanking;
--grant all privileges to cb;
--commit;

-- Create table CUSTOMER
create table CUSTOMER
(
  customer_id      NVARCHAR2(10) not null,
  customer_name    NVARCHAR2(20),
  customer_family  NVARCHAR2(25),
  customer_address NVARCHAR2(150)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column CUSTOMER.customer_id
  is 'این فیلد باید به عنوان شماره مشتری از کد ملی استفاده کند( لازم است پر شود )';
comment on column CUSTOMER.customer_name
  is 'نام مشتری (لازم است پر شود )';
comment on column CUSTOMER.customer_family
  is 'نام خانوادگی مشتری (لازم است پر شود )';
comment on column CUSTOMER.customer_address
  is 'آدرس مشتری ( الزامی به پر کردن آن نیست)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CUSTOMER
  add constraint CUSTOMER_ID primary key (CUSTOMER_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table USERS
create table USERS
(
  user_id       NUMBER(10) not null,
  user_name     NVARCHAR2(15),
  user_password NVARCHAR2(15),
  person_name   NVARCHAR2(20),
  person_family NVARCHAR2(25),
  kind_of_user  CHAR(1) not null
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column USERS.user_id
  is 'شماره پرسنلی ( باید پر شود )';
comment on column USERS.user_name
  is 'نام کاربری ( باید پر شود )';
comment on column USERS.user_password
  is 'پسورد ( باید پر شود )';
comment on column USERS.person_name
  is 'نام پرسنل';
comment on column USERS.person_family
  is 'نام خانوادگی پرسنل';
comment on column USERS.kind_of_user
  is 'نوع کاربر (  باید با M برای مدیران و یا E برای کارمندان  پر شود )';


-- Create table ACCOUNT
create table ACCOUNT
(
  account_id       NUMBER(20) not null,
  account_balance  LONG,
  kind_of_currency CHAR(1),
  customer_id      NVARCHAR2(10) not null
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column ACCOUNT.account_balance
  is 'موجودی حساب ( نباید کمتر از 50000 تومان باشد و خالی باشد )';
comment on column ACCOUNT.kind_of_currency
  is 'نوع ارز ( نباید خالی باشد و باید کاراکترهای $ ویا R باشد )';
comment on column ACCOUNT.customer_id
  is 'کلید خارجی جدول مشتری';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ACCOUNT
  add constraint ACCOUNT_ID primary key (ACCOUNT_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table ACCOUNT
  add constraint CUSTOMER_ID_FK_ACCOUNT foreign key (CUSTOMER_ID)
  references CUSTOMER (CUSTOMER_ID) on delete set null;


-- Create table LOAN
create table LOAN
(
  loan_id               NUMBER(20) not null,
  loan_principle_amount NUMBER,
  loan_interest         NUMBER,
  loan_sum_pi_amount    LONG,
  loan_status           CHAR(1),
  customer_id           NVARCHAR2(10) not null,
  account_id            NUMBER(20) not null
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column LOAN.loan_principle_amount
  is 'مبلغ اصلی قسط';
comment on column LOAN.loan_interest
  is 'سود قسط';
comment on column LOAN.loan_sum_pi_amount
  is 'مجموع قسط پرداختی ( مبلغ اصلی + سود قسط )';
comment on column LOAN.loan_status
  is 'با حروف N , P باید پرشود به معنای پرداخت شده و پرداخت نشده';
comment on column LOAN.customer_id
  is 'کلید خارجی مشتری';
comment on column LOAN.account_id
  is 'کلید خارجی حساب';
-- Create/Recreate primary, unique and foreign key constraints 
alter table LOAN
  add constraint LOAN_ID primary key (LOAN_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table LOAN
  add constraint ACCOUNT_ID_FK_LOAN foreign key (ACCOUNT_ID)
  references ACCOUNT (ACCOUNT_ID) on delete set null;
alter table LOAN
  add constraint CUSTOMER_ID_FK_LOAN foreign key (CUSTOMER_ID)
  references CUSTOMER (CUSTOMER_ID) on delete set null;
 
-- Create table
create table FINANCIAL_RESOURCES
(
  financial_resources_id NUMBER(20) not null,
  financial_amount       LONG not null
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column FINANCIAL_RESOURCES.financial_amount
  is 'مبلغ منابع سیستم مالی بانک';
-- Create/Recreate primary, unique and foreign key constraints 
alter table FINANCIAL_RESOURCES
  add constraint FINANCIAL_RESOURCES primary key (FINANCIAL_RESOURCES_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

-- Create table
create table TRANSACTIONS
(
  transaction_id          NUMBER(20) not null,
  transaction_date        DATE,
  transaction_time        TIMESTAMP(6),
  transaction_amount      NUMBER,
  transaction_status      CHAR(1),
  customer_id             NVARCHAR2(10) not null,
  account_id              NUMBER(20) not null,
  transaction_origin      NVARCHAR2(15),
  transaction_destination NVARCHAR2(15)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table TRANSACTIONS
  add constraint TRANSACTION_ID primary key (TRANSACTION_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TRANSACTIONS
  add constraint ACCOUNT_ID_FK_TRANSACTION foreign key (ACCOUNT_ID)
  references ACCOUNT (ACCOUNT_ID);
alter table TRANSACTIONS
  add constraint CUSTOMER_ID_FK_TRANSACTION foreign key (CUSTOMER_ID)
  references CUSTOMER (CUSTOMER_ID);


SELECT * from user_tables;
