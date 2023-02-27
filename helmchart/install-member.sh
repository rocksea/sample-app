export IMAGE_REPO_URL='320811633077.dkr.ecr.ap-northeast-2.amazonaws.com'
export IMAGE_REPO_NAME="sample-member-api"
export IMAGE_TAG='blue'
export VERSION='v1.0.0'
export BRANCH_NAME='main'
export DOMAIN_NAME="rocksea.achromaticscenery.com"
export HOST_NAME="member.dev.${DOMAIN_NAME}"
export SERVICE_NAME='member-api'
export ENV='dev'
export HELM_EXPERIMENTAL_OCI=1 
export ECR_PASS=`aws ecr get-login-password --region ap-northeast-2`
export ARGOCD_PASS=`kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d && echo`
export ARGOCD_URL='localhost:8080'

# Packaging Chart
cat ${SERVICE_NAME}/values.template.yaml | envsubst > ${SERVICE_NAME}/values.yaml
helm package ${SERVICE_NAME} --version ${VERSION}

# Login to ECR
aws ecr get-login-password --region ap-northeast-2 | helm registry login --username AWS --password-stdin ${IMAGE_REPO_URL}

# Push to ECR
helm push ${SERVICE_NAME}-${VERSION}.tgz oci://${IMAGE_REPO_URL}

#helm uninstall ${SERVICE_NAME} -ndev
#envsubst < ${SERVICE_NAME}/values.yaml | helm install ${SERVICE_NAME} ./${SERVICE_NAME} -n dev --values -

# Login to Argocd
argocd login --insecure ${ARGOCD_URL} --username 'admin' --password ${ARGOCD_PASS} 

# add a repository
argocd repo add ${IMAGE_REPO_URL} --enable-oci --username AWS --type helm --password ${ECR_PASS} --name eks --upsert

# create an application
argocd app create ${SERVICE_NAME} --repo ${IMAGE_REPO_URL} --helm-chart ${SERVICE_NAME} --revision ${VERSION} --dest-namespace dev --dest-server https://kubernetes.default.svc --sync-policy auto --auto-prune --upsert

# remove manifest cache
argocd app get ${SERVICE_NAME} --hard-refresh
