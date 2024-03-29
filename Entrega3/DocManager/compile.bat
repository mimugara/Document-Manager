@echo off

echo ""
echo "[*]Compiling"
javac "-cp" ".;lib/json-simple-1.1.1.jar;lib/junit.jar;lib/hamcrest-core-1.3.jar" "-Xlint:unchecked" "--release" "11" "src/*.java" "src/database/*.java" "src/datalayer/*.java" "src/datalayer/databaseinterface/*.java" "src/domainlayer/*.java" "src/domainlayer/model/*.java" "src/domainlayer/datainterface/*.java" "src/domainlayer/controllers/*.java" "src/presentationlayer/*.java" "src/utils/*.java" "src/utils/exceptions/*.java" "src/utils/exceptions/document/*.java" "src/utils/exceptions/boolexpression/*.java" "src/utils/exceptions/database/*.java" "test/*.java" "test/database/*.java" "test/datalayer/*.java" "test/domainlayer/model/*.java" "test/utils/*.java" "-d" "bin"
echo ""
echo "[*]Running Tests"
java "-cp" ".;lib/json-simple-1.1.1.jar;lib/junit.jar;lib/hamcrest-core-1.3.jar;bin" "test/TestRunner"
echo ""
echo "[*]Changing Directory"
cd "bin"
echo ""
echo "[*]Creating Executable"
jar "cfvme" "DocumentManagementSystem.jar" "..\MANIFEST.MF" "presentationlayer.Main" *
move "DocumentManagementSystem.jar" "..\"

PAUSE