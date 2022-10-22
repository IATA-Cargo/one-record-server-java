print('Begin to create ONE Record Business Partners users and databases');
one_record_business_partners = [
    {
        "name": "Shipper",
        "username": "shipper_user",
        "password": "shipper_password",
        "database": "shipper_db"
    },
    {
        "name": "Freight Forwarder",
        "username": "forwarder_user",
        "password": "forwarder_password",
        "database": "forwarder_db"
    },
    {
        "name": "Carrier/Airline",
        "username": "carrier_user",
        "password": "carrier_password",
        "database": "carrier_db"
    },
    {
        "name": "Ground Handling Agent",
        "username": "gha_user",
        "password": "gha_password",
        "database": "gha_db"
    }
]

one_record_business_partners.forEach(function (bp) {
    print(`Create user and database for ${bp["database"]}`);
    db = db.getSiblingDB(bp['database']);
    db.createUser(
        {
            user: bp['username'],
            pwd: bp['password'],
            roles: [{ role: 'readWrite', db: bp['database'] }],
        },
    );

})
print('Finished to create ONE Record Business Partners users and databases');
