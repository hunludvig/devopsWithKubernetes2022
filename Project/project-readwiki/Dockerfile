FROM alpine:latest

RUN apk add --no-cache curl grep sed

ARG TODOS=http://localhost:8080/todos
ENV TODOS=${TODOS}

COPY run.sh /opt/run.sh

CMD ["sh", "/opt/run.sh"]