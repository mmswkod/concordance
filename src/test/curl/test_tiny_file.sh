#!/bin/bash

curl -X POST -v -i --data-binary @tiny.txt -H "Transfer-Encoding: chunked" http://localhost:8080/concordance