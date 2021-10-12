call docker build -f orders\src\main\docker\Dockerfile.openj9 -t fisnikz/orders-service ./orders
call docker build -f customers\src\main\docker\Dockerfile.openj9 -t fisnikz/customers-service ./customers
call docker build -f finance\src\main\docker\Dockerfile.openj9 -t fisnikz/finance-service ./finance
call docker build -f barista\src\main\docker\Dockerfile.openj9 -t fisnikz/barista-service ./barista
call docker build -f orders-history\src\main\docker\Dockerfile.openj9 -t fisnikz/orders-history-service ./orders-history
call docker build -f api-gateway\src\main\docker\Dockerfile.openj9 -t fisnikz/api-gateway-service ./api-gateway