#! /bin/bash

source conffile

if [[ $EUID -ne 0 ]];
then
	echo "Only root can use this script, try this:"
	echo "	sudo $0"
	exit 1;
fi

umount "$SDCARD_BOOT_DIR"
mount -t vfat "$SDCARD_DRIVE"1 "$SDCARD_BOOT_DIR"

if [ "$(ls -A $SDCARD_BOOT_DIR)" ];
then
	echo "$SDCARD_BOOT_DIR is not empty, need to reformat SD-card:
	./format_sdcard.sh"
	exit 1;
fi

cp "$YOCTO_IMAGES_DIR"/MLO "$SDCARD_BOOT_DIR"/MLO
sync
cp "$YOCTO_IMAGES_DIR"/u-boot.img "$SDCARD_BOOT_DIR"/u-boot.img
cp uEnv_card.txt "$SDCARD_BOOT_DIR"/uEnv.txt
sync
umount "$SDCARD_BOOT_DIR"

cp uEnv_pc.txt "$TFTP_DIR"/uEnv.txt

echo "DONE";
