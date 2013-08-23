SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
LICENSE = "CLOSED"
PR = "r0"
#RDEPENDS_${PN} = "kernel-module-dspbridge kernel-module-bridgedriver"

#PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
#FILES_${PN} = "${libdir}/lib*.so.* ${libdir}/lib*.so"
#FILES_${PN}-dev = "${includedir} ${libdir}/lib*.a"

TAG="v${PV}"
SRC_URI = "git://dev.omapzoom.org/pub/scm/tidspbridge/userspace-dspbridge.git;protocol=git;tag=${TAG} \
		   file://fixbuild.patch \
		   file://libbridge.pc"

S = "${WORKDIR}/git"
TARGET_CC_ARCH += "${LDFLAGS}"

addtask customprep after do_patch before do_compile
do_customprep() {
	rm -rf ${S}/source/target/
	mkdir -p ${S}/source/target/lib
	cp -x ${TMPDIR}/sysroots/${MACHINE}/lib/libpthread* ${S}/source/target/lib/
	ln -s ${S}/source/target/lib/libpthread.so.0 ${S}/source/target/lib/libpthread.so
}

do_compile_prepend() {
	install -m 0644 ${WORKDIR}/libbridge.pc ${S}
}

do_compile() {
	unset CFLAGS
	oe_runmake -C source .api 'DEPOT=/tmp' 'MAKE=make -e'
}

do_install() {
	oe_libinstall -so -C ${S}/source/target/lib libbridge ${D}${libdir}
	oe_libinstall -so -a -C ${S}/source/target/lib libqos ${D}${libdir}
	install -d ${D}${includedir}/dspbridge
	install -m 0644 ${S}/source/mpu_api/inc/*.h ${D}${includedir}/dspbridge/
	install -d ${D}${libdir}/pkgconfig
	install -m 0644 ${S}/libbridge.pc ${D}${libdir}/pkgconfig/
}
