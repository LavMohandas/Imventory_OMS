create table SFCCInventory
(
   sfcc_catentryId integer not null,
   sfcc_quantity integer(10) not null,
   sfcc_storeId integer not null,
   primary key (sfcc_catentryId)
);