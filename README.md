<p align="center">
<img src="./docs/img/logo.png" width="550" alt="Coffee Express" />
</p>

**Coffee Express** is a cloud-native microservices demo application. The application is a web-based app for a coffee shop, where users can use this application to browse menu of coffees, add them in a cart and purchase them.

**Coffee Express shows how to build & run a microservices-based application**. It shows usage of sorts of different microservices patterns. It also demonstrate use of Kubernetes and how you can deploy a microservices application in Kubernetes. Application can be deployed locally in `Minikube cluster`. It requires **little to no configuration for deploying**.

## Screenshots

Menu             |  Cart
:-------------------------:|:-------------------------:
![](./docs/img/5%20menu.png)  |  ![](./docs/img/7%20cart.png)
Checkout             |  Order Track
![](./docs/img/8%20checkout.png)  |  ![](./docs/img/9%20ordertrack.png)
Order Details             |  Orders History
![](./docs/img/10%20orderdetails.png)  |  ![](./docs/img/11%20ordershistory.png)
Bank Accounts               
![](./docs/img/12%20bankaccounts.png) 

## Building and running the microservices
Coffee Express can run either using Docker Compose or a Kubernetes (minikube cluster). In both options you need to first build all java projects using maven, then build locak docker images. You can do this by using command:

```
./build-images.bat
```

After all Docker images have been created, you can either use `docker-compose up` or if you want to run on `Kubernetes minikube cluster`, follow these steps:

1. **First you need to have a Kubernetes cluster up and running**. For this process you can use [Minikube](https://minikube.sigs.k8s.io/docs/). Begin by downloading [minikube binaries](https://minikube.sigs.k8s.io/docs/start/) depending on your OS/architecture. After downloading binaries you can use `minikube cli command` to start cluster. For a quickstart of minikube cluster, you can run minikube as a Docker container, by using this command:
```
minikube start --driver=docker
```

2. After minikube cluster is up and running you can then proceed to **deploy coffee-express microservices** in Kubernetes (`don't forget to first build docker images`). Change directory to `kubernetes/` then execute:

```
kubectl apply -f .
```

3. **Wait for the Pods to be ready.**

```
kubectl get pods
```

After a few minutes, you should see something like this:

```
NAME                                     READY   STATUS    RESTARTS   AGE
activemq-artemis-0                       1/1     Running   0          119s
api-gateway-service-75f4898958-wf8hg     0/1     Running   0          119s
barista-service-fddcf4b5d-6sp6r          1/1     Running   2          119s
customers-service-66869cbb88-hvh7b       1/1     Running   2          119s
finance-service-655c5bfc6b-97x8z         1/1     Running   2          119s
keycloak-5ff7f8c466-ghfvf                0/1     Running   1          119s
mongodb-set-0                            1/1     Running   0          119s
mysql-6c8d4bdbd9-sqtb9                   1/1     Running   0          119s
orders-history-service-f759c7dd9-7bdqt   1/1     Running   0          118s
orders-service-6fdd46b7bd-lsptp          1/1     Running   2          118s
```

## Using the web application
After all containers are up and running you can access web application's UI (which is a Vue.js app) by visiting following URL: `http://localhost:3333`. If you want to access each service swagger-ui, you can do this by visiting URL of specific service (if Kubernetes is used, you must use port-forwarding to access in-cluster services from outside cluster): `http://localhost:{SERVICE_HTTP_PORT}/swagger-ui`