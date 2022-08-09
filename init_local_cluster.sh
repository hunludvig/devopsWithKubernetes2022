#!/bin/sh
echo "Create cluster"
k3d cluster create --port 8089:80@loadbalancer --agents 2 --k3s-arg "--disable=traefik@server:0"
echo "Create flux secret"
kubectl create namespace flux-system
kubectl -n flux-system create secret generic sops-age --from-file=age.agekey=tmp/local-flux-cluster-key.age
echo "Bootstrap Flux"
flux bootstrap github --owner=hunludvig --repository=devopsWithKubernetes2022 --personal --path=clusters/local --branch=master