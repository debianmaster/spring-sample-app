# spring-sample-app


How to run this example:   

```sh
oc import-image --from=registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift s2i-java --confirm
oc new-app s2i-java~https://github.com/debianmaster/spring-sample-app --name=myjavaapp
```


more info here:     
https://developers.redhat.com/blog/2017/02/23/getting-started-with-openshift-java-s2i/
