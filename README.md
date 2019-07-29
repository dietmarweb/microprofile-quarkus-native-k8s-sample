Native microprofile java app in local k8s
=

This project illustrates how you can run a native compiled microprofile app in k8s based on docker4win/mac.

## Building and running native in docker

Optionally first check if it runs in pure docker:

`docker-compose up`

Then, open your browser to `http://localhost:8080/prices.html`, and you should see a fluctuating price.

## Activate local k8s with docker4win/mac

* enable k8s in your docker4win/mac settings
https://docs.docker.com/docker-for-windows/#kubernetes

* check what k8s context is active on your local machine. Should be docker-for-desktop but can be spoiled if you have previously installed minikube/minishift etc:
`kubectl config current-context`
 
## Building and running native in k8s

```bash
docker-compose build
docker stack deploy --compose-file docker-compose.yaml sampleapp
```

If the k8s pod for the service fails to connect to mosquitto, then restart docker4win-k8s. 

Then, open your browser to http://localhost:8080/prices.html

## Building and running/debugging the application locally in dev mode

If you want to test/debug the app locally (IDE/command line) first start the mosquito server:

`docker-compose up mosquitto`

Then start the application in dev/debug mode: 

`gradlew quarkusDev`  

## References

Local k8s with docker4win/mac:
https://docs.docker.com/docker-for-windows/kubernetes/

Optionally install the k8s dashboard:
http://collabnix.com/kubernetes-dashboard-on-docker-desktop-for-windows-2-0-0-3-in-2-minutes/

The sample project ist based on:
https://github.com/quarkusio/quarkus-quickstarts/tree/master/microprofile-messaging-mqtt

Modifications of the original quarkus quickstart-app:
* gradle
* lombok (enable annotation processing in intellij!)
* Slf4j
