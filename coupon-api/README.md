#  Coupon Api Application
Coupon API is a sample CRUD Rest API Application.

### Build and Run an Application
Build
```shell
./gradlew :coupon-api:build
```
Run
```shell
./gradlew :coupon-api:bootRun

OR

DB_HOST=0.0.0.0 DB_NAME=sample DB_USERNAME=sample DB_PASSWORD=sample DB_DDL_AUTO=update ./gradlew :coupon-api:bootRun
```

### Containerization
You should build dockerfile and push to ECR.
```
$ aws configure
$ aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin xxxxxxx.dkr.ecr.ap-northeast-2.amazonaws.com
$ docker build -t xxxxxxx.dkr.ecr.ap-northeast-2.amazonaws.com/sample-coupon-api:v1 -t xxxxxxx.dkr.ecr.ap-northeast-2.amazonaws.com/sample-coupon-api:latest .
$ docker push xxxxxxx.dkr.ecr.ap-northeast-2.amazonaws.com/sample-coupon-api
```

### Installing a chart
```shell
helm install coupon-api ./coupon-api -n sample

helm list -n sample
NAME      	NAMESPACE	REVISION	UPDATED                             	STATUS  	CHART           	APP VERSION
coupon-api	sample   	1       	2021-12-14 09:29:36.464709 +0900 KST	deployed	coupon-api-0.1.0	1.16.0
```

### Packaging a chart
```shell
export IMAGE_REPO_URL='XXXXXXXXXXX.dkr.ecr.ap-northeast-2.amazonaws.com'
export IMAGE_REPO_NAME="${IMAGE_REPO_URL}/coupon-api"
export IMAGE_TAG='latest'
export BRANCH_NAME='main'
export DOMAIN_NAME='helloworld.co.kr'
export HOST_NAME="${BRANCH_NAME}.${DOMAIN_NAME}"
export SERVICE_NAME='coupon-api'
export ENV='dev'
export HELM_EXPERIMENTAL_OCI=1

cd ./helmchart

# Packaging Chart
cat coupon-api/values.template.yaml | envsubst > coupon-api/values.yaml
helm package coupon-api --version 0.0.1

# Login to ECR
aws ecr get-login-password --region ap-northeast-2 | helm registry login --username AWS --password-stdin $IMAGE_REPO_URL

# Push to ECR
helm push coupon-api-0.0.1.tgz oci://$IMAGE_REPO_URL
```

### HelmChart pull & install from ECR
```
helm pull oci://xxxxxxxxx.dkr.ecr.ap-northeast-2.amazonaws.com/coupon-api --version 0.0.1
helm install coupon-api ./coupon-api-0.0.1.tgz -ndev
```

### Create an app on ArgoCd
```
argocd app create coupon-api --repo https://github.com/rocksea/sample-app.git --path helmchart/coupon-api --dest-namespace dev --dest-server https://kubernetes.default.svc --sync-policy auto
```
