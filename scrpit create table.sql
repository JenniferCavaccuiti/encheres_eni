create table CATEGORY
(
    category_id int identity
        constraint PK_CATEGORY
            primary key,
    wording     varchar(30) not null
)
go

create table ITEM
(
    item_id         int identity
        constraint PK_ITEM_ID
            primary key,
    item_name       varchar(30)  not null,
    description     varchar(300) not null,
    bids_start_date datetime     not null,
    bids_end_date   datetime     not null,
    initial_price   int          not null,
    current_price   int,
    seller_id       int          not null
        constraint FK_ITEM_USER
            references [USERs] (user_id),
    street          varchar(100) not null,
    postal_code     char(5)      not null,
    city            varchar(100) not null,
    category_id     int          not null
        constraint FK_ITEM_CATEGORY
            references CATEGORY
)
go

create table BID
(
    buyer_id   int      not null
        constraint FK_BID_USER
            references [USERS] (user_id),
    item_id    int      not null
        constraint FK_ITEM_BID
            references ITEM,
    bid_date   datetime not null,
    bid_amount int      not null,
    bid_id     int identity
        constraint BID_pk
            primary key nonclustered,
    constraint PK_BID
        unique clustered (buyer_id, item_id)
)
go

create table USERS
(
    user_id       int identity
        constraint PK_USER
            primary key,
    login         varchar(30)  not null,
    lastname      varchar(30)  not null,
    firstname     varchar(30)  not null,
    email         varchar(50)  not null,
    phone_number  varchar(10)  not null,
    street        varchar(150) not null,
    postal_code   char(5)      not null,
    city          varchar(100) not null,
    password      varchar(30)  not null,
    credits       int          not null,
    administrator bit          not null
)
go
