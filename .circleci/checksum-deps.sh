CHECKSUMS_FILE="checksums.txt"
FILES=(
  "build.gradle"
  "app/build.gradle"
  "buildSrc/build.gradle.kts"
  "buildSrc/src/main/java/com/tylerkindy/github/Dependencies.kt"
)

rm -f $CHECKSUMS_FILE
touch $CHECKSUMS_FILE

for file in "${FILES[@]}"
do
  cksum $file >> $CHECKSUMS_FILE
done
