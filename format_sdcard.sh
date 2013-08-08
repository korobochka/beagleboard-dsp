#! /bin/bash

source conffile

if [[ $EUID -ne 0 ]];
then
	echo "Only root can use this script, try this:"
	echo "	sudo $0"
	exit 1;
fi

umount "$SDCARD_BOOT_DIR"
umount "$SDCARD_ROOTFS_DIR"

DRIVE="$SDCARD_DRIVE"
PATH="$PATH":/usr/local/sbin:/usr/sbin:/sbin

export LC_ALL=C

dd if=/dev/zero of=$DRIVE bs=1024 count=1024

SIZE=`fdisk -l $DRIVE | grep Disk | grep bytes | awk '{print $5}'`
#echo DISK SIZE - $SIZE bytes
CYLINDERS=`echo $SIZE/255/63/512 | bc`
#echo CYLINDERS - $CYLINDERS

{
echo ,9,0x0C,*
echo ,,,-
} | sfdisk -D -H 255 -S 63 -C $CYLINDERS $DRIVE

sleep 1

PARTITION1=${DRIVE}1
if [ ! -b ${PARTITION1} ]; then
	PARTITION1=${DRIVE}p1
fi

DRIVE_NAME=`basename $DRIVE`
DEV_DIR=`dirname $DRIVE`

if [ ! -b ${PARTITION1} ]; then
	PARTITION1=$DEV_DIR/mapper/${DRIVE_NAME}p1
fi

# now make partitions.
if [ -b ${PARTITION1} ]; then
	umount ${PARTITION1}
	mkfs.vfat -F 32 -n "boot" ${PARTITION1}
else
	echo "Cant find boot partition in /dev"
fi

echo "DONE";
