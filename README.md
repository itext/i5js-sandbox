iText 5 Sandbox
---------------

To build these examples:

* Either checkout the latest release tag. The jars you need are from the latest official iText release and are published on the [Maven Central][1] repository.
* Or checkout the `develop` branch. This depends on the current `HEAD` of iText. You have two options:
  * Use the [iText Artifactory server][2] for `SNAPSHOT` builds. Add this to your `pom.xml`:
  ```
  <repositories>
    <repository>
      <id>itext</id>
      <name>iText Repository - snapshots</name>
      <url>https://repo.itextsupport.com/snapshot/</url>
    </repository>
  </repositories>
  ```
  * [Clone the iText repository][3] and build _everything_ yourself.

[1]: https://search.maven.org/
[2]: https://repo.itextsupport.com/snapshot/
[3]: https://github.com/itext/itextpdf
