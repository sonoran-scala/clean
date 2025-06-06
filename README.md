Let's build a hit counter using a [functional domain model](https://pragprog.com/titles/swdddf/domain-modeling-made-functional/) based on the [Tagless-Final](https://blog.rockthejvm.com/tagless-final/) style and [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

This project is based on the post [F[unctional] Core, IO[mperative] Shell](https://earldouglas.com/clean.html).

## Usage

### Running the tests

```
$ sbt test
drivers.mem.MemSuite:
  + example test that succeeds 0.07s
drivers.db.DbSuite:
  + example test that succeeds 0.06s
[info] Passed: Total 2, Failed 0, Errors 0, Passed 2
```

### Running the HTTP server

```
$ sbt l4_runners/run
[info] running (fork) http.Main 
```

```
$ curl localhost:8080/count
1
$ curl localhost:8080/count
2
$ curl localhost:8080/count
3
```

## Layers

The domain is the lowest layer. Each additional layer may access any of the layers beneath it.

```
.-------------------------------------------.
| LAYER 4: RUNNERS                        o |
|-----------------------------------------|-|
| * Main method with an http server       | |
'-----------------------------------------|-'
                                          |
.-----------------------------------------|-.
| LAYER 3: DRIVERS                     o  v |
|--------------------------------------|--|-|
| * In-memory data store               |  | |
| * RDBMS/JDBC data store              |  | |
'--------------------------------------|--|-'
                                       |  |
impure                                 |  |
=======================================|==|==
pure                                   |  |
                                       |  |
.--------------------------------------|--|-.
| LAYER 2: USE CASES                o  v  v |
|-----------------------------------|--|--|-|
| * Increment and get the count     |  |  | |
'-----------------------------------|--|--|-'
                                    |  |  |
.-----------------------------------|--|--|-.
| LAYER 1: DOMAIN                   v  v  v |
|-------------------------------------------|
| * Entities                                |
|     * Count                               |
| * Operations                              |
|     * Increment the count                 |
|     * Get the count                       |
'-------------------------------------------'
```
