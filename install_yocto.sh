#! /bin/bash

source conffile

SAVED_DIR=$(pwd)

if [ -e "$YOCTO_RELEASE_DIR" ];
then
	echo "Yocto seems to be there already, remove it for clean install:
	rm -rf $YOCTO_RELEASE_DIR";
	exit 1;
fi

mkdir "$YOCTO_HOME_DIR"
cd "$YOCTO_HOME_DIR"

git clone "$YOCTO_GIT"
cd "$YOCTO_RELEASE_NAME"
git reset --hard "$YOCTO_GIT_COMMIT"

git clone "$METATI_GIT"
cd meta-ti
git reset --hard "$METATI_GIT_COMMIT"
cd ..

#cp -r "$SAVED_DIR"/yocto/* "$YOCTO_RELEASE_DIR"
ln -s "$SAVED_DIR"/yocto/meta-beaglesnd "$YOCTO_RELEASE_DIR"/meta-beaglesnd

# This fixes paths in config files
YOCTO_HOME_DIR_ESCAPED=$(echo "$YOCTO_HOME_DIR" | sed 's/\//\\\//g')
sed -i "s/\/home\/korobochka\/yocto/$YOCTO_HOME_DIR_ESCAPED/g" "$YOCTO_BUILD_DIR"/conf/bblayers.conf


echo "To build image:
	cd $YOCTO_RELEASE_DIR
	. ./oe-init-build-env
	bitbake $YOCTO_IMAGE_NAME
";
