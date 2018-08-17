#!/usr/bin/env bash

IFS=',' read -r -a PORT_MAPPINGS_ARRAY <<< $PORT_MAPPINGS

for PORT_MAPPING in "${PORT_MAPPINGS_ARRAY[@]}"; do
  IFS='>' read -r -a PORT <<< $PORT_MAPPING
  echo "Mapping localhost:${PORT[0]} to $TARGET_IP:${PORT[1]}"
  socat TCP-LISTEN:${PORT[0]},fork TCP:$TARGET_IP:${PORT[1]},forever,interval=1 &
done

/opt/bin/entry_point.sh