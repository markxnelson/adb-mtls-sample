#!/bin/bash
# Copyright 2022, Oracle and/or its affiliates

# Add the Oracle Wallet files to the CLASSPATH
CLASSPATH=$HOME/.m2/repository/com/oracle/database/security/oraclepki/19.3.0.0/oraclepki-19.3.0.0.jar
CLASSPATH=$CLASSPATH:$HOME/.m2/repository/com/oracle/database/security/osdt_core/19.3.0.0/osdt_core-19.3.0.0.jar
CLASSPATH=$CLASSPATH:$HOME/.m2/repository/com/oracle/database/security/osdt_cert/19.3.0.0/osdt_cert-19.3.0.0.jar

# Set the location of the wallet you downloaded from the OCI Console
USER_DEFINED_WALLET=$HOME/blog

# Get the database password
read -s -r -p "Please enter the database username: " user
echo
read -s -r -p "Please enter the password for the database user: " db_pwd
echo
read -s -r -p "Please enter the password for the wallet: " wallet_pwd
echo
umask 177 
USER="$user"
DB_PASSWORD="$db_pwd"
WALLET_PASSWORD="$wallet_pwd"
umask 22

# Add User Credentials to the ATP Wallet
java \
    -Doracle.pki.debug=true \
    -classpath ${CLASSPATH} \
    oracle.security.pki.OracleSecretStoreTextUI \
    -nologo \
    -wrl "$USER_DEFINED_WALLET" \
    -createCredential "myquickstart_high" \
    $USER >/dev/null <<EOF
$DB_PASSWORD
$DB_PASSWORD
$WALLET_PASSWORD
EOF
