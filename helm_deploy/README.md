
### Example deploy command
```
helm --namespace namespace namespace assessment-api-devtest --tiller-namespace offender-assessments-api upgrade assessment-api-devtest ./offender-assessments-api/ --install --values=values-dev.yaml --values=example-secrets.yaml
```

### Helm init

```
helm init --tiller-namespace assessment-api-devtest --uk.gov.digital.justice.service-account tiller --history-max 200
```
```
