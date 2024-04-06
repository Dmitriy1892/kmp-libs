#!/bin/scripts
# run script with parameter, for example: "sh increment_library_version.sh kmp-libs-mvvm"
# where "kmp-libs-mvvm" - is a key of version that need to update.
# script will find version by key and will increment last "patch" segment to +1

# 0. Declare a path to version catalog
version_catalog=../gradle/libs.versions.toml

# 1. Find version line by passed as argument $1 string key in "libs.versions.toml" file
# Found line will looks like "<passed-key> = <major_version>.<minor_version>.<patch_version>"
version_string="$(grep "$1 =" $version_catalog | head -1)"
echo "version_string=$version_string"

if [ "$version_string" == "" ]; then
   echo "===> BASH: Key \"$1\" was not found in \"$version_catalog\", version was not incremented."
   exit
fi

# 2. Get the "major_version" part of the found library version
major_version=$(cut -d. -f1 <<< $version_string | cut -f2 -d"\"")
echo "major_version=$major_version"

# 2. Get the "minor_version" part of the found library version
minor_version=$(cut -d. -f2 <<< $version_string | cut -f2 -d"\"")
echo "minor_version=$minor_version"

# 3. Get the "patch_version" part of the found library version
patch_version=$(cut -d. -f3 <<< $version_string | cut -f1 -d"\"")
echo "patch_version=$patch_version"

# 4. Update "patch_version" part - increment to 1
updated_patch_version=$((patch_version + 1))
echo "updated_patch_version=$updated_patch_version"

# 5. Build updated version line with key and value
updated_string=$(egrep -o '^[^"]+' <<< $version_string)\"$major_version.$minor_version.$updated_patch_version\"
echo "updated_string: $updated_string"

# 6. Overwrite found in 1st step "version_string" to updated "updated_string" in file "libs.versions.toml"
sed -i '' "s/$version_string/$updated_string/" $version_catalog