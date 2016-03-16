## Realm-test project

This demo project is provided to demonstrate how to work with Realm from scala

### Requirements
- Android sdk 23 installed
- device\emu with api >= 21

### Run
Make sure you have emu\device connected and running then do
```
$ sbt run
```
Realm plugin will take care of processing every file that is realm-related.

### Instrumental tests
To run [Instrumental tests](src/androidTest/scala) simply do

```
$ sbt test
```

