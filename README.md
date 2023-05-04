# drones-test

Local installation

You need Java 17 installed

## Clone

```sh
git clone git@github.com:SebastianGamboa/drones-test.git
```

## Project folder

```sh
cd drones-test
```

## Run

```sh
./gradlew :bootRun
```

## Access

```
http://localhost:8000
```

## Endpoints

**Create drone**

```
POST http//localhost:8000/api/drone
```

> Request Body

`
{
    "serialNumber": "222",
    "model": "Lightweight",
    "maxWeight": 500
}
`

**Get drone by id**

```
GET http//localhost:8000/api/drone/{id}
```

**Get drone by state**

```
GET http//localhost:8000/api/drone?state=IDLE
```

**Create item type**

```
POST http//localhost:8000/api/item-type
```

> Request Body

`
{ "name": "Medication" }
`

**Create item**

```
POST http//localhost:8000/api/item
```

> Request Body

`
{
    "name": "Ibuprofen",
    "weight": 10,
    "code": "3356212",
    "imageUrl": "google.com",
    "itemType": 1
}
`

**Create shipping**

```
POST http//localhost:8000/api/shipping
```

> Request Body

`
{
    "droneId": 1,
    "itemIds": [1, 2, 3]
}
`
