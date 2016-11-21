#!/usr/bin/env bash
export JAVA_HOME=~/java8
mvn clean install -DskipTests=true
cf push "${CF_APP}"