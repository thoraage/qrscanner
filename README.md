# What

Scans QR-code from image on clipboard. This is done to circumvent overly restrictive environments that stops the
developer from copying text of internet. See
http://stackoverflow.com/questions/8923995/citrix-and-disabled-copy-paste/13043982#13043982 for a use case.

## Building and running

$ mvn clean assembly:assembly
...
$ java -jar target/qrscanner-1-jar-with-dependencies.jar
