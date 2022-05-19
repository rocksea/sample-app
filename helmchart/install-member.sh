export IMAGE_REPO_URL='320811633077.dkr.ecr.ap-northeast-2.amazonaws.com'
export IMAGE_REPO_NAME="sample-member-api"
export IMAGE_TAG='8dca5da'
export BRANCH_NAME='main'
export DOMAIN_NAME="rocksea.achromaticscenery.com"
export HOST_NAME="member.dev.${DOMAIN_NAME}"
export SERVICE_NAME='member-api'
export ENV='dev'
export HELM_EXPERIMENTAL_OCI=1 

# Packaging Chart
cat member-api/values.template.yaml | envsubst > member-api/values.yaml
helm package member-api --version 0.0.1-${BRANCH_NAME}

# Login to ECR
aws ecr get-login-password --region ap-northeast-2 | helm registry login --username AWS --password-stdin $IMAGE_REPO_URL

# Push to ECR
helm push member-api-0.0.1-${BRANCH_NAME}.tgz oci://$IMAGE_REPO_URL

#helm uninstall member-api -ndev
envsubst < member-api/values.yaml | helm install member-api ./member-api -n dev --values -
