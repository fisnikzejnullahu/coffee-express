call mvn clean package -DskipTests -f orders/pom.xml
call docker build -f orders\src\main\docker\Dockerfile.openj9 -t fisnikz/orders-service ./orders
call mvn clean package -DskipTests -f customers/pom.xml
call docker build -f customers\src\main\docker\Dockerfile.openj9 -t fisnikz/customers-service ./customers
call mvn clean package -DskipTests -f finance/pom.xml
call docker build -f finance\src\main\docker\Dockerfile.openj9 -t fisnikz/finance-service ./finance
call mvn clean package -DskipTests -f barista/pom.xml
call docker build -f barista\src\main\docker\Dockerfile.openj9 -t fisnikz/barista-service ./barista
call mvn clean package -DskipTests -f orders-history/pom.xml
call docker build -f orders-history\src\main\docker\Dockerfile.openj9 -t fisnikz/orders-history-service ./orders-history
call mvn clean package -DskipTests -f api-gateway/pom.xml
call docker build -f api-gateway\src\main\docker\Dockerfile.openj9 -t fisnikz/api-gateway-service ./api-gateway