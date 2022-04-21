#!/bin/bash
kubectl apply -f ./gateway-config.yaml
kubectl apply -f ./products-route-config.yaml
kubectl apply -f ./mapping.yaml
kubectl -n spring-cloud-gateway patch svc scg-operator -p '{"spec":{"type":"LoadBalancer"}}'
kubectl expose pod yugastore-gateway-0 --port=80 --target-port=8080 --type=LoadBalancer
echo -n Waiting for external IPs
until [ -n "$(kubectl get svc yugastore-gateway-0 -o jsonpath='{.status.loadBalancer.ingress[0].ip}')" ]; do
    sleep 2
    echo -n .
done
echo
echo Test Access to Managed Service
echo
echo curl http://$(kubectl get svc yugastore-gateway-0 -o jsonpath='{.status.loadBalancer.ingress[0].ip}')/yugastore
echo
until [ -n "$(kubectl -n spring-cloud-gateway get svc scg-operator -o jsonpath='{.status.loadBalancer.ingress[0].ip}')" ]; do
    sleep 2
done
echo "Observe openapi documentation for exposed APIs here; http://$(kubectl -n spring-cloud-gateway get svc scg-operator -o jsonpath='{.status.loadBalancer.ingress[0].ip}')/openapi"
echo
