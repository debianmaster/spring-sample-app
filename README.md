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

oc start-build sample-app-img --from-dir=./deployments/

oc new-app sample-app-img --name=sample-app
```

Templates:
```sh
oc create -f https://gist.githubusercontent.com/tqvarnst/3ca512b01b7b7c1a1da0532939350e23/raw/3869a54c7dd960965f0e66907cdc3eba6d160cad/openjdk-s2i-imagestream.json
```


Data load
```sh
curl https://raw.githubusercontent.com/debianmaster/spring-sample-app/master/schema.sql > schema.sql
mysql -u mysql -p sampledb < schema.sql
```

more info here:     
https://developers.redhat.com/blog/2017/02/23/getting-started-with-openshift-java-s2i/
