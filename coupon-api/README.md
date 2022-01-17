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
