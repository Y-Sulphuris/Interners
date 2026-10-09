# Java Interners
![Java](https://img.shields.io/badge/Java-1.8-ED8B00?logo=openjdk&logoColor=white)
[![Maven Central](https://img.shields.io/maven-central/v/com.ydo4ki/Interners?label=Maven%20Central)](https://central.sonatype.com/artifact/com.ydo4ki/Interners)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

An extremely lightweight Java interners library.<br>
Allows to add `intern()` method (similarly to `String.intern()`) to your immutable classes with customizable interners.<br>
#### There is some standard interner implementations:<br>
`MapInterner` - interner based on java.util.Map (`java.util.HashMap` in particular)<br>
`ConcurrentMapInterner` - same as MapInterner but based on `ConcurrentMap`<br>
`ProbInterner` - interner that accepts the chance to deduplicate a given value and the parent interner (which technically makes it just a deduplicator and not an interner, but anyway).<br>
***
## Usage

```java
import com.ydo4ki.interners.ConcurrentMapInterner;
import com.ydo4ki.interners.Internable;
import com.ydo4ki.interners.Interner;
import com.ydo4ki.interners.MapInterner;

class MyImmutableClass implements Internable<MyImmutableClass> {

    private final int x;
    private final long a;

    MyImmutableClass(int x, long a) {
        this.x = x;
        this.a = a;
    }

    private static final MapInterner<MyImmutableClass> interner = new ConcurrentMapInterner<>();

    @Override
    public Interner<MyImmutableClass> interner() {
        return interner;
    }

    public static MyImmutableClass create(int x, long a) {
        return new MyImmutableClass(x, a).intern(); // returns the canonical instance with x and a values
    }

    public static void dropInterner() {
        interner.clear(); // clear the table of MyImmutableClass
    }

    // it is important to define equals and hashcode so the interner maps knows which two instances are considered equal.
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        MyImmutableClass that = (MyImmutableClass) o;
        return x == that.x && a == that.a;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + Long.hashCode(a);
        return result;
    }
}
```


## Installation

### Maven

```xml
<dependency>
    <groupId>com.ydo4ki</groupId>
    <artifactId>Interners</artifactId>
    <version>1.0.1</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.ydo4ki:Interners:1.0.1'
```

### No build system
```
Go to the releases tab and download latest jar
```

## How to build
1. Clone this repository
```bash
git clone https://github.com/Y-Sulphuris/Interners.git
cd Interners
```
2. Run maven build:
```bash
mvn clean package
```
