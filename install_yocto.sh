#! /bin/bash

# Set this line to the location you want to place yocto
YOCTO_HOME="/home/korobochka/yocto-new/"


YOCTO_GIT="git://git.yoctoproject.org/poky"
METATI_GIT="git://git.yoctoproject.org/meta-ti"


SAVED_DIR=$(pwd)

if [ -e "$YOCTO_HOME"/poky ];
	then
		echo "Yocto seems to be there already, remove it for clean install";
		exit 1;
	fi

mkdir "$YOCTO_HOME"
cd "$YOCTO_HOME"
git clone "$YOCTO_GIT"
cd poky
git clone "$METATI_GIT"
cp -r "$SAVED_DIR"/yocto/* "$YOCTO_HOME"/poky/

echo "To build image:
	cd $YOCTO_HOME/poky
	. ./oe-init-build-env
	bitbake image-beaglesnd
";
