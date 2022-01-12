#!/bin/bash
mvn -f ./document-service clean package -Dquarkus.container-image.build=true
mvn -f ./graph-service clean package -Dquarkus.container-image.build=true
