DESCRIPTION = "A console-only image for beagleboard."

IMAGE_FEATURES += "splash tools-testapps"

IMAGE_INSTALL += "\
    packagegroup-core-boot \
    packagegroup-core-basic \
    gstreamer \
    gst-plugins-base-apps \
    gst-plugins-base-meta \
    gst-plugins-good \
    gst-plugins-good-meta \
    gst-plugins-bad \
    gst-plugins-bad-meta \
    gst-plugins-ugly \
    gst-plugins-ugly-meta \
    gst-ffmpeg gst-fluendo-mp3 gst-fluendo-mpegdemux \
    alsa-lib alsa-tools alsa-utils \
    dsp-files dsp-tools \
    gst-dsp \
    libbridge \
    "

inherit core-image
