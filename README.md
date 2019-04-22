# Sample App Api

Build `mvn clean install`

Run `./target/sample-app-api.jar`

Run in Dev mode `mvn spring-boot:run`

Api for build info: `http://localhost:8080/actuator/info`

### Building with Skaffold and minishift

Make sure that your minishift cluster is up and running with `minishift status`

Note: skaffold/helm require that you have access to the whole cluster so make sure that your account can read the required namespaces

```bash

export DOCKER_REGISTRY=$(minishift openshift registry)
eval $(minishift oc-env)
eval $(minishift docker-env)
oc login -u system:admin
skaffold -p minishift dev

```
