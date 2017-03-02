# spring-sample-app


Deploy using source Code:  

```sh
oc import-image --from=registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift s2i-java --confirm
oc new-app s2i-java~https://github.com/debianmaster/spring-sample-app --name=myjavaapp
```

Deploy using jar:
```sh
oc import-image --from=registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift s2i-java --confirm  #one time activity
oc new-build --binary=true --strategy=source --image-stream=s2i-java --name=sample-app-img
oc start-build sample-foo --from-dir=./deployments/
oc new-app sample-app-img --name=sample-app
```


more info here:     
https://developers.redhat.com/blog/2017/02/23/getting-started-with-openshift-java-s2i/
