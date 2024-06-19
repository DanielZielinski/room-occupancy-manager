# Prerequisites

Java 17 
maven 3.8.7 <=

# Build
clone repository
cd to repo
mvn clean install

# Run
mvn spring-boot:run

# Execute endpoint

1. By curl
curl --location 'http://localhost:7654/api/room-occupancy' \
--header 'Content-Type: application/json' \
--data '{
"numberOfPremiumRooms": 1,
"numberOfEconomyRooms": 1
}'

2. By postman

Http Method
POST

url
http://localhost:7654/api/room-occupancy

body
{
"numberOfPremiumRooms": 1,
"numberOfEconomyRooms": 1
}


