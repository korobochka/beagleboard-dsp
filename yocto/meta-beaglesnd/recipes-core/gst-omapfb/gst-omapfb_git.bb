DESCRIPTION = "GStreamer plug-in for omapfb rendering."
#LICENSE = "GPL"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINE_ARCH}"
DEPENDS = "gst-plugins-base"

SRC_URI = "git://github.com/felipec/${PN}.git;protocol=git"
#SRCREV = "41c0ef0d67dc9b718ab3a2aeecccc53b9ba8d783"
SRCREV = "677473bcc5b67ad7377e384f99357ee2f205bb28"
PV = "1.0+${PR}+gitr${SRCREV}"

S = "${WORKDIR}/git"
EXTRA_OEMAKE += " V=1 CROSS_COMPILE=${HOST_PREFIX}"

do_install() {
	install -d ${D}/usr/lib/gstreamer-0.10
	oe_runmake V=1 DESTDIR=${D} install
}

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstomapfb.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
