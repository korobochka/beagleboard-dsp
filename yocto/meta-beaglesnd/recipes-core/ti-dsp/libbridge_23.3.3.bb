SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
HOMEPAGE = "http://omappedia.org/wiki/DSPBridge_Project"
#LICENSE = "LGPL"
LICENSE = "CLOSED"
PR = "r5"

#PACKAGES += "${PN}-samples ${PN}-samples-dbg "
#FILES_${PN} = "${libdir}/lib*.so.* "
#FILES_${PN}-dev += "${includedir} ${libdir}/lib*.so ${libdir}/pkgconfig/*.pc "
#FILES_${PN}-samples-dbg += "/dspbridge/.debug/* "
#FILES_${PN}-samples += "/dspbridge/* "

TAG="${PV}-vjaquez2"
SRC_URI = "git://gitorious.org/vjaquez-beagleboard/libbridge.git;protocol=git;tag=${TAG} \
	file://nosamples.patch \
	file://libbridge.pc"

S = "${WORKDIR}/git"

LEAD_SONAME = "libbridge.so.2"

do_compile_prepend() {
	install -m 0644 ${WORKDIR}/libbridge.pc ${S}
}

do_install() {
	oe_libinstall -so -C libbridge libbridge ${D}${libdir}
	oe_libinstall -so -C libqos libqos ${D}${libdir}
	install -d ${D}${includedir}/dspbridge
	install -m 0644 ${S}/include/*.h ${D}${includedir}/dspbridge/
	install -d ${D}${libdir}/pkgconfig
	install -m 0644 ${S}/libbridge.pc ${D}${libdir}/pkgconfig/

	oe_runmake DESTDIR=${D} install
}
