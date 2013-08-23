beagleboard-dsp
===============

This project on github is used to store files related to my sound with DSP on beagleboard work.
Currently this is not supposed to be used by anyone and not intended for public. Sorry.

Description of files in this repo:

* conffile: all configuration for scripts is here, edit this file before runnning them
* connect_to_board.sh: start screen session to see beagleboard's console
* copy_extra.sh: deploy files from extra folder into rootfs
* copy_kernel_root.sh: deploy kernel and rootfs tarball built by poky
* copy_loader.sh: install loader and it's config on SD card
* format_sdcard.sh: self-explanatory; use before previous script
* init_build_env.sh: export variables for manual crosscompiling
* install_yocto.sh: convenience script, prepares Yocto and meta-beaglesnd layer on your PC, don't forget to edit conffile before running it
* test_config.sh: not really useful, just prints some variables
* uEnv_card.txt: u-boot config file, installed by copy_loader.sh on SD card
* uEnv_pc.txt: u-boot config file, installed by copy_kernel_root.sh, is loaded by uEnv_card.txt over network
* **extra**: scripts and samples to be used on beagleboard for testing
* **yocto**: meta-beaglensd layer is here, also config files for poky


meta-beaglesnd
==============

Yocto/poky layer for beagleboard that contains recipes for different DSP related software.
Depends on meta-ti layer.
Was tested with specific revisions of poky and meta-ti (you can find them in the bottom of conffile in this repository) on Beagleboard xM rev. C.

*image-beaglesnd* is bitbake target with everything included.

Contains recipes for:
* tidspbridge kernel module
* dsp-tools
* gst-dsp
* libbridge (libdspbridge)
* ...


To try everything out
=====================

* Connect beagleboard's network to your PC ( config files assume 192.168.0.2 is your computer and 192.168.0.3 is beagleboard )
* Connect beagleboard's serial to your PC
* Install and configure tftp, dhcp and nfs servers
* Clone this repo
* Edit conffile
* Run install_yocto.sh script
* Follow instructions it prints in the end to build image
* Inserd SD card into card reader
* Run with sudo: format_sdcard.sh, copy_loader.sh, copy_kernel_root.sh, copy_extra.sh, connect_to_board.sh
* Insert SD card into beagleboard
* Plug power into your beagleboard
* Wait till it loads, then login as root and play with multimedia
* 

```bash
git clone https://github.com/Korobochka/beagleboard-dsp.git
cd beagleboard-dsp
nano conffile # okay, okay, you can use vim
./install_yocto.sh
# here you build image
sudo ./format_sdcard.sh
sudo ./copy_loader.sh
sudo ./copy_kernel_root.sh
sudo ./copy_extra.sh
sudo ./connect_to_board.sh
# loading....
login as: root
ls # you will see samples and scripts for tests
# Ctrl+A Ctrl+D to disconnect
```
