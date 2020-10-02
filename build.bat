call docker-compose kill
call mvn clean package -DskipTests -f orders/pom.xml
call mvn clean package -DskipTests -f customers/pom.xml
call mvn clean package -DskipTests -f finance/pom.xml
call mvn clean package -DskipTests -f barista/pom.xml
call mvn clean package -DskipTests -f orders-history/pom.xml
call docker-compose up --build