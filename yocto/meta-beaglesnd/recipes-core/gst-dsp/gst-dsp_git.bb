SECTION = "multimedia"
PRIORITY = "optional"
DESCRIPTION = "GStreamer plug-ins for TI's DSP algorithms."
AUTHOR = "Felipe Contreras <felipe.contreras@nokia.com>"
#LICENSE = "LGPL"
LICENSE = "CLOSED"
DEPENDS = "gstreamer"
RDEPENDS_${PN} = "kernel-module-bridgedriver"

PR = "r2"
PV = "0.7.1+gitr${SRCREV}"

SRC_URI = "git://github.com/felipec/gst-dsp.git;protocol=git;branch=master"

#SRCREV = "1634ad3"
SRCREV = "1634ad307c7c3f93536829f59442fd07c582303f"
S = "${WORKDIR}/git"

PACKAGES = "${PN} ${PN}-dbg"
FILES_${PN} += "${libdir}/gstreamer-0.10/libgstdsp.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"

do_compile() {
	oe_runmake V=1
}

do_install() {
	install -d ${D}/${libdir}/gstreamer-0.10
	oe_libinstall -so libgstdsp ${D}/${libdir}/gstreamer-0.10
}
