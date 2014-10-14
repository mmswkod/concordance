#concordance

A simple "REST" endpoint implemented using Spray and Akka to produce a concordance for an arbitrary text document uploaded. The upload service is able to deal with large files taking advantage of cuncked uploads based on the HTTP 1.1 and Akka actors.


##Dependences

Scala 2.11.x and SBT 0.13.x


##How to clone

```bash
git clone https://github.com/mmswkod/concordance.git
cd concordance
```

##To compile
```bash
sbt compile
```

##To execute the tests
```bash
sbt test
```

##To start the service
```bash
sbt run
```

#To test uploading a file
```bash
cd concordance
sh src/test/curl/test_tiny_file.sh
or sh src/test/curl/test_shakespeare_file.sh
```

## More Info

You can use the curl tests under the ./concordance/src/test/curl to upload a file using the "Transfer-Encoding: chunked" and get the json result which contains the concordance for a specific uploaded file. Example result: 

```json
{  
    "words":[  
        {  
            "word":"a",
            "total":2,
            "sentencesNumber":[  
                1,
                1
            ]
        },
        {  
            "word":"all",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"alphabetical",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"an",
            "total":2,
            "sentencesNumber":[  
                1,
                1
            ]
        },
        {  
            "word":"appeared",
            "total":1,
            "sentencesNumber":[  
                2
            ]
        },
        {  
            "word":"arbitrary",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"bonus",
            "total":1,
            "sentencesNumber":[  
                2
            ]
        },
        {  
            "word":"concordance",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"document",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"each",
            "total":2,
            "sentencesNumber":[  
                2,
                2
            ]
        },
        {  
            "word":"english",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"frequencies",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"generate",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"given",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"i.e.",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"in",
            "total":2,
            "sentencesNumber":[  
                1,
                2
            ]
        },
        {  
            "word":"label",
            "total":1,
            "sentencesNumber":[  
                2
            ]
        },
        {  
            "word":"labeled",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"list",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"numbers",
            "total":1,
            "sentencesNumber":[  
                2
            ]
        },
        {  
            "word":"occurrence",
            "total":1,
            "sentencesNumber":[  
                2
            ]
        },
        {  
            "word":"occurrences",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"of",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"program",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"sentence",
            "total":1,
            "sentencesNumber":[  
                2
            ]
        },
        {  
            "word":"text",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"that",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"the",
            "total":1,
            "sentencesNumber":[  
                2
            ]
        },
        {  
            "word":"which",
            "total":1,
            "sentencesNumber":[  
                2
            ]
        },
        {  
            "word":"will",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"with",
            "total":2,
            "sentencesNumber":[  
                1,
                2
            ]
        },
        {  
            "word":"word",
            "total":3,
            "sentencesNumber":[  
                1,
                1,
                2
            ]
        },
        {  
            "word":"write",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        },
        {  
            "word":"written",
            "total":1,
            "sentencesNumber":[  
                1
            ]
        }
    ]
}
```



