#!/bin/sh

gst-launch filesrc location=hd.mp4 ! qtdemux name=demux demux.video_00 ! queue ! h264parse ! ffdec_h264 ! ffmpegcolorspace ! fbdevsink demux.audio_00 ! queue ! aacparse ! faad ! alsasink &
