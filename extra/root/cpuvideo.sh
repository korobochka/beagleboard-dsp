#!/bin/sh

gst-launch filesrc location=video_test.mp4 ! qtdemux name=demux demux.video_00 ! queue ! mpeg4videoparse ! ffdec_mpeg4 ! ffmpegcolorspace ! fbdevsink demux.audio_00 ! queue ! aacparse ! faad ! alsasink &
