
### Example deploy command
```
helm --namespace namespace namespace offender-assessments-api --tiller-namespace offender-assessments-api upgrade offender-assessments-api ./offender-assessments-api/ --install --values=values-dev.yaml --values=example-secrets.yaml
```

### Helm init

```
helm init --tiller-namespace offender-assessments-api --uk.gov.digital.justice.service-account tiller --history-max 200
```
```
