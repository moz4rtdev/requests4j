# requests4j
<p>This lib uses only native Java libraries to mimic Python requests.</p>

# Installation
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.moz4rtdev</groupId>
  <artifactId>requests4j</artifactId>
  <version>Tag</version> <!-- Replace with a release tag -->
</dependency>
```
> [!IMPORTANT]
> If you want the documentation in your editor consider running the command below
```bash
mvn dependency:get \
  -Dartifact=com.github.moz4rtdev:requests4j:v0.2.0:javadoc \
  -DremoteRepositories=https://jitpack.io

mvn dependency:get \
  -Dartifact=com.github.moz4rtdev:requests4j:v0.2.0:sources \
  -DremoteRepositories=https://jitpack.io
```

# BASIC USAGE
```java
import com.github.moz4rtdev.requests4j.*;
import java.io.IOException;

public class App {

    public static void main(String[] args)
            throws IOException, InterruptedException {
        System.setProperty("jdk.http.auth.proxying.disabledSchemes", "");
        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");

        String proxy = args.length > 0 ? args[0] : null;
        Response response = Request.get("https://ifconfig.me/ip")
                .proxy(proxy)
                .send();
        System.out.println("IP: " + response.body());
    }
}
```

# CONTRIBUTION
<p>Any contribution is welcome, feel free to send your PR or suggest improvements.</p>
