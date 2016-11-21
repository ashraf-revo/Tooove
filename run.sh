#!/usr/bin/env bash
cf create-service cleardb spark Mysql
export JAVA_HOME=~/java8
mvn clean install -DskipTests=true
cf push "${CF_APP}"