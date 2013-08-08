#! /bin/bash

source conffile

if [[ $EUID -ne 0 ]];
then
	echo "Only root can use this script, try this:"
	echo "	sudo $0"
	exit 1;
fi

rm -rf "$TFTP_DIR"/*
rm -rf "$NFS_DIR"/*

cp uEnv_pc.txt "$TFTP_DIR"/uEnv.txt
cp "$YOCTO_IMAGES_DIR"/uImage "$TFTP_DIR"/uImage

cp "$YOCTO_IMAGES_DIR"/"$YOCTO_IMAGE_NAME"-beagleboard.tar.bz2 "$NFS_DIR"/rootfs.tar.bz2
cp "$YOCTO_IMAGES_DIR"/modules-beagleboard.tgz "$NFS_DIR"/modules.tgz

cd "$NFS_DIR"
tar -jxvf rootfs.tar.bz2
tar -zxvf modules.tgz

echo "DONE";
