# Putin
A common interface for filling containers.

## Usage
```java
Putin.the(map)
        .pair("Email", email)
        .pair("UserId", id);
        
Putin.newMap()
        .pair("Email", email)
        .pair("UserId", id)
        .getMap();
```

