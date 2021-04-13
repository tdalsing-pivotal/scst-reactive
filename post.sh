#!/bin/bash

post() {
  echo "Sending $1"
  curl -X POST -H "Content-Type: application/json" -d "$1" http://localhost:8401/
}

post '{"id":"id-1","name":"name-1","count":1,"amount":11.1,"filter":true}'
post '{"id":"id-2","name":"name-2","count":2,"amount":22.2,"filter":false}'
post '{"id":"id-3","name":"name-3","count":3,"amount":33.3,"filter":true}'
post '{"id":"id-4","name":"name-4","count":4,"amount":44.4,"filter":false}'
post '{"id":"id-5","name":"name-5","count":5,"amount":55.5,"filter":true}'
post '{"id":"id-6","name":"name-6","count":6,"amount":66.6,"filter":false}'
post '{"id":"id-7","name":"name-7","count":7,"amount":77.7,"filter":true}'
post '{"id":"id-8","name":"name-8","count":8,"amount":88.8,"filter":false}'
post '{"id":"id-9","name":"name-9","count":9,"amount":99.9,"filter":true}'
post '{"id":"id-10","name":"name-10","count":10,"amount":111.1,"filter":false}'