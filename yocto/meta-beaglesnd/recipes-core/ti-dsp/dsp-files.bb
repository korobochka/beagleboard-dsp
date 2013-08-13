DESCRIPTION = "Image files for TI's C64x+ DSP processor"
AUTHOR = "Ilya Korobitsyn <Ilya_Korobitsyn@mentor.com>"
LICENSE = "CLOSED"

#PR = "r1"

SRC_URI = "file://baseimage.dof \
	file://conversions.dll64P \
	file://dctn_dyn.dll64P \
	file://h264vdec_sn.dll64P \
	file://jpegdec_sn.dll64P \
	file://jpegenc_sn.dll64P \
	file://m4venc_sn.dll64P \
	file://mp4vdec_sn.dll64P \
	file://mpeg4aacdec_sn.dll64P \
	file://qosdyn_3430.dll64P \
	file://ringio.dll64P \
	file://usn.dll64P \
	file://vpp_sn.dll64P"

do_install() {
	mkdir -p ${D}/lib/dsp/
	install -m 644 ${WORKDIR}/baseimage.dof -D ${D}/lib/dsp/baseimage.dof
	install -m 644 ${WORKDIR}/conversions.dll64P -D ${D}/lib/dsp/conversions.dll64P
	install -m 644 ${WORKDIR}/dctn_dyn.dll64P -D ${D}/lib/dsp/dctn_dyn.dll64P
	install -m 644 ${WORKDIR}/h264vdec_sn.dll64P -D ${D}/lib/dsp/h264vdec_sn.dll64P
	install -m 644 ${WORKDIR}/jpegdec_sn.dll64P -D ${D}/lib/dsp/jpegdec_sn.dll64P
	install -m 644 ${WORKDIR}/jpegenc_sn.dll64P -D ${D}/lib/dsp/jpegenc_sn.dll64P
	install -m 644 ${WORKDIR}/m4venc_sn.dll64P -D ${D}/lib/dsp/m4venc_sn.dll64P
	install -m 644 ${WORKDIR}/mp4vdec_sn.dll64P -D ${D}/lib/dsp/mp4vdec_sn.dll64P
	install -m 644 ${WORKDIR}/mpeg4aacdec_sn.dll64P -D ${D}/lib/dsp/mpeg4aacdec_sn.dll64P
	install -m 644 ${WORKDIR}/qosdyn_3430.dll64P -D ${D}/lib/dsp/qosdyn_3430.dll64P
	install -m 644 ${WORKDIR}/ringio.dll64P -D ${D}/lib/dsp/ringio.dll64P
	install -m 644 ${WORKDIR}/usn.dll64P -D ${D}/lib/dsp/usn.dll64P
	install -m 644 ${WORKDIR}/vpp_sn.dll64P -D ${D}/lib/dsp/vpp_sn.dll64P
}

FILES_${PN} += "/lib/dsp/"
