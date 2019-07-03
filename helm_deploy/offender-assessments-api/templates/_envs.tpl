{{/* vim: set filetype=mustache: */}}
{{/*
Environment variables for web and worker containers
*/}}
{{- define "deployment.envs" }}
env:
  - name: SERVER_PORT
    value: "{{ .Values.image.port }}"

  - name: SPRING_PROFILES_ACTIVE
    value: "oracle"

  - name: JAVA_OPTS
    value: "{{ .Values.env.JAVA_OPTS }}"

  - name: SPRING_DATASOURCE_USERNAME
    valueFrom:
      secretKeyRef:
        name: offender-assessments-api
        key: database_username

  - name: SPRING_DATASOURCE_PASSWORD
    valueFrom:
      secretKeyRef:
        name: offender-assessments-api
        key: database_password

  - name: SPRING_DATASOURCE_URL
    valueFrom:
      secretKeyRef:
        name: offender-assessments-api
        key: database_endpoint

  - name: OAUTH_PUBLIC_KEY
    valueFrom:
      secretKeyRef:
        name: offender-assessments-api
        key: oauth_public_key



{{- end -}}
