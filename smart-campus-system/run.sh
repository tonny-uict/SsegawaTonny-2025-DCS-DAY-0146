#!/bin/bash
# Smart Campus - Run Script
export PATH="$HOME/tools/maven/bin:$PATH"
cd "$(dirname "$0")"
echo "Building..."
mvn package -DskipTests -q && echo "Starting Spring Boot..."
java -jar target/smart-campus-system-1.0.0.jar
