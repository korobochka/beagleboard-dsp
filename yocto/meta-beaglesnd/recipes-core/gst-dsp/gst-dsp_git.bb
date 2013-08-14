SECTION = "multimedia"
PRIORITY = "optional"
DESCRIPTION = "GStreamer plug-ins for TI's DSP algorithms."
AUTHOR = "Felipe Contreras <felipe.contreras@nokia.com>"
#LICENSE = "LGPL"
LICENSE = "CLOSED"
DEPENDS = "gstreamer"
#RDEPENDS_${PN} = "kernel-module-bridgedriver"

PR = "r3"
PV = "0.10.2+gitr${SRCREV}"

SRC_URI = "git://github.com/felipec/gst-dsp.git;protocol=git;branch=master"

#SRCREV = "1634ad3"
#SRCREV = "1634ad307c7c3f93536829f59442fd07c582303f"
SRCREV = "f2e7b9470918c6b764ee849134660985b8345c3d"
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
