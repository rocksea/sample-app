export IMAGE_REPO_URL='320811633077.dkr.ecr.ap-northeast-2.amazonaws.com'
export IMAGE_REPO_NAME="sample-coupon-api"
export IMAGE_TAG='8dca5da'
export BRANCH_NAME='main'
export DOMAIN_NAME="rocksea.achromaticscenery.com"
export HOST_NAME="coupon.dev.${DOMAIN_NAME}"
export SERVICE_NAME='coupon-api'
export ENV='dev'
export HELM_EXPERIMENTAL_OCI=1 

# Packaging Chart
cat coupon-api/values.template.yaml | envsubst > coupon-api/values.yaml
helm package coupon-api --version 0.0.1-${BRANCH_NAME}

# Login to ECR
aws ecr get-login-password --region ap-northeast-2 | helm registry login --username AWS --password-stdin $IMAGE_REPO_URL

# Push to ECR
helm push coupon-api-0.0.1-${BRANCH_NAME}.tgz oci://$IMAGE_REPO_URL

#helm uninstall coupon-api -ndev
envsubst < coupon-api/values.yaml | helm install coupon-api ./coupon-api -n dev --values -
