#!/bin/bash
# load product data into YugabyteDB.
# USAGE: $0 [dbport]


# UNCOMMENT FOLLOWING LINE TO PORT FORWARD TO APPROPRIATE T-SERVER (BASED ON YOUR NS HOSTING YB)
#kubectl -n yb-demo-us-east1-b port-forward yb-tserver-0 5433:5433 &


PORT=${1:-5433}

./parse_metadata_json.py products.json
ysqlsh -p $PORT -f schema.sql
./chunker.py --port $PORT -d yugabyte -t products  cronos_products.csv
./chunker.py --port $PORT -d yugabyte -t product_inventory  cronos_product_inventory.csv
./chunker.py --port $PORT -d yugabyte -t product_rankings  cronos_product_rankings.csv
