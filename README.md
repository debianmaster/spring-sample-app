# spring-sample-app


Notes:   

```sh
oc import-image --from=registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift s2i-java --confirm
oc new-app s2i-java~https://github.com/debianmaster/spring-sample-app --name=s2i-java
```
