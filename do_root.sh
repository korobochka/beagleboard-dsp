#!/bin/bash

YOCTO="/home/korobochka/yocto/poky-danny-8.0"
UENV="/home/korobochka/beagleboard/uEnv2.txt"
LINUX=$1
TARGET="/srv/tftp"

if [ $# -ne 1 ]; then
	echo "Usage: $0 <linux_dir>"
	exit 1;
fi

rm -rf $TARGET/*
cp $YOCTO/build/tmp/deploy/images/core-image-minimal-beagleboard.tar.bz2 $TARGET/
cd $TARGET
tar -jxvf core-image-minimal-beagleboard.tar.bz2
cp $LINUX/arch/arm/boot/uImage $TARGET/
cd $LINUX
make ARCH=arm INSTALL_MOD_PATH=$TARGET modules_install
cp $UENV $TARGET/uEnv.txt
