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
        name: {{ template "app.name" . }}
        key: SPRING_DATASOURCE_USERNAME

  - name: SPRING_DATASOURCE_PASSWORD
    valueFrom:
      secretKeyRef:
        name: {{ template "app.name" . }}
        key: SPRING_DATASOURCE_PASSWORD

  - name: SPRING_DATASOURCE_URL
    valueFrom:
      secretKeyRef:
        name: {{ template "app.name" . }}
        key: SPRING_DATASOURCE_URL

  - name: OAUTH_PUBLIC_KEY
    valueFrom:
      secretKeyRef:
        name: {{ template "app.name" . }}
        key: OAUTH_PUBLIC_KEY



{{- end -}}
