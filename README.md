# db-lift

A Clojure library designed to load data to some data store like a DBMS

## User Story

As an app developer or tester, I want to easily load initial or test data into my database so that I can avoid writing lots of SQL scripts and concentrate on preparing the data.

## Usage

java -jar db-lift.jar -i <data-file>

## Data Format

Initially, only EDN data formats are supported. In future, Excel spreadsheet and CSV formats may be supported.

``` edn
    {
    :tables [
        {
        :name "tbl_user"
        :columns ["id" "name" "address" "phone"]
        :pk-seq "seq_user"
        :data [
            [:unpriv-user "Elvis Presley" "1 Cementery Rd" "180023244"]
            [:normal-user "Elvis Presley" "1 Cementery Rd" "180023244"]
            [:support-user "Elvis Presley" "1 Cementery Rd" "180023244"]
            [:admin-user "Elvis Presley" "1 Cementery Rd" "180023244"]
            ]}
        {
        :name "tbl_role"
        :columns ["id" "name"]
        :pk-seq "seq_role"
        :data [
            [:nopriv-role "Unprivileged Role"]
            [:basic-role "Basic Privileges Role"]
            [:admin-role "Admin Role"]
            [:support-role "Support Role"]
            ]}
        {
        :name "tbl_user_role"
        :columns ["user_id" "role_id"]
        :data [
            [:unpriv-user :nopriv-role]
            [:normal-user :basic-role]
            [:support-user :support-tole]
            [:admin-user :admin-role]
        ]}
        {
        :name "tbl_permission"
        :columns ["id" "name" "desc"]
        :pk-seq "seq_permission"
        :unique ["name"]
        :data [
            [:create-user "create_user" "create a user"]
            [:delete-user "delete_user" "delete a user"]
            [:update-user "update_user" "update a user"]
            [:search-user "search_user" "search a user"]
        ]}
        {
        :name "tbl_role_permission"
        :columns ["role_id" "permission_id"]
        :data [
            [:basic-role :search-user]
            [:admin-role [:create-user :delete-user :update-user]]
            [:support-role [:search-user :update-user]]
        ]}
        ]
    }
```


## License

Copyright © 2017

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
