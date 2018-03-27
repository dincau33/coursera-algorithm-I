# Description
Coursera Algorithm I lectures and assignments as part of a maven project.

# How to run
`mvn clean compile` compile project and run checkstyle, findbugs and PMD.

mvn exec:java -Dexec.mainClass="UF" -Dexec.args="< ./target/classes/week1-lecture/tinyUF.txt"
java -cp "/Users/dincau33/.m2/repository/com/googlecode/princeton-java-introduction/stdlib/1.0.1/stdlib-1.0.1.jar:./target/classes/" UF < ./target/classes/week1-lecture/tinyUF.txt