#!/usr/bin/env sh
AGENT_VERSION=0.5.0
AGENT_URL=https://artefacts.tax.service.gov.uk/artifactory/hmrc-releases/uk/gov/hmrc/jvm-bobby/${AGENT_VERSION}/jvm-bobby-${AGENT_VERSION}.jar
curl --location --noproxy "discoverd" --retry 5 --fail "${AGENT_URL}" -o "agent.jar"
export JAVA_OPTS="$JAVA_OPTS -javaagent:${PWD}/agent.jar"
SCRIPT=$(find . -type f -name platops-example-frontend-microservice)
exec $SCRIPT $HMRC_CONFIG -Dconfig.file=conf/platops-example-frontend-microservice.conf
