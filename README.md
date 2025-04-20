# Java Interners
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Simple java interners library<br>
Allows to add `intern()` method (similarly to `String.intern()`) to your immutable classes with customizable interners<br>
#### There is some standard interner implementations:<br>
`MapInterner` - interner based on java.util.Map (java.util.HashMap in particular)<br>
`ConcurrentMapInterner` - same as MapInterner but based on ConcurrentMap<br>
`ProbInterner` - interner that accepts chance to deduplicate value and parent interner<br>
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
		return new MyImmutableClass(x, a).intern(); // returns canonical instance with x and a values
	}
	
	public static void dropInterner() {
		interner.clear(); // clear table of MyImmutableClass
	}
}
```


## Installation

### Maven

```xml
<dependency>
    <groupId>com.ydo4ki</groupId>
    <artifactId>Interners</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.ydo4ki:Interners:1.0.0'
```

