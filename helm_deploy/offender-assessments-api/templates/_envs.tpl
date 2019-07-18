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

  - name: TZ
    value: "{{ .Values.env.TZ }}"

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

  - name: JWT_PUBLIC_KEY
    value: "{{ .Values.env.JWT_PUBLIC_KEY }}"



{{- end -}}
