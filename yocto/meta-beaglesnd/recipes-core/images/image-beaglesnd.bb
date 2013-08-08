DESCRIPTION = "A console-only image for beagleboard."

IMAGE_FEATURES += "splash tools-testapps"

IMAGE_INSTALL += "\
    packagegroup-core-boot \
    packagegroup-core-basic \
    gstreamer gst-plugins-base gst-plugins-good gst-plugins-bad \
    gst-ffmpeg gst-fluendo-mp3 gst-fluendo-mpegdemux gst-openmax \
    alsa-lib alsa-tools alsa-utils \
    "

inherit core-image
